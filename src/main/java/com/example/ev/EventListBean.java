package com.example.ev;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.util.List;

@Singleton
@ManagedBean
@Named("eventListBean")
public class EventListBean {

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    private List<Event> eventList;

}
