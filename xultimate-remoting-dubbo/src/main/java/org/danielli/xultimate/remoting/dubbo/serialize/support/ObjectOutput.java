package org.danielli.xultimate.remoting.dubbo.serialize.support;

import java.io.IOException;
import java.io.OutputStream;

import org.danielli.xultimate.core.compression.Compressor;
import org.danielli.xultimate.core.io.AbstractObjectOutput;
import org.danielli.xultimate.core.serializer.java.util.SerializerUtils;

/**
 * 对象输出流。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class ObjectOutput implements com.alibaba.dubbo.common.serialize.ObjectOutput {

	protected AbstractObjectOutput objectOutput;
	
	protected OutputStream outputStream;
	
	protected int compressionThreshold;
	
	protected Compressor<byte[], byte[]> compressor;
	
	public ObjectOutput(AbstractObjectOutput objectOutput, OutputStream outputStream, int compressionThreshold, Compressor<byte[], byte[]> compressor) {
		this.objectOutput = objectOutput;
		this.outputStream = outputStream;
		this.compressionThreshold = compressionThreshold;
		this.compressor = compressor;
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
		try {
			if (objectOutput.position() > compressionThreshold) {
				outputStream.write(SerializerUtils.encodeByte((byte) 1));
				outputStream.write(compressor.compress(objectOutput.toBytes()));
			} else {
				outputStream.write(SerializerUtils.encodeByte((byte) 0));
				outputStream.write(objectOutput.getBuffer(), 0, objectOutput.position());
			}
		} finally {
			objectOutput.close();
			outputStream.flush();
		}
	}

	@Override
	public void writeObject(Object obj) throws IOException {
		objectOutput.writeObject(obj);
	}
}
