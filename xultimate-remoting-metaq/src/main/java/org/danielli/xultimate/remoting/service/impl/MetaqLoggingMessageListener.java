package org.danielli.xultimate.remoting.service.impl;

import javax.annotation.Resource;

import org.danielli.xultimate.remoting.dto.Logging;
import org.danielli.xultimate.remoting.service.LoggingService;

import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessage;

public class MetaqLoggingMessageListener extends DefaultMessageListener<Logging> {

	@Resource(name= "loggingServiceImpl")
	private LoggingService loggingService;
	
	@Override
	public void onReceiveMessages(MetaqMessage<Logging> msg) {
//		msg.getPartition().setAutoAck(false);
		loggingService.saveLogging(msg.getBody());
//		msg.getPartition().ack();
	}

}
