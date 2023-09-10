package main.gui.panel;

import main.gui.MainFrame;
import main.gui.Refresh;

import javax.swing.*;

abstract public class AbstractPanel extends JPanel implements Refresh {

    protected final MainFrame frame;

    public AbstractPanel (MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void refresh() {
        removeAll();
        init();
    }

    abstract protected void init();

}
