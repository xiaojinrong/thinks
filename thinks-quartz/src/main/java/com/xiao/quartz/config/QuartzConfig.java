package com.xiao.quartz.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * quartz的初始化配置
 * @author XiaoJinRong
 * @times 2018年10月25日 下午2:25:36 
 * @version 1.0
 */
@Configuration
public class QuartzConfig {
	@Autowired
    private MyJobFactory myJobFactory;
	
	@Bean(name = "schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        //获取配置属性
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        //创建SchedulerFactoryBean
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        Properties pro = propertiesFactoryBean.getObject();
        factory.setAutoStartup(true);
        factory.setOverwriteExistingJobs(true);
        factory.setStartupDelay(30); 
        factory.setQuartzProperties(pro);
        factory.setJobFactory(myJobFactory);
        return factory;
    }
}
