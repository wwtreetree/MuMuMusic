package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicTest {
    MusicLists ml;
    Music m1;
    Music m2;
    Music m3;
    Individual i1;

    @BeforeEach
    void runBefore() {
        ml = new MusicLists();
        i1 = new Individual("Gem",ComeFrom.ASIA,true);
        m1 = new Music(i1,"AINI",MusicStyle.POP
        ,true);

        m2 = new Music(new Individual("Lady GaGa",ComeFrom.NORTH_AMERICA,false),
                "Poker Face",MusicStyle.POP,false);
        m3 = new Music(new Individual("Edsheer",ComeFrom.NORTH_AMERICA,false),
                "Perfect",MusicStyle.ELECTRONIC,true);

    }

    @Test
    void testConstructor() {
        assertEquals(i1,m1.getArtist());
        assertEquals("AINI",m1.getMusicName());
        assertEquals(MusicStyle.POP,m1.getMusicStyle());

        assertTrue(m1.getIsFavoriteMusic());
        assertFalse(m2.getIsFavoriteMusic());
    }


    @Test
    void testSetGetMusicID1() {
        ml.addMusicToMusicList(m1);
        assertEquals(1,m1.getMusicID());
        ml.addMusicToMusicList(m2);
        assertEquals(2,m2.getMusicID());
        ml.addMusicToMusicList(m3);
        assertEquals(3,m3.getMusicID());

        ml.removeMusicFromMusicList(m1);

        assertEquals(1,m2.getMusicID());
        assertEquals(2,m3.getMusicID());
    }


    @Test
    void testSetGetMusicID2() {
        ml.addMusicToMusicList(m1);
        assertEquals(1,m1.getMusicID());
        ml.addMusicToMusicList(m2);
        assertEquals(2,m2.getMusicID());
        ml.addMusicToMusicList(m3);
        assertEquals(3,m3.getMusicID());

        ml.removeMusicFromMusicList(m2);

        assertEquals(1,m1.getMusicID());
        assertEquals(2,m3.getMusicID());
    }

    @Test
    void testSetGetMusicID3() {
        ml.addMusicToMusicList(m1);
        assertEquals(1,m1.getMusicID());
        ml.addMusicToMusicList(m2);
        assertEquals(2,m2.getMusicID());
        ml.addMusicToMusicList(m3);
        assertEquals(3,m3.getMusicID());

        ml.removeMusicFromMusicList(m3);

        assertEquals(1,m1.getMusicID());
        assertEquals(2,m2.getMusicID());
    }


    @Test
    void testSetChangeIsFavoriteInMusic() {
        m1.changeIsFavoriteMusic();
        assertFalse(m1.getIsFavoriteMusic());

        m2.changeIsFavoriteMusic();
        assertTrue(m2.getIsFavoriteMusic());
    }
}