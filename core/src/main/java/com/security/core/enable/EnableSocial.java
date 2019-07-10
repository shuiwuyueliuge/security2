package com.security.core.enable;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import org.springframework.social.config.annotation.SocialConfiguration;

import com.security.core.config.SocialConfig;
import com.security.core.config.SocialConfigurerAdapterRegistrar;
import com.security.core.social.SocialEnum;

@Retention(RUNTIME)
@Target(TYPE)
@Import({ SocialConfiguration.class, SocialConfigurerAdapterRegistrar.class, SocialConfig.class })
public @interface EnableSocial {

	SocialEnum[] value();
}
