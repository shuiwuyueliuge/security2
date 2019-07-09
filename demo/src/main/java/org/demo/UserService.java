package org.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements SocialUserDetailsService {
	
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		return new SocialUser("test", new BCryptPasswordEncoder().encode("123"), authorities());
	}
	
	private Collection<GrantedAuthority> authorities() {        
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		List<String> list = new ArrayList<String>();
		list.stream().forEach(role -> authList.add(new SimpleGrantedAuthority(role)));  
		return authList;
	}
}
