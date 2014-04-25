package org.danielli.xultimate.remoting.dubbo.serialize.support;

import java.io.BufferedInputStream;
import java.io.IOException;

import org.danielli.xultimate.core.serializer.support.BaseTypeDeserializer;

import com.alibaba.dubbo.common.serialize.DataInput;

/**
 * Java抽象数据解序列化。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public abstract class JavaAbstractDataInput implements DataInput {

	protected BaseTypeDeserializer baseTypeDeserializer;
	
	private BufferedInputStream inputStream;

	public JavaAbstractDataInput(BaseTypeDeserializer baseTypeDeserializer, BufferedInputStream inputStream) {
		this.inputStream = inputStream;
		this.baseTypeDeserializer = baseTypeDeserializer;
	}
	
	@Override
	public boolean readBool() throws IOException {
		return baseTypeDeserializer.deserializeBoolean(inputStream);
	}

	@Override
	public byte readByte() throws IOException {
		return baseTypeDeserializer.deserializeByte(inputStream);
	}

	@Override
	public short readShort() throws IOException {
		return baseTypeDeserializer.deserializeShort(inputStream);
	}

	@Override
	public int readInt() throws IOException {
		return baseTypeDeserializer.deserializeInt(inputStream);
	}

	@Override
	public long readLong() throws IOException {
		return baseTypeDeserializer.deserializeLong(inputStream);
	}

	@Override
	public float readFloat() throws IOException {
		return baseTypeDeserializer.deserializeFloat(inputStream);
	}

	@Override
	public double readDouble() throws IOException {
		return baseTypeDeserializer.deserializeDouble(inputStream);
	}
	
	@Override
	public byte[] readBytes() throws IOException {
		return baseTypeDeserializer.deserializeBytes(inputStream);
	}
	
	@Override
	public String readUTF() throws IOException {
		return baseTypeDeserializer.deserializeString(inputStream);
	}
}
