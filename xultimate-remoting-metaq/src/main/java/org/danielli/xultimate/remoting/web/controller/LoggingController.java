package org.danielli.xultimate.remoting.web.controller;

import httl.util.StringUtils;

import java.util.Date;

import javax.annotation.Resource;

import org.danielli.xultimate.remoting.dto.Logging;
import org.danielli.xultimate.remoting.service.LoggingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/logging")
public class LoggingController {
	
	@Resource(name = "metaqClientLoggingService")
	public LoggingService loggingService;
	
	@RequestMapping(method = { RequestMethod.GET })
	public String saveLogging(@RequestParam(required = false) String content, ModelMap modelMap) {
		if (!StringUtils.isEmpty(content)) {
			Logging logging = new Logging();
			logging.setContent(content);
			logging.setDate(new Date());
			loggingService.saveLogging(logging);
		} else {
			loggingService.saveLogging(null);
		}
		return "logging_save_success";
	}
}
