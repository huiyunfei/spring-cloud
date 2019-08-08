==========后台系统==========
springboot集成websocket的两种实现方式
添加websocket接口，java服务端，js客户端
1.1：pom添加配置文件
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>
1.2：配置文件添加项目port，thymelife
server:
  port: 8885
#添加Thymeleaf配置
thymeleaf:
  cache: false
  prefix: classpath:/templates/
  suffix: .html
  mode: HTML5
  encoding: UTF-8
  content-type: text/html
1.3：websocket配置
添加webSocketConfig配置类，开启websocket支持
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
1.4：添加websocket服务端代码WebSocketServer类
1.5：添加index.html页面
访问：index.html页面，debug查看socket连接信息。发送消息调用webSocketServer.sendMessage方法，可以添加TimeTask定时器自动调用
另提供群发自定义消息webSocketServer.sendInfo方法
2.1:pom添加相同配置
2.2：配置类webSocketConfig extends AbstractWebSocketMessageBrokerConfigurer，提供websocket的连接url
2.3：controller添加对应的服务端接口
@MessageMapping("/change-notice")//客户端访问服务端的时候config中配置的服务端接收前缀也要加上 例：/app/change-notice
@SendTo("/topic/notice")//config中配置的订阅前缀记得要加上
2.4：html页面提供连接websocket信息和提供回调接口信息
哈哈