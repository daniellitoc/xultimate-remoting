package org.danielli.xultimate.zookeeper;

import org.danielli.xultimate.util.StringUtils;
import org.springframework.beans.factory.FactoryBean;

import com.netflix.curator.RetryPolicy;
import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.framework.CuratorFrameworkFactory.Builder;

public class CuratorFrameworkFactoryBean implements FactoryBean<CuratorFramework> {

	protected String connectString;
	
	protected int sessionTimeoutMs = 60000;
	
	protected int connectionTimeoutMs = 15000;
	
	protected RetryPolicy retryPolicy;
	
	protected String username;
	
	protected String password;
	
	private CuratorFramework curatorFramework;
	
	@Override
	public CuratorFramework getObject() throws Exception {
		Builder builder = CuratorFrameworkFactory.builder().connectString(connectString)
				.sessionTimeoutMs(sessionTimeoutMs)
				.connectionTimeoutMs(connectionTimeoutMs)
				.retryPolicy(retryPolicy);
		if (StringUtils.isBlank(username) && StringUtils.isBlank(password)) {
			curatorFramework = builder.build();
		} else {
			curatorFramework = builder.authorization("digest", new StringBuilder(username).append(":").append(password).toString().getBytes()).build();
		}
		
		curatorFramework.start();
		
		return curatorFramework;		
	}
	
	public void close() {
		curatorFramework.close();
	}

	@Override
	public Class<?> getObjectType() {
		return CuratorFramework.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setConnectString(String connectString) {
		this.connectString = connectString;
	}

	public void setSessionTimeoutMs(int sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}

	public void setConnectionTimeoutMs(int connectionTimeoutMs) {
		this.connectionTimeoutMs = connectionTimeoutMs;
	}

	public void setRetryPolicy(RetryPolicy retryPolicy) {
		this.retryPolicy = retryPolicy;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
