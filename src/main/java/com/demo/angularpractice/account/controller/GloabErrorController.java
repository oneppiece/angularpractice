package com.demo.angularpractice.account.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * liyan-下午9:36
 *
 * @author liyan
 **/
@Controller
@Slf4j
public class GloabErrorController implements ErrorController {
	private static final String ERROR_PATH = "/error";

	@RequestMapping(value = ERROR_PATH)
	@ResponseBody
	public Object handleError(HttpServletRequest request, HttpServletResponse response) {
		int status = response.getStatus();
		System.out.println(status);
		System.out.println(request);
		return status;
//		log.info(bindingResult.getAllErrors().toString());
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}
