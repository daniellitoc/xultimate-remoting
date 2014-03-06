package org.danielli.xultimate.remoting.dubbo.serialize.support;

import java.io.IOException;
import java.io.OutputStream;

import org.danielli.xultimate.core.serializer.java.BooleanSerializer;
import org.danielli.xultimate.core.serializer.java.ByteSerializer;
import org.danielli.xultimate.core.serializer.java.DoubleSerializer;
import org.danielli.xultimate.core.serializer.java.FloatSerializer;
import org.danielli.xultimate.core.serializer.java.IntegerSerializer;
import org.danielli.xultimate.core.serializer.java.LongSerializer;
import org.danielli.xultimate.core.serializer.java.ShortSerializer;
import org.danielli.xultimate.core.serializer.java.StringSerializer;

import com.alibaba.dubbo.common.serialize.DataOutput;

/**
 * Java抽象数据序列化。。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public abstract class JavaAbstractDataOutput implements DataOutput {

	private OutputStream outputStream;
	
	private BooleanSerializer booleanSerializer;
	
	private ByteSerializer byteSerializer;
	
	private ShortSerializer shortSerializer;
	
	private IntegerSerializer integerSerializer;
	
	private LongSerializer longSerializer;
	
	private FloatSerializer floatSerializer;
	
	private DoubleSerializer doubleSerializer;
	
	private StringSerializer stringSerializer;
	
	public JavaAbstractDataOutput(OutputStream outputStream, 
			BooleanSerializer booleanSerializer, 
			ByteSerializer byteSerializer,
			ShortSerializer shortSerializer,
			IntegerSerializer integerSerializer,
			LongSerializer longSerializerr,
			FloatSerializer floatSerializer,
			DoubleSerializer doubleSerializer,
			StringSerializer stringSerializer) {
		this.outputStream = outputStream;
		this.booleanSerializer = booleanSerializer;
		this.byteSerializer = byteSerializer;
		this.shortSerializer = shortSerializer;
		this.integerSerializer = integerSerializer;
		this.longSerializer = longSerializerr;
		this.floatSerializer = floatSerializer;
		this.doubleSerializer = doubleSerializer;
		this.stringSerializer = stringSerializer;
	}
	
	@Override
	public void writeBool(boolean v) throws IOException {
		booleanSerializer.serialize(v, outputStream);
	}

	@Override
	public void writeByte(byte v) throws IOException {
		byteSerializer.serialize(v, outputStream);
	}

	@Override
	public void writeShort(short v) throws IOException {
		shortSerializer.serialize(v, outputStream);
	}

	@Override
	public void writeInt(int v) throws IOException {
		integerSerializer.serialize(v, outputStream);
	}

	@Override
	public void writeLong(long v) throws IOException {
		longSerializer.serialize(v, outputStream);
	}

	@Override
	public void writeFloat(float v) throws IOException {
		floatSerializer.serialize(v, outputStream);
	}

	@Override
	public void writeDouble(double v) throws IOException {
		doubleSerializer.serialize(v, outputStream);
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
			stringSerializer.serialize(v, outputStream);
		}
	}
}
