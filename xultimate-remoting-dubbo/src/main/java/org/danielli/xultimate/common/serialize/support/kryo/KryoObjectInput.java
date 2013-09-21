package org.danielli.xultimate.common.serialize.support.kryo;

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
import org.danielli.xultimate.core.serializer.kryo.RpcKryoSerializer;

import com.alibaba.dubbo.common.serialize.ObjectInput;

public class KryoObjectInput extends JavaAbstractDataInput implements ObjectInput {

	private RpcKryoSerializer rpcKryoSerializer;
	private InputStream inputStream;
	
	public KryoObjectInput(InputStream inputStream, 
			BooleanSerializer booleanSerializer, 
			ByteSerializer byteSerializer,
			ShortSerializer shortSerializer,
			IntegerSerializer integerSerializer,
			LongSerializer longSerializerr,
			FloatSerializer floatSerializer,
			DoubleSerializer doubleSerializer,
			StringSerializer stringSerializer,
			RpcKryoSerializer rpcKryoSerializer) throws IOException {
		super(inputStream, booleanSerializer, byteSerializer, shortSerializer, integerSerializer, longSerializerr, floatSerializer, doubleSerializer, stringSerializer);
		
		this.inputStream = inputStream;
		this.rpcKryoSerializer = rpcKryoSerializer;
	}

	@Override
	public Object readObject() throws IOException, ClassNotFoundException {
		byte b = readByte();
		if( b == 0 )
			return null;
		return this.rpcKryoSerializer.deserialize(inputStream, Object.class);
	}

	@Override
	public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
		byte b = readByte();
		if( b == 0 )
			return null;
		return this.rpcKryoSerializer.deserialize(inputStream, cls);
	}

	@Override
	public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
		byte b = readByte();
		if( b == 0 )
			return null;
		return this.rpcKryoSerializer.deserialize(inputStream, cls);
	}
}
