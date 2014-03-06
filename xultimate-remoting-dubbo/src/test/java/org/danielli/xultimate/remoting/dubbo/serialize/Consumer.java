package org.danielli.xultimate.remoting.dubbo.serialize;
import org.danielli.xultimate.remoting.dto.Account;
import org.danielli.xultimate.remoting.service.AccountService;
import org.danielli.xultimate.util.performance.PerformanceMonitor;
import org.danielli.xultimate.util.time.stopwatch.support.AdvancedStopWatchSummary;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Consumer {
	public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext-service-remoting-dubbo-client.xml"});
        context.start();
 
        AccountService demoService = (AccountService)context.getBean("remotingDubboAccountService"); // 获取远程服务代理
        
        PerformanceMonitor.start("Dubbo Performance Test");
        for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < 1000; j++) {
        		Account account = new Account();
                account.setName("Daniel Li");
                demoService.insertAccount(account); // 执行远程方法

                System.out.println(demoService.getAccounts("Daniel Li").size()); // 显示调用结果
        	}
        	PerformanceMonitor.mark("dubbo" + i);
        }
        PerformanceMonitor.stop();
        PerformanceMonitor.summarize(new AdvancedStopWatchSummary(true));
        PerformanceMonitor.remove();
        // dubbo running time: 0000:00:00 0:00:12.614
        // hessian2 running time: 0000:00:00 0:00:08.967
        // java running time: 0000:00:00 0:00:11.082
        // compactedjava running time: 0000:00:00 0:00:10.744
        // kryo running time: 0000:00:00 0:00:09.133
        // protostuff running time: 0000:00:00 0:00:10.115
        // protobuf running time: 0000:00:00 0:00:10.440
        context.close();
    }
}
