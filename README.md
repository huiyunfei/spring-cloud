======Point=======
积分系统（生产者）
所有Point项目需要共享的服务及模型都定义在该项目中
其他项目使用的时候直接添加maven依赖

======Point-share=======
该项目是积分系统feign分离出来的一个api（Point项目的service）
所有Point项目需要共享的服务及模型都定义在该项目中
其他项目使用的时候直接添加maven依赖

======order=======
订单系统（消费者），直接添加Point-share依赖就可以使用Point服务
启动文件一定要添加对应的扫描
@EnableFeignClients(basePackages = "com.example.pointshare.feign")//扫描feign的路径
@SpringBootApplication(scanBasePackages = {"com.example"})//除了扫描本服务的注解外还要扫描feign项目的注解
dev