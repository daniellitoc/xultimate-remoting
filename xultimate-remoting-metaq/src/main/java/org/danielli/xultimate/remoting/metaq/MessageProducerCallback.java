package org.danielli.xultimate.remoting.metaq;

import com.taobao.metamorphosis.client.extension.spring.MessageBodyConverter;
import com.taobao.metamorphosis.client.producer.MessageProducer;

public interface MessageProducerCallback {
	
	void doInMessageProducer(MessageProducer messageProducer, MessageBodyConverter<Object> converter, String topic) throws Exception;
	
}
