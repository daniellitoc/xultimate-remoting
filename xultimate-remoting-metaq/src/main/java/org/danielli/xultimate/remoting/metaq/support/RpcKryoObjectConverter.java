package org.danielli.xultimate.remoting.metaq.support;

import java.io.IOException;

import org.danielli.xultimate.core.compression.Compressor;
import org.danielli.xultimate.core.compression.Decompressor;
import org.danielli.xultimate.core.compression.support.NullCompressor;
import org.danielli.xultimate.core.io.support.RpcKryoObjectInput;
import org.danielli.xultimate.core.io.support.RpcKryoObjectOutput;
import org.danielli.xultimate.core.serializer.kryo.KryoGenerator;
import org.danielli.xultimate.core.serializer.kryo.support.ThreadLocalKryoGenerator;

import com.taobao.metamorphosis.client.extension.spring.MessageBodyConverter;
import com.taobao.metamorphosis.exception.MetaClientException;

/**
 * 通过{@code RpcKryoObjectInput}和{@code RpcKryoObjectOutput}提供的功能完成序列化/解序列化支持。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class RpcKryoObjectConverter implements MessageBodyConverter<Object> {
	
	protected int bufferSize = 256;

	protected int compressionThreshold = 512;
	
	protected KryoGenerator kryoGenerator = ThreadLocalKryoGenerator.INSTANCE;
	/** 压缩器 */
	protected Compressor<byte[], byte[]> compressor = NullCompressor.COMPRESSOR;
	/** 解压缩器 */
	protected Decompressor<byte[], byte[]> decompressor = NullCompressor.COMPRESSOR;
	
	public RpcKryoObjectConverter() {

	}
	
	public RpcKryoObjectConverter(KryoGenerator kryoGenerator, Compressor<byte[], byte[]> compressor, Decompressor<byte[], byte[]> decompressor) {
		this.kryoGenerator = kryoGenerator;
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
		RpcKryoObjectOutput kryoObjectOutput = new RpcKryoObjectOutput(bufferSize, kryoGenerator.generate());
		try {
			kryoObjectOutput.writeObject(body);
			if (kryoObjectOutput.position() > compressionThreshold) {
	    		kryoObjectOutput.writeByte(1);
				byte[] result = kryoObjectOutput.toBytes();
	    		return compressor.compress(result);
			} else {
	    		kryoObjectOutput.writeByte(0);
				return kryoObjectOutput.toBytes();
			}
		} catch (IOException e) {
			throw new MetaClientException(e.getMessage(), e);
		} finally {
			kryoObjectOutput.close();
		}
//		return compressor.compress(rpcSerializer.serialize(body));
	}

	@Override
	public Object fromByteArray(byte[] bs) throws MetaClientException {
		RpcKryoObjectInput kryoObjectInput = null;
		try {
			if (bs[bs.length - 1] == 0) {
				kryoObjectInput = new RpcKryoObjectInput(bs, 0, bs.length - 1, kryoGenerator.generate());
			} else {
				kryoObjectInput = new RpcKryoObjectInput(decompressor.decompress(bs), kryoGenerator.generate());
			}
			return kryoObjectInput.readObject();
    	} catch (Exception e) {
			throw new MetaClientException(e.getMessage(), e);
		} finally {
    		kryoObjectInput.close();
    	}
//		return rpcSerializer.deserialize(decompressor.decompress(bs), Object.class);
	}
}
