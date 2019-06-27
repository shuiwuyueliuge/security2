package com.security.core.autoconfig;

import java.util.Set;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import com.security.core.request.DefaultRequestManager;
import com.security.core.request.RequestManager;
import com.security.core.request.RequestProvider;

public class AutoConfig {

	@Bean
	public RequestManager requestManager(Set<RequestProvider> providers) {
		return new DefaultRequestManager(providers);
	}

	@Bean
	@ConfigurationProperties(prefix = "security.login")
	@ConditionalOnProperty(prefix = "security.login", name = { "page", "processing-url", "username-uarameter", "password-uarameter" })
	public LoginProperties loginProperties() {
		return new LoginProperties();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "security.logout")
	@ConditionalOnProperty(prefix = "security.logout", name = "url")
	public LogoutProperties logoutProperties() {
		return new LogoutProperties();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "security.session")
	public SessionProperties sessionProperties() {
		return new SessionProperties();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "security.remember-me")
	@ConditionalOnProperty(prefix = "security.remember-me", name = "token-validity-seconds")
	public RememberMeProperties rememberMeProperties() {
		return new RememberMeProperties();
	}
	
	@Bean
	@ConditionalOnClass(DataSource.class)
	@ConditionalOnProperty(prefix = "security.remember-me", name = "token-validity-seconds")
	public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		jdbcTokenRepository.setCreateTableOnStartup(false);
		return jdbcTokenRepository;
	}
}
