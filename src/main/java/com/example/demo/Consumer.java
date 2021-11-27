package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jboss.ejb3.annotation.ResourceAdapter;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.lang.reflect.Type;
import java.util.List;


@MessageDriven(name="Consumer",activationConfig ={
        @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "false"),
        @ActivationConfigProperty(propertyName = "destination",propertyValue = "Q.test"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
@ResourceAdapter("activemq.rar")
@ServerEndpoint("/endpoint")
public class Consumer implements MessageListener {

    @Inject
    EventListBean eventListBean;

    private Session session;

    @OnOpen
    public void connect(Session session) {
        this.session = session;
        System.out.println("Session=" + session);
    }

    @OnClose
    public void close() {
        this.session = null;
        System.out.println("Closed");
    }

    @OnMessage
    public void onMessage(String msg) {
        System.out.println("msg"+msg);
    }


    @Override
    public void onMessage(Message message)  {

        Gson gson = new Gson();
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println(textMessage.getText());
            Type collectionType = new TypeToken<List<Event>>(){}.getType();
            List<Event> eventList = (List<Event>) gson.fromJson(textMessage.getText(),collectionType);
            eventListBean.setEventList(eventList);
            this.session.getAsyncRemote().sendText("Echo:");
            System.out.println("msg" + eventListBean.getEventList());

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}