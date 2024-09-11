package com.example.demo;


public class AdministratorUtills {

    //banowanie
    public static int ban(int token){
        //                ban 4
//natychmiastowe wygaśnięcie(lub usunięcie) tokena

        Token.removeToken(token);

//usunięcie z bazy wszystkich rekordów związanych z tym tokenem

        Database database=Database.getInstance();
        int numberOfDeletedRecords=database.removeRecords(token);

//regenerację obrazu na podstawie pozostałych danych
        ImageRGB image=ImageRGB.getInstance();
        image.setImageBasedOnPixels();

        return numberOfDeletedRecords;
    }
}
