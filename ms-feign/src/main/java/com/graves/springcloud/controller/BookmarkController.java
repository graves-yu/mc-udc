/**
 * 
 */
package com.graves.springcloud.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graves.springcloud.service.BookmarkService;

/**  
 * <p>Title: BookmarkController</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年8月9日  
 */
@RestController
@RequestMapping("bookmark/")
public class BookmarkController {
    @Autowired
    BookmarkService bookmarkService;
    
    @RequestMapping(value = "addBookmark", method = RequestMethod.POST, produces = "application/json")
	public Object addBookmark(@RequestParam("synchronize")boolean synchronize,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestBody String body){
    	return bookmarkService.addBookmark(synchronize, groupType, body);
    }
    
    @RequestMapping(value = "listBookmark", method = RequestMethod.GET)
    public Object getBookmarkList(@RequestParam("userid") String userId, 
			@RequestParam(value="begin",required=false) Integer begin, @RequestParam(value="pagesize",required=false) Integer pageSize,
			@RequestParam(value="sp",required=false) String sp,
			@RequestParam(value="contentId",required=false) String contentId,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestParam("synchronize")boolean synchronize) {
        return bookmarkService.getBookmarkList(userId, begin, pageSize, sp, contentId, groupType, synchronize);
    }
    
    @GetMapping(value = "countBookmark")
	public Object getCounts(@RequestParam("userid") String userId,HttpServletRequest request) {
    	return bookmarkService.getCounts(userId);
    }
    
    @DeleteMapping( value = "removeBookmark")
	public Object removeBookmark(@RequestParam("userid") String userId, 
			@RequestParam("pcontentids") String pContentIds,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestParam("synchronize")boolean synchronize) {
    	return bookmarkService.removeBookmark(userId, pContentIds, groupType, synchronize);
    }
    
    @GetMapping( value = "checkBookmark")
	public Object checkBookmark(@RequestParam("userid") String userId,
			@RequestParam("contentid") String contentId,
			@RequestParam(value="index",required=false) Integer index,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestParam("synchronize")boolean synchronize) {
    	return bookmarkService.checkBookmark(userId, contentId, index, groupType, synchronize);
    }
}
