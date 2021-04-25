package com.sfarc.monitor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Sanduni Pavithra
 * Created on 4/18/2021
 */

@Component
public class Constants {
    public static final String CLIENT_ENDPOINT = "/chat";
//    public static final String CLIENT_URL = "ws://localhost:8095/chat?user=innerClient";



    private Constants() {

    }
}
