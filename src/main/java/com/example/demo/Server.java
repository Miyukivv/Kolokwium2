package com.example.demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private String login="hej";
    private String haslo="siema";
    private BufferedReader reader;

    public Server (int port){
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(){
        boolean isConnected = false;
            while(true){
                Socket clientSocket;
                try {
                    if(!isConnected) {
                        clientSocket = serverSocket.accept();

                        OutputStream output = clientSocket.getOutputStream();
                        PrintWriter pw = new PrintWriter(output, true);

//                    if (clientSocket.isConnected()){
//                        pw.println("Uzytkownik jest juz polaczony");
//                        clientSocket.close();
//                    }

                        InputStream input = clientSocket.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(input));

                        pw.println("Podaj login");
                        String userName;
                        userName = reader.readLine();
                        System.out.println(userName);

                        String password;
                        pw.println("Podaj haslo");
                        password = reader.readLine();
                        System.out.println(password);

                        if (userName.equals(login) && password.equals(haslo)) {
                            pw.println("Zostales polaczony");
                            isConnected = true;
                        } else {
                            pw.println("Podano zle dane");
                            clientSocket.close();
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
