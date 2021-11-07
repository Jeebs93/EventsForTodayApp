package com.example.ev;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jboss.ejb3.annotation.ResourceAdapter;


import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.Stateful;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.lang.reflect.Type;
import java.util.List;


@MessageDriven(name="Consumer",activationConfig ={
        @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "false"),
        @ActivationConfigProperty(propertyName = "destination",propertyValue = "Q.test"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
@ResourceAdapter("activemq.rar")
public class Consumer implements MessageListener {

    @Inject
    EventListBean eventListBean;

    @Override
    public void onMessage(Message message) {

        Gson gson = new Gson();
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println(textMessage.getText());
            Type collectionType = new TypeToken<List<Event>>(){}.getType();
            List<Event> eventList = (List<Event>) gson.fromJson(textMessage.getText(),collectionType);
            eventListBean.setEventList(eventList);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}