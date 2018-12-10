# RpcTree
Thrift, Hessian,RMI

<pre>
RCP vs HTTP
      RPC基于TCP/IP协议；
      HTTP服务是基于HTTP协议的；
</pre>

![](https://i.imgur.com/h9gA4un.png)

<pre>
RPC架构模型
      1）客户端Client,服务的调用方
      2）Server服务的提供者
      3）Registry
</pre>

RPC调用流程

![](https://i.imgur.com/Xa67OLz.png)

<pre>
调用流程步骤：
      1）服务消费方client以本地方式调用服务；
      2）client stub接收到调用后负责将方法，参数等组装成能够进行网络传输的消息体。
      3）client stub找到服务地址，并将消息发送给服务端。
      5）server stub收到消息后解码。
      6）server stub根据解码结果调用本地的服务。
      7）本地服务执行并将结果返回给server stub
      8) server stub将返回结果打包成消息并发送给消费方。
      9）client stub接收到消息，并进行解码。
      10）服务消费方得到最终结果

使用到的技术
      1）动态代理 
        生成 client stub和server stub需要用到 Java 动态代理技术 ，我们可以使用JDK
      原生的动态代理机制，可以使用一些开源字节码工具框架 如：CgLib、Javassist等

      2）序列化
         为了能在网络上传输和接收java对象，需要对它进行序列化和反序列化操作。
         成熟的序列化技术： protobuf, Thrift, hessian,

      3) NIO
         推荐使用Netty作为底层通信框架。

      5）推荐注册中心
         Redis
         Zookeeper
         Consul
</pre>

<pre>
RPC与RMI的区别

      RPC：远程过程调用，它是一种通过网络从远程计算机程序上请求服务，而不需要了解底层网络
           技术的协议。RPC协议假定某些传输层协议的存在，如TCP,UDP，在OSI网络通信模型中，
           RPC跨越了传输层和应用层。

      RMI：
           远程方法调用。
           方法调用从客户端对象经过Stub，远程引用层和传输层相依，传递给主机，然后再经过
           传输层，向上穿过远程调用层和骨干网，导到服务器对象。占位程序扮演者远程服务器对
           象的代理的角色，

      RPC与RMI之间最主要的区别在于方法是如何被调用的。在RMI中，远程接口使每个远程方法都
      具有方法签名。在RPC中，当一个请求导到RPC服务器时，这个请求就包含了一个参数集合文本值
      ，通常形成“classname.methodname”的形式。这就向RPC服务器表明，被请求的方法
      在为“classname”的类中，名叫“methodname”。然后RPC服务器就去搜索与之相匹配的类和
      方法，并把它作为那种方法参数类型的输入。这里的参数类型是与RPC请求中的类型是匹配的
      。一旦匹配成功，这个方法就被调用了，其结果被编码后返回客户方
</pre>