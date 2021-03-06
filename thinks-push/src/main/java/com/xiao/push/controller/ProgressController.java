package com.xiao.push.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xiao.tools.net.DownLoadUtil;

@Controller
@RequestMapping("/progress")
public class ProgressController {

	@GetMapping("/push")
	public String toDown() {
		return "index";
	}

	@RequestMapping("/down")
	public void down(HttpServletRequest request, HttpServletResponse response) {
		String filePath = "D:\\1.gif";
		DownLoadUtil.downFile(filePath, request, response);
	}

	@PostMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file,@RequestParam("files") MultipartFile files, ModelMap modelMap) throws Exception {
		File img = new File("D://2/" + file.getOriginalFilename());
		File imgs = new File("D://2/" + files.getOriginalFilename());
		file.transferTo(img);
		files.transferTo(imgs);
		return "OK";
	}

}
