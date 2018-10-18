package it.sevenbits.format;

import it.sevenbits.cfg.Config;

public class Formatter {

    public String format(String str) {
        StringBuilder intentLine = new StringBuilder();
        for (int i = 0; i < Config.indentNum; i++) {
            intentLine.append(Config.intentChar);
        }
        StringBuilder outer = new StringBuilder();
        String work = str.trim();
        boolean newString = false;
        int nestCntr = 0;
        for (int i = 0; i < work.length(); ) {
            if (work.charAt(i) == Config.lineBreaker) {
                appendSymbol(outer, work.charAt(i));
                if (i + 1 < work.length() && work.charAt(i + 1) != Config.wrapEnd) {
                    jumpToNextLine(outer);
                    newString = true;
                    i = getNextNotSpaceIndex(work, i + 1);
                } else {
                    i++;
                }
            } else if (work.charAt(i) == Config.wrapStart) {
                appendSymbol(outer, work.charAt(i));
                jumpToNextLine(outer);
                newString = true;
                nestCntr++;
                /*if (i + 1 < work.length() && work.charAt(i + 1) != Config.wrapEnd)
                    intentLineWithCounter(outer, intentLine.toString(), nestCntr);*/
                i = getNextNotSpaceIndex(work, i + 1);
            } else if (work.charAt(i) == Config.wrapEnd) {
                if (!newString) {
                    jumpToNextLine(outer);
                } else {
                    newString = false;
                }
                nestCntr--;
                intentLineWithCounter(outer, intentLine.toString(), nestCntr);
                appendSymbol(outer, work.charAt(i));
                i++;
            } else {
                if (newString) {
                    intentLineWithCounter(outer, intentLine.toString(), nestCntr);
                    newString = false;
                }
                appendSymbol(outer, work.charAt(i));
                i++;
            }   //TODO:remove big if case
        }
        return outer.toString();
    }

    private int getNextNotSpaceIndex(String str, int index) {
        while (index < str.length() && str.charAt(index) == Config.intentChar || str.charAt(index) == Config.lineJumpChar) {
            index++;
        }
        return index;
    }

    private void jumpToNextLine(StringBuilder sb) {
        sb.append(Config.lineJumpChar);
    }

    private void appendSymbol(StringBuilder sb, char ch) {
        sb.append(ch);
    }

    private void intentLineWithCounter(StringBuilder sb, String intenter, int cntr) {
        for (int i = 0; i < cntr; i++) {
            sb.append(intenter);
        }
    }

}
