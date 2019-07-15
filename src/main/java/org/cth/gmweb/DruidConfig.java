package org.cth.gmweb;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * 配置中写成spring.datasource.druid即可加载到druid DataSource配置中
 * @author cth
 * @date 2019/06/04
 */
//@Configuration
public class DruidConfig {
    /**
     * 将所有前缀为spring.datasource下的配置项都加载到DataSource中
     */
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
    public DataSource druidSource() {
        return new DruidDataSource();
    }
}
