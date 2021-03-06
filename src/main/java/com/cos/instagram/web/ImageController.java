package com.cos.instagram.web;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.instagram.config.auth.LoginUserAnnotation;
import com.cos.instagram.config.auth.PrincipalDetails;
import com.cos.instagram.config.auth.dto.LoginUser;

@Controller
public class ImageController {

	@GetMapping({"","/","image/feed"})
	public String feed(@LoginUserAnnotation LoginUser loginUser) {
		// System.out.println("@AuthenticationPrincipal: " + principal.getUser());
		// LoginUser loginUser = (LoginUser)session.getAttribute("loginUser");
		System.out.println("loginUser: " + loginUser);
		return "image/feed";
	}
}
