package main.gui.toolbar;

import main.gui.MainButton;
import main.gui.Refresh;
import main.gui.handler.Handler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

abstract public class AbstractToolBar extends JPanel implements Refresh {

    private final Handler handler;

    public AbstractToolBar(EmptyBorder border, Handler handler) {
        super();
        this.handler = handler;
        setBorder(border);
    }

    abstract protected void init();

    protected MainButton addButton(String title, ImageIcon icon, String action, boolean topIcon) {
        MainButton btn = new MainButton(title, icon, handler, action);
        if(topIcon) {
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        } else {
            btn.setHorizontalTextPosition(SwingConstants.RIGHT);
            btn.setVerticalTextPosition(SwingConstants.CENTER);
        }
        add(btn);
        return btn;
    }

    @Override
    public void refresh() {
        removeAll();
        init();
    }
}
