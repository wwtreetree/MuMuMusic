package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicListsTest {
    MusicLists ml;
    Music m1;
    Music m2;
    Music m3;
    Music m4;
    Music m5;


    @BeforeEach
    void runBefore() {
        ml = new MusicLists();
        m1 = new Music(new Individual("Taylor Swift",ComeFrom.NORTH_AMERICA,true),"22",
                MusicStyle.POP,false);

        m2 = new Music(new Individual("Justin Bieber",ComeFrom.NORTH_AMERICA,false),
                "Sorry", MusicStyle.POP,true);

        m3 = new Music(new Group("Ikon",ComeFrom.NORTH_AMERICA,true),
                "Love Scenario", MusicStyle.POP,true);

        m4 = new Music(new Group("Ikon",ComeFrom.NORTH_AMERICA,false),
                "Snow", MusicStyle.JAZZ,false);

        m5 = new Music(new Individual("GEM",ComeFrom.ASIA,true),
                "Hua", MusicStyle.ELECTRONIC,true);

    }

    @Test
    void testConstructor() {

    }


    @Test
    void addMusicToMusicList() {
        ml.addMusicToMusicList(m1);
        assertEquals(m1,ml.getMusicList().get(0));
        assertEquals(1,ml.getMusicList().size());

        ml.addMusicToMusicList(m2);
        assertEquals(m2,ml.getMusicList().get(1));
        assertEquals(2,ml.getMusicList().size());
    }


    @Test
    void removeMusicFromMusicList() {
        ml.addMusicToMusicList(m1);
        ml.removeMusicFromMusicList(m1);
        assertEquals(0,ml.getMusicList().size());

        ml.addMusicToMusicList(m1);
        ml.addMusicToMusicList(m2);
        ml.removeMusicFromMusicList(m1);
        assertEquals(1,ml.getMusicList().size());
        assertEquals(m2,ml.getMusicList().get(0));
    }



    @Test
    //cannot get music with artistName
    void getMusicWithArtistName1() {
        ml.addMusicToMusicList(m1);
        assertEquals(0,ml.getMusicListWithArtistName("Kacy").size());
    }

    @Test
        //can get music with artistName
    void getMusicWithArtistName2() {
        ml.addMusicToMusicList(m1);
        assertEquals(m1,ml.getMusicListWithArtistName("Taylor Swift").get(0));
        ml.addMusicToMusicList(m3);
        assertEquals(m3,ml.getMusicListWithArtistName("Ikon").get(0));
    }

    @Test
    //get an musicList with Music Name
    void getMusicWithArtistName3() {
        ml.addMusicToMusicList(m3);
        ml.addMusicToMusicList(m4);
        assertEquals(m3,ml.getMusicListWithArtistName("Ikon").get(0));
        assertEquals(m4,ml.getMusicListWithArtistName("Ikon").get(1));
        assertEquals(2,ml.getMusicListWithArtistName("Ikon").size());
    }

    @Test
    //base case empty music list with that style
    void getMusicListWithMusicStyle1() {
        ml.addMusicToMusicList(m1);
        assertEquals(0,ml.getMusicListWithMusicStyle(MusicStyle.HIP_HOP).size());
    }



    @Test
    //get Music with that music Style
    void getMusicListWithMusicStyle3() {
        ml.addMusicToMusicList(m1);
        ml.addMusicToMusicList(m2);
        ml.addMusicToMusicList(m3);
        ml.addMusicToMusicList(m4);

        assertEquals(m4,ml.getMusicListWithMusicStyle(MusicStyle.JAZZ).get(0));
        assertEquals(1,ml.getMusicListWithMusicStyle(MusicStyle.JAZZ).size());

        assertEquals(m1,ml.getMusicListWithMusicStyle(MusicStyle.POP).get(0));
        assertEquals(m2,ml.getMusicListWithMusicStyle(MusicStyle.POP).get(1));
        assertEquals(m3,ml.getMusicListWithMusicStyle(MusicStyle.POP).get(2));
        assertEquals(3,ml.getMusicListWithMusicStyle(MusicStyle.POP).size());
    }


    @Test
    //base case: empty musicList
    void getFavoriteMusicList1() {
        ml.addMusicToMusicList(m1);
        assertEquals(0,ml.getFavoriteMusicList().size());
    }

    @Test
    void getFavoriteMusicList2() {
        ml.addMusicToMusicList(m1);
        ml.addMusicToMusicList(m2);
        ml.addMusicToMusicList(m3);
        ml.addMusicToMusicList(m4);

        assertEquals(m2,ml.getFavoriteMusicList().get(0));
        assertEquals(m3,ml.getFavoriteMusicList().get(1));
        assertEquals(2,ml.getFavoriteMusicList().size());
    }


    @Test
    //empty case: no favorite music or no matching music style
    void getFavoriteMusicListWithMusicStyle1() {
        ml.addMusicToMusicList(m1);
        assertEquals(0,ml.getFavoriteMusicListWithMusicStyle(MusicStyle.ROCK).size());
        assertEquals(0,ml.getFavoriteMusicListWithMusicStyle(MusicStyle.POP).size());

        ml.addMusicToMusicList(m2);
        assertEquals(0,ml.getFavoriteMusicListWithMusicStyle(MusicStyle.ROCK).size());
    }


    @Test
    void getFavoriteMusicListWithMusicStyle2() {
        ml.addMusicToMusicList(m1);
        ml.addMusicToMusicList(m2);
        ml.addMusicToMusicList(m4);
        ml.addMusicToMusicList(m5);


        assertEquals(0,ml.getFavoriteMusicListWithMusicStyle(MusicStyle.JAZZ).size());


        assertEquals(m5,ml.getFavoriteMusicListWithMusicStyle(MusicStyle.ELECTRONIC).get(0));
        assertEquals(1,ml.getFavoriteMusicListWithMusicStyle(MusicStyle.ELECTRONIC).size());


        assertEquals(m2,ml.getFavoriteMusicListWithMusicStyle(MusicStyle.POP).get(0));
        assertEquals(1,ml.getFavoriteMusicListWithMusicStyle(MusicStyle.POP).size());
        ml.addMusicToMusicList(m3);
        assertEquals(m2,ml.getFavoriteMusicListWithMusicStyle(MusicStyle.POP).get(0));
        assertEquals(m3,ml.getFavoriteMusicListWithMusicStyle(MusicStyle.POP).get(1));
        assertEquals(2,ml.getFavoriteMusicListWithMusicStyle(MusicStyle.POP).size());
    }

}