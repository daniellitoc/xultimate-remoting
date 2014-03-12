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
		super(inputStream);
		this.input = new Input(inputStream);
	}

	@Override
	public Object readObject() throws IOException, ClassNotFoundException {
		byte b = readByte();
		if( b == 0 )
			return null;
		return SerializerTotal.rpcKryoSerializer.deserialize(input, Object.class);
	}

	@Override
	public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
		byte b = readByte();
		if( b == 0 )
			return null;
		return SerializerTotal.rpcKryoSerializer.deserialize(input, cls);
	}

	@Override
	public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
		byte b = readByte();
		if( b == 0 )
			return null;
		return SerializerTotal.rpcKryoSerializer.deserialize(input, cls);
	}
}
