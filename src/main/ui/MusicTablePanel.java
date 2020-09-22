package ui;

/*
 * TableSortDemo.java requires no other files.
 */

import model.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

//Jtable  to display information
public class MusicTablePanel extends JPanel {
    List<TableCellEditor> editors = new ArrayList<TableCellEditor>();

    private boolean debug = false;
    private String filename;
    private MyTableModel tableModel;
    private JTable table;

    public MusicTablePanel(String filename, List<Music> musicList) {
        super(new GridLayout(1, 0));
        this.filename = filename;
        tableModel = new MyTableModel(musicList);
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    //EFFECTS: get file name
    public String getFilename() {
        return this.filename;
    }

    //EFFECTS: get table
    public JTable getMTable() {
        return table;
    }


    //EFFECTS: get music list
    public List<Music> getMusicList() {
        return tableModel.getMusicListFromTable();
    }

    //MODIFIES: this
    //EFFECTS: update music table
    public void updateMusic(List<Music> musicList, String newFilename) {
//        Writer.writeMusics(getMusicList(), new File("./data/" + this.filename));
        removeAll();

        this.filename = newFilename;
        tableModel = new MyTableModel(musicList);
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }


    private class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"# ", "Artist Name",
                "ComeFrom",
                "Favorite Artist?",
                "Music Name",
                "Music Style",
                "Favorite Music?",
                "Member Info",
                "Audio Path"};
        private Object[][] data;


        public MyTableModel(List<Music> musicList) {
            data = new Object[musicList.size()][columnNames.length];
            int i = 0;
            int k = 1;
            for (Music music : musicList) {

                data[i] = new Object[]{
                        k,
                        music.getArtist().getArtistName(),
                        music.getArtist().getComeFrom(),
                        music.getArtist().isFavoriteArtist(),
                        music.getMusicName(),
                        music.getMusicStyle(),
                        music.getIsFavoriteMusic(),

//                        music.getArtist().getMemberInfo(),

                        music.getArtist(),
//                        music.getMembers(),
                        music.getAudioPath()
                };
                i++;
                k++;
            }
        }

        //EFFECTS: get music List from table
        private List<Music> getMusicListFromTable() {
            List<Music> musicList = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {
                if (data[i][7] instanceof Individual) {
                    Music m = new Music(new Individual((String) data[i][1], (ComeFrom) data[i][2],
                            (boolean) data[i][3]), (String) data[i][4], (MusicStyle) data[i][5], (boolean) data[i][6]);
                    if (data[i][8] != "") {
                        m.setAudioPath((String) data[i][8]);
                    }


                    musicList.add(m);
                } else {
//          Music m = new Music((Group) data[i][7], (String) data[i][4], (MusicStyle) data[i][5], (boolean) data[i][6]);
//
//                    if (data[i][8] != "") {
//                        m.setAudioPath((String) data[i][8]);
//                    }
//
//                    musicList.add(m);
//                if (data[i][7] instanceof Individual) {
//                    Music m = new Music(new Individual((String) data[i][1], (ComeFrom) data[i][2],
//                   (boolean) data[i][3]), (String) data[i][4], (MusicStyle) data[i][5], (boolean) data[i][6]);
//                    if (data[i][8] != "") {
//                        m.setAudioPath((String) data[i][8]);
//                    }
//                    musicList.add(m);
//                } else {
////                    Music m = new Music(new Individual((String) data[i][1], (ComeFrom) data[i][2],
////                    (boolean) data[i][3]), (String) data[i][4], (MusicStyle) data[i][5], (boolean) data[i][6]);
////                    //m.setMemberInfo();
////                    m.setMemberInfo((String) data[i][7]);


                    Music m = new Music((Group) data[i][7], (String) data[i][4],
                            (MusicStyle) data[i][5], (boolean) data[i][6]);
                    // m.setMembers((List<Individual>) data[i][7]);


                    if (data[i][8] != "") {
                        m.setAudioPath((String) data[i][8]);
                    }

                    musicList.add(m);
                }
            }
            return musicList;
        }


        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }


        public Object getValueAt(int row, int col) {


//            if ((col == 7) && (!getColumnName(7).isEmpty())) {
//                return ((Artist) data[row][col]).getMemberInfo();
//                return ((Group) data[row][col]).getMemberInfo();
//            }

            if (col == 7) {
                return ((Artist) data[row][col]).getMemberInfo();

            }
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            if (getValueAt(0, c) == null) {
                System.out.println(c);
            }
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        //EFFECTS: determine which column can be edit
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col == 7 || col == 0) {
                return false;
            }
            return true;
        }

        public void editEnumValues() {

        }


        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (debug) {
                System.out.println("Setting value at " + row + "," + col
                        + " to " + value
                        + " (an instance of "
                        + value.getClass() + ")");
            }

            data[row][col] = value;
            // Normally, one should call fireTableCellUpdated() when
            // a value is changed.  However, doing so in this demo
            // causes a problem with TableSorter.  The tableChanged()
            // call on TableSorter that results from calling
            // fireTableCellUpdated() causes the indices to be regenerated
            // when they shouldn't be.  Ideally, TableSorter should be
            // given a more intelligent tableChanged() implementation,
            // and then the following line can be uncommented.
            // fireTableCellUpdated(row, col);

            if (debug) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i = 0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j = 0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }


}
