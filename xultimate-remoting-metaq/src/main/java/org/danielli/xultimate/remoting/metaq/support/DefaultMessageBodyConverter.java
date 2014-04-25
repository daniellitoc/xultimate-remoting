package org.danielli.xultimate.remoting.metaq.support;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.danielli.xultimate.core.serializer.Deserializer;
import org.danielli.xultimate.core.serializer.Serializer;
import org.danielli.xultimate.util.io.IOUtils;

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
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			serializer.serialize(body, outputStream);
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
		return outputStream.toByteArray();
	}

	@Override
	public Object fromByteArray(byte[] bs) throws MetaClientException {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bs);
		try {
			return deserializer.deserialize(inputStream, Object.class);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
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
