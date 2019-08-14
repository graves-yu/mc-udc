/**
 * 
 */
package com.graves.springcloud.service;

import org.springframework.stereotype.Component;

/**  
 * <p>Title: HystrixClass</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年8月6日  
 */

@Component
public class BookmarkFallbackService implements BookmarkService{
	
	/**  
	 * 断路器返回错误信息
	 * <p>Title: addBookmark</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月13日   
	 * @param synchronize
	 * @param groupType
	 * @param body
	 * @return  
	 */ 
	@Override
	public Object addBookmark(boolean synchronize, String groupType, String body) {
		return String.format("addBookmark is error: [synchronize:%s--groupType:%s--body:%s]",synchronize, groupType, body);
	}

	/**  
	 * <p>Title: getBookmarkList</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月13日   
	 * @param userId
	 * @param begin
	 * @param pageSize
	 * @param sp
	 * @param contentId
	 * @param groupType
	 * @param synchronize
	 * @return  
	 */ 
	@Override
	public Object getBookmarkList(String userId, Integer begin, Integer pageSize, String sp, String contentId,
			String groupType, boolean synchronize) {
		return String.format("getBookmarkList is error: [userId:%s--begin:%s--pageSize:%s--sp:%s--groupType:%s--synchronize:%s]",userId, begin, pageSize, sp,groupType,synchronize);
	}

	/**  
	 * <p>Title: getCounts</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月14日   
	 * @param userId
	 * @return  
	 */ 
	@Override
	public Object getCounts(String userId) {
		return String.format("getCounts is error: [userId:%s]", userId);
	}

	/**  
	 * <p>Title: removeBookmark</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月14日   
	 * @param userId
	 * @param pContentIds
	 * @param groupType
	 * @param synchronize
	 * @return  
	 */ 
	@Override
	public Object removeBookmark(String userId, String pContentIds, String groupType, boolean synchronize) {
		return String.format("removeBookmark is error: [userId:%s--pContentIds:%s--groupType:%s--synchronize:%s]",userId,groupType,synchronize);
	}

	/**  
	 * <p>Title: checkBookmark</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月14日   
	 * @param userId
	 * @param contentId
	 * @param index
	 * @param groupType
	 * @param synchronize
	 * @return  
	 */ 
	@Override
	public Object checkBookmark(String userId, String contentId, Integer index, String groupType, boolean synchronize) {
		return String.format("checkBookmark is error: [userId:%s--contentId:%s--index:%s--groupType:%s--synchronize:%s]",userId,contentId,index,groupType,synchronize);
	}

	
}
