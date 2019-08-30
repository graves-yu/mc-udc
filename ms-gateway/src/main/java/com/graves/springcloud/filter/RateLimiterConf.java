/**
 * 
 */
package com.graves.springcloud.filter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**  
 * <p>Title: RateLimiterConf</p>  
 * <p>Description: </p>  
 * @author Graves  
 * @date 2019年8月29日  
 */

@Component
//使用配置文件的方式进行初始化
@ConfigurationProperties(prefix = "ratelimiter-conf")
public class RateLimiterConf {
	//处理速度
    private static final String DEFAULT_REPLENISHRATE="feign.replenishRate";
    //容量
    private static final String DEFAULT_BURSTCAPACITY="feign.burstCapacity";

    private Map<String , Integer> rateLimitMap = new ConcurrentHashMap<String , Integer>(){
        {
        	
        	System.out.println("DEFAULT_REPLENISHRATE:"+ DEFAULT_REPLENISHRATE);
        	System.out.println("DEFAULT_BURSTCAPACITY:"+ DEFAULT_BURSTCAPACITY);
            put(DEFAULT_REPLENISHRATE , 1);
            put(DEFAULT_BURSTCAPACITY , 1);
        }
    };

    public Map<String, Integer> getRateLimitMap() {
        return rateLimitMap;
    }

    public void setRateLimitMap(Map<String, Integer> rateLimitMap) {
        this.rateLimitMap = rateLimitMap;
    }
}
