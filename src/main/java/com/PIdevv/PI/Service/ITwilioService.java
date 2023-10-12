package com.PIdevv.PI.Service;

public interface ITwilioService {

    void sendSms(String to, String from, String body);

    void makeCall(String from, String to);

}

