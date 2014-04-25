package org.danielli.xultimate.remoting.dubbo.serialize.support;

import java.io.BufferedOutputStream;
import java.io.IOException;

import org.danielli.xultimate.core.serializer.support.BaseTypeSerializer;

import com.alibaba.dubbo.common.serialize.DataOutput;

/**
 * Java抽象数据序列化。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public abstract class JavaAbstractDataOutput implements DataOutput {

	protected BaseTypeSerializer baseTypeSerializer;
	
	private BufferedOutputStream outputStream;
	
	public JavaAbstractDataOutput(BaseTypeSerializer baseTypeSerializer, BufferedOutputStream outputStream) {
		this.outputStream = outputStream;
		this.baseTypeSerializer = baseTypeSerializer;
	}
	
	@Override
	public void writeBool(boolean v) throws IOException {
		baseTypeSerializer.serializeBoolean(v, outputStream);
	}

	@Override
	public void writeByte(byte v) throws IOException {
		baseTypeSerializer.serializeByte(v, outputStream);
	}

	@Override
	public void writeShort(short v) throws IOException {
		baseTypeSerializer.serializeShort(v, outputStream);
	}

	@Override
	public void writeInt(int v) throws IOException {
		baseTypeSerializer.serializeInt(v, outputStream);
	}

	@Override
	public void writeLong(long v) throws IOException {
		baseTypeSerializer.serializeLong(v, outputStream);
	}

	@Override
	public void writeFloat(float v) throws IOException {
		baseTypeSerializer.serializeFloat(v, outputStream);
	}

	@Override
	public void writeDouble(double v) throws IOException {
		baseTypeSerializer.serializeDouble(v, outputStream);
	}

	@Override
	public void writeBytes(byte[] v) throws IOException {
		baseTypeSerializer.serializeBytes(v, outputStream);
	}

	@Override
	public void writeBytes(byte[] v, int off, int len) throws IOException {
		baseTypeSerializer.serializeBytes(v, off, len, outputStream);
	}
	
	@Override
	public void writeUTF(String v) throws IOException {
		baseTypeSerializer.serializeString(v, outputStream);
	}
}
