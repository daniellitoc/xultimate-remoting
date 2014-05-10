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
        for (int i = 0; i < 3; i++) {
        	for (int j = 0; j < 50000; j++) {
        		Account account = new Account();
                account.setName("Daniel Li");
                demoService.insertAccount(account); // 执行远程方法
                System.out.println(j);
        	}
        	PerformanceMonitor.mark("dubbo" + i);
        }
        PerformanceMonitor.stop();
        PerformanceMonitor.summarize(new AdvancedStopWatchSummary(true));
        PerformanceMonitor.remove();
        // dubbo start timestamp(ns): 989258276356, stop timestamp(ns): 1045624291663, running time: 0000:00:00 0:00:56.366 (56366015307 ns) (56366015 us) (56366 ms) (029.33839%)
        // hessian2 start timestamp(ns): 1296299303505, stop timestamp(ns): 1352769635210, running time: 0000:00:00 0:00:56.470 (56470331705 ns) (56470331 us) (56470 ms) (029.29963%)
        // java start timestamp(ns): 5826432023255, stop timestamp(ns): 5887835317613, running time: 0000:00:00 0:01:01.403 (61403294358 ns) (61403294 us) (61403 ms) (018.26744%)
        // compactedjava
        // customJava start timestamp(ns): 2831795239652, stop timestamp(ns): 2893496929263, running time: 0000:00:00 0:01:01.701 (61701689611 ns) (61701689 us) (61701 ms) (029.44951%)
        // kryo start timestamp(ns): 3413941430671, stop timestamp(ns): 3469853249738, running time: 0000:00:00 0:00:55.911 (55911819067 ns) (55911819 us) (55911 ms) (029.36789%)
        // protobuf start timestamp(ns): 3734136178841, stop timestamp(ns): 3791089106269, running time: 0000:00:00 0:00:56.952 (56952927428 ns) (56952927 us) (56952 ms) (029.18992%)
        // protostuff start timestamp(ns): 4088411137780, stop timestamp(ns): 4145532366099, running time: 0000:00:00 0:00:57.121 (57121228319 ns) (57121228 us) (57121 ms) (029.32199%)
        context.close();
    }
}
