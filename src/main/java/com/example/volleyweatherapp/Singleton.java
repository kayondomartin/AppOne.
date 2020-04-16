package com.example.volleyweatherapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singleton {
    private static Singleton singleton;
    private RequestQueue request;
    Context context;

    Singleton(Context context){
            this.context = context;
            request = getRequest();

    }

    public static synchronized Singleton getSingleton(Context context) {
        if(singleton==null) {
            singleton = new Singleton(context);
        }
        return singleton;
    }

    public RequestQueue getRequest() {

        if(request==null){
            request = Volley.newRequestQueue(context.getApplicationContext());
        }
        return request;
    }

    public <T> void addRequestQueue(Request<T> req){
        getRequest().add(req);
    }
}
