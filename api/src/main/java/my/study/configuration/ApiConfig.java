package my.study.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by leesangpil on 2018. 9. 11..
 */
@Configuration
@EnableResourceServer
public class ApiConfig {

    @Bean
    public ResourceServerConfigurerAdapter resourceServerConfigurerAdapter() {
        return new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(HttpSecurity http) throws Exception {
                http.headers().frameOptions().disable();
                http.authorizeRequests()
                        .antMatchers("/members", "/members/**").access("#oauth2.hasScope('read')")
                        .anyRequest().authenticated();
            }
        };
    }

}
