package main.gui.handler;

import main.gui.MainFrame;
import main.gui.panel.StatisticsPanel;
import main.saveload.SaveData;
import main.settings.HandlerCode;

import java.awt.event.ActionEvent;

public class ChartHandler extends Handler {

    public ChartHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(HandlerCode.TYPE)) {
            ((StatisticsPanel) frame.getRightPanel()).nexType();
        }
        super.actionPerformed(e);
    }
}
