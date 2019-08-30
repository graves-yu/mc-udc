package com.graves.springcloud;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;

import com.graves.springcloud.filter.SystemRedisRateLimiter;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
public class MsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGatewayApplication.class, args);
	}
	
	/**
	 * 第一个router：请求“/get”请求都转发到“http://httpbin.org/get”。在route配置上，我们添加了一个filter，
	 * 该filter会将请求添加一个header,key为hello，value为world。
	 * 
	 * 
	 * 第二个router：使用host去断言请求是否进入该路由，当请求的host有“*.hystrix.com”，都会进入该router，
	 * 该router中有一个hystrix的filter,该filter可以配置名称、和指向性fallback的逻辑的地址，比如本案例中重定向到了“/fallback”
	 * <p>Title: myRoutes</p>  
	 * <p>Description: </p>  
	 * @author Graves
	 * @date 2019年8月7日   
	 * @param builder
	 * @return
	 */
	@Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		String httpUri = "http://httpbin.org:80";
       return builder.routes()
        .route(p -> p
            .path("/get")
            .filters(f -> f.addRequestHeader("Hello", "World"))
            .uri(httpUri))
        .route(p -> p
                .host("*.hystrix.com")
                .filters(f -> f
                    .hystrix(config -> config
                        .setName("mycmd")
                        .setFallbackUri("forward:/fallback")))
                .uri(httpUri))
        .build();
    }

	@RequestMapping("/fallback")
	public String test() {
		Date date = new Date();
		return "Hi,I'm hystrix --By Graves " + date.toString();
	}
	
	@Bean
	 KeyResolver sysKeyResolver(){
	 	  //从请求地址中截取sys值，进行限流。
	     return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userid"));
	 }

	@Bean
	@Primary
	//使用自己定义的限流类
	SystemRedisRateLimiter systemRedisRateLimiter(
	        ReactiveRedisTemplate<String, String> redisTemplate,
	        @Qualifier(SystemRedisRateLimiter.REDIS_SCRIPT_NAME) RedisScript<List<Long>> script,
	        Validator validator){
	    return new SystemRedisRateLimiter(redisTemplate , script , validator);
	}

}
