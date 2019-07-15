package org.cth.gmweb;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author cth
 * @date 2019/06/04
 */
@Configuration
@MapperScan("org.cth.gmweb.mapper")
@EnableTransactionManagement
public class MybatisPlusConfig {
    /**
     * mp 分页
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
