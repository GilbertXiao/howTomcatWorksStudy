## 《How Tomcat Works》书中的源码

为了进一步学习tomcat的工作原理，跟着作者大大开始了学习之旅。《How Tomcat Works》在豆瓣有9.6的高分，想来一定会给我们学习带来惊喜。所以创建了repo记录下来。

不过这本书比较久远了。原书的代码基于jdk4运行的，参考的tomcat模块是4.1.2和5.0.18，不过和tomcat8.5对比了下，核心模型还是没变的，Connector组件和Container组件（engine-host-context-wrapper）在tomcat4已经存在，从简单入手，一通百通。

为了方便学习，我将代码改造成maven工程，直接clone下载就可以导入ide运行。欢迎star。

补充下，《How Tomcat Works》原书英文版很容易看懂，建议大家直接看英文版，也当做一次英语的学习，一举多得，原书中英文版和源码可以通过如下云盘地址下载：

`链接：https://pan.baidu.com/s/1PrY_CxmDPKk3L8zlH8c-Xw 
 提取码：spt2`

|章节|内容|位置|备注|
|:---|:---|:---|:---|
|ch1|创建一个简单的HTTP服务器|ch1|请用IE测试，不要用Chrome|

