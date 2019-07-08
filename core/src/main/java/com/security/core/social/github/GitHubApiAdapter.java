package com.security.core.social.github;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class GitHubApiAdapter implements ApiAdapter<GitHub> {

	@Override
	public boolean test(GitHub api) {
		return true;
	}

	@Override
	public void setConnectionValues(GitHub api, ConnectionValues values) {
		try {
			GitHubUserInfo info = api.getUserInfo();
			values.setDisplayName(info.getLogin());
			values.setImageUrl(info.getAvatar_url());
			values.setProfileUrl(info.getHtml_url());
			values.setProviderUserId(info.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserProfile fetchUserProfile(GitHub api) {
		return null;
	}

	@Override
	public void updateStatus(GitHub api, String message) {
	}
}
