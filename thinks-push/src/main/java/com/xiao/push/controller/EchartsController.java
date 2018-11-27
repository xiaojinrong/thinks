package com.xiao.push.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EchartsController {

	
	@GetMapping("/toEchartsDemo1")
	public String toEchartsDemo1() {
		return "echart_demo1";
	}
}
