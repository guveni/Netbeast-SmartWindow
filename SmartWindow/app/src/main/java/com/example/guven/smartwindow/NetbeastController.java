package com.example.guven.smartwindow;

import java.util.HashMap;

/**
 * Created by guven on 28/11/15.
 */
public class NetbeastController {
    private String url = "http://home.netbeast/nb-window";

    public boolean TurnOnLight()
    {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Light","1");
        return ConnectionController.SendRequest(url,params);
    }

    public boolean TurnOffLight()
    {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Light","0");
        return ConnectionController.SendRequest(url,params);
    }
}
