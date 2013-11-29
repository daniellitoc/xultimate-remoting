package org.danielli.xultimate.web.support;

import httl.web.WebEngine;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * HTTL配置加载。
 * 
 * @author Daniel Li
 * @since 15 Jun 2013
 * @see WebEngine
 */
public class HTTLConfigurer implements InitializingBean {

	private Properties httlProperties;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(httlProperties, "this property 'httlProperties' is required; it must not be null");
		for (Map.Entry<Object, Object> entry : httlProperties.entrySet()) {
			WebEngine.setProperty((String) entry.getKey(), (String) entry.getValue());
		}
		
	}

	public void setHttlProperties(Properties httlProperties) {
		this.httlProperties = httlProperties;
	}
	
}
