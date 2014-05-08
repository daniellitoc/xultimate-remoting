package org.danielli.xultimate.remoting.dubbo.serialize.support.java.copy;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.dubbo.common.serialize.ObjectInput;

/**
 * Java对象解序列化。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class JavaObjectInput implements ObjectInput {

	private org.danielli.xultimate.core.serializer.java.JavaObjectInput javaObjectInput;
	
	public JavaObjectInput(org.danielli.xultimate.core.serializer.java.JavaObjectInput javaObjectInput) {
		this.javaObjectInput = javaObjectInput;
	}
	
	@Override
	public boolean readBool() throws IOException {
		return javaObjectInput.readBoolean();
	}

	@Override
	public byte readByte() throws IOException {
		return javaObjectInput.readByte();
	}

	@Override
	public short readShort() throws IOException {
		return javaObjectInput.readShort();
	}

	@Override
	public int readInt() throws IOException {
		return javaObjectInput.readInt(true);
	}

	@Override
	public long readLong() throws IOException {
		return javaObjectInput.readLong(true);
	}

	@Override
	public float readFloat() throws IOException {
		return javaObjectInput.readFloat();
	}

	@Override
	public double readDouble() throws IOException {
		return javaObjectInput.readDouble();
	}

	@Override
	public String readUTF() throws IOException {
		return javaObjectInput.readString();
	}

	@Override
	public byte[] readBytes() throws IOException {
		int length = javaObjectInput.readInt();
		if (length == -1) {
			return null;
		} else if (length == 0) {
			return new byte[0];
		} else {
			return javaObjectInput.readBytes(length);
		}
	}

	@Override
	public Object readObject() throws IOException, ClassNotFoundException {
		return javaObjectInput.readObject();
	}

	@Override
	public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
		return javaObjectInput.readObject(cls);
	}

	@Override
	public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
		return javaObjectInput.readObject(cls);
	}
}
