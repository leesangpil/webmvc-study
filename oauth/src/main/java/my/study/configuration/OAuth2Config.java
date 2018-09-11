package my.study.configuration;

import my.study.OauthApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * Created by leesangpil on 2018. 9. 11..
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config {

    @Bean
    public TokenStore jdbcTokenStore(DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }
}
