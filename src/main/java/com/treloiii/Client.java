package com.treloiii;

import java.net.Socket;

public class Client {
    private String hostname;
    private int port;
    private String username;
    private WriteThread wt;

    Client(String hostname,int port,String username){
        this.hostname=hostname;
        this.port=port;
        this.username=username;
    }

   public void listenToServer(){
        try{
            Socket socket=new Socket(hostname,port);
            Thread rt=new ReadThread(socket,this);
            rt.start();
            wt=new WriteThread(socket,this);
            wt.start();
            rt.join();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void disconnect(){
        wt.setStop();
    }

    public String getUsername() {
        return username;
    }
}
