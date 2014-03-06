package org.danielli.xultimate.remoting.service.impl;

import javax.annotation.Resource;

import org.danielli.xultimate.remoting.dto.Logging;
import org.danielli.xultimate.remoting.metaq.MessageProducerCallback;
import org.danielli.xultimate.remoting.metaq.MessageProducerWithMessageCallback;
import org.danielli.xultimate.remoting.metaq.MetaqTemplateUtils;
import org.danielli.xultimate.remoting.metaq.support.MetaqTemplate;
import org.danielli.xultimate.remoting.service.LoggingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.extension.spring.MessageBodyConverter;
import com.taobao.metamorphosis.client.extension.spring.MessageBuilder;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.SendMessageCallback;
import com.taobao.metamorphosis.client.producer.SendResult;

@Service("metaqClientLoggingService")
public class MetaqClientLoggingService implements LoggingService {

	@Resource(name = "metaqTemplate")
	public MetaqTemplate metaqTemplate;
	
	public static final String topic = "test";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MetaqClientLoggingService.class);
	
	@Override
	public void saveLogging(Logging logging) {
		MetaqTemplateUtils.send(metaqTemplate, MessageBuilder.withTopic(topic).withBody(logging), new MessageProducerWithMessageCallback() {
			
			@Override
			public void doInMessageProducer(MessageProducer messageProducer, Message message) throws Exception {
				SendResult sendResult = messageProducer.sendMessage(message);
				if (!sendResult.isSuccess()) {
					LOGGER.error("Send message failed,error message:" + sendResult.getErrorMessage());
				} else {
					LOGGER.info("Send message successfully,sent to " + sendResult.getPartition());
				}
			}
		});
		
		MetaqTemplateUtils.send(metaqTemplate, MessageBuilder.withTopic(topic).withBody(logging), new MessageProducerWithMessageCallback() {
			
			@Override
			public void doInMessageProducer(MessageProducer messageProducer, Message message) throws Exception {
				messageProducer.sendMessage(message, new SendMessageCallback() {
					
					@Override
					public void onMessageSent(SendResult result) {
						if (!result.isSuccess()) {
							LOGGER.error("Send message failed,error message:" + result.getErrorMessage());
						} else {
							LOGGER.info("Send message successfully,sent to " + result.getPartition());
						}
					}
					
					@Override
					public void onException(Throwable e) {
						LOGGER.error("Send message failed", e);
					}
				});
			}
		});
		
		final Logging transactionLogging = logging;
		MetaqTemplateUtils.send(metaqTemplate, topic, new MessageProducerCallback() {
			
			@Override
			public void doInMessageProducer(MessageProducer messageProducer, MessageBodyConverter<Object> converter, String topic) throws Exception {
				messageProducer.setTransactionTimeout(10);
				try {
		            // 开始事务
					messageProducer.beginTransaction();
		            // 在事务内发送两条消息
		            if (!messageProducer.sendMessage(new Message(topic, converter.toByteArray(transactionLogging))).isSuccess()) {
		                // 发送失败，立即回滚
		            	messageProducer.rollback();
		            }
		            if (!messageProducer.sendMessage(new Message(topic, converter.toByteArray(transactionLogging))).isSuccess()) {
		            	messageProducer.rollback();
		            }
		            // 提交
		            messageProducer.commit();
		        } catch (final Exception e) {
		        	messageProducer.rollback();
		        	throw e;
		        }
			}
		});
	}

}
