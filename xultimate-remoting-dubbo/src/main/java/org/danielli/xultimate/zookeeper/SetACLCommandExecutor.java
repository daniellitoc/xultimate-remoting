package org.danielli.xultimate.zookeeper;

import java.util.List;
import java.util.Map;

import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.danielli.xultimate.util.Assert;
import org.springframework.beans.factory.InitializingBean;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.api.BackgroundPathAndBytesable;
import com.netflix.curator.framework.api.BackgroundPathable;

public class SetACLCommandExecutor implements InitializingBean {

	protected boolean inBackground = false;
	
	protected Map<String, List<ACL>> setACLDatas;
	
	protected CuratorFramework curatorFramework;
	
	protected boolean createIfNoExists = true;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(curatorFramework, "this property `curatorFramework` is required; it must not be null");
		Assert.notEmpty(setACLDatas, "this property `setACLDatas` map must not be empty; it must contain at least one entry");
		
		for (Map.Entry<String, List<ACL>> entry : setACLDatas.entrySet()) {
			boolean nodeExists = false;
			if (curatorFramework.checkExists().forPath(entry.getKey()) != null) {
				nodeExists = true;
			} else if (createIfNoExists) {
				BackgroundPathAndBytesable<String> backgroundPathAndBytesable = curatorFramework.create().withACL(entry.getValue());
				if (inBackground) {
					backgroundPathAndBytesable.inBackground().forPath(entry.getKey());
				} else {
					backgroundPathAndBytesable.forPath(entry.getKey());
				}
			}
			if (nodeExists) {
				BackgroundPathable<Stat> backgroundPathable = curatorFramework.setACL().withACL(entry.getValue());
				if (inBackground) {
					backgroundPathable.inBackground().forPath(entry.getKey());
				} else {
					backgroundPathable.forPath(entry.getKey());
				}
			}
		}
	}

	public void setInBackground(boolean inBackground) {
		this.inBackground = inBackground;
	}

	public void setSetACLDatas(Map<String, List<ACL>> setACLDatas) {
		this.setACLDatas = setACLDatas;
	}

	public void setCuratorFramework(CuratorFramework curatorFramework) {
		this.curatorFramework = curatorFramework;
	}

	public void setCreateIfNoExists(boolean createIfNoExists) {
		this.createIfNoExists = createIfNoExists;
	}
}
