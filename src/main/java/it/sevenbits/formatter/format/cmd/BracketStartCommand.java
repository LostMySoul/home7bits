package it.sevenbits.formatter.format.cmd;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.format.FormatterBuffer;

/**
 * Formatter command for line with bracket start
 */
public class BracketStartCommand implements ICommand {
    /**
     * constructor for line with bracket start command
     */
    public BracketStartCommand() {
    }

    @Override
    public void execute() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < FormatterBuffer.getCurrentNesting(); i++) {
            for (int j = 0; j < Config.INDENT_NUM; j++) {
                sb.append(Config.INDENT_CHAR);
            }
        }
        String toAppend = FormatterBuffer.getBuffer();
        if (toAppend != null) {
            sb.append(toAppend.trim() + Config.LINE_JUMP_CHAR);
        }
        FormatterBuffer.clearBuffer();
        FormatterBuffer.setBuffer(sb);
        FormatterBuffer.increaseNesting();
    }
}
