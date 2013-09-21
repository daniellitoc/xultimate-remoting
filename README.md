# xultimate-remoting #

提供了一些远程调用的Demo。便于用的实际即时提取。包括了RMI、HttpInvoker、Hessian、Burlap、CXF、REST、ActiveMQ、MetaQ、Dubbo的配置及简单调用。


## 想法 ##

最初并没有这方面的概念，只是通过Spring提供了RMI、HttpInvoker、Hessian、Burlap、CXF、REST、ActiveMQ的配置。后来因为所参与的项目中用到了RabbitMQ，而原有的封装经常出现问题（过慢、CPU负载过高、导致应用崩溃），后来我重新写了一套封装，主要通过ClientTemplate和Listener完成，客户端通过ClientTemplate发送对象，服务端通过实现Listener<XXXBean>的形式对接受到的对象进行处理，整个配置都是通过Spring定义，最后发现不考虑具体的对象接受后处理部分，性能提升了400倍左右。于是开始关注分布式调用这部分，开始接触到的是MetaQ，后来接触到Dubbo，最后决定将这两个框架作为自己以后进行异步和同步处理的优先考虑，进行的扩展也是围绕MetaQ和Dubbo进行的。


## 扩展 ##

MetaQ部分主要是将自己原有封装的的序列化(protostuff、kryo)和XMemcachedTemplate集成进来。然后对原有的MetaqTemplate重写了一下，功能是直接搬过来的，只是觉得调用起来如何能更加方便。

Dubbo部分主要是将自己原有封装过的序列化(java、kryo、protostuff)集成到Dubbo当中。分别为kryo、protostuff、protobuf，一些基本数据类型部分采用了Java本身的。
