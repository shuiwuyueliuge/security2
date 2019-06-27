package org.demo;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestC {

	@RequestMapping("/test")
	public Object test() {
		return new Date().toLocaleString();
	}
}
