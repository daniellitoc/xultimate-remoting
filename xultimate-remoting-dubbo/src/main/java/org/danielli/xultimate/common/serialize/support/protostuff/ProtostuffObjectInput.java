package org.danielli.xultimate.common.serialize.support.protostuff;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import org.danielli.xultimate.common.serialize.support.JavaAbstractDataInput;
import org.danielli.xultimate.core.serializer.java.BooleanSerializer;
import org.danielli.xultimate.core.serializer.java.ByteSerializer;
import org.danielli.xultimate.core.serializer.java.DoubleSerializer;
import org.danielli.xultimate.core.serializer.java.FloatSerializer;
import org.danielli.xultimate.core.serializer.java.IntegerSerializer;
import org.danielli.xultimate.core.serializer.java.LongSerializer;
import org.danielli.xultimate.core.serializer.java.ShortSerializer;
import org.danielli.xultimate.core.serializer.java.StringSerializer;
import org.danielli.xultimate.core.serializer.protostuff.RpcProtostuffSerializer;

import com.alibaba.dubbo.common.serialize.ObjectInput;

public class ProtostuffObjectInput extends JavaAbstractDataInput implements ObjectInput {
	
	private RpcProtostuffSerializer rpcProtostuffSerializer;
	private InputStream inputStream;
	
	public ProtostuffObjectInput(InputStream inputStream, 
			BooleanSerializer booleanSerializer, 
			ByteSerializer byteSerializer,
			ShortSerializer shortSerializer,
			IntegerSerializer integerSerializer,
			LongSerializer longSerializerr,
			FloatSerializer floatSerializer,
			DoubleSerializer doubleSerializer,
			StringSerializer stringSerializer,
			RpcProtostuffSerializer rpcProtostuffSerializer) {
		super(inputStream, booleanSerializer, byteSerializer, shortSerializer, integerSerializer, longSerializerr, floatSerializer, doubleSerializer, stringSerializer);
		
		this.inputStream = inputStream;
		this.rpcProtostuffSerializer = rpcProtostuffSerializer;
	}
	
	@Override
	public Object readObject() throws IOException, ClassNotFoundException {
		byte b = readByte();
		if( b == 0 )
			return null;
		return this.rpcProtostuffSerializer.deserialize(inputStream, Object.class);
	}

	@Override
	public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
		byte b = readByte();
		if( b == 0 )
			return null;
		return this.rpcProtostuffSerializer.deserialize(inputStream, cls);
	}

	@Override
	public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
		
		byte b = readByte();
		if( b == 0 )
			return null;
		return this.rpcProtostuffSerializer.deserialize(inputStream, cls);
	}
}
