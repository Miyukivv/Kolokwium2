package com.example.demo;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread{
    private BufferedReader reader;   // Strumień wejściowy do odbierania wiadomości od klienta
    private PrintWriter out;
    private Socket socket;

    // Konstruktor przyjmujący socket klienta i referencję do serwera
    public ClientHandler(Socket socket, BufferedReader reader, PrintWriter out) {
        this.socket = socket;
        this.reader = reader;
        this.out = out;
    }

    @Override
    public void run() {
        try {
            String message;

            // Pętla do odczytywania wiadomości od administratora, np. "ban 4"
            while ((message = reader.readLine()) != null) {
                System.out.println("Masz wiadomosc");

                System.out.println(message);

                String[] messageParts = message.split(" ");
//                messageParts[0] = "ban"
//                messageParts[1] = "4"

                if (messageParts.length==2 && messageParts[0].equals("ban") && isInteger(messageParts[1])){
                    int token = Integer.parseInt(messageParts[1]);
//                    coś nie działa - usuwa pierwszy a potem nie
//                    TODO: naprawić
                    synchronized (this) {
                        int numberOfDeletedRecords=AdministratorUtills.ban(token);
                        out.println(String.format("Usunieto %d rekordow", numberOfDeletedRecords));
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Obsługa błędów wejścia/wyjścia
        }
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    // Metoda do wysyłania wiadomości do klienta
    public void sendMessage(String message) {
        out.println(message); // Wysłanie wiadomości przez strumień wyjściowy
    }
}
