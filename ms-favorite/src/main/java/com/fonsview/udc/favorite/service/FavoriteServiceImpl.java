/**
 * 
 */
package com.fonsview.udc.favorite.service;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fonsview.udc.favorite.constans.ConfigKey;
import com.fonsview.udc.favorite.constans.Constants;
import com.fonsview.udc.favorite.dao.FavoriteDao;
import com.fonsview.udc.favorite.model.Favorite;
import com.fonsview.udc.favorite.utils.ConfigUtil;
import com.fonsview.udc.favorite.utils.DateUtils;
import com.fonsview.udc.favorite.utils.DateUtils.Pattern;
import com.fonsview.udc.favorite.utils.StringUtils;
import com.fonsview.udc.favorite.utils.ThreadPoolUtil;
import com.fonsview.udc.favorite.utils.Utils;

/**
 * @Description 
 * @author hoob
 * @date 2018年11月11日下午10:20:15
 */
@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService {

	@Resource
	private FavoriteDao favoriteDao;
	
	Boolean synchronizeFlag = "1".equals(ConfigUtil.getProperties(ConfigKey.SYNCHRONIZE.name()));
	/**
	 * @Title add
	 * @Description 
	 * @param 
	 * @return FavoriteService
	 * @throws 
	 */
	@Override
	public void add(Favorite favorite,boolean  synchronize,String groupType) {
		favorite.setCreateTime(DateUtils.getCurrentTime());
		favorite.setUpdateTime(favorite.getCreateTime());
		favorite.setUserId(favorite.getUserId());
		//根据业务处理
		favorite=favoriteDao.add(favorite);
		deleteFavoriteByFavoriteSize(favorite,synchronize,groupType);
	}
	private void deleteFavoriteByFavoriteSize(Favorite favorite,boolean synchronize, String groupType) {
		ThreadPoolUtil.createTask(new  Runnable() {
			@Override
			public void run() {
				//检查是否有数量限制，有则需要淘汰久的
				String favoroteSize=ConfigUtil.getProperties(ConfigKey.FAVORITESIZE.name());
				if(StringUtils.isNotEmpty(favoroteSize)&&!"0".equals(favoroteSize)){
					favoriteDao.deleteFavoriteByFavoriteSize(favorite.getUserId(), favoroteSize,favorite.getMediaType());
				}
			}});
	}
	/**
	 * @throws ParseException 
	 * @Title getFavoriteList
	 * @Description 
	 * @param 
	 * @return FavoriteService
	 * @throws 
	 */
	@Override
	public List<Favorite> getFavoriteList(String userId, Integer begin,
			Integer pageSize, String sp, String date,String mediatype,String groupType,boolean synchronize) throws Exception {
		List<Favorite>result=new ArrayList<Favorite>();
		//如果begin和pageSize为空，设置默认值
		begin = Utils.obj2Int(begin,Constants.BEGIN);
		pageSize= Utils.obj2Int(pageSize,Constants.PAGESIZE);
		//如果date 为空，则设置默认值为三个月前
		if (Utils.objIsNull(date)) {
			String limitmonth=ConfigUtil.getProperties(ConfigKey.LIMITMONTH.name());
			if(StringUtils.isEmpty(limitmonth)){
				limitmonth="3";
			}
			date = DateUtils.plusMonth(-(Integer.parseInt(limitmonth)), DateUtils.getCurrentTime(), Pattern.yyyy_MM_dd.getValue());
		}
	    result= favoriteDao.getFavoriteList(userId, begin, pageSize, sp,date,mediatype);
		return result;
	}

	/**
	 * @Title getFavoriteCount
	 * @Description 
	 * @param 
	 * @return FavoriteService
	 * @throws 
	 */
	@Override
	public long getFavoriteCount(String userId, String mediaType) {
		//获取用收藏时先拉取
		return favoriteDao.getFavoriteCount(userId, mediaType);
	}

	/**
	 * @Title remove
	 * @Description 
	 * @param 
	 * @return FavoriteService
	 * @throws 
	 */
	@Override
	public void remove(String userId, String contentIds, String mediaType,String groupType,boolean synchronize) {
	    favoriteDao.remove(userId, contentIds, mediaType);
	}

	/**
	 * @Title checkFavorite
	 * @Description 
	 * @param 
	 * @return FavoriteService
	 * @throws 
	 */
	@Override
	public boolean checkFavorite(String userId, String contentId,String mediatype,String groupType,boolean synchronize) {

		return favoriteDao.checkFavorite(userId, contentId,mediatype);
		
	}

}
