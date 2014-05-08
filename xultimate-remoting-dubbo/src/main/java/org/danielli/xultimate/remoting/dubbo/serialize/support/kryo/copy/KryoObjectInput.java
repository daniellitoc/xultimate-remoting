package org.danielli.xultimate.remoting.dubbo.serialize.support.kryo.copy;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.dubbo.common.serialize.ObjectInput;

/**
 * Kryo对象解序列化。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class KryoObjectInput implements ObjectInput {

	private org.danielli.xultimate.core.serializer.kryo.KryoObjectInput kryoObjectInput;
	
	public KryoObjectInput(org.danielli.xultimate.core.serializer.kryo.KryoObjectInput kryoObjectInput) {
		this.kryoObjectInput = kryoObjectInput;
	}
	
	@Override
	public boolean readBool() throws IOException {
		return kryoObjectInput.readBoolean();
	}

	@Override
	public byte readByte() throws IOException {
		return kryoObjectInput.readByte();
	}

	@Override
	public short readShort() throws IOException {
		return kryoObjectInput.readShort();
	}

	@Override
	public int readInt() throws IOException {
		return kryoObjectInput.readInt(true);
	}

	@Override
	public long readLong() throws IOException {
		return kryoObjectInput.readLong(true);
	}

	@Override
	public float readFloat() throws IOException {
		return kryoObjectInput.readFloat();
	}

	@Override
	public double readDouble() throws IOException {
		return kryoObjectInput.readDouble();
	}

	@Override
	public String readUTF() throws IOException {
		return kryoObjectInput.readString();
	}

	@Override
	public byte[] readBytes() throws IOException {
		int length = kryoObjectInput.readInt();
		if (length == -1) {
			return null;
		} else if (length == 0) {
			return new byte[0];
		} else {
			return kryoObjectInput.readBytes(length);
		}
	}

	@Override
	public Object readObject() throws IOException, ClassNotFoundException {
		return kryoObjectInput.readObject();
	}

	@Override
	public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
		return kryoObjectInput.readObject(cls);
	}

	@Override
	public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
		return kryoObjectInput.readObject(cls);
	}
}
