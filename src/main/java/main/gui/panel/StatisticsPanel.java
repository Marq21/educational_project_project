package main.gui.panel;

import main.gui.Chart;
import main.gui.MainFrame;
import main.model.Statistics;
import main.saveload.SaveData;
import main.settings.Style;

import javax.swing.*;

public class StatisticsPanel extends RightPanel {

    public static final int TYPE_INCOME = 0;
    public static final int TYPE_EXP = 1;
    private int type = TYPE_INCOME;

    public StatisticsPanel(MainFrame frame) {
        super(frame, null, "STATISTICS", Style.ICON_PANEL_STATISTICS,
                new JPanel[]{
                        new FilterPanel(frame),
                        new StatisticsTypePanel(frame,"CHART_INCOME"),
                        new Chart(Statistics.
                                getDataForChartOnIncomeArticles(),
                                "CHART_INCOME",
                                SaveData.getInstance()
                                        .getBaseCurrency()
                                        .getCode())
                                .getPanel()
                });

    }

    public void nexType() {
        type++;
        if (type > TYPE_EXP) type = TYPE_INCOME;
    }

    @Override
    public void refresh() {
        Chart chart = null;
        String title = null;
        if (type == TYPE_INCOME) {
            title = "CHART_INCOME";
            chart = new Chart(Statistics.
                    getDataForChartOnIncomeArticles(),
                    title,
                    SaveData.getInstance()
                            .getBaseCurrency()
                            .getCode());
        } else if (type == TYPE_EXP) {
            title = "CHART_EXP";
            chart = new Chart(Statistics.
                    getDataForChartOnExpArticles(),
                    title,
                    SaveData.getInstance()
                            .getBaseCurrency()
                            .getCode());
        }
        setPanels(new JPanel[]{
                new FilterPanel(frame),
                new StatisticsTypePanel(frame, title),
                chart.getPanel()
        });
        super.refresh();
    }
}
