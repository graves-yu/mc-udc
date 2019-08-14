/**
 * 
 */
package com.fonsview.udc.favorite.dao;

import java.util.List;

import com.fonsview.udc.favorite.model.Favorite;

/**
 * @Description 
 * @author hoob
 * @date 2018年11月11日下午08:11:47
 */
public interface FavoriteDao{
public Favorite add(Favorite favorite);
public List<Favorite> deleteFavoriteByFavoriteSize(String userId,String size,String mediaType);
public List<Favorite> getFavoriteList(String userId, Integer begin, Integer pageSize, String sp, String date,String mediaType);
public long getFavoriteCount(String userId,String mediaType);
public List<Favorite> remove(String userId,String contentIds,String mediaType);
public boolean checkFavorite(String userId,String contentId,String mediaType);
/**  
 * <p>Title: getFavorites</p>  
 * <p>Description: </p>  
 * @author Graves
 * @date 2018年11月14日   
 * @param userId
 * @param stbid
 * @return  
 */ 
public List<Favorite> getFavorites(String userId, String stbid);

}
