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
 * <p>Title: ReminderService</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年8月26日  
 */
@FeignClient(value = "reminder-service" , fallback = ReminderFallbackSercvice.class )
public interface ReminderService {
	
	/**
	 * 添加提醒
	 * <p>Title: addReminder</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月26日   
	 * @param body
	 * @return
	 */
	@PostMapping(value = "v1/user/reminder", produces = "application/json")
	public Object addReminder(@RequestBody String body);
	
	/**
	 * 删除提醒
	 * <p>Title: removeReminder</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月26日   
	 * @param contentId
	 * @param userId
	 * @param mediaType
	 * @return
	 */
	@DeleteMapping(value = "/v1/user/removereminder", produces = "application/json")
	public Object removeReminder(@RequestParam("contentid") String contentId, 
										  @RequestParam("userid") String userId,
										  @RequestParam("mediatype") String mediaType);
	
	/**
	 * 获取提醒
	 * <p>Title: reminderList</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月26日   
	 * @param userId
	 * @param contentId
	 * @param mediaType
	 * @return
	 */
	@GetMapping(value = "v1/user/reminder/list", produces = "application/json")
	public Object reminderList(@RequestParam("userid") String userId,
			@RequestParam(value = "contentid", required = false) String contentId,
			@RequestParam(value = "mediatype", required = false) String mediaType);
}
