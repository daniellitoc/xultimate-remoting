package org.danielli.xultimate.common.serialize;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Provider {
	public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext-service.xml", "applicationContext-service-remoting-dubbo-server.xml"});
        context.start();
 
        System.in.read(); // 按任意键退出
        context.close();
    }
}
