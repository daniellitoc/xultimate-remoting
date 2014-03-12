package org.danielli.xultimate.remoting.dubbo.serialize.support.protostuff;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Type;

import org.danielli.xultimate.remoting.SerializerTotal;
import org.danielli.xultimate.remoting.dubbo.serialize.support.JavaAbstractDataInput;

import com.alibaba.dubbo.common.serialize.ObjectInput;

/**
 * Protostuff对象序列化。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class ProtostuffObjectInput extends JavaAbstractDataInput implements ObjectInput {
	
	private BufferedInputStream inputStream;
	
	public ProtostuffObjectInput(BufferedInputStream inputStream) {
		super(inputStream);
		this.inputStream = inputStream;
	}
	
	@Override
	public Object readObject() throws IOException, ClassNotFoundException {
		byte b = readByte();
		if( b == 0 )
			return null;
		return SerializerTotal.rpcProtostuffSerializer.deserialize(inputStream, Object.class);
	}

	@Override
	public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
		byte b = readByte();
		if( b == 0 )
			return null;
		return SerializerTotal.rpcProtostuffSerializer.deserialize(inputStream, cls);
	}

	@Override
	public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
		byte b = readByte();
		if( b == 0 )
			return null;
		return SerializerTotal.rpcProtostuffSerializer.deserialize(inputStream, cls);
	}
}
