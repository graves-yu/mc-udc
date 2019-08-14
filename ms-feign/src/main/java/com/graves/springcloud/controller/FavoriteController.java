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

import com.graves.springcloud.service.FavoriteService;

/**  
 * <p>Title: FavoriteController</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年8月14日  
 */
@RestController
@RequestMapping("favorite/")
public class FavoriteController {
	@Autowired
	private FavoriteService favoriteService;
	
	@PostMapping(value = "addFavorite", produces = "application/json")
	public Object addFavorite(@RequestParam("synchronize")boolean synchronize,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestBody String body){
		return favoriteService.addFavorite(synchronize, groupType, body);
	};
	
	@GetMapping( value = "listFavorite")
	public Object getFavoriteList(@RequestParam("userid") String userId, 
			@RequestParam(value="begin",required=false) Integer begin, 
			@RequestParam(value="pagesize",required=false) Integer pageSize,
			@RequestParam(value="date",required=false) String date,
			@RequestParam(value="mediatype",required=false) String mediaType,
			@RequestParam(value="sp",required=false) String sp,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestParam("synchronize")boolean synchronize) {
		return favoriteService.getFavoriteList(userId, begin, pageSize, date, mediaType, sp, groupType, synchronize);
	}
	
	@GetMapping( value = "countFavorite")
	public Object getFavoriteCount(@RequestParam("userid") String userId, 
			@RequestParam(value="mediatype",required=false) String mediaType) {
		return favoriteService.getFavoriteCount(userId, mediaType);
	}
	
	@DeleteMapping( value = "removeFavorite")
	public Object removeFavorite(@RequestParam("userid") String userId, 
			@RequestParam(value="contentids",required=false) String contentIds,
			@RequestParam(value="mediatype") String mediaType,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestParam("synchronize")boolean synchronize) {
		return favoriteService.removeFavorite(userId, contentIds, mediaType, groupType, synchronize);
	}
	
	@GetMapping( value = "checkFavorite")
	public Object checkFavorite(@RequestParam("userid") String userId, 
			@RequestParam("contentid") String contentId,			
			@RequestParam(value="mediatype",required=false) String mediaType,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestParam("synchronize")boolean synchronize) {
		return favoriteService.checkFavorite(userId, contentId, mediaType, groupType, synchronize);
	}
}
