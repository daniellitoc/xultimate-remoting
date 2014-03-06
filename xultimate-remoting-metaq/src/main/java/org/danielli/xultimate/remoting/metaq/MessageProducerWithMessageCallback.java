package org.danielli.xultimate.remoting.metaq;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.producer.MessageProducer;

/**
 * 消息提供器回调。
 * 
 * @author Daniel Li
 * @since 15 Jun 2013
 */
public interface MessageProducerWithMessageCallback {
	
	void doInMessageProducer(MessageProducer messageProducer, Message message) throws Exception;
	
}
