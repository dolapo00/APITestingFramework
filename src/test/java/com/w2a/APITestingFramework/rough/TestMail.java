package com.w2a.APITestingFramework.rough;

import com.w2a.APITestingFramework.utilities.MonitoringMail;
import com.w2a.APITestingFramework.utilities.TestConfig;

import javax.mail.MessagingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestMail {

    static String messageBody;

    public static void main(String[] args) throws MessagingException {

        try {
            messageBody = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/job/APITestingFramework/Extent_20Reports/";
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        MonitoringMail mail = new MonitoringMail();
        mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);

    }

}
