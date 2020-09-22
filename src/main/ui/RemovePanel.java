package ui;

import model.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


//this class set up and execute remove
public class RemovePanel extends JPanel {
    MusicTablePanel musicTable;
    private static JDialog d;


    private JLabel rowNumberLable;
    JPanel labelPane;
    JPanel fieldPane;


    private static String rowNumberString = "Row #: ";
    private static JFormattedTextField rowNumberField;


    public RemovePanel(MusicTablePanel musicTable) {
        this.musicTable = musicTable;
        rowNumberLable = new JLabel(rowNumberString);

        labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(rowNumberLable);

        fieldPane = new JPanel(new GridLayout(0, 1));
        rowNumberField = new JFormattedTextField();
        rowNumberField.setColumns(10);
        fieldPane.add(rowNumberField);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);


        JButton removeBtn = new JButton("Remove");
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkValidility(rowNumberField.getText())) {
                    removeBtn(musicTable);

                } else {
                    errorDialog();
                }
            }
        });

        add(removeBtn);
    }


    //EFFECTS: remove button execute
    private void removeBtn(MusicTablePanel musicTable) {
        int index = Integer.parseInt(rowNumberField.getText()) - 1;
//                    musicTable.getMusicList().remove(index);

        List<Music> currentList = musicTable.getMusicList();
        currentList.remove(index);

        musicTable.updateMusic(currentList, musicTable.getFilename());
        musicTable.updateUI();
    }

    //EFFECTS: appear error dialog if invalid thing happen
    private void errorDialog() {
        System.out.println("error");
        JFrame f = new JFrame();
        d = new JDialog(f, "Error Message", true);
        d.setLayout(new FlowLayout());
        JButton b = new JButton("OK");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
            }
        });
        d.add(new JLabel("Invalid row#, try again!"));
        d.add(b);
        d.setSize(300, 300);
        d.setVisible(true);
    }


    //EFFECTS: return boolean to see if it can be removed from table
    public boolean checkValidility(String userResponse) {
        try {
            int i = Integer.parseInt(userResponse);
            if (i <= 0 || i > musicTable.getMTable().getRowCount()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
