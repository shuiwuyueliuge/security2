package com.security.core.config;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
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
	
	private Map<SocialEnum, Class<?>> map;
	
	{
		map = new HashMap<SocialEnum, Class<?>>();
		map.put(SocialEnum.QQ, QQSocialConfigurerAdapter.class);
		map.put(SocialEnum.GITHUB, GitHubSocialConfigurerAdapter.class);
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableSocial.class.getName()));
		SocialEnum[] values = (SocialEnum[]) annoAttrs.get("value");
		Stream.of(values).forEach(data -> registerBeanDefinition(data, registry));
	}
	
	private void registerBeanDefinition(SocialEnum socialEnum, BeanDefinitionRegistry registry) {
		Class<?> adapter = map.get(socialEnum);
		if (adapter == null) {
			return;
		}
		
		BeanDefinition beanDe = BeanDefinitionBuilder.rootBeanDefinition(adapter).getBeanDefinition();
		registry.registerBeanDefinition(adapter.getName(), beanDe);
	}
}
