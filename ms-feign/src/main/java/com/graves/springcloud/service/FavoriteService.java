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
 * <p>Title: FavoriteService</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年8月14日  
 */
@FeignClient(value = "favorite-service", fallback = FavoriteFallbackService.class)
public interface FavoriteService {
	@PostMapping(value = "v2/favorite/add", produces = "application/json")
	public Object addFavorite(@RequestParam("synchronize")boolean synchronize,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestBody String body);
	
	@GetMapping( value = "v2/favorite/list")
	public Object getFavoriteList(@RequestParam("userid") String userId, 
			@RequestParam(value="begin",required=false) Integer begin, 
			@RequestParam(value="pagesize",required=false) Integer pageSize,
			@RequestParam(value="date",required=false) String date,
			@RequestParam(value="mediatype",required=false) String mediaType,
			@RequestParam(value="sp",required=false) String sp,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestParam("synchronize")boolean synchronize);
	
	@GetMapping( value = "v2/favorite/counts")
	public Object getFavoriteCount(@RequestParam("userid") String userId, 
			@RequestParam(value="mediatype",required=false) String mediaType);
	
	@DeleteMapping( value = "v2/favorite/remove")
	public Object removeFavorite(@RequestParam("userid") String userId, 
			@RequestParam(value="contentids",required=false) String contentIds,
			@RequestParam(value="mediatype") String mediaType,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestParam("synchronize")boolean synchronize);
	
	@GetMapping( value = "v2/favorite/check/content")
	public Object checkFavorite(@RequestParam("userid") String userId, 
			@RequestParam("contentid") String contentId,			
			@RequestParam(value="mediatype",required=false) String mediaType,
			@RequestParam(value="grouptype",required=false) String groupType,
			@RequestParam("synchronize")boolean synchronize);
}
