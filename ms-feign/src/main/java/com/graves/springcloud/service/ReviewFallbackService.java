/**
 * 
 */
package com.graves.springcloud.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**  
 * <p>Title: ReviewFallbackService</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年8月23日  
 */
@Component
public class ReviewFallbackService implements ReviewService{

	/**  
	 * <p>Title: addScore</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param userId
	 * @param body
	 * @param request
	 * @return  
	 */ 
	@Override
	public Object addScore(String userId, String body, HttpServletRequest request) {
		return String.format("addScore is error: [userId:%s--body:%s]",userId, body);
	}

	/**  
	 * <p>Title: getAvgScore</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param userId
	 * @param contentId
	 * @param request
	 * @return  
	 */ 
	@Override
	public Object getAvgScore(String userId, String contentId, HttpServletRequest request) {
		return String.format("getAvgScore is error: [userId:%s--contentId:%s]",userId, contentId);
	}

	/**  
	 * <p>Title: addRecommendation</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param userId
	 * @param body
	 * @param request
	 * @return  
	 */ 
	@Override
	public Object addRecommendation(String userId, String body, HttpServletRequest request) {
		return String.format("addRecommendation is error: [userId:%s--body:%s]",userId, body);
	}

	/**  
	 * <p>Title: countRecommendation</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param contentId
	 * @param request
	 * @return  
	 */ 
	@Override
	public Object countRecommendation(String contentId, HttpServletRequest request) {
		return String.format("getContentRecommendation is error: [contentId:%s]", contentId);
	}

	/**  
	 * <p>Title: addComment</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param body
	 * @param request
	 * @return  
	 */ 
	@Override
	public Object addComment(String body, HttpServletRequest request) {
		return String.format("addComment is error: [body:%s]", body);
	}

	/**  
	 * <p>Title: getComment</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param contentId
	 * @param commentId
	 * @param begin
	 * @param pagesize
	 * @param request
	 * @return  
	 */ 
	@Override
	public Object getComment(String contentId, String commentId, Integer begin, Integer pagesize,
			HttpServletRequest request) {
		return String.format("getContentRecommendation is error: [contentId:%s--commentId:%s--begin:%s--pagesize:%s]",
					contentId, commentId, begin, pagesize);
	}

	/**  
	 * <p>Title: addReminder</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param body
	 * @param request
	 * @return  
	 */ 
	@Override
	public Object addReminder(String body, HttpServletRequest request) {
		return String.format("addReminder is error: [body:%s]", body);
	}

	/**  
	 * <p>Title: removeReminder</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param contentId
	 * @param userId
	 * @param mediaType
	 * @param request
	 * @return  
	 */ 
	@Override
	public Object removeReminder(String contentId, String userId, String mediaType, HttpServletRequest request) {
		return String.format("removeReminder is error: [contentId:%s--userId:%s--mediaType:%s]", contentId, userId, mediaType);
	}

	/**  
	 * <p>Title: reminderList</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param userId
	 * @param contentId
	 * @param mediaType
	 * @param request
	 * @return  
	 */ 
	@Override
	public Object reminderList(String userId, String contentId, String mediaType, HttpServletRequest request) {
		return String.format("getReminderList is error: [userId:%s--contentId:%s--mediaType:%s]",
				userId, contentId, mediaType);
	}

}
