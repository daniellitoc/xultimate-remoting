package org.danielli.xultimate.remoting.dubbo.serialize.support.protobuf.copy;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.dubbo.common.serialize.ObjectInput;

/**
 * Protobuf对象解序列化。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class ProtobufObjectInput implements ObjectInput {
	
	private org.danielli.xultimate.core.serializer.protostuff.ProtobufObjectInput protobufObjectInput;
	
	public ProtobufObjectInput(org.danielli.xultimate.core.serializer.protostuff.ProtobufObjectInput protobufObjectInput) {
		this.protobufObjectInput = protobufObjectInput;
	}
	
	@Override
	public boolean readBool() throws IOException {
		return protobufObjectInput.readBoolean();
	}

	@Override
	public byte readByte() throws IOException {
		return protobufObjectInput.readByte();
	}

	@Override
	public short readShort() throws IOException {
		return protobufObjectInput.readShort();
	}

	@Override
	public int readInt() throws IOException {
		return protobufObjectInput.readInt(true);
	}

	@Override
	public long readLong() throws IOException {
		return protobufObjectInput.readLong(true);
	}

	@Override
	public float readFloat() throws IOException {
		return protobufObjectInput.readFloat();
	}

	@Override
	public double readDouble() throws IOException {
		return protobufObjectInput.readDouble();
	}

	@Override
	public String readUTF() throws IOException {
		return protobufObjectInput.readString();
	}

	@Override
	public byte[] readBytes() throws IOException {
		int length = protobufObjectInput.readInt();
		if (length == -1) {
			return null;
		} else if (length == 0) {
			return new byte[0];
		} else {
			return protobufObjectInput.readBytes(length);
		}
	}

	@Override
	public Object readObject() throws IOException, ClassNotFoundException {
		return protobufObjectInput.readObject();
	}

	@Override
	public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
		return protobufObjectInput.readObject(cls);
	}

	@Override
	public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
		return protobufObjectInput.readObject(cls);
	}
}
