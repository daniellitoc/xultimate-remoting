package org.danielli.xultimate.remoting.dubbo.serialize.support.java.copy;

import java.io.IOException;

import com.alibaba.dubbo.common.serialize.ObjectOutput;

/**
 * Java对象序列化。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class JavaObjectOutput implements ObjectOutput {

	private org.danielli.xultimate.core.serializer.java.JavaObjectOutput javaObjectOutput;
	
	public JavaObjectOutput(org.danielli.xultimate.core.serializer.java.JavaObjectOutput javaObjectOutput) {
		this.javaObjectOutput = javaObjectOutput;
	}
	
	@Override
	public void writeBool(boolean v) throws IOException {
		javaObjectOutput.writeBoolean(v);
	}

	@Override
	public void writeByte(byte v) throws IOException {
		javaObjectOutput.writeByte(v);
	}

	@Override
	public void writeShort(short v) throws IOException {
		javaObjectOutput.writeShort(v);
	}

	@Override
	public void writeInt(int v) throws IOException {
		javaObjectOutput.writeInt(v, true);
	}

	@Override
	public void writeLong(long v) throws IOException {
		javaObjectOutput.writeLong(v, true);
	}

	@Override
	public void writeFloat(float v) throws IOException {
		javaObjectOutput.writeFloat(v);
	}

	@Override
	public void writeDouble(double v) throws IOException {
		javaObjectOutput.writeDouble(v);
	}

	@Override
	public void writeUTF(String v) throws IOException {
		javaObjectOutput.writeString(v);
	}

	@Override
	public void writeBytes(byte[] v) throws IOException {
		if (v == null) {
			javaObjectOutput.writeInt(-1);
		} else {
			javaObjectOutput.writeBytes(v);
		}
	}

	@Override
	public void writeBytes(byte[] v, int off, int len) throws IOException {
		if (v == null) {
			javaObjectOutput.writeInt(-1);
		} else {
			javaObjectOutput.writeBytes(v, off, len);
		}
	}

	@Override
	public void flushBuffer() throws IOException {
		javaObjectOutput.flush();
	}

	@Override
	public void writeObject(Object obj) throws IOException {
		javaObjectOutput.writeObject(obj);
	}

	
}
