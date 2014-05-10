package org.danielli.xultimate.remoting.dubbo.serialize.support.protostuff;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.danielli.xultimate.core.compression.Compressor;
import org.danielli.xultimate.core.compression.Decompressor;
import org.danielli.xultimate.core.compression.support.SnappyJavaCompressor;
import org.danielli.xultimate.core.io.support.RpcProtostuffObjectInput;
import org.danielli.xultimate.core.io.support.RpcProtostuffObjectOutput;
import org.danielli.xultimate.core.serializer.kryo.support.ThreadLocalKryoGenerator;
import org.danielli.xultimate.core.serializer.protostuff.util.LinkedBufferUtils;
import org.danielli.xultimate.remoting.dubbo.serialize.support.ObjectInput;
import org.danielli.xultimate.remoting.dubbo.serialize.support.ObjectOutput;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.Serialization;

/**
 * Protostuff序列化器。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class ProtostuffSerialization implements Serialization {
	
	protected int bufferSize = 256;
	
	protected Compressor<byte[], byte[]> compressor = SnappyJavaCompressor.COMPRESSOR;
	
	protected Decompressor<byte[], byte[]> decompressor = SnappyJavaCompressor.COMPRESSOR;
	
	public byte getContentTypeId() {
        return 10;
    }

    public String getContentType() {
        return "x-application/protostuff";
    }

	@Override
	public com.alibaba.dubbo.common.serialize.ObjectOutput serialize(URL url, OutputStream output) throws IOException {
		return new ObjectOutput(new RpcProtostuffObjectOutput(compressor.wrapper(output), bufferSize, LinkedBufferUtils.getCurrentLinkedBuffer(bufferSize), ThreadLocalKryoGenerator.INSTANCE.generate()));
	}

	@Override
	public com.alibaba.dubbo.common.serialize.ObjectInput deserialize(URL url, InputStream input) throws IOException {
		return new ObjectInput(new RpcProtostuffObjectInput(decompressor.wrapper(input), bufferSize, LinkedBufferUtils.getCurrentLinkedBuffer(bufferSize), ThreadLocalKryoGenerator.INSTANCE.generate()));
	}
}
