package com.example.ev;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Event {

private String patientName;

private String appointmentValue;

private String dateString;


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAppointmentValue() {
        return appointmentValue;
    }

    public void setAppointmentValue(String appointmentValue) {
        this.appointmentValue = appointmentValue;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
