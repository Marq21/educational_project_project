package main.gui.table.model;

import main.gui.Refresh;
import main.model.Common;
import main.settings.Text;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

abstract public class MainTableModel extends AbstractTableModel implements Refresh {

    protected List<? extends Common> data;
    protected List<String> columns;
    Logger log = Logger.getLogger(MainTableModel.class.getName());

    public MainTableModel(List<? extends Common> data, String[] columns) {
        this.data = data;
        this.columns = new ArrayList<>(Arrays.asList(columns));
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return Text.get(columns.get(columnIndex));
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Object obj = getValueAt(0, columnIndex);
        if (obj == null) return Object.class;
        return obj.getClass();
    }

    @Override
    public void refresh() {
        updateData();
        fireTableStructureChanged();
        fireTableDataChanged();
    }

    public Common getCommonByRow(int row) {
        Common c = null;
        try {
            c = data.get(row);
        } catch (IndexOutOfBoundsException e) {
            log.info("Caught IndexOutOfBoundsException e\n" + e.getMessage()
                    + "\nTrying to delete or edit data from List but choose nothing: " + data.getClass());
        }
        return c;
    }

    abstract protected void updateData();
}
