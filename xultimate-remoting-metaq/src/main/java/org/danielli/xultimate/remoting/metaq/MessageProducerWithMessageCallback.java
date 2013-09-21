package org.danielli.xultimate.remoting.metaq;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.producer.MessageProducer;

public interface MessageProducerWithMessageCallback {
	
	void doInMessageProducer(MessageProducer messageProducer, Message message) throws Exception;
	
}
