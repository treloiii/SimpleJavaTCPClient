package com.treloiii;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        if(args.length<3){
            System.out.println("Syntax error expected args");
        }
        Thread a=new Thread(()-> {
            Client client=new Client(args[0],Integer.parseInt(args[1]),args[2]);
            client.listenToServer();

        });
        a.start();
        a.join();

        System.out.println("stopped");

    }
}
