package it.sevenbits.formatter.format.cmd;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.format.FormatterBuffer;

/**
 * Formatter command for comment
 */
public class CommentCommand implements ICommand {
    /**
     * constructor for comment command
     */
    public CommentCommand() {
    }

    @Override
    public void execute() {
        String prev = FormatterBuffer.getPreviousLexeme();
        String comment = FormatterBuffer.getBuffer().trim();
        if (prev != null && prev.length() != 0) {
            StringBuilder sb = new StringBuilder(prev);
            sb.delete(sb.length() - 1, sb.length());
            for (int i = 0; i < Config.INDENT_NUM; i++) {
                sb.append(Config.INDENT_CHAR);
            }
            sb.append(comment);
            FormatterBuffer.setPreviousLexeme(sb.toString() + Config.LINE_JUMP_CHAR);
            FormatterBuffer.setBuffer(new StringBuilder());
        } else {
            FormatterBuffer.setPreviousLexeme(comment);
            FormatterBuffer.setBuffer(new StringBuilder());
        }

    }
}
