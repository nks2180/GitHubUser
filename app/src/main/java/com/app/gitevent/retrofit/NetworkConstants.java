package com.app.gitevent.retrofit;

import java.util.HashMap;

/**
 * Created by niranjan on 03/11/16.
 */

public class NetworkConstants {

    public static final int API_FETCH_APPOINTMENT_SLOTS = 1001;
    public static final int API_FETCH_GIT_EVENTS = 1000;

    public static HashMap<String, String> getSlotDefaultParams(){
        HashMap<String, String> requestParams =  new HashMap<>();
        requestParams.put("username", "alok@x.coz");
        requestParams.put("api_key", "a4aeb4e27f27b5786828f6cdf00d8d2cb44fe6d7");
        requestParams.put("vc", "276");
        requestParams.put("expert_username", "neha@healthifyme.com");
        requestParams.put("format", "json");
        return requestParams;
    }
}
