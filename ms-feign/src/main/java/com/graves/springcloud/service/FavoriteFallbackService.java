/**
 * 
 */
package com.graves.springcloud.service;

import org.springframework.stereotype.Component;

/**  
 * <p>Title: FavoriteFallbacoService</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年8月14日  
 */
@Component
public class FavoriteFallbackService implements FavoriteService{

	/**  
	 * <p>Title: addFavorite</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月14日   
	 * @param synchronize
	 * @param groupType
	 * @param body
	 * @return  
	 */ 
	@Override
	public Object addFavorite(boolean synchronize, String groupType, String body) {
		return String.format("addFavorite is error: [synchronize:%s--groupType:%s--body:%s]",synchronize, groupType, body);
	}

	/**  
	 * <p>Title: getFavoriteList</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月14日   
	 * @param userId
	 * @param begin
	 * @param pageSize
	 * @param date
	 * @param mediaType
	 * @param sp
	 * @param groupType
	 * @param synchronize
	 * @return  
	 */ 
	@Override
	public Object getFavoriteList(String userId, Integer begin, Integer pageSize, String date,
			String mediaType, String sp, String groupType, boolean synchronize) {
		return String.format("getFavoriteList is error: [userId:%s--begin:%s--pageSize:%s--date:%s--meidaType:%s--sp:%s--groupType:%s--synchronize:%s]",userId, begin, pageSize, date,mediaType, sp, groupType,synchronize);
	}

	/**  
	 * <p>Title: getFavoriteCount</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月14日   
	 * @param userId
	 * @param mediaType
	 * @return  
	 */ 
	@Override
	public Object getFavoriteCount(String userId, String mediaType) {
		return String.format("getCounts is error: [userId:%s--mediaType:%s]", userId, mediaType);
	}

	/**  
	 * <p>Title: removeFavorite</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月14日   
	 * @param userId
	 * @param contentIds
	 * @param mediaType
	 * @param groupType
	 * @param synchronize
	 * @return  
	 */ 
	@Override
	public Object removeFavorite(String userId, String contentIds, String mediaType, String groupType,
			boolean synchronize) {
		return String.format("removeFavorite is error: [userId:%s--contentIds:%s--mediaType:%s--groupType:%s--synchronize:%s]",userId,contentIds,mediaType,groupType,synchronize);
	}

	/**  
	 * <p>Title: checkFavorite</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月14日   
	 * @param userId
	 * @param contentId
	 * @param mediaType
	 * @param groupType
	 * @param synchronize
	 * @return  
	 */ 
	@Override
	public Object checkFavorite(String userId, String contentId, String mediaType, String groupType,
			boolean synchronize) {
		return String.format("checkBookmark is error: [userId:%s--contentId:%s--mediaType:%s--groupType:%s--synchronize:%s]",userId,contentId,mediaType,groupType,synchronize);
	}

}
