package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//play audio from audio path
public class MusicPlayerPanel extends JPanel implements LineListener {
    MusicTablePanel musicTable;
    private JLabel audioPathLable;
    JPanel labelPane;
    JPanel fieldPane;
    boolean playCompleted;
    Clip audioClip;
    boolean isPlay = false;
    JButton playMusicBtn;
    JButton stopMusicBtn;


    private static String audioPathString = "Audio Path: ";
    private static JFormattedTextField audioPathField;


    public MusicPlayerPanel(MusicTablePanel musicTable) {
        this.musicTable = musicTable;
        audioPathLable = new JLabel(audioPathString);

        labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(audioPathLable);


        fieldPane = new JPanel(new GridLayout(0, 1));
        audioPathField = new JFormattedTextField();
        audioPathField.setColumns(15);
        fieldPane.add(audioPathField);

        setUpMusicPlayerBorder();
        setUpPlayAndStopBtn();
    }

    //MODIFIES: this
    //EFFECTS: set up play and stop btn
    private void setUpPlayAndStopBtn() {
        playMusicBtn = new JButton("play");
        stopMusicBtn = new JButton("stop");
        add(playMusicBtn);
        add(stopMusicBtn);


        playMusicBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPlay == false) {
                    String path = audioPathField.getText();
                    play(path);
                }
            }

        });


        stopMusicBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioClip.stop();
                isPlay = false;

            }
        });
    }

    //MODIFES: this
    //EFFECTS: set up Music player border
    private void setUpMusicPlayerBorder() {
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
    }

    void play(String audioFilePath) {
        isPlaysetUp();

        File audioFile = new File(audioFilePath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);

            audioClip.start();


//            while (!playCompleted) {
//                // wait for the playback completes
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            audioClip.close();
//            if (playCompleted) {
//                isPlay = false;
//            } else {
//                isPlay = true;
//            }
//            isPlay = true;


            System.out.println("playing");

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: to see if the play button is pressed before
    private void isPlaysetUp() {
        if (playCompleted) {
            audioClip.close();
            isPlay = false;
        } else {
            isPlay = true;
        }
    }


    //EFFECTS: update audio clip information
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }

    }
}
