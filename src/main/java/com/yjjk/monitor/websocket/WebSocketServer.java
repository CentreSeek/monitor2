package com.yjjk.monitor.websocket;

import com.alibaba.fastjson.JSON;
import com.yjjk.monitor.entity.websocket.MonitorParam;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;


@ServerEndpoint("/websocket/{departmentId}")
@Component
@Data
public class WebSocketServer {

    protected static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineCount = new AtomicInteger(0);
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    // 参数
    private MonitorParam param;

    public synchronized MonitorParam getParam() {
        return param;
    }

    public synchronized void setParam(MonitorParam param) {
        this.param = param;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("departmentId") Integer departmentId) {
        Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听, 当前在线人数为" + getOnlineCount());
        log.info("param:     " + requestParameterMap.toString());
        param = new MonitorParam();
        param.setDepartmentId(departmentId);
        param.setStart(Integer.parseInt(requestParameterMap.get("start").get(0)));
        param.setEnd(Integer.parseInt(requestParameterMap.get("end").get(0)));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);   //从set中删除
        subOnlineCount();               //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        MonitorParam changeParam = JSON.parseObject(message, MonitorParam.class);
        if (changeParam.getDepartmentId() != null) {
            param.setDepartmentId(changeParam.getDepartmentId());
        }
        param.setStart(changeParam.getStart());
        param.setEnd(changeParam.getEnd());
        log.info("更新参数:" + JSON.toJSONString(param));
    }

//    public static void main(String[] args) {
//        MonitorParam a = new MonitorParam();
//        a.setDepartmentId(1).setStart(1).setEnd(1);
//        System.out.println(JSON.toJSONString(a));
//    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        synchronized (this.getSession()) {
            this.session.getBasicRemote().sendText(message);
        }
    }

    public void sendMessage(Session session, String message) throws IOException {
        synchronized (session) {
            this.session.getBasicRemote().sendText(message);
        }
    }

//    /**
//     * 群发自定义消息
//     * */
//    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
//    	log.info("推送消息到窗口"+sid+"，推送内容:"+message);
//        for (WebSocketServer item : webSocketSet) {
//            try {
//            	//这里可以设定只推送给这个sid的，为null则全部推送
//            	if(sid==null) {
//            		item.sendMessage(message);
//            	}else if(item.sid.equals(sid)){
//            		item.sendMessage(message);
//            	}
//            } catch (IOException e) {
//                continue;
//            }
//        }
//    }

    public static synchronized int getOnlineCount() {
        return onlineCount.get();
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount.incrementAndGet();
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount.decrementAndGet();
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }

    public static void setWebSocketSet(CopyOnWriteArraySet<WebSocketServer> webSocketSet) {
        WebSocketServer.webSocketSet = webSocketSet;
    }

}

