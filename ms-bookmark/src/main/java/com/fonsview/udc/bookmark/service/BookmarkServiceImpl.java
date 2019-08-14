/**
 * 
 */
package com.fonsview.udc.bookmark.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fonsview.udc.bookmark.constants.ConfigKey;
import com.fonsview.udc.bookmark.constants.Constants;
import com.fonsview.udc.bookmark.dao.BookmarkDao;
import com.fonsview.udc.bookmark.model.Bookmark;
import com.fonsview.udc.bookmark.utils.ConfigUtil;
import com.fonsview.udc.bookmark.utils.DateUtils;
import com.fonsview.udc.bookmark.utils.DateUtils.Pattern;
import com.fonsview.udc.bookmark.utils.StringUtils;
import com.fonsview.udc.bookmark.utils.ThreadPoolUtil;
import com.fonsview.udc.bookmark.utils.Utils;

/**
 * @Description 
 * @author hoob
 * @date 2018年11月12日下午1:35:20
 */
@Service("bookmarkService")
public class BookmarkServiceImpl implements BookmarkService{
	private Logger logger = LoggerFactory.getLogger(BookmarkServiceImpl.class);
	@Resource
	private BookmarkDao bookmarkDao;
	//@Resource
	//private MqService mqService;
	
	@Override
	public void add(Bookmark bookmark,boolean synchronize,String groupType) throws Exception {
		
		Integer time = bookmark.getTime();
		if(time < Utils.obj2Int(ConfigUtil.getProperties(ConfigKey.BOOKMARKLIMITTIME.name()), 0)){
			logger.debug("time is too short -- userId is:"+bookmark.getUserId()+"-- contentId is:"+bookmark.getContentId() );
			return;
		}
		
		bookmark.setCreateTime(DateUtils.getCurrentTime());
		bookmark.setUpdateTime(bookmark.getCreateTime());
		bookmark.setUserId(bookmark.getUserId());
		// 根据业务处理
		if("true".equals(ConfigUtil.getProperties(ConfigKey.BOOKMARKPROGRAMSINGLE.name()))){
			bookmark = bookmarkDao.addOrUpdateSingleBookmark(bookmark);
		}else{
			bookmark = bookmarkDao.add(bookmark);
		}
		
		deleteBookmarkByBookmarkSize(bookmark, synchronize, groupType);
	}

	private void deleteBookmarkByBookmarkSize(Bookmark bookmark, boolean synchronize, String groupType) {
		ThreadPoolUtil.createTask(new  Runnable() {
			@Override
			public void run() {
				String bookmarkSize=ConfigUtil.getProperties(ConfigKey.BOOKMARKSIZE.name());
				if(StringUtils.isNotEmpty(bookmarkSize)&&!"0".equals(bookmarkSize)){
					bookmarkDao.deleteBookmarkByBookmarkSize(bookmark.getUserId(), bookmarkSize,bookmark.getMediaType());
				}
			}});
	}

	@Override
	public List<Bookmark> getBookmarkList(String userId, Integer begin, Integer pageSize, String sp, String contentId, String groupType,boolean synchronize) throws Exception{
		//如果begin和pageSize为空，设置默认值
		Integer firstNum = Utils.obj2Int(begin, Constants.BEGIN);
		Integer size = Utils.obj2Int(pageSize,Constants.PAGESIZE);
		
		//不分页
		String bookmarkSize=ConfigUtil.getProperties(ConfigKey.BOOKMARKSIZE.name());
		if(StringUtils.isNotEmpty(bookmarkSize)){
			int booksize=Integer.parseInt(bookmarkSize);
			if(booksize>size){
				size=booksize;
			}
		}
		
		String limitmonth=ConfigUtil.getProperties(ConfigKey.LIMITMONTH.name());
		if(StringUtils.isEmpty(limitmonth)){
			limitmonth="3";
		}
	    String date = DateUtils.plusMonth(-(Integer.parseInt(limitmonth)), DateUtils.getCurrentTime(), Pattern.yyyy_MM_dd.getValue());
		//根据配置区分是获取合集下的最新的一条记录
		List<Bookmark> list = new ArrayList<Bookmark>();
		String bookmarkreturnsingle=ConfigKey.BOOKMARKRETURNSINGLE.name();
		if("true".equals(ConfigUtil.getProperties(bookmarkreturnsingle))){
			//分组查询
			list = bookmarkDao.getBookmarkListByGroup(userId, firstNum, size, sp, contentId, date);
		}else{
			list=bookmarkDao.getBookmarkList(userId, firstNum, size, sp, contentId, date);
		}
		
		return list;
	}
	
	@Override
	public Integer getCounts(String userId) {
		
		return bookmarkDao.getCounts(userId);
	}

	@Override
	public void removeBookmark(String userId, String pContentIds,String groupType,boolean synchronize) {
		
		String[] pcArr = pContentIds.trim().split(",");
		List<Bookmark> deleteList =new ArrayList<Bookmark>();
		//需要删除的contentId集合
		List<String> delContentIdList = new ArrayList<>();
		if (pcArr.length == 1 && "_all_".equals(pcArr[0])) {
			deleteList=bookmarkDao.removeBookmark(userId, pcArr);
		}else {
			for (String str : pcArr) {
				if (str.matches("cid_\\d+_\\d+")) {
					String[] strs = str.split("_");
					String contentId = strs[1];
					String index = strs[2];
					deleteList.addAll(bookmarkDao.removeBookmark(userId, contentId, Integer.valueOf(index)));
				}else {
					delContentIdList.add(str);
				}
			}
			deleteList.addAll(bookmarkDao.removeBookmark(userId, delContentIdList.toArray(new String[]{})));
		}
		
	}

	
	@Override
	public List<Map<String, Object>> checkBookmark(String userId, String contentId,Integer index,String groupType,boolean synchronize) {
		
		List<Map<String, Object>> resList = new ArrayList<>();
	    List<Bookmark> list = bookmarkDao.checkBookmark(userId,contentId,index);
		for (Bookmark bookmark : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("pContentId", bookmark.getpContentId());
			map.put("index", bookmark.getIndex());
			map.put("periods", bookmark.getPeriods());
			map.put("programType", bookmark.getProgramType());
			map.put("mediaType", bookmark.getMediaType());
			map.put("bookmark", bookmark.getTime());
			resList.add(map);
		}
		
		return resList;
	}
	
}
