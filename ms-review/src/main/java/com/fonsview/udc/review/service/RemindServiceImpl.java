/**
 * 
 */
package com.fonsview.udc.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fonsview.udc.review.constants.ConfigKey;
import com.fonsview.udc.review.constants.Constants;
import com.fonsview.udc.review.dao.ReminderDao;
import com.fonsview.udc.review.model.Reminder;
import com.fonsview.udc.review.utils.ConfigUtil;
import com.fonsview.udc.review.utils.DateUtils;
import com.fonsview.udc.review.utils.ThreadPoolUtil;
import com.fonsview.udc.review.utils.Utils;

/**
 * 提醒服务
 * <p>Title: RemindServiceImpl</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年1月4日
 */
@Service("reminderService")
public class RemindServiceImpl implements ReminderService{
	
	@Autowired
	private ReminderDao reminderDao;
	
	
	/**  
	 * 添加提醒  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月4日   
	 * @param reminder
	 * @return  
	 */ 
	@Override
	public Reminder addReminder(Reminder reminder) {
		reminder.setCreateTime(DateUtils.getCurrentTime());
		reminder.setUpdateTime(DateUtils.getCurrentTime());
		//判断用户提醒数量是否超标（默认用户最多可提交1个直播提醒，5个追剧提醒）
		List<Reminder> list = reminderDao.getReminder(reminder);
		String vodSize = ConfigUtil.getProperties(ConfigKey.VODREMINDERSIZE.name());
		String liveSize = ConfigUtil.getProperties(ConfigKey.LIVEREMINDERSIZE.name());
		if ((Constants.VOD.equals(reminder.getMediaType()) && list.size() >= Utils.obj2Int(vodSize))
		  ||(Constants.LIVE.equals(reminder.getMediaType()) && list.size() >= Utils.obj2Int(liveSize))){
			
			//删除最早设置的提醒
			removeEarliestRemind(reminder);
		}
		
		//添加提醒记录
		return reminderDao.addReminder(reminder);
	}


	/**  
	 * 删除提醒  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月7日   
	 * @param contentId
	 * @param userId
	 * @param mediaType  
	 */ 
	@Override
	public Integer removeReminder(String contentId, String userId, String mediaType) {
		
		return reminderDao.removeReminder(contentId,userId,mediaType);
	}


	/**  
	 * 获取用户提醒列表
	 * <p>Title: getReminderList</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月8日   
	 * @param userId
	 * @param contentId
	 * @param mediaType
	 * @return  
	 */ 
	@Override
	public List<Reminder> getReminderList(String userId, String contentId, String mediaType) {
		
		return reminderDao.getReminderList(userId, contentId, mediaType);
	}

	/**
	 * 删除最早的提醒
	 * <p>Title: removeEarliestRemind</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年2月27日   
	 * @param reminder
	 */
	private void removeEarliestRemind(Reminder reminder) {
		ThreadPoolUtil.createTask(new Runnable() {
			@Override
			public void run() {
				reminderDao.removeEarliestRemind(reminder);
			}
		});
	}
}
