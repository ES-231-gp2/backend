package com.ufcg.es.biblioconex.utils;

public class Formatador {

    public static String formatarIsbn(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            return "";
        }

        String cleanIsbn = isbn.replaceAll("[^0-9]", "");

        if (cleanIsbn.length() == 10) {
            isbn = cleanIsbn.substring(0, 2) + "-" +
                    cleanIsbn.substring(2, 5) + "-" +
                    cleanIsbn.substring(5, 9) + "-" +
                    cleanIsbn.charAt(9);
        } else if (cleanIsbn.length() == 13) {
            isbn = cleanIsbn.substring(0, 3) + "-" +
                    cleanIsbn.substring(3, 5) + "-" +
                    cleanIsbn.substring(5, 9) + "-" +
                    cleanIsbn.substring(9, 12) + "-" +
                    cleanIsbn.charAt(12);
        }

        return isbn;
    }
}
