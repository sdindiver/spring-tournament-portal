package com.bo.tournament.oauthclient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bo.tournament.model.HttpWrapper;

@Controller
public class OAuthClient {

	@RequestMapping("/get-github-authcode")
	public void getAuthCode(HttpWrapper wrapper){
		System.out.println(wrapper);
	}
}
