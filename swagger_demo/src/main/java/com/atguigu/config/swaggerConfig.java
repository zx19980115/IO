package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class swaggerConfig {

    //配置了Swagger的Docket的Bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev","test");
        //通过enviroment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2).groupName("test")
                .apiInfo(apiInfo())
                .enable(flag)//enable是否启动swagger，如果为false，则swagger不能在浏览器中访问
                .select()
                //RequestHandlerSelectors 配置要扫描接口的方式
                //basePackage:指定要扫描的包
                //any():扫描全部
                //none()://不扫描
                //withClassAnnotation:扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation:扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.atguigu.controller"))
                //paths()过滤什么路径
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("test1");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("test2");
    }

    @Bean
    public ApiInfo apiInfo(){
        //作者信息
        Contact contact =  new Contact("周翔", "www.baidu.com", "zhouxiang19980115@126.com");
        return new ApiInfo("周翔的第一个swagger",
                "周翔的第一个swagger1",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
