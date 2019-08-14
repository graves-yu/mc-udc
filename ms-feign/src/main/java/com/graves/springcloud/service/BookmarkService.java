/**
 * 
 */
package com.graves.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**  
 * <p>Title: testService</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年8月9日  
 */
@FeignClient(value = "bookmark-service", fallback = BookmarkFallbackService.class)
public interface BookmarkService {
	
	/**
	 * <p>Title: addBookmark</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月13日   
	 * @param synchronize
	 * @param groupType
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "/v2/bookmark/add",method = RequestMethod.POST, produces="application/json")
	Object addBookmark(@RequestParam(value="synchronize")boolean synchronize,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestBody String body);
	
	/**
	 * 获取书签列表
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
	@RequestMapping(value = "/v2/bookmark/list",method = RequestMethod.GET)
    Object getBookmarkList(@RequestParam("userid") String userId, 
			@RequestParam(value="begin",required=false) Integer begin, @RequestParam(value="pagesize",required=false) Integer pageSize,
			@RequestParam(value="sp",required=false) String sp,
			@RequestParam(value="contentId",required=false) String contentId,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestParam("synchronize")boolean synchronize);
	
	@GetMapping(value = "v2/bookmark/counts")
	Object getCounts(@RequestParam("userid") String userId);
	
	@DeleteMapping( value = "v2/bookmark/remove")
	Object removeBookmark(@RequestParam("userid") String userId, 
			@RequestParam("pcontentids") String pContentIds,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestParam("synchronize")boolean synchronize);
	
	@GetMapping( value = "v2/bookmark/check/content")
	Object checkBookmark(@RequestParam("userid") String userId,
			@RequestParam("contentid") String contentId,
			@RequestParam(value="index",required=false) Integer index,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestParam("synchronize")boolean synchronize);
}
