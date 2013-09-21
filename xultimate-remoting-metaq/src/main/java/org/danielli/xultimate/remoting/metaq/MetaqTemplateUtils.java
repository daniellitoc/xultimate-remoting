package org.danielli.xultimate.remoting.metaq;

import org.danielli.xultimate.remoting.metaq.support.MetaqTemplate;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.extension.spring.MessageBodyConverter;
import com.taobao.metamorphosis.client.extension.spring.MessageBuilder;
import com.taobao.metamorphosis.client.producer.MessageProducer;

public class MetaqTemplateUtils {

	public static void send(MetaqTemplate metaqTemplate, MessageBuilder builder, MessageProducerWithMessageCallback callback) throws MetaqClientException {
		Message msg = builder.build(metaqTemplate.getMessageBodyConverter());
        final String topic = msg.getTopic();
        MessageProducer producer = metaqTemplate.getOrCreateProducer(topic);
        try {
        	callback.doInMessageProducer(producer, msg);
        } catch (Exception e) {
            throw new MetaqClientException(e.getMessage(), e);
        }
	}
	
	public static void send(MetaqTemplate metaqTemplate, String topic, MessageProducerCallback callback) throws MetaqClientException {
		MessageBodyConverter<Object> converter = metaqTemplate.getMessageBodyConverter();
		MessageProducer producer = metaqTemplate.getOrCreateProducer(topic);
        try {
        	callback.doInMessageProducer(producer, converter, topic);
        } catch (Exception e) {
            throw new MetaqClientException(e.getMessage(), e);
        }
	}
}
