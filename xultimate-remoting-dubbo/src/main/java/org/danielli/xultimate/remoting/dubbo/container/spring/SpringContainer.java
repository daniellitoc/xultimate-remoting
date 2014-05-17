package org.danielli.xultimate.remoting.dubbo.container.spring;

import org.danielli.xultimate.util.Assert;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.container.Container;

/**
 * SpringContainer. (SPI, Singleton, ThreadSafe)
 * 
 * @author william.liangf
 */
public class SpringContainer implements Container {

    private static final Logger logger = LoggerFactory.getLogger(SpringContainer.class);

    public static final String SPRING_CONFIG = "dubbo.spring.config";

    static ClassPathXmlApplicationContext context;
    
    public static ClassPathXmlApplicationContext getContext() {
		return context;
	}

	public void start() {
        String configPath = ConfigUtils.getProperty(SPRING_CONFIG);
        Assert.hasLength(configPath, "you must set `dubbo.spring.config` in dubbo.properties");
        context = new ClassPathXmlApplicationContext(configPath.split("[,\\s]+"));
        context.start();
    }

    public void stop() {
        try {
            if (context != null) {
                context.stop();
                context.close();
                context = null;
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

}
