package com.example.demo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Token {
    private int id;
    private LocalDateTime timeOfCreation;

    private static int actualValueOfToken=0;

    private static List<Token> tokens = new ArrayList<Token>();
    
    public Token() {
        id=actualValueOfToken++;
        timeOfCreation=LocalDateTime.now();
        tokens.add(this);
    }

    public static List<Token> getTokens() {
        return tokens;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTimeOfCreation() {
        return timeOfCreation;
    }

/*
LocalDateTime start = LocalDateTime.now();
//some time inbetween
LocalDateTime end = LocalDateTime.now();

long elapsedMinutes = ChronoUnit.MINUTES.between(start, end);
 */
    public boolean isTokenActive() {
        LocalDateTime now = LocalDateTime.now();
        long elapsedMinutes= ChronoUnit.MINUTES.between(timeOfCreation, now);

        return elapsedMinutes<5;
    }

    static class TokenDTO {
        public int id;
        public LocalDateTime timeOfCreation;
        public boolean isActive;

        public TokenDTO(Token token) {
            this.id = token.id;
            this.timeOfCreation = token.timeOfCreation;
            this.isActive = token.isTokenActive();
        }
    }
}
