package it.sevenbits.formatter.format.cmd;

import it.sevenbits.formatter.cfg.Config;
import it.sevenbits.formatter.command.ICommand;
import it.sevenbits.formatter.format.FormatterBuffer;

/**
 * Formatter command for regular line
 */
public class RegLineCommand implements ICommand {
    /**
     * constructor for regular line command
     */
    public RegLineCommand() {
    }

    @Override
    public void execute() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < FormatterBuffer.getCurrentNesting(); i++) {
            for (int j = 0; j < Config.INDENT_NUM; j++) {
                sb.append(Config.WHITESPACE);
            }
        }
        sb.append(FormatterBuffer.getBuffer().trim() + Config.LINE_JUMP_CHAR);
        FormatterBuffer.clearBuffer();
        FormatterBuffer.setBuffer(sb);
    }
}
