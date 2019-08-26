/**
 * 
 */
package com.graves.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graves.springcloud.service.ReminderService;

/**  
 * <p>Title: ReminderController</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年8月26日  
 */
@RestController
@RequestMapping("reminder/")
public class ReminderController {
	@Autowired
	private ReminderService reminderService;
	
	/**
	 * 添加提醒
	 * <p>Title: addReminder</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月26日   
	 * @param body
	 * @return
	 */
	@PostMapping(value = "addReminder", produces = "application/json")
	public Object addReminder(@RequestBody String body){
		return reminderService.addReminder(body);
	}
	
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
	@DeleteMapping(value = "removeReminder", produces = "application/json")
	public Object removeReminder(@RequestParam("contentid") String contentId, 
										  @RequestParam("userid") String userId,
										  @RequestParam("mediatype") String mediaType){
		return reminderService.removeReminder(contentId, userId, mediaType);
	}
	
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
	@GetMapping(value = "getReminder", produces = "application/json")
	public Object reminderList(@RequestParam("userid") String userId,
			@RequestParam(value = "contentid", required = false) String contentId,
			@RequestParam(value = "mediatype", required = false) String mediaType){
		return reminderService.reminderList(userId, contentId, mediaType);
	}
}
