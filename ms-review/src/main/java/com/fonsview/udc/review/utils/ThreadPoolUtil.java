/**
 * 
 */
package com.fonsview.udc.review.utils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import com.fonsview.udc.review.constants.ConfigKey;
/**
 * @Description 
 * @author hoob
 * @date 2019年1月28日上午11:13:47
 */
public class ThreadPoolUtil {
	
	private static Integer deleteBookmarkCorepoolsize = Utils.obj2Int(ConfigUtil.getProperties(ConfigKey.DELETEBOOKMARKCOREPOOLSIZE.name()), 20);
	private static Integer deleteFavoriteCorepoolsize = Utils.obj2Int(ConfigUtil.getProperties(ConfigKey.DELETEFAVORITECOREPOOLSIZE.name()), 2);
			
	private static final  ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(300,
			new BasicThreadFactory.Builder().namingPattern("thread-pool-util-%d").daemon(true).build()); 
	public static void createTask(Runnable runnable){
		executorService.execute(runnable);
	}
	
	//书签删除线程池
	private static final  ScheduledExecutorService deteleBookmarkService = new ScheduledThreadPoolExecutor(deleteBookmarkCorepoolsize,
			new BasicThreadFactory.Builder().namingPattern("thread-pool-deteleBookmarkThread-%d").daemon(true).build()); 
	public static void deleteBookmarkTask(Runnable runnable){
		deteleBookmarkService.execute(runnable);
	}
	
	//收藏删除线程池
	private static final  ScheduledExecutorService deteleFavoriteService = new ScheduledThreadPoolExecutor(deleteFavoriteCorepoolsize,
			new BasicThreadFactory.Builder().namingPattern("thread-pool-deteleFavoriteThread-%d").daemon(true).build()); 
	public static void deleteFavoriteTask(Runnable runnable){
		deteleFavoriteService.execute(runnable);
	}
}
