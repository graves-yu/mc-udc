/**
 * 
 */
package com.fonsview.udc.review.dao;

import java.util.List;
import java.util.Map;

import com.fonsview.udc.review.model.Comment;
import com.fonsview.udc.review.model.Recommendation;
import com.fonsview.udc.review.model.Score;

/**
 * <p>Title: ReviewDao</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2018年12月29日
 */
public interface ReviewDao {

	/**
	 * 添加评分
	 * <p>Title: addScore</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2018年12月29日   
	 * @param score
	 * @return 
	 */
	void addScore(Score score);
	
	/**
	 * 查询用户评分
	 * <p>Title: getScore</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月2日   
	 * @param userId
	 * @param contentId
	 * @return
	 */
	Score getScore(String userId, String contentId);

	/**  
	 * 根据内容ID获取平均分  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月2日   
	 * @param contentId
	 * @return  
	 */ 
	Float getAvgScore(String contentId);

	/**  
	 * 查询踩/赞记录 
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月2日   
	 * @param recommendation
	 * @return  
	 */ 
	Recommendation getRecommendation(Recommendation recommendation);

	/**  
	 * 添加踩/赞记录 
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月2日   
	 * @param recommendation  
	 */ 
	void addRecommendation(Recommendation recommendation);

	/**  
	 * 删除踩/赞记录
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月2日   
	 * @param recommendation  
	 */ 
	void removeRecommendation(Recommendation recommendation);

	/**  
	 * 修改踩/赞记录
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月2日   
	 * @param recommendation  
	 */ 
	void updateRecommendation(Recommendation recommendation);

	/**  
	 * 统计踩/赞总数
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月2日   
	 * @param recommendation  
	 */ 
	Map<String, Integer> countAAndV(String contentId);

	/**  
	 * 用户评论
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月3日   
	 * @param comment
	 * @return  
	 */ 
	Comment addComment(Comment comment);

	/**  
	 * 获取内容的评论 
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年1月3日   
	 * @param contentId
	 * @param commentId
	 * @param begin
	 * @param pagesize
	 * @return  
	 */ 
	List<Comment> getComment(String contentId, String commentId, Integer begin, Integer pagesize);

}
