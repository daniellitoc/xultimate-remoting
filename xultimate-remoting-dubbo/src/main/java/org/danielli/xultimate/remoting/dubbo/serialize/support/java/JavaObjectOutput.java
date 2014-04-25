package org.danielli.xultimate.remoting.dubbo.serialize.support.java;

import java.io.BufferedOutputStream;
import java.io.IOException;

import org.danielli.xultimate.remoting.SerializerTotal;
import org.danielli.xultimate.remoting.dubbo.serialize.support.JavaAbstractDataOutput;

import com.alibaba.dubbo.common.serialize.ObjectOutput;

/**
 * Java对象序列化。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class JavaObjectOutput extends JavaAbstractDataOutput implements ObjectOutput {

	private BufferedOutputStream outputStream;
	
	public JavaObjectOutput(BufferedOutputStream outputStream) throws IOException {
		super(SerializerTotal.OBJECT_BASE_TYPE_SERIALIZER, outputStream);
		this.outputStream = outputStream;
	}

	@Override
	public void flushBuffer() throws IOException {
		outputStream.flush();
	}

	@Override
	public void writeObject(Object obj) throws IOException {
		baseTypeSerializer.serialize(obj, outputStream);
	}
}
