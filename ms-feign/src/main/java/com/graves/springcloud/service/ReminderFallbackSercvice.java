/**
 * 
 */
package com.graves.springcloud.service;

import org.springframework.stereotype.Component;

/**  
 * <p>Title: ReminderFallbackSercvice</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年8月26日  
 */
@Component
public class ReminderFallbackSercvice implements ReminderService{

	/**  
	 * <p>Title: addReminder</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月26日   
	 * @param body
	 * @return  
	 */ 
	@Override
	public Object addReminder(String body) {
		return String.format("addReminder is error: [body:%s]", body);
	}

	/**  
	 * <p>Title: removeReminder</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月26日   
	 * @param contentId
	 * @param userId
	 * @param mediaType
	 * @return  
	 */ 
	@Override
	public Object removeReminder(String contentId, String userId, String mediaType) {
		return String.format("removeReminder is error: [contentId:%s--userId:%s--mediaType:%s]",contentId, userId, mediaType);
	}

	/**  
	 * <p>Title: reminderList</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月26日   
	 * @param userId
	 * @param contentId
	 * @param mediaType
	 * @return  
	 */ 
	@Override
	public Object reminderList(String userId, String contentId, String mediaType) {
		return String.format("getReminderList is error: [userId:%s--contentId:%s--mediaType:%s]",userId, contentId, mediaType);
	}
	
}
