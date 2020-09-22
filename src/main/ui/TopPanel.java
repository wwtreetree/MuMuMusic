package ui;

import model.Music;
import persistence.Reader;
import persistence.Writer;

import java.util.List;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// store and load information, I set up them both in the same panel, it is not neccessary to separate them.
public class TopPanel extends JPanel {
    private JPanel musicTable;
    private JButton saveBtn;
    private JButton loadBtn;
    private JTextField fileText;

    public TopPanel(MusicTablePanel musicTable) {
        this.musicTable = musicTable;

        loadBtn = new JButton("LOAD");
        fileText = new JTextField(30);
        fileText.setText("MuMuMusic.json");

        setUpSaveBtn(musicTable);
        setUpLoadBtn(musicTable);

        add(saveBtn);
        add(loadBtn);
        add(fileText);
    }

    //MODIFIES: this
    //EFFECT: active load button
    private void setUpLoadBtn(MusicTablePanel musicTable) {
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(fileText.getText());
                String filename = fileText.getText();
                try {
                    List<Music> musicList = Reader.readMusics(new File("./data/" + filename));
                    musicTable.updateMusic(musicList, filename);
                    musicTable.repaint();
                    musicTable.updateUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTL active save button
    private void setUpSaveBtn(MusicTablePanel musicTable) {
        saveBtn = new JButton("SAVE");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(fileText.getText());
                String filename = fileText.getText();
                List<Music> musicList = musicTable.getMusicList();
                Writer.writeMusics(musicList, new File("./data/" + filename));
            }
        });
    }
}
