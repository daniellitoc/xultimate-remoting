package org.danielli.xultimate.remoting.service.impl;

import org.danielli.xultimate.remoting.dto.Logging;
import org.danielli.xultimate.remoting.service.LoggingService;
import org.springframework.stereotype.Component;

@Component("loggingServiceImpl")
public class LoggingServiceImpl implements LoggingService {

	@Override
	public void saveLogging(Logging logging) {
		System.out.println(logging.getDate() + ":" + logging.getContent());
	}

}
