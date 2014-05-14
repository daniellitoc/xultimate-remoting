package org.danielli.xultimate.remoting.dubbo.serialize.support.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.danielli.xultimate.core.compression.Compressor;
import org.danielli.xultimate.core.compression.Decompressor;
import org.danielli.xultimate.core.compression.support.SnappyJavaCompressor;
import org.danielli.xultimate.core.io.support.RpcProtobufObjectInput;
import org.danielli.xultimate.core.io.support.RpcProtobufObjectOutput;
import org.danielli.xultimate.core.serializer.kryo.support.ThreadLocalKryoGenerator;
import org.danielli.xultimate.core.serializer.protostuff.util.LinkedBufferUtils;
import org.danielli.xultimate.remoting.dubbo.serialize.support.ObjectInput;
import org.danielli.xultimate.remoting.dubbo.serialize.support.ObjectOutput;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.Serialization;

/**
 * Protobuf序列化器。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class ProtobufSerialization implements Serialization {
	
	protected int bufferSize = 256;
	
	protected Compressor<byte[], byte[]> compressor = SnappyJavaCompressor.COMPRESSOR;
	
	protected Decompressor<byte[], byte[]> decompressor = SnappyJavaCompressor.COMPRESSOR;
	
	protected int compressionThreshold = 512;
	
	public byte getContentTypeId() {
        return 11;
    }

    public String getContentType() {
        return "x-application/protobuf";
    }

	@Override
	public com.alibaba.dubbo.common.serialize.ObjectOutput serialize(URL url, OutputStream output) throws IOException {
		return new ObjectOutput(new RpcProtobufObjectOutput(bufferSize, LinkedBufferUtils.getCurrentLinkedBuffer(bufferSize), ThreadLocalKryoGenerator.INSTANCE.generate()), output, compressionThreshold, compressor);
	}

	@Override
	public com.alibaba.dubbo.common.serialize.ObjectInput deserialize(URL url, InputStream input) throws IOException {
		return new ObjectInput(new RpcProtobufObjectInput(bufferSize, ThreadLocalKryoGenerator.INSTANCE.generate()), input, decompressor);
	}
}
