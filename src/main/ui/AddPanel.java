

package ui;

import model.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;


// this class is for add information  on my panel
public class AddPanel extends JPanel {

    JFormattedTextField memberNameField;
    JComboBox memberComeFromList;
    JCheckBox isFavoriteMemberCheckBox;
    JLabel memberNameLabel;
    JLabel memberComeFromLabel;
    JLabel isFavoriteMemberLabel;
    JButton addMemberBtn;
    JFrame memberFrame;

    private static JDialog d;
    JPanel fieldPane;
    JPanel addBtnPane;
    JButton addBtn;
    JPanel labelPane;

    //Labels to identify the fields
    private JLabel artistNameLabel;
    private JLabel artistComeFromLabel;
    private JLabel isFavoriteArtistLabel;
    private JLabel musicNameLabel;
    private JLabel musicStyleLabel;
    private JLabel isFavoriteMusicLabel;
    private JLabel memberLabel;
    private JLabel audioPathLabel;


    //Strings for the labels
    private static String artistNameString = "Artist Name: ";
    private static String artistComeFromString = "Artist ComeFrom: ";
    private static String isFavoriteArtistString = "Favorite Artist?: ";
    private static String musicNameString = "Music Name: ";
    private static String musicStyleString = "Music Style: ";
    private static String isFavoriteMusicString = "Favorite Music?: ";
    private static String memberString = "Member Name: ";
    private static String audioPathString = "Audio Path: ";


    //Fields for data entry
    private JFormattedTextField artistNameField;
    private JFormattedTextField musicNameField;
    private JButton memberFieldBtn;
    private JFormattedTextField audioPathField;
    JComboBox comeFromList;
    JComboBox musicStyleList;
    JCheckBox isFavoriteMusicCheckBox;
    JCheckBox isFavoriteArtistCheckBox;


    List<Individual> memberList; // stored members when adding members
    List<Music> currentMusicList; //stored music when press add btn

    //Formats to format and parse numbers
    public AddPanel(MusicTablePanel musicTable) {
        super(new BorderLayout());
        memberList = new ArrayList<>();
        //Create the labels.


        addBtn = new JButton("ADD");
        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkValidity()) {
//              model.addRow(textFieldDemo.getTextInfo());
                    addBtnSetUp(musicTable);
                    memberList.clear();
                    resetTextField();
                    currentMusicList.clear();

                } else {
                    errorDialog();
                }

            }
        });

        setUpJLabel();
        setUpTextFields();
        setUpLabelTextFieldPairs();
        layOutLabels();
        layOutTextFieldsInPanel();
        setUpBorder();
    }

    private void resetTextField() {
        artistNameField.setText("");
        isFavoriteArtistCheckBox.setSelected(false);
        isFavoriteMusicCheckBox.setSelected(false);
        musicNameField.setText("");
        comeFromList.setSelectedItem(ComeFrom.ASIA);
        musicStyleList.setSelectedItem(MusicStyle.POP);
        audioPathField.setText("");
    }


    //MODIFIES: this, musicTable
    //EFFECT: set up add button and take action too
    private void addBtnSetUp(MusicTablePanel musicTable) {
        currentMusicList = musicTable.getMusicList();
        Object[] info = getTextInfo();
        if (memberList.isEmpty()) {
            Music oneMusic1 = new Music(new Individual((String) info[0], ComeFrom.valueOf((String) info[1]),
                    (boolean) info[2]), (String) info[3], MusicStyle.valueOf((String) info[4]), (boolean) info[5]);
            oneMusic1.setAudioPath((String) info[7]);
            currentMusicList.add(oneMusic1);
        } else {
            Music oneMusic2 = new Music(new Group((String) info[0], ComeFrom.valueOf((String) info[1]),
                    (boolean) info[2]), (String) info[3], MusicStyle.valueOf((String) info[4]), (boolean) info[5]);

            //oneMusic2.setMemberInfo((String) info[6]);
//            oneMusic2.setMembers(memberList);

            for (Individual individual : memberList) {
                ((Group) oneMusic2.getArtist()).addToGroupMemberList(individual);
            }


            oneMusic2.setAudioPath((String) info[7]);

            currentMusicList.add(oneMusic2);
        }
        musicTable.updateMusic(currentMusicList, musicTable.getFilename());
//                musicTable.repaint();
        memberList.clear();
        musicTable.updateUI();
    }

    //MODIFIES: this
    //EEFECT: set up border arrangement
    private void setUpBorder() {
        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
        add(addBtnPane, BorderLayout.PAGE_END);
    }






    //MODIFIES: this
    //EFFECT: layout textfield in panel
    private void layOutTextFieldsInPanel() {
        //Layout the text fields in a panel.
        fieldPane = new JPanel(new GridLayout(0, 1));

        fieldPane.add(artistNameField);
        fieldPane.add(comeFromList);
        fieldPane.add(isFavoriteArtistCheckBox);
        fieldPane.add(musicNameField);
        fieldPane.add(musicStyleList);
        fieldPane.add(isFavoriteMusicCheckBox);
        fieldPane.add(memberFieldBtn);
        fieldPane.add(audioPathField);

        addBtnPane = new JPanel();
        addBtnPane.add(addBtn);
    }


    //MODIFIES: this
    //EFFECTS: lay out labels
    private JPanel layOutLabels() {
        //Lay out the labels in a panel.
        labelPane = new JPanel(new GridLayout(0, 1));

        labelPane.add(artistNameLabel);
        labelPane.add(artistComeFromLabel);
        labelPane.add(isFavoriteArtistLabel);
        labelPane.add(musicNameLabel);
        labelPane.add(musicStyleLabel);
        labelPane.add(isFavoriteMusicLabel);
        labelPane.add(memberLabel);
        labelPane.add(audioPathLabel);
        return labelPane;
    }


    //MODIFES: this
    //EFFECTS: set accessibility for label and textfield in pairs
    private void setUpLabelTextFieldPairs() {
        artistNameLabel.setLabelFor(artistNameField);
        musicNameLabel.setLabelFor(musicNameField);
        audioPathLabel.setLabelFor(audioPathField);
    }


    //MODIFIES: this
    //EFFECTS: //Create the text fields and set them up.
    private void setUpTextFields() {

        //Create the text fields and set them up.
        artistNameField = new JFormattedTextField();
        artistNameField.setColumns(10);
        audioPathField = new JFormattedTextField();
        audioPathField.setColumns(15);

        comeFromList = new JComboBox();
        setUpComeFromTextFields(comeFromList);

        isFavoriteArtistCheckBox = new JCheckBox();
        isFavoriteArtistCheckBox.setSelected(true);

        musicNameField = new JFormattedTextField();
        musicNameField.setColumns(10);


        MusicStyle[] musicStyleSelection = {MusicStyle.POP, MusicStyle.ELECTRONIC, MusicStyle.JAZZ, MusicStyle.ROCK,
                MusicStyle.HIP_HOP, MusicStyle.COUNTRY, MusicStyle.CLASSICAL, MusicStyle.OTHER};
        musicStyleList = new JComboBox(musicStyleSelection);
        musicStyleList.setSelectedIndex(7);
        musicStyleList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        isFavoriteMusicCheckBox = new JCheckBox();
        isFavoriteMusicCheckBox.setSelected(true);

        setUpAddMemberBtn();


    }

    //MODIFIES: this
    //EFFECTS： set up add member button
    private void setUpAddMemberBtn() {
        memberFieldBtn = new JButton("Add Member?");
        memberFieldBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memberInfoDialog();
            }
        });
    }

    //MODIFIES: this, memberComboList
    //EFFECTS:  set up comeFrom text fields
    private void setUpComeFromTextFields(JComboBox memberComboList) {
        ComeFrom[] comeFromSelection = {ComeFrom.ASIA, ComeFrom.NORTH_AMERICA, ComeFrom.SOUTH_AMERICA,
                ComeFrom.AUSTRALIA, ComeFrom.EUROPE, ComeFrom.AFRICA, ComeFrom.ANTARCTICA};
        memberComboList.setModel(new DefaultComboBoxModel(comeFromSelection));
        memberComboList.setSelectedIndex(6);
    }

    //MODIFIES: this
    //EFFECTS: set up JLabel
    private void setUpJLabel() {
        artistNameLabel = new JLabel(artistNameString);
        artistComeFromLabel = new JLabel(artistComeFromString);
        isFavoriteArtistLabel = new JLabel(isFavoriteArtistString);
        musicNameLabel = new JLabel(musicNameString);
        musicStyleLabel = new JLabel(musicStyleString);
        isFavoriteMusicLabel = new JLabel(isFavoriteMusicString);
        memberLabel = new JLabel(memberString);
        audioPathLabel = new JLabel(audioPathString);
    }

    //MODIFIES: this
    //EFFECTS: show error Dialog if incorrect is typing
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
        d.add(new JLabel("Incompleted Information, try again!"));
        d.add(b);
        d.setSize(300, 300);
        d.setVisible(true);
    }


    //EFFECTS:
    private void memberInfoDialog() {

        memberComeFromList = new JComboBox();
        memberNameLabel = new JLabel("Member Name: ");
        memberComeFromLabel = new JLabel("Member Come From: ");
        isFavoriteMemberLabel = new JLabel("Favorite Member?: ");
        memberNameField = new JFormattedTextField();
        memberNameField.setColumns(10);
        setUpComeFromTextFields(memberComeFromList);
        isFavoriteMemberCheckBox = new JCheckBox();
        isFavoriteMemberCheckBox.setSelected(true);
        memberFrame = new JFrame();
        addMemberBtn = new JButton("Add");


        addMemberBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(memberNameField.getText().isEmpty())) {
                    setUpMemberAdd();

                } else {
                    errorDialog();
                }
            }
        });

        memberDialog();

    }


    //EFFECTS: set up member dialog information
    private void memberDialog() {
        d = new JDialog(memberFrame, "Member Info", true);
        d.setLayout(new FlowLayout());
        d.add(memberNameLabel);
        d.add(memberNameField);
        d.add(memberComeFromLabel);
        d.add(memberComeFromList);
        d.add(isFavoriteMemberLabel);
        d.add(isFavoriteMemberCheckBox);
        d.add(addMemberBtn);

        d.setSize(730, 200);
        d.setVisible(true);
    }

    //EFFECTS: set up  member information
    private void setUpMemberAdd() {
        //d.setVisible(false);
        Individual oneMemberInfo = new Individual(memberNameField.getText(),
                (ComeFrom) memberComeFromList.getSelectedItem(), isFavoriteMemberCheckBox.isSelected());

        memberList.add(oneMemberInfo);
        // retrieve inputs, and create a Individual
        // save it to a global List. memberToadd
        // clear the inputs

        memberNameField.setText("");
        memberComeFromList.setSelectedItem(ComeFrom.ASIA);
        isFavoriteMemberCheckBox.setSelected(false);


        memberNameField.setText("");
        memberComeFromList.setSelectedIndex(0);
        isFavoriteMemberCheckBox.setSelected(false);
    }


    //EFFECTS: get the textInformation for text fields together
    public Object[] getTextInfo() {
        Object[] arr = {artistNameField.getText(), comeFromList.getSelectedItem().toString(),
                isFavoriteArtistCheckBox.isSelected(), musicNameField.getText(),
                musicStyleList.getSelectedItem().toString(), isFavoriteMusicCheckBox.isSelected(),
                memberList, audioPathField.getText()};
        return arr;
    }


    //EFFECTS: check if the information is valid to add to Jtable
    public boolean checkValidity() {
        if (artistNameField.getText().isEmpty() || musicNameField.getText().isEmpty()
                || audioPathField.getText().isEmpty()) {
            System.out.println("invalid");
            return false;
        } else {
            return true;
        }
    }


}


