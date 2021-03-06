package org.danielli.xultimate.remoting.service.impl;

import org.danielli.xultimate.remoting.dto.Logging;
import org.danielli.xultimate.remoting.service.LoggingService;
import org.springframework.stereotype.Service;

@Service("loggingServiceImpl")
public class LoggingServiceImpl implements LoggingService {

	@Override
	public void saveLogging(Logging logging) {
		if (logging == null) {
			System.out.println("Logging is null");
		} else {
			System.out.println(logging.getDate() + ":" + logging.getContent());
		}
	}

}
