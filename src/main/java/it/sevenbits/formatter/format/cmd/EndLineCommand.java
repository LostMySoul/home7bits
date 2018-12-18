package it.sevenbits.formatter.format.cmd;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.format.FormatterBuffer;

/**
 * Formatter command for end line
 */
public class EndLineCommand implements ICommand {
    /**
     * constructor for end line command
     */
    public EndLineCommand() {
    }

    @Override
    public void execute() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < FormatterBuffer.getCurrentNesting(); i++) {
            for (int j = 0; j < Config.INDENT_NUM; j++) {
                sb.append(Config.WHITESPACE);
            }
        }
        String toAppend = FormatterBuffer.getBuffer();
        if (toAppend != null) {
            sb.append(toAppend.trim() + Config.LINE_JUMP_CHAR);
        }
        FormatterBuffer.clearBuffer();
        FormatterBuffer.setBuffer(sb);
    }
}
