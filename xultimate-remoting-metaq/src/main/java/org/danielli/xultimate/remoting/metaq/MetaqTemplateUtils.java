package org.danielli.xultimate.remoting.metaq;

import org.danielli.xultimate.remoting.metaq.support.MetaqTemplate;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.extension.spring.MessageBodyConverter;
import com.taobao.metamorphosis.client.extension.spring.MessageBuilder;
import com.taobao.metamorphosis.client.producer.MessageProducer;

/**
 * 消息模板工具类。
 * 
 * @author Daniel Li
 * @since 15 Jun 2013
 */
public class MetaqTemplateUtils {

	/**
	 * 发送消息。
	 * 
	 * @param metaqTemplate 消息模板。
	 * @param builder 消息构造器。
	 * @param callback 回调。
	 * @throws MetaqClientException 回调过程中可能出现的任何异常。
	 */
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
	
	/**
	 * 
	 * @param metaqTemplate 消息模板。
	 * @param topic 消息主题。
	 * @param callback 回调。
	 * @throws MetaqClientException 回调过程中可能出现的任何异常。
	 */
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
