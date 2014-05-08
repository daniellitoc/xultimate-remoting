package org.danielli.xultimate.remoting.dubbo.serialize.support.protobuf.copy;

import java.io.IOException;

import com.alibaba.dubbo.common.serialize.ObjectOutput;

/**
 * Protobuf对象序列化。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class ProtobufObjectOutput implements ObjectOutput {

	private org.danielli.xultimate.core.serializer.protostuff.ProtobufObjectOutput protobufObjectOutput;
	
	public ProtobufObjectOutput(org.danielli.xultimate.core.serializer.protostuff.ProtobufObjectOutput protobufObjectOutput) {
		this.protobufObjectOutput = protobufObjectOutput;
	}
	
	@Override
	public void writeBool(boolean v) throws IOException {
		protobufObjectOutput.writeBoolean(v);
	}

	@Override
	public void writeByte(byte v) throws IOException {
		protobufObjectOutput.writeByte(v);
	}

	@Override
	public void writeShort(short v) throws IOException {
		protobufObjectOutput.writeShort(v);
	}

	@Override
	public void writeInt(int v) throws IOException {
		protobufObjectOutput.writeInt(v, true);
	}

	@Override
	public void writeLong(long v) throws IOException {
		protobufObjectOutput.writeLong(v, true);
	}

	@Override
	public void writeFloat(float v) throws IOException {
		protobufObjectOutput.writeFloat(v);
	}

	@Override
	public void writeDouble(double v) throws IOException {
		protobufObjectOutput.writeDouble(v);
	}

	@Override
	public void writeUTF(String v) throws IOException {
		protobufObjectOutput.writeString(v);
	}

	@Override
	public void writeBytes(byte[] v) throws IOException {
		if (v == null) {
			protobufObjectOutput.writeInt(-1);
		} else {
			protobufObjectOutput.writeBytes(v);
		}
	}

	@Override
	public void writeBytes(byte[] v, int off, int len) throws IOException {
		if (v == null) {
			protobufObjectOutput.writeInt(-1);
		} else {
			protobufObjectOutput.writeBytes(v, off, len);
		}
	}

	@Override
	public void flushBuffer() throws IOException {
		protobufObjectOutput.flush();
	}

	@Override
	public void writeObject(Object obj) throws IOException {
		protobufObjectOutput.writeObject(obj);
	}

}
