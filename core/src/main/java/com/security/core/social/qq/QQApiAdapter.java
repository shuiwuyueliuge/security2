package com.security.core.social.qq;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQApiAdapter implements ApiAdapter<QQ> {

	@Override
	public boolean test(QQ api) {
		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		try {
			QQUserInfo info = api.getUserInfo();
			values.setDisplayName(info.getNickname());
			values.setImageUrl(info.getFigureurl_qq_1());
			values.setProfileUrl("");
			values.setProviderUserId(api.getOpenId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		
	}

}
