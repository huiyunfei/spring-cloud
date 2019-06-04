package com.example.admin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSession.Receiptable;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * java客户端访问websocket服务
 * Created by hui.yunfei@qq.com on 2019/6/4
 */
public class Client {
    final static Logger LOGGER= LoggerFactory.getLogger(Client.class);
    private final static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();//请求头
    private WebSocketStompClient client=null;//stomp客户端
    private org.springframework.web.socket.sockjs.client.SockJsClient SockJsClient=null;//socket客户端
    private ThreadPoolTaskScheduler Ttask=null;//连接池
    private StompSession session=null;//连接会话
    private static Map<String, String> WebSocketConfig;//配置参数
    public  volatile boolean RecvFlag=false;//期待的返回标志位，当收到的消息与配置中exceptionRecv相等时为true

    public static void main(String[] args) throws Exception {

        String sendMsg="我是java版的stomp over websocket的客户端";

        sendMsg=(args!=null&&args.length!=0)? args[0]:sendMsg;

        Client myClient = new Client();
        //读取配置文件
        //WebSocketConfig=myClient.readConfig();

        String uri = "ws://172.16.0.11:8885/socket";
        String subscribe = "/topic/notice";
        String send = "/app/change-notice";
//        if (WebSocketConfig!=null) {
//            //连接到客户端
//            myClient.runStompClient(myClient, uri,subscribe,send,sendMsg);
//            LOGGER.info("成功使用配置文件加载客户端");
//        }else {//默认参数连接到客户端
//            myClient.runStompClient(myClient,
//                    "ws://localhost:8080/StompWithSSM/stomp",
//                    "/topic/message","/app/send",sendMsg);
//            LOGGER.info("使用默认参数加载客户端");
//        }
        //连接到客户端
        myClient.runStompClient(myClient, uri,subscribe,send,sendMsg);
        LOGGER.info("成功使用配置文件加载客户端");

        while (!myClient.RecvFlag) {
            //持续等待返回标志位为true
            LOGGER.info("-------------------持续等待返回验证消息中……，当前flag:"+myClient.RecvFlag);
            Thread.sleep(3000);
        }
        //关闭所有连接终止程序
        myClient.Ttask.destroy();
        myClient.SockJsClient.stop();
        myClient.client.stop();
        myClient.session.disconnect();
        System.exit(0);
    }


    public void runStompClient(Client client,String URI,String subscribe,String send, final String sendMsg) throws ExecutionException, InterruptedException, UnsupportedEncodingException {
        //连接到对应的endpoint点上，也就是建立起websocket连接
        ListenableFuture<StompSession> f = client.connect(URI);
        //建立成功后返回一个stomp协议的会话
        StompSession stompSession = f.get();

        LOGGER.info("Subscribing to greeting topic using session " + stompSession);
        //绑定订阅的消息地址subscribe
        client.subscribeGreetings(subscribe, stompSession);
        //设置Receipt头，不设置无法接受返回消息
        stompSession.setAutoReceipt(true);
        //绑定发送的的地址send，注意这里使用的字节方式发送数据
        Receiptable rec= stompSession.send(send,sendMsg.getBytes("UTF-8"));
        //添加消息发送成功的回调
        rec.addReceiptLostTask(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("消息发送成功,发送内容为:"+sendMsg);
            }
        });
    }

    public ListenableFuture<StompSession> connect(String url) {

        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());

        List<Transport> transports = Collections.singletonList(webSocketTransport);

        SockJsClient sockJsClient = new SockJsClient(transports);
        //设置对应的解码器，理论支持任意的pojo自带转json格式发送，这里只使用字节方式发送和接收数据
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        stompClient.setReceiptTimeLimit(300);

        stompClient.setDefaultHeartbeat(new long[]{10000l,10000l});

        ThreadPoolTaskScheduler task=new ThreadPoolTaskScheduler();

        task.initialize();

        stompClient.setTaskScheduler(task);

        client=stompClient;
        SockJsClient=sockJsClient;
        Ttask=task;
        return stompClient.connect(url, headers, new MyHandler(), "localhost", 8080);
    }

    public void subscribeGreetings(String url, StompSession stompSession) throws ExecutionException, InterruptedException {
        stompSession.subscribe(url, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;//设置订阅到消息用字节方式接收
            }
            @Override
            public void handleFrame(StompHeaders stompHeaders, Object o) {
                String recv=null;
                try {
                    recv = new String((byte[]) o,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                LOGGER.info("收到返回的消息" + recv);
                if (WebSocketConfig!=null&&recv.equals("exceptionRecv")) {
                    RecvFlag=true;
                }else if (recv.equals("success")) {
                    RecvFlag=true;
                }

            }
        });
    }

    private class MyHandler extends StompSessionHandlerAdapter {
        @Override
        public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
            session=stompSession;
            LOGGER.info("连接成功");
        }

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {
            LOGGER.error("连接出现异常");
            exception.printStackTrace();
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            super.handleFrame(headers, payload);
            LOGGER.info("=========================handleFrame");
        }
    }

    private Map<String, String> readConfig() {
        Map<String, String> ConfigMap=null;
        String [] keys= {"URI","subscribe","send","exceptionRecv"};
        //D:\\dkWorkSpace\\Java\\SocketGettingStart\\StompClient\\WebSocketConfig.properties
        File file =new File("src/resource/WebSocketConfig.properties");
        if (file.exists()) {
            LOGGER.info("开始读取配置文件");
            ConfigMap=new HashMap<String, String>();
            FileInputStream FIS=null;
            InputStreamReader ISReader=null;
            BufferedReader reader=null;
            try {
                FIS=new FileInputStream(file);
                ISReader=new InputStreamReader(FIS,"UTF-8");
                reader=new BufferedReader(ISReader);
                String readline=null;
                LOGGER.info("开始按行读取配置文件");
                while ((readline=reader.readLine())!=null) {
                    LOGGER.info("当前行内容："+readline);
                    String readStr []=readline.split("=");
                    if (readStr==null||readStr.length!=2) {
                        LOGGER.error("配置文件格式不符合规范，必须一行一个配置，并用‘=’分割，当前行内容："+readline);
                    }
                    ConfigMap.put(readStr[0], readStr[1]);
                }
                LOGGER.info("文件读取完成,最终的配置信息："+ConfigMap);
                boolean notice=false;
                for (int i = 0; i < keys.length; i++) {
                    if (!ConfigMap.containsKey(keys[i])) {
                        LOGGER.error("缺少对关键参数："+keys[i]+"的配置，配置将无法生效");
                        notice=true;
                    }
                }
                ConfigMap=notice? null:ConfigMap;
            } catch (Exception e) {
                LOGGER.info("文件读取过程发生异常："+e.getMessage());
            }finally{
                if (reader!=null) {
                    try {
                        FIS.close();
                        ISReader.close();
                        reader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        }else {
            LOGGER.info("不存在配置文件，请检查路径：");
            LOGGER.info("开始使用默认socketConfig");
        }

        return ConfigMap;
    }
}
