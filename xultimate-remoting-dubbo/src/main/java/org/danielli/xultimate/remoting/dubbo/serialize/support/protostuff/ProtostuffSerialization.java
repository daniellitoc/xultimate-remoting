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
	
	protected int compressionThreshold = 512;
	
	public byte getContentTypeId() {
        return 10;
    }

    public String getContentType() {
        return "x-application/protostuff";
    }

	@Override
	public com.alibaba.dubbo.common.serialize.ObjectOutput serialize(URL url, OutputStream output) throws IOException {
		return new ObjectOutput(new RpcProtostuffObjectOutput(bufferSize, LinkedBufferUtils.getCurrentLinkedBuffer(bufferSize), ThreadLocalKryoGenerator.INSTANCE.generate()), output, compressionThreshold, compressor);
	}

	@Override
	public com.alibaba.dubbo.common.serialize.ObjectInput deserialize(URL url, InputStream input) throws IOException {
		return new ObjectInput(new RpcProtostuffObjectInput(bufferSize, ThreadLocalKryoGenerator.INSTANCE.generate()), input, decompressor);
	}
}
