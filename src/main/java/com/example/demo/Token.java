package com.example.demo;

import java.time.LocalDateTime;

public class Token {
    private int id;
    private LocalDateTime timeOfCreation;

    private static int actualValueOfToken=0;

    public Token() {
        id=actualValueOfToken++;
        timeOfCreation=LocalDateTime.now();
    }

  public int getId() {
        return id;
    }

    public LocalDateTime getTimeOfCreation() {
        return timeOfCreation;
    }


}
