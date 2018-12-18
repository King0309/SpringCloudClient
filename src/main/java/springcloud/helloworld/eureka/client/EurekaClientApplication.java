package springcloud.helloworld.eureka.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author king
 * Eureka Client 提供消费者
 * 2018-12-18
 * */

@SpringBootApplication
@EnableEurekaClient
@RestController

public class EurekaClientApplication {

    Logger logger = LoggerFactory.getLogger(EurekaClientApplication.class);

    @Autowired
    DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @Value("${server.port}")
    String port;
    @RequestMapping("/")
    public String home() {
        return "hello world from port " + port;
    }

    @RequestMapping("/test")
    public String test() {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        //打印服务的服务id
        logger.info("*********" + instance.getServiceId());
        return "hello world ";
    }

}
