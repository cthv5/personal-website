package org.cth.gmweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author cth
 * @date 2019/06/03
 */
@SpringBootApplication
public class GmwebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GmwebApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(GmwebApplication.class, args);
	}

}
