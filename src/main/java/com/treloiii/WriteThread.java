package com.treloiii;

import com.google.gson.Gson;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class WriteThread extends Thread {

    private Socket socket;
    private PrintWriter writer;
    private Client client;
    private boolean isStop=false;
    private Gson gson;

    WriteThread(Socket socket,Client client){
        this.socket=socket;
        this.client=client;
        this.gson=new Gson();
        try {
            OutputStream out=socket.getOutputStream();
            this.writer=new PrintWriter(out,true);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setStop(){
        isStop=true;
    }

    @Override
    public void run() {
        try {
            this.writer.println(gson.toJson(new Message("connect", this.client.getUsername())));
            System.out.println("connected");
            while (!isStop) {
                System.out.println("send ping");
                Thread.sleep(5000);
                this.writer.println(gson.toJson(new Message("ping", this.client.getUsername())));
            }
            System.out.println("close");
            socket.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
