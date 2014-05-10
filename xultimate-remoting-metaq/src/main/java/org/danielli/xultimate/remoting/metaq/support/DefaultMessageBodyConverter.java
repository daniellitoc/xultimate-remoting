package org.danielli.xultimate.remoting.metaq.support;

import org.danielli.xultimate.core.compression.Compressor;
import org.danielli.xultimate.core.compression.Decompressor;
import org.danielli.xultimate.core.compression.support.NullCompressor;
import org.danielli.xultimate.core.serializer.RpcSerializer;

import com.taobao.metamorphosis.client.extension.spring.MessageBodyConverter;
import com.taobao.metamorphosis.exception.MetaClientException;

/**
 * 默认消息体转换。支持组合配置序列化方式。
 * 
 * @author Daniel Li
 * @since 15 Jun 2013
 */
public class DefaultMessageBodyConverter implements MessageBodyConverter<Object> {
	
	/** Rpc序列化器和解序列化器 */
	protected RpcSerializer rpcSerializer;
	/** 压缩器 */
	protected Compressor<byte[], byte[]> compressor = NullCompressor.COMPRESSOR;
	/** 解压缩器 */
	protected Decompressor<byte[], byte[]> decompressor = NullCompressor.COMPRESSOR;
	
	@Override
	public byte[] toByteArray(Object body) throws MetaClientException {
		return compressor.compress(rpcSerializer.serialize(body));
	}

	@Override
	public Object fromByteArray(byte[] bs) throws MetaClientException {
		return rpcSerializer.deserialize(decompressor.decompress(bs), Object.class);
	}

	/**
	 * 设置Rpc序列化器和解序列化器。
	 * 
	 * @param rpcSerializer Rpc序列化器和解序列化器
	 */
	public void setRpcSerializer(RpcSerializer rpcSerializer) {
		this.rpcSerializer = rpcSerializer;
	}

	/**
	 * 设置压缩器。
	 * 
	 * @param compressor 压缩器。
	 */
	public void setCompressor(Compressor<byte[], byte[]> compressor) {
		this.compressor = compressor;
	}

	/**
	 * 设置解压缩器。
	 * 
	 * @param decompressor 解压缩器。
	 */
	public void setDecompressor(Decompressor<byte[], byte[]> decompressor) {
		this.decompressor = decompressor;
	}
}
