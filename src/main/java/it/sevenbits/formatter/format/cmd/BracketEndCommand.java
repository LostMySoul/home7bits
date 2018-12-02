package it.sevenbits.formatter.format.cmd;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.format.FormatterBuffer;

/**
 * Formatter command for bracket end
 */
public class BracketEndCommand implements ICommand {
    /**
     * constructor for bracket end command
     */
    public BracketEndCommand() {
    }

    @Override
    public void execute() {
        FormatterBuffer.decreaseNesting();
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
    }
}
