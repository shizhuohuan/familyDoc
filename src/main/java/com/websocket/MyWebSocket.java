package com.websocket;

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。

import com.entity.FdHistory;
import com.service.FdHistoryService;
import com.util.ApplicationContextRegister;
import com.util.Constants;
import com.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 类似Servlet的注解mapping。无需在web.xml中配置。
 * configurator = SpringConfigurator.class是为了使该类可以通过Spring注入。
 */
@ServerEndpoint(value = "/websocket/{sid}/{rid}", configurator = SpringConfigurator.class)
public class MyWebSocket {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    private String sendId;
    private String recId;

    public MyWebSocket() {
    }


    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    // 若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    //与客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("sid") String sid, @PathParam("rid") String rid, Session session) {
        this.sendId = sid;
        this.recId = rid;
        this.session = session;
        webSocketSet.add(this); //加入set中
        addOnlineCount(); //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this); //从set中删除
        subOnlineCount(); //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        boolean flag = false;
        //发消息
        for (MyWebSocket item : webSocketSet) {
            try {
                if (StringUtils.isNotEmpty(recId) && item.sendId.equals(recId)) {
                    item.sendMessage(message,Constants.MESSAGE_CONFIRM);
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        if(flag == false){
            try {
                this.sendMessage(message,Constants.MESSAGE_UNCONFIRM);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message,String confirm) throws Exception {
        ApplicationContext act = ApplicationContextRegister.getApplicationContext();
        FdHistoryService historyService=act.getBean(FdHistoryService.class);
        //保存数据到数据库
        FdHistory history = new FdHistory();
        history.setId(UUIDs.randomBase64UUID());
        history.setContent(message);
        history.setType(Constants.MESSAGE_CHAT);
        history.setConfirm(confirm);
        history.setSendId(sendId);
        history.setReciveId(recId);
        history.setCreateDate(DateUtil.NowDate2String());
        history.setMessageId(sendId + "&" + recId);
        historyService.add(history);
        if(confirm.equals(Constants.MESSAGE_CONFIRM)){
            this.session.getBasicRemote().sendText(message);
        }
//this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}
