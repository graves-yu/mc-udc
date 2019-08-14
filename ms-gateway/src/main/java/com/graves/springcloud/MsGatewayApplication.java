package com.graves.springcloud;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
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
}
