package main.gui;

import main.gui.handler.MainToolBarHandler;
import main.gui.handler.MainWindowHandler;
import main.gui.menu.MainMenu;
import main.gui.panel.*;
import main.gui.toolbar.MainToolBar;
import main.settings.Style;
import main.settings.Text;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Refresh{

    private final GridBagConstraints constraints;
    private final MainMenu mb;
    private final LeftPanel lp;
    private RightPanel rp;
    private final MainToolBar tb;

    public MainFrame() {
        super(Text.get("PROGRAM_NAME"));

        setResizable(false);
        setIconImage(Style.ICON_MAIN.getImage());
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        mb = new MainMenu(this);
        setJMenuBar(mb);

        setLayout(new GridBagLayout());

        constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;

        tb = new MainToolBar(new MainToolBarHandler(this));
        add(tb, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTH;

        lp = new LeftPanel(this);
        add(lp, constraints);
        setRightPanel(new OverviewPanel(this));

        pack();
        setLocationRelativeTo(null);

        addWindowListener(new MainWindowHandler());
    }

    public void setRightPanel(RightPanel panel) {
        if (rp != null) remove(rp);
        constraints.gridy = 1;
        constraints.gridx = 1;
        rp = panel;
        panel.setBorder(Style.BORDER_PANEL);
        add(rp, constraints);
        pack();
    }

    @Override
    public void refresh() {
        SwingUtilities.updateComponentTreeUI(this);
        tb.refresh();
        lp.refresh();
        rp.refresh();
        pack();
    }

    public MainMenu getMenu() {
        return mb;
    }

    public RightPanel getRightPanel() {
        return rp;
    }
}
