/**
 * 
 */
package com.fonsview.udc.review.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.fonsview.udc.review.constants.Constants;
import com.fonsview.udc.review.model.Reminder;
import com.fonsview.udc.review.utils.StringUtils;
import com.fonsview.udc.review.utils.Utils;
import com.mongodb.client.result.DeleteResult;

/**
 * 
 * <p>Title: ReviewDaoImpl</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年1月2日
 */
@Repository("remindDao")
public class ReminderDaoImpl implements ReminderDao{
	@Resource
    private MongoTemplate mongoTemplate;

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
		Criteria criteria = new Criteria();
		criteria.and("contentId").is(reminder.getContentId());
		criteria.and("mediaType").is(reminder.getMediaType());
		criteria.and("userId").is(reminder.getUserId());
		Query query = new Query(criteria);
		
		Update update = new Update();
		update.set("reminderStrategy", reminder.getReminderStrategy());
		update.set("name", reminder.getName());
		update.set("currentEpisodes", reminder.getCurrentEpisodes());
		update.set("createTime", reminder.getCreateTime());
		update.set("updateTime", reminder.getUpdateTime());
		return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true).upsert(true), Reminder.class,Constants.MONGO_REMINDER_COL_NAME);
	}

	/**  
	 * 查询用户提醒
	 * <p>Title: getRemind</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月4日   
	 * @param reminder
	 * @return  
	 */ 
	@Override
	public List<Reminder> getReminder(Reminder reminder) {
		Criteria criteria = new Criteria();
		criteria.and("userId").is(reminder.getUserId());
		criteria.and("mediaType").is(reminder.getMediaType());

		return mongoTemplate.find(new Query(criteria), 
				Reminder.class, Constants.MONGO_REMINDER_COL_NAME);
	}

	/**  
	 * 删除最早设置的提醒
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月4日   
	 * @param reminder
	 */ 
	@SuppressWarnings("deprecation")
	@Override
	public void removeEarliestRemind(Reminder reminder) {
		Criteria criteria = new Criteria();
		criteria.and("userId").is(reminder.getUserId());
		criteria.and("mediaType").is(reminder.getMediaType());
		Query query = new Query(criteria);
		query.with(new Sort(new Order(Direction.ASC,"createTime")));
		query.limit(1);
		mongoTemplate.findAndRemove(query, Reminder.class, Constants.MONGO_REMINDER_COL_NAME);
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
		Criteria criteria = new Criteria();
		criteria.and("userId").is(userId);
		if(StringUtils.isNotEmpty(contentId)){
			criteria.and("contentId").is(contentId);
		}
		if(StringUtils.isNotEmpty(mediaType)){
			criteria.and("mediaType").is(mediaType);
		}
		Query query = new Query(criteria);
		DeleteResult deleteResult = mongoTemplate.remove(query, Reminder.class, Constants.MONGO_REMINDER_COL_NAME);
		
		return Utils.obj2Int(deleteResult.getDeletedCount());
	}

	/**  
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
		Criteria criteria = new Criteria();
		criteria.and("userId").is(userId);
		if (!Utils.objIsNull(contentId)) {
			criteria.and("contentId").is(contentId);
		}
		if (!Utils.objIsNull(mediaType)) {
			criteria.and("mediaType").is(mediaType);
		}
		
		Query query = new Query(criteria);
		return mongoTemplate.find(query, Reminder.class, Constants.MONGO_REMINDER_COL_NAME);
	}

}
