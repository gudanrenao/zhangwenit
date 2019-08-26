package com.zhangwen.learn.zhangwenit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Description Swagger配置
 * @Author ZWen
 * @Date 2019/4/26 9:01 AM
 * @Version 1.0
 **/
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
//	    ParameterBuilder ticketPar = new ParameterBuilder();
//	    ticketPar.name("x-access-token").description("登录凭证")
//			    .modelRef(new ModelRef("string")).parameterType("header")
//			    //header中的ticket参数非必填，传空也可以
//			    .required(false).build();
//	    List<Parameter> pars = new ArrayList<Parameter>();
        //根据每个方法名也知道当前方法在设置什么参数
//	    pars.add(ticketPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //设置全局参数
//                .globalOperationParameters(pars)
                .select()
                //只暴露@RestController注解修饰的controller,隐藏了系统自带的@controller注解修饰的controller
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("zhangwenit API说明文档")
                .description("接口文档")
                .version("1.0")
                .build();
    }
}