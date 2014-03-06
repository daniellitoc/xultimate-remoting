package org.danielli.xultimate.remoting.metaq.support;

import net.rubyeye.xmemcached.MemcachedClient;

import org.danielli.xultimate.context.kvStore.memcached.xmemcached.XMemcachedCallback;
import org.danielli.xultimate.context.kvStore.memcached.xmemcached.XMemcachedReturnedCallback;
import org.danielli.xultimate.context.kvStore.memcached.xmemcached.support.XMemcachedTemplate;

import com.taobao.metamorphosis.client.consumer.MessageIdCache;
import com.taobao.metamorphosis.client.consumer.SimpleFetchManager;

public class XMemcacheMessageIdCache implements MessageIdCache {
	
	private XMemcachedTemplate memcachedTemplate;

	private int expireInSeconds = 60;

	public void setMemcachedTemplate(XMemcachedTemplate memcachedTemplate) {
		this.memcachedTemplate = memcachedTemplate;
	}

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
	
	public void addSampleFetchManager() {
		SimpleFetchManager.setMessageIdCache(this);
	}
	
	
}
