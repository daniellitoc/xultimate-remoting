package org.danielli.xultimate.remoting.dubbo.serialize.support.kryo;

import java.io.IOException;
import java.io.OutputStream;

import org.danielli.xultimate.core.serializer.java.BooleanSerializer;
import org.danielli.xultimate.core.serializer.java.ByteSerializer;
import org.danielli.xultimate.core.serializer.java.DoubleSerializer;
import org.danielli.xultimate.core.serializer.java.FloatSerializer;
import org.danielli.xultimate.core.serializer.java.IntegerSerializer;
import org.danielli.xultimate.core.serializer.java.LongSerializer;
import org.danielli.xultimate.core.serializer.java.ShortSerializer;
import org.danielli.xultimate.core.serializer.java.StringSerializer;
import org.danielli.xultimate.core.serializer.kryo.RpcKryoSerializer;
import org.danielli.xultimate.remoting.dubbo.serialize.support.JavaAbstractDataOutput;

import com.alibaba.dubbo.common.serialize.ObjectOutput;

/**
 * Kryo对象序列化。。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class KryoObjectOutput extends JavaAbstractDataOutput implements ObjectOutput {

	private RpcKryoSerializer rpcKryoSerializer;
	private OutputStream outputStream;
	
	public KryoObjectOutput(OutputStream outputStream, 
			BooleanSerializer booleanSerializer, 
			ByteSerializer byteSerializer,
			ShortSerializer shortSerializer,
			IntegerSerializer integerSerializer,
			LongSerializer longSerializerr,
			FloatSerializer floatSerializer,
			DoubleSerializer doubleSerializer,
			StringSerializer stringSerializer,
			RpcKryoSerializer rpcKryoSerializer) throws IOException {
		super(outputStream, booleanSerializer, byteSerializer, shortSerializer, integerSerializer, longSerializerr, floatSerializer, doubleSerializer, stringSerializer);
		
		this.outputStream = outputStream;
		this.rpcKryoSerializer = rpcKryoSerializer;
	}

	@Override
	public void flushBuffer() throws IOException {
		outputStream.flush();
	}

	@Override
	public void writeObject(Object obj) throws IOException {
		if( obj == null ) {
			writeByte((byte) 0);
		} else {
			writeByte((byte) 1);
			rpcKryoSerializer.serialize(obj, outputStream);
		}
	}
}
