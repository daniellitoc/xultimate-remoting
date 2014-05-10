# xultimate-remoting #

* 起初只是提供Java中一些分布式远程调用的ShowCase，都是基于Spring来构建的，这样便于需要的时候提取并修改。
* 基础的分布式调用包括RMI、HttpInvoker、Hessian、Burlap；异步调用提供ActiveMQ；Web服务则包括了REST、CXF。
* 后来因为所参与的项目使用了RabbitMQ，而原有的封装经常会出现问题(过慢、CPU负载过高)导致应用崩溃，因此我重新封装了一套。客户端通过RabbitMQClientTemplate发送可序列化对象，序列化协议使用原有的Hessian协议，服务端搬照Tomcat的JIoEndpoint写法并采用信号量进行并发控制，中间通过JobService进行任务分配，最终通过定义不同的Job\<XXXDto\>完成具体任务。整个配置通过Spring定义。最终经过简单测试，发现性能有提升400倍左右。然后才开始真正关注和思考分布式调用。
* 最终决定公共服务采用Spring MVC开发REST + JSON，同步调用采用Dubbo，异步调用采用MetaQ。


## xultimate-remoting-service ##

* 包括了公共Service和Service实现类。


## xultimate-remoting-rmi ##

* 提供了基于RMI协议的同步调用演示。


## xultimate-remoting-httpinvoker ##

* 提供了基于Spring HTTP invoker的同步调用演示。


## xultimate-remoting-hessian ##

* 提供了基于Hessian协议的同步调用演示。


## xultimate-remoting-burlap ##

* 提供了基于Burlap协议的同步调用演示。


## xultimate-remoting-cxf ##

* 提供了基于Apache CXF Web服务框架的调用演示。


## xultimate-remoting-rest ##

* 提供了基于Spring MVC实现的REST风格，通过FastJSON实现JSON处理的调用演示。


## xultimate-remoting-jms ##

* 提供了基于Apache ActiveMQ消息中间件的异步调用演示。


## xultimate-remoting-metaq ##

* 提供了基于MetaQ队列模型消息中间件的异步调用演示。
* 重写MetaqTemplate，功能是直接Copy过来的，只是觉得调用起来如何能更加方便。
* 提供MessageProducerCallback和MessageProducerWithMessageCallback，用于回调处理。
* 提供MetaqTemplateUtils，用于执行消息回调。
* 提供DefaultMessageBodyConverter，内部封装RpcSerializer，通过组合方式实现，因此支持Java、Kryo、Protobuf、Protostuff等序列化方式；同时具备压缩功能，可配置为GZIP、Snappy。
* 提供XMemcachedMessageIdCache，消息ID实现，内部封装XMemcachedTemplate。


## xultimate-remoting-dubbo ##

* 提供了基于Dubbo分布式服务框架的同步调用演示。
* 提供ObjectInput、ObjectOutput，内部封装AbstractObjectInput、AbstractObjectOutput，提供真正解序列化。
* 提供JavaSerialization，完成Java序列化/解序列化功能；同时具备压缩/解压缩功能，默认配置为Snappy；缓冲大小默认256；都可通过继承覆盖。
* 提供KryoSerialization，完成Kryo序列化/解序列化功能；同时具备压缩/解压缩功能，默认配置为Snappy；缓冲大小默认256；都可通过继承覆盖。
* 提供ProtobufSerialization，完成Protobuf序列化/解序列化功能；同时具备压缩/解压缩功能，默认配置为Snappy；缓冲大小默认256；都可通过继承覆盖。
* 提供ProtostuffSerialization，完成Protostuff序列化/解序列化功能；同时具备压缩/解压缩功能，默认配置为Snappy；缓冲大小默认256；都可通过继承覆盖。
* 提供CuratorFrameworkFactoryBean，用于通过Spring创建CuratorFramework实例。
* 提供SetACLCommandExecutor，用于执行setACL指令。