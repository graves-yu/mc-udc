/**
 * 
 */
package com.graves.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graves.springcloud.service.ReviewService;

/**  
 * <p>Title: ReviewController</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年8月23日  
 */
@RestController
@RequestMapping("review/")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping(value = "addScore", produces = "application/json")
	public Object addScore(@RequestParam("userid") String userId, @RequestBody String body) {
		return reviewService.addScore(userId, body);
	}
	
	@GetMapping(value = "getContentScore", produces = "application/json")
	public Object getAvgScore(@RequestParam(value = "userid", required = false) String userId,
			@RequestParam("contentid") String contentId) {
		return reviewService.getAvgScore(userId, contentId);
	}
	
	@PostMapping(value = "addRecommendation", produces = "application/json")
	public Object addRecommendation(@RequestParam(value = "userid", required = true) String userId,
			@RequestBody String body) {
		return reviewService.addRecommendation(userId, body);
	}
	
	@GetMapping(value = "getCountRecommendation", produces = "application/json")
	public Object countRecommendation(@RequestParam("contentid") String contentId) {
		return reviewService.countRecommendation(contentId);
	}
	
	@PostMapping(value = "addComment", produces = "application/json")
	public Object addComment(@RequestBody String body) {
		return reviewService.addComment(body);
	}
	
	@GetMapping(value = "getComment", produces = "application/json")
	public Object getComment(@RequestParam("contentid") String contentId, 
										  @RequestParam(value = "commentid", required = false) String commentId,
										  @RequestParam("begin") Integer begin,
										  @RequestParam("pagesize") Integer pagesize) {
		return reviewService.getComment(contentId, commentId, begin, pagesize);
	}
}
