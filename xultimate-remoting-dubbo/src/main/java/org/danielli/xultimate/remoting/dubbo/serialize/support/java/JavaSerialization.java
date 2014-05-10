package org.danielli.xultimate.remoting.dubbo.serialize.support.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.danielli.xultimate.core.compression.Compressor;
import org.danielli.xultimate.core.compression.Decompressor;
import org.danielli.xultimate.core.compression.support.SnappyJavaCompressor;
import org.danielli.xultimate.core.io.support.JavaObjectInput;
import org.danielli.xultimate.core.io.support.JavaObjectOutput;
import org.danielli.xultimate.remoting.dubbo.serialize.support.ObjectInput;
import org.danielli.xultimate.remoting.dubbo.serialize.support.ObjectOutput;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.Serialization;

/**
 * Java序列化器。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class JavaSerialization implements Serialization {

	protected int bufferSize = 256;
	
	protected Compressor<byte[], byte[]> compressor = SnappyJavaCompressor.COMPRESSOR;
	
	protected Decompressor<byte[], byte[]> decompressor = SnappyJavaCompressor.COMPRESSOR;
	
	@Override
	public byte getContentTypeId() {
		return 13;
	}

	@Override
	public String getContentType() {
		return "x-application/java";
	}

	@Override
	public com.alibaba.dubbo.common.serialize.ObjectOutput serialize(URL url, OutputStream output) throws IOException {
		return new ObjectOutput(new JavaObjectOutput(compressor.wrapper(output), bufferSize));
	}

	@Override
	public com.alibaba.dubbo.common.serialize.ObjectInput deserialize(URL url, InputStream input) throws IOException {
		return new ObjectInput(new JavaObjectInput(decompressor.wrapper(input), bufferSize));
	}
}
