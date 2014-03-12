package org.danielli.xultimate.remoting.dubbo.serialize.support.protobuf;

import java.io.BufferedOutputStream;
import java.io.IOException;

import org.danielli.xultimate.remoting.SerializerTotal;
import org.danielli.xultimate.remoting.dubbo.serialize.support.JavaAbstractDataOutput;

import com.alibaba.dubbo.common.serialize.ObjectOutput;

/**
 * Protobuf对象序列化。。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class ProtobufObjectOutput extends JavaAbstractDataOutput implements ObjectOutput {

	private BufferedOutputStream outputStream;
	
	public ProtobufObjectOutput(BufferedOutputStream outputStream) {
		super(outputStream);
		this.outputStream = outputStream;
	}

	@Override
	public void flushBuffer() throws IOException {
		outputStream.flush();
	}

	@Override
	public void writeObject(Object obj) throws IOException {
		if( obj == null ) {
			writeByte((byte) 0);
		} else {
			writeByte((byte) 1);
			SerializerTotal.rpcProtobufSerializer.serialize(obj, outputStream);
		}
	}
}
