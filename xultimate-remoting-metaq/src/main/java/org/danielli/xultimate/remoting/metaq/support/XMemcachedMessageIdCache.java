package org.danielli.xultimate.remoting.metaq.support;

import net.rubyeye.xmemcached.MemcachedClient;

import org.danielli.xultimate.context.kvStore.memcached.xmemcached.XMemcachedCallback;
import org.danielli.xultimate.context.kvStore.memcached.xmemcached.XMemcachedReturnedCallback;
import org.danielli.xultimate.context.kvStore.memcached.xmemcached.support.XMemcachedTemplate;

import com.taobao.metamorphosis.client.consumer.MessageIdCache;
import com.taobao.metamorphosis.client.consumer.SimpleFetchManager;

/**
 * XMemcached消息ID缓存。
 * 
 * @author Daniel Li
 * @since 15 Jun 2013
 */
public class XMemcachedMessageIdCache implements MessageIdCache {
	/** XMemcached模板类 */
	private XMemcachedTemplate memcachedTemplate;

	/** 失效时间 */
	private int expireInSeconds = 60;

	/**
	 * 设置XMemcached模板类。
	 * @param memcachedTemplate XMemcached模板类。
	 */
	public void setMemcachedTemplate(XMemcachedTemplate memcachedTemplate) {
		this.memcachedTemplate = memcachedTemplate;
	}

	/**
	 * 设置失效时间。
	 * @param expireInSeconds 失效时间。
	 */
	public void setExpireInSeconds(int expireInSeconds) {
		this.expireInSeconds = expireInSeconds;
	}

	@Override
	public void put(final String key, final Byte exists) {
		this.memcachedTemplate.execute(new XMemcachedCallback() {
			
			@Override
			public void doInXMemcached(MemcachedClient memcachedClient) throws Exception {
				memcachedClient.set(key, expireInSeconds, exists);
			}
		});
	}

	@Override
	public Byte get(final String key) {
		return this.memcachedTemplate.execute(new XMemcachedReturnedCallback<Byte>() {

			@Override
			public Byte doInXMemcached(MemcachedClient memcachedClient) throws Exception {
				return memcachedClient.get(key);
			}
		
		});
		
	}
	
	/**
	 * 设置XMemcachedMessageIdCache。
	 */
	public void addSampleFetchManager() {
		SimpleFetchManager.setMessageIdCache(this);
	}
	
	
}
