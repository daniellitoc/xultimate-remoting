package org.danielli.xultimate.remoting.dubbo.serialize.support;

import java.io.IOException;
import java.io.InputStream;

import org.danielli.xultimate.core.serializer.java.BooleanSerializer;
import org.danielli.xultimate.core.serializer.java.ByteSerializer;
import org.danielli.xultimate.core.serializer.java.DoubleSerializer;
import org.danielli.xultimate.core.serializer.java.FloatSerializer;
import org.danielli.xultimate.core.serializer.java.IntegerSerializer;
import org.danielli.xultimate.core.serializer.java.LongSerializer;
import org.danielli.xultimate.core.serializer.java.ShortSerializer;
import org.danielli.xultimate.core.serializer.java.StringSerializer;

import com.alibaba.dubbo.common.serialize.DataInput;

/**
 * Java抽象数据解序列化。。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public abstract class JavaAbstractDataInput implements DataInput {

	private InputStream inputStream;
	
	private BooleanSerializer booleanSerializer;
	
	private ByteSerializer byteSerializer;
	
	private ShortSerializer shortSerializer;
	
	private IntegerSerializer integerSerializer;
	
	private LongSerializer longSerializer;
	
	private FloatSerializer floatSerializer;
	
	private DoubleSerializer doubleSerializer;
	
	private StringSerializer stringSerializer;
	
	public JavaAbstractDataInput(InputStream inputStream, 
			BooleanSerializer booleanSerializer, 
			ByteSerializer byteSerializer,
			ShortSerializer shortSerializer,
			IntegerSerializer integerSerializer,
			LongSerializer longSerializerr,
			FloatSerializer floatSerializer,
			DoubleSerializer doubleSerializer,
			StringSerializer stringSerializer) {
		this.inputStream = inputStream;
		this.booleanSerializer = booleanSerializer;
		this.byteSerializer = byteSerializer;
		this.shortSerializer = shortSerializer;
		this.integerSerializer = integerSerializer;
		this.longSerializer = longSerializerr;
		this.floatSerializer = floatSerializer;
		this.doubleSerializer = doubleSerializer;
		this.stringSerializer = stringSerializer;
	}
	
	@Override
	public boolean readBool() throws IOException {
		return booleanSerializer.deserialize(inputStream, Boolean.class);
	}

	@Override
	public byte readByte() throws IOException {
		return byteSerializer.deserialize(inputStream, Byte.class);
	}

	@Override
	public short readShort() throws IOException {
		return shortSerializer.deserialize(inputStream, Short.class);
	}

	@Override
	public int readInt() throws IOException {
		return integerSerializer.deserialize(inputStream, Integer.class);
	}

	@Override
	public long readLong() throws IOException {
		return longSerializer.deserialize(inputStream, Long.class);
	}

	@Override
	public float readFloat() throws IOException {
		return floatSerializer.deserialize(inputStream, Float.class);
	}

	@Override
	public double readDouble() throws IOException {
		return doubleSerializer.deserialize(inputStream, Double.class);
	}
	
	@Override
	public byte[] readBytes() throws IOException {
        int len = readInt();
		if( len < 0 )
			return null;
		if( len == 0 )
			return new byte[0];

		byte[] b = new byte[len];
		inputStream.read(b);
		return b;
	}
	
	@Override
	public String readUTF() throws IOException {
		int len = readInt();
		if( len < 0 )
			return null;
		return stringSerializer.deserialize(inputStream, String.class);
	}
}
