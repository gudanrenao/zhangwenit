package com.zhangwen.learn.zhangwenit.swagger;

import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * @Description 根据swagger生成confluence文档
 * @Author ZWen
 * @Date 2019/5/29 5:25 PM
 * @Version 1.0
 **/
public class SwaggerToConfluenceDemo {

    /**
     * 根据swagger生成confluence文档
     *
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException {
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.CONFLUENCE_MARKUP)
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:8080/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("src/docs/confluence/generated"));
//                .toFile(Paths.get("src/docs/confluence/generated/all")); //整合到一个文件中
    }
}