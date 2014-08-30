package org.danielli.xultimate.remoting.metaq.support;

import org.danielli.xultimate.context.kvStore.memcached.xmemcached.XMemcachedClient;

import com.taobao.metamorphosis.client.consumer.MessageIdCache;
import com.taobao.metamorphosis.client.consumer.SimpleFetchManager;

/**
 * XMemcached消息ID缓存。
 * 
 * @author Daniel Li
 * @since 15 Jun 2013
 */
public class XMemcachedMessageIdCache implements MessageIdCache {
	/** XMemcached客户端 */
	private XMemcachedClient xMemcachedClient;

	/** 失效时间 */
	private int expireInSeconds = 60;

	/**
	 * 设置XMemcached客户端。
	 * @param xMemcachedClient XMemcached客户端。
	 */
	public void setxMemcachedClient(XMemcachedClient xMemcachedClient) {
		this.xMemcachedClient = xMemcachedClient;
	}
	/**
	 * 设置失效时间。
	 * @param expireInSeconds 失效时间。
	 */
	public void setExpireInSeconds(int expireInSeconds) {
		this.expireInSeconds = expireInSeconds;
	}

	@Override
	public void put(String key, Byte exists) {
		xMemcachedClient.set(key, expireInSeconds, exists);
	}

	@Override
	public Byte get(final String key) {
		return xMemcachedClient.get(key);
	}
	
	/**
	 * 设置XMemcachedMessageIdCache。
	 */
	public void addSampleFetchManager() {
		SimpleFetchManager.setMessageIdCache(this);
	}
	
	
}
