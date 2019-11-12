package com.luo.house.biz.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * druid 可以把慢sql 打印出来
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.druid")
    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setProxyFilters(Lists.newArrayList(statFilter()));
        return druidDataSource;
    }

    @Bean
    public Filter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(5);//用了5 秒表示 慢 sql
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        return statFilter;

    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

    }


}
