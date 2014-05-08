package org.danielli.xultimate.remoting.dubbo.serialize.support.kryo.copy;

import java.io.IOException;

import com.alibaba.dubbo.common.serialize.ObjectOutput;

/**
 * Kryo对象序列化。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class KryoObjectOutput implements ObjectOutput {

	private org.danielli.xultimate.core.serializer.kryo.KryoObjectOutput kryoObjectOutput;
	
	public KryoObjectOutput(org.danielli.xultimate.core.serializer.kryo.KryoObjectOutput kryoObjectOutput) {
		this.kryoObjectOutput = kryoObjectOutput;
	}
	
	@Override
	public void writeBool(boolean v) throws IOException {
		kryoObjectOutput.writeBoolean(v);
	}

	@Override
	public void writeByte(byte v) throws IOException {
		kryoObjectOutput.writeByte(v);
	}

	@Override
	public void writeShort(short v) throws IOException {
		kryoObjectOutput.writeShort(v);
	}

	@Override
	public void writeInt(int v) throws IOException {
		kryoObjectOutput.writeInt(v, true);
	}

	@Override
	public void writeLong(long v) throws IOException {
		kryoObjectOutput.writeLong(v, true);
	}

	@Override
	public void writeFloat(float v) throws IOException {
		kryoObjectOutput.writeFloat(v);
	}

	@Override
	public void writeDouble(double v) throws IOException {
		kryoObjectOutput.writeDouble(v);
	}

	@Override
	public void writeUTF(String v) throws IOException {
		kryoObjectOutput.writeString(v);
	}

	@Override
	public void writeBytes(byte[] v) throws IOException {
		if (v == null) {
			kryoObjectOutput.writeInt(-1);
		} else {
			kryoObjectOutput.writeBytes(v);
		}
	}

	@Override
	public void writeBytes(byte[] v, int off, int len) throws IOException {
		if (v == null) {
			kryoObjectOutput.writeInt(-1);
		} else {
			kryoObjectOutput.writeBytes(v, off, len);
		}
	}

	@Override
	public void flushBuffer() throws IOException {
		kryoObjectOutput.flush();
	}

	@Override
	public void writeObject(Object obj) throws IOException {
		kryoObjectOutput.writeObject(obj);
	}
}
