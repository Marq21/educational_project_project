package main.gui.panel;

import main.gui.MainButton;
import main.gui.MainFrame;
import main.gui.handler.ChartHandler;
import main.settings.HandlerCode;
import main.settings.Text;

public final class StatisticsTypePanel extends AbstractPanel {

    private final String title;

    public StatisticsTypePanel(MainFrame frame, String title) {
        super(frame);
        this.title = Text.get(title);
        init();
    }

    @Override
    protected void init() {
        MainButton type = new MainButton(title, new ChartHandler(frame), HandlerCode.TYPE);
        add(type);
    }
}
