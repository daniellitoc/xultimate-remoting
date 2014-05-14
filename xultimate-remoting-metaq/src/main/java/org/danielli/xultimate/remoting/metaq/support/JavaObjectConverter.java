package org.danielli.xultimate.remoting.metaq.support;

import java.io.IOException;

import org.danielli.xultimate.core.compression.Compressor;
import org.danielli.xultimate.core.compression.Decompressor;
import org.danielli.xultimate.core.compression.support.NullCompressor;
import org.danielli.xultimate.core.io.support.JavaObjectInput;
import org.danielli.xultimate.core.io.support.JavaObjectOutput;

import com.taobao.metamorphosis.client.extension.spring.MessageBodyConverter;
import com.taobao.metamorphosis.exception.MetaClientException;

/**
 * 通过{@code JavaObjectInput}和{@code JavaObjectOutput}提供的功能完成序列化/解序列化支持。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class JavaObjectConverter implements MessageBodyConverter<Object> {
	
	protected Compressor<byte[], byte[]> compressor = NullCompressor.COMPRESSOR;
	
	protected Decompressor<byte[], byte[]> decompressor = NullCompressor.COMPRESSOR;
	
	protected int bufferSize = 256;
	
	protected int compressionThreshold = 512;
	
	public JavaObjectConverter() {

	}
	
	public JavaObjectConverter(Compressor<byte[], byte[]> compressor, Decompressor<byte[], byte[]> decompressor) {
		this.compressor = compressor;
		this.decompressor = decompressor;
	}
	
	/** 设置缓冲大小 */
	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}
	
	/** 设置压缩器 */
	public void setCompressor(Compressor<byte[], byte[]> compressor) {
		this.compressor = compressor;
	}

	/** 设置解压缩器 */
	public void setDecompressor(Decompressor<byte[], byte[]> decompressor) {
		this.decompressor = decompressor;
	}
	
	/** 设置压缩限度 */
	public void setCompressionThreshold(int compressionThreshold) {
		this.compressionThreshold = compressionThreshold;
	}
	
	@Override
	public byte[] toByteArray(Object body) throws MetaClientException {
		JavaObjectOutput javaObjectOutput = null;
		try {
			javaObjectOutput = new JavaObjectOutput(bufferSize) ;
    		javaObjectOutput.writeObject(body);
			if (javaObjectOutput.position() > compressionThreshold) {
				javaObjectOutput.writeByte(1);
				byte[] result = javaObjectOutput.toBytes();
	    		return compressor.compress(result);
			} else {
				javaObjectOutput.writeByte(0);
				return javaObjectOutput.toBytes();
			}
		} catch (IOException e) {
			throw new MetaClientException(e.getMessage(), e);
		} finally {
			javaObjectOutput.close();
		}
//		return compressor.compress(rpcSerializer.serialize(body));
	}

	@Override
	public Object fromByteArray(byte[] bs) throws MetaClientException {
		JavaObjectInput javaObjectInput = null;
		try {
			if (bs[bs.length - 1] == 0) {
				javaObjectInput = new JavaObjectInput(bs, 0, bs.length - 1);
			} else {
				javaObjectInput = new JavaObjectInput(decompressor.decompress(bs));
			}
			return javaObjectInput.readObject();
    	} catch (Exception e) {
			throw new MetaClientException(e.getMessage(), e);
		} finally {
    		javaObjectInput.close();
    	}
//		return rpcSerializer.deserialize(decompressor.decompress(bs), Object.class);
	}
}
