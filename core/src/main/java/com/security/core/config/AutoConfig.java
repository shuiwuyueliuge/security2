package com.security.core.config;

import java.util.Set;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
	@ConditionalOnProperty(prefix = "security.login", name = { "page", "processing-url", "username-parameter",
			"password-parameter" })
	public LoginProperties loginProperties() {
		return new LoginProperties();
	}

	@Bean
	@ConfigurationProperties(prefix = "security.code")
	@ConditionalOnProperty(prefix = "security.code", name = { "key-parameter", "value-parameter" })
	public ValidateCodeProperties validateCodeProperties() {
		return new ValidateCodeProperties();
	}

	@Bean
	@ConfigurationProperties(prefix = "security.logout")
	@ConditionalOnProperty(prefix = "security.logout", name = "url")
	public LogoutProperties logoutProperties() {
		return new LogoutProperties();
	}

	@Bean
	@ConfigurationProperties(prefix = "security.sms")
	@ConditionalOnProperty(prefix = "security.sms", name = { "processing-url", "mobile" })
	public SmsProperties smsProperties() {
		return new SmsProperties();
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
	@ConditionalOnBean(RememberMeProperties.class)
	public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		jdbcTokenRepository.setCreateTableOnStartup(false);
		return jdbcTokenRepository;
	}
}
