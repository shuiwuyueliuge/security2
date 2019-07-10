package com.security.core.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import com.security.core.enable.EnableSocial;
import com.security.core.social.SocialEnum;
import com.security.core.social.github.GitHubSocialConfigurerAdapter;
import com.security.core.social.qq.QQSocialConfigurerAdapter;

public class SocialConfigurerAdapterRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableSocial.class.getName()));
		SocialEnum[] values = (SocialEnum[]) annoAttrs.get("value");
		for (SocialEnum socialEnum : values) {
			if (socialEnum == SocialEnum.QQ) {
				BeanDefinition beanDe = BeanDefinitionBuilder.rootBeanDefinition(QQSocialConfigurerAdapter.class).getBeanDefinition();
				registry.registerBeanDefinition("QQSocialConfigurerAdapter", beanDe);
			}
			
			if (socialEnum == SocialEnum.GITHUB) {
				BeanDefinition beanDe = BeanDefinitionBuilder.rootBeanDefinition(GitHubSocialConfigurerAdapter.class).getBeanDefinition();
				registry.registerBeanDefinition("GitHubSocialConfigurerAdapter", beanDe);
			}
		}
	}
}
