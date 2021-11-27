package com.example.demo;

import org.jboss.ejb3.annotation.ResourceAdapter;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.jms.*;

@ResourceAdapter("activemq.rar")
@Stateless
public class Producer {

    @Resource(lookup ="java:jboss/activemq/QueueConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup="java:jboss/activemq/queue/TestQueue")
    private Queue destination;

    public void sendString(String enterString) {
        try {

            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            message.setText(enterString);
            producer.send(message);
            System.out.println("message sent");
            session.close();
            connection.close();

        } catch (JMSException ex) {
            System.err.println("Sending message error");
            ex.printStackTrace();
        }

    }
}
