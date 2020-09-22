package ui;

import model.ComeFrom;
import model.MusicLists;
import model.MusicStyle;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MuMuGUI extends JPanel {
    private static ImagePanel ip;
    private static MusicLists ml;
    static MusicTablePanel mtable;
    static BufferedImage image1;
    static Image background;
    static ImageIcon iconCat;
    static JLabel catBackGroundImage;

    static JPanel backPanel;
    static JPanel titlePanel;
    static JPanel musicTablePanel;
    static JPanel actionPanel;
    static JFrame jframe;
//    static Add a;
    static RemovePanel rm;
    static MusicPlayerPanel mp;


    public MuMuGUI() {
        ml = new MusicLists();
        ml.loadMusics();

        mtable = new MusicTablePanel("MuMuMusic.json", ml.getMusicList());
        editMusicStyle();

        setUpTitlePanel();
        setUpMusicTablePanel();
        editComeFrom();

        rm = new RemovePanel(mtable);
        mp = new MusicPlayerPanel(mtable);

        try {
            background = ImageIO.read(new File("./image/black-cat-adoption-and-other-october-facts.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setUpActionPanel();

        setUpBackPanel();

        setUpJFrame();


    }

    //MODIFIES: this
    //EFFECTS: set up ActionPanel
    private void setUpActionPanel() {
        actionPanel = new JPanel();
//        actionPanel.setBackground(new Color(0,0,0,0));

        AddPanel textBox = new AddPanel(mtable);
        actionPanel.add(textBox);
//        actionPanel.add(a);
        actionPanel.add(rm);
        actionPanel.add(mp);



        iconCat = new ImageIcon("./image/black-cat-adoption-and-other-october-facts.jpeg");
        catBackGroundImage = new JLabel(iconCat);

        catBackGroundImage.setBounds(0,0,1000,400);


        // actionPanel.add(catBackGroundImage);
        actionPanel.revalidate();
        actionPanel.repaint();


    }

    //MODIFIES: this
    //EFFECTS: set up MusicTablenPanel
    private static void setUpMusicTablePanel() {
        musicTablePanel = new JPanel();
        musicTablePanel.setBackground(new Color(0,0,0,0));
        //musicTablePanel.setBackground(Color.BLACK);
        musicTablePanel.setPreferredSize(new Dimension(1000, 400));


        musicTablePanel.add(mtable);
        mtable.setPreferredSize(new Dimension(950, 400));
    }

    //MODIFIES: this
    //EFFECTS: set up TitlePanel
    private static void setUpTitlePanel() {
        titlePanel = new JPanel();
//        titlePanel.setBackground(Color.yellow);
        titlePanel.setBackground(new Color(0,0,0,0));
        titlePanel.setPreferredSize(new Dimension(1000, 30));
        TopPanel sl = new TopPanel(mtable);
        titlePanel.add(sl);
    }

    //MODIFIES: this
    //EFFECTS: set up BackPanel
    private static void setUpBackPanel() {
        backPanel = new ImagePanel();
//        backPanel.setBackground(Color.red);
        backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));
//
        backPanel.add(titlePanel);
        backPanel.add(musicTablePanel);
        backPanel.add(actionPanel);
    }

    //MODIFIES: this
    //EFFECTS: set up ActionPanel
    private static void setUpJFrame() {
        jframe = new JFrame();
        jframe.setBackground(Color.BLACK);
        jframe.setSize(new Dimension(1200, 1200));

        jframe.add(backPanel);

        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }



    //EFFECTS: editMusicStyle in combo box
    private static void editMusicStyle() {
        TableColumn editMusicStyleColumn = mtable.getMTable().getColumnModel().getColumn(5);

        JComboBox<String> musicStyleComboBox = new JComboBox<>();
        musicStyleComboBox.addItem(MusicStyle.POP.toString());
        musicStyleComboBox.addItem(MusicStyle.ROCK.toString());
        musicStyleComboBox.addItem(MusicStyle.ELECTRONIC.toString());
        musicStyleComboBox.addItem(MusicStyle.COUNTRY.toString());
        musicStyleComboBox.addItem(MusicStyle.CLASSICAL.toString());
        musicStyleComboBox.addItem(MusicStyle.HIP_HOP.toString());
        musicStyleComboBox.addItem(MusicStyle.JAZZ.toString());
        musicStyleComboBox.addItem(MusicStyle.OTHER.toString());

        editMusicStyleColumn.setCellEditor(new DefaultCellEditor(musicStyleComboBox));
    }

    //EFFECTS: edit Come From box
    private static void editComeFrom() {
        TableColumn editArtistComeFromColumn = mtable.getMTable().getColumnModel().getColumn(2);

        JComboBox<String> artistComeFromComboBox = new JComboBox<>();
//
        artistComeFromComboBox.addItem(ComeFrom.ASIA.toString());
        artistComeFromComboBox.addItem(ComeFrom.NORTH_AMERICA.toString());
        artistComeFromComboBox.addItem(ComeFrom.SOUTH_AMERICA.toString());
        artistComeFromComboBox.addItem(ComeFrom.AFRICA.toString());
        artistComeFromComboBox.addItem(ComeFrom.AUSTRALIA.toString());
        artistComeFromComboBox.addItem(ComeFrom.ANTARCTICA.toString());
        artistComeFromComboBox.addItem(ComeFrom.EUROPE.toString());

        editArtistComeFromColumn.setCellEditor(new DefaultCellEditor(artistComeFromComboBox));
        int c = artistComeFromComboBox.getItemCount();

        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        editArtistComeFromColumn.setCellRenderer(renderer);

        mtable.getMTable().updateUI();
    }


    //MODIFIES: this
    //EFFECTS: draw image
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, actionPanel);
    }



//    public void loadImage(String image1Name) {
//        try {
//            image1 = ImageIO.read(MuMuGUI.class.getResource(image1Name));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}











