package com.security.core.validatecode;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.security.core.authentication.sms.UsernameRequestMatcher;

public class DefaultValidateCodeGeneratorHolder implements ValidateCodeGeneratorHolder {

	private Map<String, ValidateCodeGenerator> generators = new ConcurrentHashMap<String, ValidateCodeGenerator>();

	public DefaultValidateCodeGeneratorHolder(Set<ValidateCodeGenerator> set) {
		set.forEach(generator -> generators.put(generator.getCodeType(), generator));
	}

	@Override
	public ValidateCodeGenerator getGeneratorByType(String validateType) {
		return generators.get(validateType);
	}

	@Override
	public int size() {
		return generators.size();
	}

	@Override
	public void addGenerator(ValidateCodeGenerator generator) {
		generators.put(generator.getCodeType(), generator);
	}

	@Override
	public ValidateCodeGenerator getGeneratorByUri(String uri) {
		return stream().filter((gen) -> gen.getLoginUri().equals(uri)).findFirst().get();
	}

	@Override
	public Set<RequestMatcher> toAntPathRequestMatcher() {
		return stream().map(gen -> new AntPathRequestMatcher(gen.getLoginUri(), "POST")).collect(Collectors.toSet());
	}

	@Override
	public Set<String> loginUris() {
		return stream().map(gen -> gen.getLoginUri()).collect(Collectors.toSet());
	}

	private Stream<ValidateCodeGenerator> stream() {
		return generators.values().stream();
	}

	@Override
	public Set<RequestMatcher> toUsernameRequestMatcher() {
		return stream().filter((gen) -> gen.isUsernameOnly()).map(gen -> new UsernameRequestMatcher(gen.getUsername(), gen.getLoginUri())).collect(Collectors.toSet());
	}
}
