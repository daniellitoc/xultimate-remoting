package org.danielli.xultimate.remoting.dubbo.serialize.support;

import java.io.IOException;
import java.io.OutputStream;

import org.danielli.xultimate.core.io.AbstractObjectOutput;

/**
 * 对象输出流。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class ObjectOutput implements com.alibaba.dubbo.common.serialize.ObjectOutput {

	protected AbstractObjectOutput objectOutput;
	
	public ObjectOutput(AbstractObjectOutput objectOutput) {
		this.objectOutput = objectOutput;
	}

	@Override
	public void writeBool(boolean v) throws IOException {
		objectOutput.writeBoolean(v);
	}

	@Override
	public void writeByte(byte v) throws IOException {
		objectOutput.writeByte(v);
	}

	@Override
	public void writeShort(short v) throws IOException {
		objectOutput.writeShort(v);
	}

	@Override
	public void writeInt(int v) throws IOException {
		objectOutput.writeInt(v, true);
	}

	@Override
	public void writeLong(long v) throws IOException {
		objectOutput.writeLong(v, true);
	}

	@Override
	public void writeFloat(float v) throws IOException {
		objectOutput.writeFloat(v);
	}

	@Override
	public void writeDouble(double v) throws IOException {
		objectOutput.writeDouble(v);
	}

	@Override
	public void writeUTF(String v) throws IOException {
		objectOutput.writeString(v);
	}

	@Override
	public void writeBytes(byte[] v) throws IOException {
        if (v == null) {
        	objectOutput.writeInt(-1, true);
        } else {
            writeBytes(v, 0, v.length);
        }
	}

	@Override
	public void writeBytes(byte[] v, int off, int len) throws IOException {
        if (v == null) {
        	objectOutput.writeInt(-1, true);
        } else {
        	objectOutput.writeInt(len, true);
        	objectOutput.writeBytes(v, off, len);
        }
	}

	@Override
	public void flushBuffer() throws IOException {
		objectOutput.flush();
		OutputStream outputStream = objectOutput.getOutputStream();
		if (outputStream != null) {
			outputStream.flush();
		}
	}

	@Override
	public void writeObject(Object obj) throws IOException {
		objectOutput.writeObject(obj);
	}
}
