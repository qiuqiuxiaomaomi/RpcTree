# RpcTree
Thrift, Hessian,RMI

<pre>
常用的RPC框架：

      1）Dubbo
      2) Webservice
      3) RMI
      5) Hessian
         1.默认支持跨语言
         2.较慢
      6) Thrift
         Thrift源于faceBook
         1.Thrift支持多种语言（C++,C#,Cocoa,Erlag,Haskell,java,Ocami,Perl,PHP,Python,Ruby,
           和SmallTalk）
         2.Thrift适用了组建大型数据交换及存储工具，对于大型系统中的内部数据传输，相对于Json和xml在
           性能上和传输大小上都有明显的优势。
         3.Thrift支持三种比较典型的编码方式。（通用二进制编码，压缩二进制编码，优化的可选字段压缩编解码）
      7）Protobuf
         Protobuf是google开源的项目，全称 Google Protocol Buffers
         1.结构化数据存储格式（xml,json等）
         2.高性能编解码技术
         3.语言和平台无关，扩展性好
         4.支持java,C++,Python三种语言。
</pre>

<pre>
Java序列化的缺点

      Java自己提供序列化而且用起来也非常简单，但是在远程服务调用中很少用它，主要存在以下
      缺点。
         1）无法跨语言，这应该是Java序列化最致命的问题，由于Java序列化是java内部私有的
            协议，其他语言不支持，导致别的语言无法反序列化，这严重阻碍了它的应用。
         2）序列化后的码流太大，java序列化的大小是二进制编码的5倍多。
         3）序列化性能太低。

      判断一个序列化框架的优缺点：
         1）是否支持跨语言
         2）编码后的码流
         3）解编码的性能
         5）类库是否小巧，API使用是否方便
         6）使用者开发的工作量和难度        
</pre>

<pre>
1) 如果你不需要很多语言相互调用， 希望保持清晰的java接口代码（无任何业务不相关的接口继承和
   方法，属性定义），减少开发工作量，推荐Hessian
2）如果系统间传输的数据量不是很大，推荐Hessian
3) 如果需要支持大数据量的传输，多语言调用，极高的并发支持，推荐使用Thrift/ProtoBuf,
</pre>

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

###RMI

服务器端

![](https://i.imgur.com/hdeTA7P.png)

客户端
![](https://i.imgur.com/lD8hyNt.png)

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