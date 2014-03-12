package org.danielli.xultimate.remoting.dubbo.serialize.support;

import java.io.BufferedOutputStream;
import java.io.IOException;

import org.danielli.xultimate.remoting.SerializerTotal;

import com.alibaba.dubbo.common.serialize.DataOutput;

/**
 * Java抽象数据序列化。。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public abstract class JavaAbstractDataOutput implements DataOutput {

	private BufferedOutputStream outputStream;
	
	public JavaAbstractDataOutput(BufferedOutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	@Override
	public void writeBool(boolean v) throws IOException {
		SerializerTotal.booleanSerializer.serialize(v, outputStream);
	}

	@Override
	public void writeByte(byte v) throws IOException {
		SerializerTotal.byteSerializer.serialize(v, outputStream);
	}

	@Override
	public void writeShort(short v) throws IOException {
		SerializerTotal.shortSerializer.serialize(v, outputStream);
	}

	@Override
	public void writeInt(int v) throws IOException {
		SerializerTotal.integerSerializer.serialize(v, outputStream);
	}

	@Override
	public void writeLong(long v) throws IOException {
		SerializerTotal.longSerializer.serialize(v, outputStream);
	}

	@Override
	public void writeFloat(float v) throws IOException {
		SerializerTotal.floatSerializer.serialize(v, outputStream);
	}

	@Override
	public void writeDouble(double v) throws IOException {
		SerializerTotal.doubleSerializer.serialize(v, outputStream);
	}

	@Override
	public void writeBytes(byte[] v) throws IOException {
        if (v == null) {
            writeInt(-1);
        } else {
            writeBytes(v, 0, v.length);
        }
	}

	@Override
	public void writeBytes(byte[] v, int off, int len) throws IOException {
        if (v == null) {
            writeInt(-1);
        } else {
            writeInt(len);
            outputStream.write(v, off, len);
        }
	}
	
	@Override
	public void writeUTF(String v) throws IOException {
		if( v == null ) {
			writeInt(-1);
		} else {
			writeInt(v.length());
			SerializerTotal.stringSerializer.serialize(v, outputStream);
		}
	}
}
