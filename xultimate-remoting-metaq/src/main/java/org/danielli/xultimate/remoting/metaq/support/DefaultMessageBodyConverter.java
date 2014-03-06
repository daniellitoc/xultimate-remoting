package org.danielli.xultimate.remoting.metaq.support;

import org.danielli.xultimate.core.serializer.Deserializer;
import org.danielli.xultimate.core.serializer.Serializer;

import com.taobao.metamorphosis.client.extension.spring.MessageBodyConverter;
import com.taobao.metamorphosis.exception.MetaClientException;

/**
 * 默认消息体转换。支持组合配置序列化方式。
 * 
 * @author Daniel Li
 * @since 15 Jun 2013
 */
public class DefaultMessageBodyConverter implements MessageBodyConverter<Object> {
	/** 序列化器 */
	private Serializer serializer;
	/** 解序列化器 */
	private Deserializer deserializer;
	
	@Override
	public byte[] toByteArray(Object body) throws MetaClientException {
		return serializer.serialize(body);
	}

	@Override
	public Object fromByteArray(byte[] bs) throws MetaClientException {
		return deserializer.deserialize(bs, Object.class);
	}
	
	/**
	 * 设置序列化器。
	 * @param serializer 序列化器。
	 */
	public void setSerializer(Serializer serializer) {
		this.serializer = serializer;
	}

	/**
	 * 设置解序列化器。
	 * @param deserializer 解序列化器。
	 */
	public void setDeserializer(Deserializer deserializer) {
		this.deserializer = deserializer;
	}

}
