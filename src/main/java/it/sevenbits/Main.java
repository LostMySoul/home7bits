package it.sevenbits;

import it.sevenbits.format.Formatter;

public class Main {
    public static void main(String[] args) {
        //Improvised main
        //TODO: rework main class
        String formatted = "aaa {\n" +
                "    bbbb;\n" +
                "    ccc;\n" +
                "}";
        System.out.println("Formatted string to test:\n" + formatted);

        Formatter formatter = new Formatter();
        System.out.println("\nFormatted string after formatter:\n" + formatter.format(formatted));
    }
}
