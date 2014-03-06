package org.danielli.xultimate.remoting.dubbo.serialize.support.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import org.danielli.xultimate.core.serializer.java.BooleanSerializer;
import org.danielli.xultimate.core.serializer.java.ByteSerializer;
import org.danielli.xultimate.core.serializer.java.DoubleSerializer;
import org.danielli.xultimate.core.serializer.java.FloatSerializer;
import org.danielli.xultimate.core.serializer.java.IntegerSerializer;
import org.danielli.xultimate.core.serializer.java.LongSerializer;
import org.danielli.xultimate.core.serializer.java.ShortSerializer;
import org.danielli.xultimate.core.serializer.java.StringSerializer;
import org.danielli.xultimate.core.serializer.protostuff.RpcProtobufSerializer;
import org.danielli.xultimate.remoting.dubbo.serialize.support.JavaAbstractDataInput;

import com.alibaba.dubbo.common.serialize.ObjectInput;

public class ProtobufObjectInput extends JavaAbstractDataInput implements ObjectInput {

	private RpcProtobufSerializer rpcProtobufSerializer;
	private InputStream inputStream;
	
	public ProtobufObjectInput(InputStream inputStream, 
			BooleanSerializer booleanSerializer, 
			ByteSerializer byteSerializer,
			ShortSerializer shortSerializer,
			IntegerSerializer integerSerializer,
			LongSerializer longSerializerr,
			FloatSerializer floatSerializer,
			DoubleSerializer doubleSerializer,
			StringSerializer stringSerializer,
			RpcProtobufSerializer rpcProtobufSerializer) {
		super(inputStream, booleanSerializer, byteSerializer, shortSerializer, integerSerializer, longSerializerr, floatSerializer, doubleSerializer, stringSerializer);
		
		this.inputStream = inputStream;
		this.rpcProtobufSerializer = rpcProtobufSerializer;
	}

	@Override
	public Object readObject() throws IOException, ClassNotFoundException {
		byte b = readByte();
		if( b == 0 )
			return null;
		return this.rpcProtobufSerializer.deserialize(inputStream, Object.class);
	}

	@Override
	public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
		byte b = readByte();
		if( b == 0 )
			return null;
		return this.rpcProtobufSerializer.deserialize(inputStream, cls);
	}

	@Override
	public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
		
		byte b = readByte();
		if( b == 0 )
			return null;
		return this.rpcProtobufSerializer.deserialize(inputStream, cls);
	}
}
