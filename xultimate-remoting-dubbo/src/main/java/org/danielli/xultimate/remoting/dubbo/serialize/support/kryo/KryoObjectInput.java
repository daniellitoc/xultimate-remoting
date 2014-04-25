package org.danielli.xultimate.remoting.dubbo.serialize.support.kryo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Type;

import org.danielli.xultimate.remoting.SerializerTotal;
import org.danielli.xultimate.remoting.dubbo.serialize.support.JavaAbstractDataInput;

import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.esotericsoftware.kryo.io.Input;

/**
 * Kryo对象解序列化。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class KryoObjectInput extends JavaAbstractDataInput implements ObjectInput {

	private Input input;
	
	public KryoObjectInput(BufferedInputStream inputStream) throws IOException {
		super(SerializerTotal.KRYO_BASE_TYPE_DESERIALIZER, inputStream);
		this.input = new Input(inputStream);
	}

	@Override
	public Object readObject() throws IOException, ClassNotFoundException {
		return baseTypeDeserializer.deserialize(input, Object.class);
	}

	@Override
	public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
		return baseTypeDeserializer.deserialize(input, cls);
	}

	@Override
	public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
		return baseTypeDeserializer.deserialize(input, cls);
	}
}
