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
        // Kryo、Protobuf、Protostuff的序列化性能都要好于Hessian2很多，在xultimate-core里的测试类可以看到相关的性能测试。
        // 目前在dubbo中应用，速度上比hessian2慢一点，不过序列化部分需要重新进行设计，且已经知道原因所在。
        // dubbo start timestamp(ns): 4311299299241, stop timestamp(ns): 4368039819226, running time: 0000:00:00 0:00:56.740 (56740519985 ns) (56740519 us) (56740 ms) (018.40126%)
        // hessian2 start timestamp(ns): 4564823105698, stop timestamp(ns): 4619882542361, running time: 0000:00:00 0:00:55.059 (55059436663 ns) (55059436 us) (55059 ms) (018.26991%)
        // java start timestamp(ns): 5826432023255, stop timestamp(ns): 5887835317613, running time: 0000:00:00 0:01:01.403 (61403294358 ns) (61403294 us) (61403 ms) (018.26744%)
        // compactedjava
        // customJava start timestamp(ns): 6552100723229, stop timestamp(ns): 6611358581955, running time: 0000:00:00 0:00:59.257 (59257858726 ns) (59257858 us) (59257 ms) (018.40684%)
        // kryo start timestamp(ns): 6887972740289, stop timestamp(ns): 6944583992692, running time: 0000:00:00 0:00:56.611 (56611252403 ns) (56611252 us) (56611 ms) (018.41684%)
        // protobuf start timestamp(ns): 7379823567896, stop timestamp(ns): 7435527050692, running time: 0000:00:00 0:00:55.703 (55703482796 ns) (55703482 us) (55703 ms) (018.29331%)
        // protostuff start timestamp(ns): 7832325784632, stop timestamp(ns): 7888115964498, running time: 0000:00:00 0:00:55.790 (55790179866 ns) (55790179 us) (55790 ms) (018.41977%)
        context.close();
    }
}
