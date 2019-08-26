/**
 * 
 */
package com.graves.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**  
 * <p>Title: ReviewService</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年8月23日  
 */
@FeignClient(value = "review-service" , fallback = ReviewFallbackService.class )
public interface ReviewService {
	/**
	 * 添加评分
	 * <p>Title: addScore</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param userId
	 * @param body
	 * @param request
	 * @return
	 */
	@PostMapping(value = "v2/user/score", produces = "application/json")
	public Object addScore(@RequestParam("userid") String userId,
			@RequestBody String body);
	
	/**
	 * 获取内容平均分
	 * <p>Title: getAvgScore</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param userId
	 * @param contentId
	 * @param request
	 * @return
	 */
	@GetMapping(value = "v2/content/score", produces = "application/json")
	public Object getAvgScore(@RequestParam(value = "userid", required = false) String userId,
			@RequestParam("contentid") String contentId);
	
	/**
	 * 用户踩赞
	 * <p>Title: addRecommendation</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param userId
	 * @param body
	 * @param request
	 * @return
	 */
	@PostMapping(value = "v2/user/recommendation", produces = "application/json")
	public Object addRecommendation(@RequestParam(value = "userid", required = true) String userId,
			@RequestBody String body);
	
	/**
	 * 获取内容踩/赞数
	 * <p>Title: countRecommendation</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param contentId
	 * @param request
	 * @return
	 */
	@GetMapping(value = "v2/content/recommendation", produces = "application/json")
	public Object countRecommendation(@RequestParam("contentid") String contentId);
	/**
	 * 用户评论
	 * <p>Title: addComment</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param body
	 * @param request
	 * @return
	 */
	@PostMapping(value = "v2/user/comment", produces = "application/json")
	public Object addComment(@RequestBody String body);
	
	/**
	 * 获取内容评论
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
	@GetMapping(value = "v2/content/comment", produces = "application/json")
	public Object getComment(@RequestParam("contentid") String contentId, 
										  @RequestParam(value = "commentid", required = false) String commentId,
										  @RequestParam("begin") Integer begin,
										  @RequestParam("pagesize") Integer pagesize);
	
	/**
	 * 添加提醒
	 * <p>Title: addReminder</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月23日   
	 * @param body
	 * @param request
	 * @return
	 */
	@PostMapping(value = "v1/user/reminder", produces = "application/json")
	public Object addReminder(@RequestBody String body);
	
	/**
	 * 取消提醒
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
	@DeleteMapping(value = "/v1/user/removereminder", produces = "application/json")
	public Object removeReminder(@RequestParam("contentid") String contentId, 
										  @RequestParam("userid") String userId,
										  @RequestParam("mediatype") String mediaType);
	
	/**
	 * 获取用户提醒列表
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
	@GetMapping(value = "v1/user/reminder/list", produces = "application/json")
	public Object reminderList(@RequestParam("userid") String userId,
			@RequestParam(value = "contentid", required = false) String contentId,
			@RequestParam(value = "mediatype", required = false) String mediaType);
}
