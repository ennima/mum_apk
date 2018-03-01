package com.example.gvadmin.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.net.*;
import android.webkit.WebView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    public boolean isConecctedToInternet(String ipAddr) {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 "+ipAddr);
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String appIp = "192.168.196.125";
        String main_html = "login.html";
        if(isConecctedToInternet(appIp) ){
            Log.d("LOG","--------------->Conectado");
            appIp = "http://"+appIp+"/"+main_html;
            Log.d("LOG",appIp);
        }else{
            Log.d("LOG","--------------->No Conectado");
            appIp = "http://38.124.174.204:60259"+"/"+main_html;
            Log.d("LOG",appIp);
        }


        /*WebView myWebView = (WebView) this.findViewById(R.id.web1);
        myWebView.loadUrl(appIp);*/
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appIp));
        startActivity(browserIntent);

    }
}
