package com.account.manage.controller;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 在这里我们使用@SpringBootApplication指定这是一个 spring boot的应用程序.
 * 
 * @author Angel -- 守护天使
 * @version v.0.1
 * @date 2016年12月10日
 */
// extends WebMvcConfigurerAdapter
@SpringBootApplication
public class App {

    // @Override
    // public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    // super.configureMessageConverters(converters);
    //
    // /*
    // * 1、需要先定义一个 convert 转换消息的对象;
    // * 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
    // * 3、在convert中添加配置信息.
    // * 4、将convert添加到converters当中.
    // *
    // */
    //
    // // 1、需要先定义一个 convert 转换消息的对象;
    // FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    //
    // //2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
    // FastJsonConfig fastJsonConfig = new FastJsonConfig();
    // fastJsonConfig.setSerializerFeatures(
    // SerializerFeature.PrettyFormat
    // );
    //
    // //3、在convert中添加配置信息.
    // fastConverter.setFastJsonConfig(fastJsonConfig);
    //
    // //4、将convert添加到converters当中.
    // converters.add(fastConverter);
    //
    // }

    /**
     * 在这里我们使用 @Bean注入 fastJsonHttpMessageConvert
     * 
     * @return
     */
    @Autowired
    private Environment env;



/*
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1、需要先定义一个 convert 转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        // 3、在convert中添加配置信息.
        fastConverter.setFastJsonConfig(fastJsonConfig);

        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }*/

    /**
     * 这是springloader的配置方式：-javaagent:.\lib\springloaded-1.2.4.RELEASE.jar -noverify
     * 
     * @param args
     */
    public static void main(String[] args) {
        /*
         * 在main方法进行启动我们的应用程序.
         */
        SpringApplication.run(App.class, args);
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }
    
}
