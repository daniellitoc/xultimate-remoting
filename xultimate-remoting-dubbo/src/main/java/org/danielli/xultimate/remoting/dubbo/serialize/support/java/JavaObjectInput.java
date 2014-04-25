package org.danielli.xultimate.remoting.dubbo.serialize.support.java;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Type;

import org.danielli.xultimate.remoting.SerializerTotal;
import org.danielli.xultimate.remoting.dubbo.serialize.support.JavaAbstractDataInput;

import com.alibaba.dubbo.common.serialize.ObjectInput;

/**
 * Java对象解序列化。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class JavaObjectInput extends JavaAbstractDataInput implements ObjectInput {

	private BufferedInputStream inputStream;
	
	public JavaObjectInput(BufferedInputStream inputStream) throws IOException {
		super(SerializerTotal.OBJECT_BASE_TYPE_DESERIALIZER, inputStream);
		this.inputStream = inputStream;
	}

	@Override
	public Object readObject() throws IOException, ClassNotFoundException {
		return baseTypeDeserializer.deserialize(inputStream, Object.class);
	}

	@Override
	public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
		return baseTypeDeserializer.deserialize(inputStream, cls);
	}

	@Override
	public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
		return baseTypeDeserializer.deserialize(inputStream, cls);
	}
}
