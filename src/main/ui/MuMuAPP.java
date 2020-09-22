package ui;

import model.*;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.util.Scanner;

public class MuMuAPP {
    private ArtistLists artistLists;
    private MusicLists musicLists;
    private Group groupInfo;
    private Scanner input;


    //EFFECTS: run the MuMu application
    public MuMuAPP() {
        musicLists = new MusicLists();
        musicLists.loadMusics();
        artistLists = new ArtistLists();
        //get each music from musiclist and get the artist to add the artistList
        runMuMu();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMuMu() {
        boolean keepGoing = true;

        init();

        while (keepGoing) {
            displayMuMuMainMenu();
            String command1 = input.next();
            if (command1.equals("#")) {
                keepGoing = false;
                System.out.println("good bye");
            } else {
                processSelectMuMuCommand(command1);
            }
        }

    }

    private void processSelectMuMuCommand(String command) {
        boolean keepGoing = true;
        while (keepGoing) {
            if (command.equals("A")) {
                addMusicInfo();
            } else if (command.equals("B")) {
                displayRemoveMenu();
                String command1 = input.next();
                processSelectRemoveCommand(command1);
            } else if (command.equals("C")) {
                viewAllMusicInformation();
            } else if (command.equals("D")) {
                veiwAllArtistInformation();
            } else if (command.equals("E")) {
                saveAllMusic();
//            } else if (command.equals("F")) {
//
//            } else {
                System.out.println("Invalid selection, try again");
                command = input.next();
            }
            keepGoing = false;
        }
    }

    private void saveAllMusic() {
        musicLists.save();
        System.out.println("Music saved to file " + "./data/music.json");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private Artist processSelectAddArtistCommand(String command) {
        while (true) {
            if (command.equals("1")) {
                return addIndividualArtistInfo();
            } else if (command.equals("2")) {
                return addGroupArtistInfo();
            } else {
                System.out.println("Invalid selection,try again");
                command = input.next();
            }
        }
    }

    //EFFECTS: select artist come from from displaycomefrom menu
    private ComeFrom processSelectComeFromCommand(String command) {
        while (true) {
            if (command.equals("1")) {
                return ComeFrom.AFRICA;

            } else if (command.equals("2")) {
                return ComeFrom.ASIA;

            } else if (command.equals("3")) {
                return ComeFrom.NORTH_AMERICA;

            } else if (command.equals("4")) {
                return ComeFrom.SOUTH_AMERICA;

            } else if (command.equals("5")) {
                return ComeFrom.EUROPE;

            } else if (command.equals("6")) {
                return ComeFrom.AUSTRALIA;

            } else if (command.equals("7")) {
                return ComeFrom.ANTARCTICA;

            } else {
                System.out.println("Invalid selection, try again");
                command = input.next();
            }
        }
    }

    private MusicStyle processSelectMusicStyleCommand(String command) {
        while (true) {
            if (command.equals("1")) {
                return MusicStyle.CLASSICAL;
            } else if (command.equals("2")) {
                return MusicStyle.COUNTRY;
            } else if (command.equals("3")) {
                return MusicStyle.POP;
            } else if (command.equals("4")) {
                return MusicStyle.HIP_HOP;
            } else if (command.equals("5")) {
                return MusicStyle.JAZZ;
            } else if (command.equals("6")) {
                return MusicStyle.ROCK;
            } else if (command.equals("7")) {
                return MusicStyle.ELECTRONIC;
            } else if (command.equals("8")) {
                return MusicStyle.OTHER;
            } else {
                System.out.println("Invalid selection, try again");
                command = input.next();
            }
        }
    }


    private boolean processSelectIsFavoriteArtistCommand(String command) {
        while (true) {
            if (command.equals("1")) {
                return true;
            } else if (command.equals("2")) {
                return false;
            } else {
                System.out.println("Invalid selection, try again");
                command = input.next();
            }
        }
    }

    private boolean processSelectIsFavoriteMusicCommand(String command) {
        while (true) {
            if (command.equals("1")) {
                return true;
            } else if (command.equals("2")) {
                return false;
            } else {
                System.out.println("Invalid selection, try again");
                command = input.next();
            }
        }

    }


    private void processSelectAddMemberCommand(String command) {
        boolean keepGoing = true;
        while (keepGoing) {
            if (command.equals("1")) {
                System.out.println("Type member name:");
                String individualArtistName = input.next();

                displayArtistComeFromMenu();
                String individualArtistComeFrom = input.next();
                ComeFrom icomefrom = processSelectComeFromCommand(individualArtistComeFrom);

                displayIsFavoriteArtistMenu();
                String individualArtistIsFavorite = input.next();

                boolean iif = processSelectIsFavoriteArtistCommand(individualArtistIsFavorite);
                Individual i = new Individual(individualArtistName, icomefrom, iif);
                groupInfo.addToGroupMemberList(i);
                System.out.println("Do you want to add another member");
                displayAddMemberMenu();
                command = input.next();
            } else if (command.equals("2")) {
                keepGoing = false;
            } else {
                System.out.println("Invalid selection,try again");
                command = input.next();
            }
        }
    }


    private void processSelectRemoveCommand(String command) {
        if (command.equals("1")) {
            removeOneMusic(0);
        } else if (command.equals("2")) {
            removeOneArtist(0);
        } else {
            System.out.println("Invalid selection, try again");
            command = input.next();
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes MuMu
    private void init() {
        input = new Scanner(System.in);
    }


    private void displayArtistComeFromMenu() {
        System.out.println("\nSelect where the artist come from: ");

        System.out.println("\t1: AFRICA");
        System.out.println("\t2: ASIA");
        System.out.println("\t3: NORTH_AMERICA");
        System.out.println("\t4: SOUTH_AMERICA");
        System.out.println("\t5: EUROPE");
        System.out.println("\t6: AUSTRALIA");
        System.out.println("\t7: ANTARCTICA");

    }

    private void displayMuMuMainMenu() {
        System.out.println("\nLet's start your MuMu!");

        System.out.println("\tA -> addMusic");
        System.out.println("\tB -> removeMusic");
        System.out.println("\tC -> viewAllMusicInfo");
        System.out.println("\tD -> viewAllArtistInfo");
        System.out.println("\tE -> save");
        System.out.println("\tF -> print to screen");
        System.out.println("\t# -> quit");
    }

    private void displayMusicStyleMenu() {
        System.out.println("\nSelect the music style");

        System.out.println("\t1: CLASSICAL");
        System.out.println("\t2: COUNTRY");
        System.out.println("\t3: POP");
        System.out.println("\t4: HIP_HOP");
        System.out.println("\t5: JAZZ");
        System.out.println("\t6: ROCK");
        System.out.println("\t7: ELECTRONIC");
        System.out.println("\t8: OTHER");
    }


    //EFFECTS: to show if it is favorite artist
    private void displayIsFavoriteArtistMenu() {
        System.out.println("\nFavorite Artist? Select from:");

        System.out.println("\t1: Yes");
        System.out.println("\t2: No/Not Sure (you can set up later)");
    }

    //EFFECTS: to show if it is favorite music
    private void displayIsFavoriteMusicMenu() {
        System.out.println("\nFavorite Music? Select from:");

        System.out.println("\t1: Yes");
        System.out.println("\t2: No/Not Sure (you can set up later)");
    }


    // EFFECTS: displays menu of options to user
    private void displayAddArtistMenu() {
        System.out.println("\nFirst add your music artist! Select from:");

        System.out.println("\t1: add individual");
        System.out.println("\t2: add group");
    }

    private void displayAddMemberMenu() {

        System.out.println("\t1: Yes");
        System.out.println("\t2: No/Not Sure (You can set up later)");
    }

    private void displayRemoveMenu() {
        System.out.println("\nRemove select from: ");

        System.out.println("\t1: remove music");
        System.out.println("\t2: remove artist");

    }

    //EFFECTS: remove music from veiwing all musicInformation
    private void removeOneMusic(int command) {

        viewAllMusicInformation();
        System.out.println("\nType the music # you want to delete: ");
        command = input.nextInt();
        boolean keepGoing = true;

        while (keepGoing) {
            if (command <= musicLists.getMusicList().size() && command >= 1) {
                musicLists.removeMusicFromMusicList(musicLists.getMusicList().get(command - 1));
                viewAllMusicInformation(); //view music info after delete;
                keepGoing = false;
            } else {
                System.out.println("Invalid selection, try again");
                command = input.nextInt();
            }
        }

    }

    //EFFECTS: remove artist
    private void removeOneArtist(int command) {

        veiwAllArtistInformation();
        System.out.println("\nType the artist # you want to delete:");
        command = input.nextInt();


        boolean keepGoing = true;
        while (keepGoing) {
            if (command <= artistLists.getArtistList().size() && command >= 1) {
                artistLists.removeFromArtistList(artistLists.getArtistList().get(command - 1));
                veiwAllArtistInformation();
                keepGoing = false;
            } else {
                System.out.println("Invalid selection, try again");
                command = input.nextInt();
            }
        }
    }


    //EFFECTS: add individual artist info based on user input and return individual too
    private Individual addIndividualArtistInfo() {
        System.out.println("Type individual name:");
        String individualArtistName = input.next();
        String iartistname = individualArtistName;

        displayArtistComeFromMenu();
        String individualArtistComeFrom = input.next();
        ComeFrom icomefrom = processSelectComeFromCommand(individualArtistComeFrom);

        displayIsFavoriteArtistMenu();
        String individualArtistIsFavorite = input.next();
        boolean iif = processSelectIsFavoriteArtistCommand(individualArtistIsFavorite);

        Individual i = new Individual(iartistname, icomefrom, iif);
        artistLists.addToArtistList(i);
        return i;
    }


    //EFFECTS: add group artist info based on user input
    private Group addGroupArtistInfo() {
        System.out.println("Type group name:");
        String groupArtistName = input.next();
        String gartistname = groupArtistName;

        displayArtistComeFromMenu();
        String groupArtistComeFrom = input.next();
        ComeFrom gcomefrom = processSelectComeFromCommand(groupArtistComeFrom);


        displayIsFavoriteArtistMenu();
        String groupArtistIsFavorite = input.next();
        boolean gif = processSelectIsFavoriteArtistCommand(groupArtistIsFavorite);

        Group g = new Group(gartistname, gcomefrom, gif);
        artistLists.addToArtistList(g);
        groupInfo = g;

        System.out.println("Do you want to add memberinfo?");
        displayAddMemberMenu();
        String addMemberSelect = input.next();
        processSelectAddMemberCommand(addMemberSelect);

        System.out.println("GroupMember number is:" + groupInfo.getMemberNum());
        return g;
    }

    private void addMusicInfo() {
        displayAddArtistMenu();
        String command = input.next();
        Artist artist = processSelectAddArtistCommand(command);

        System.out.println("Type this artist music name");
        String musicName = input.next();
        String mname = musicName;

        displayMusicStyleMenu();
        String musicStyle = input.next();
        MusicStyle ms = processSelectMusicStyleCommand(musicStyle);

        displayIsFavoriteMusicMenu();
        String favoriteMusic = input.next();
        boolean fm = processSelectIsFavoriteMusicCommand(favoriteMusic);

        Music m = new Music(artist, mname, ms, fm);
        musicLists.addMusicToMusicList(m);
    }


    //EFFECTS: print out one music info
    private String printOutOneMusicInformation(Music m) {
        String artistInfo = m.getArtist().toString() + printOutOneArtistInformation(m.getArtist());
        ;


        String oneMusicInfo = artistInfo + ("\nMUSICINFO "
                + "\tname: " + m.getMusicName()
                + "\tfmusic style: " + m.getMusicStyle()
                + "\tfavoriteM" + m.getIsFavoriteMusic())
                + "\n"
                + "\n";
        return oneMusicInfo;
    }


    //EFFECTS: print out all music info
    private void viewAllMusicInformation() {
        for (Music m : musicLists.getMusicList()) {
            String musicInfo = printOutOneMusicInformation(m);
            System.out.println(musicInfo);
        }
    }

    private String printOutOneArtistInformation(Artist a) {
        String oneArtistInfo = "\nARTISTINFO "
                + "\tname: " + a.getArtistName()
                + "\tcome from: " + a.getComeFrom()
                + "\tfavoriteA: " + a.isFavoriteArtist()
                + "\n";
        return oneArtistInfo;
    }

    private void veiwAllArtistInformation() {
        String allArtistInfo = "";
        for (Artist a : artistLists.getArtistList()) {
            allArtistInfo = allArtistInfo + a.toString() + printOutOneArtistInformation(a);
        }
        System.out.println(allArtistInfo);
    }


//    private void viewMemberInformation() {
//        System.out.println();
//    }


}
