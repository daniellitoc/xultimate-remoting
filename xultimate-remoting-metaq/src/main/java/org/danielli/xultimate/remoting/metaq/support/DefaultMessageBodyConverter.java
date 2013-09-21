package org.danielli.xultimate.remoting.metaq.support;

import org.danielli.xultimate.core.serializer.Deserializer;
import org.danielli.xultimate.core.serializer.Serializer;

import com.taobao.metamorphosis.client.extension.spring.MessageBodyConverter;
import com.taobao.metamorphosis.exception.MetaClientException;

public class DefaultMessageBodyConverter implements MessageBodyConverter<Object> {

	private Serializer serializer;
	
	private Deserializer deserializer;
	
	@Override
	public byte[] toByteArray(Object body) throws MetaClientException {
		return serializer.serialize(body);
	}

	@Override
	public Object fromByteArray(byte[] bs) throws MetaClientException {
		return deserializer.deserialize(bs, Object.class);
	}
	
	public void setSerializer(Serializer serializer) {
		this.serializer = serializer;
	}

	public void setDeserializer(Deserializer deserializer) {
		this.deserializer = deserializer;
	}

}
