package org.danielli.xultimate.remoting.dubbo.serialize.support;

import java.io.BufferedInputStream;
import java.io.IOException;

import org.danielli.xultimate.remoting.SerializerTotal;

import com.alibaba.dubbo.common.serialize.DataInput;

/**
 * Java抽象数据解序列化。。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public abstract class JavaAbstractDataInput implements DataInput {

	private BufferedInputStream inputStream;
	
	public JavaAbstractDataInput(BufferedInputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	@Override
	public boolean readBool() throws IOException {
		return SerializerTotal.booleanSerializer.deserialize(inputStream, Boolean.class);
	}

	@Override
	public byte readByte() throws IOException {
		return SerializerTotal.byteSerializer.deserialize(inputStream, Byte.class);
	}

	@Override
	public short readShort() throws IOException {
		return SerializerTotal.shortSerializer.deserialize(inputStream, Short.class);
	}

	@Override
	public int readInt() throws IOException {
		return SerializerTotal.integerSerializer.deserialize(inputStream, Integer.class);
	}

	@Override
	public long readLong() throws IOException {
		return SerializerTotal.longSerializer.deserialize(inputStream, Long.class);
	}

	@Override
	public float readFloat() throws IOException {
		return SerializerTotal.floatSerializer.deserialize(inputStream, Float.class);
	}

	@Override
	public double readDouble() throws IOException {
		return SerializerTotal.doubleSerializer.deserialize(inputStream, Double.class);
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
		return SerializerTotal.stringSerializer.deserialize(inputStream, String.class);
	}
}
