package persistence;

import model.*;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {

    @Test
    void testParseMusicFile1() {
        try {
            List<Music> musics = Reader.readMusics(new File("./data/music.json"));

            Music music1 = musics.get(0);
            assertEquals("Person1", music1.getArtist().getArtistName());
            assertEquals(ComeFrom.ASIA, music1.getArtist().getComeFrom());
            assertTrue( music1.getArtist().isFavoriteArtist());
            assertEquals("Rainbow",music1.getMusicName());
            assertEquals(MusicStyle.JAZZ,music1.getMusicStyle());
            assertTrue(music1.getIsFavoriteMusic());
            assertEquals("",music1.getAudioPath());


            Music music2 = musics.get(1);
            assertEquals("Person2", music2.getArtist().getArtistName());
            assertEquals(ComeFrom.ASIA, music2.getArtist().getComeFrom());
            assertTrue( music1.getArtist().isFavoriteArtist());

            Group group = (Group) music2.getArtist();


            Individual i1 = (Individual) group.getGroup().get(0);
            assertEquals("M1", i1.getArtistName());
            assertEquals(ComeFrom.ASIA,i1.getComeFrom());
            assertTrue(i1.isFavoriteArtist());

            Individual i2 = (Individual) group.getGroup().get(1);
            assertEquals("M2", i2.getArtistName());
            assertEquals(ComeFrom.EUROPE,i2.getComeFrom());
            assertFalse(i2.isFavoriteArtist());

            Individual i3 = (Individual) group.getGroup().get(2);
            assertEquals("M3", i3.getArtistName());
            assertEquals(ComeFrom.NORTH_AMERICA,i3.getComeFrom());
            assertTrue(i3.isFavoriteArtist());

            assertEquals("Sunny", music2.getMusicName());
            assertEquals(MusicStyle.POP,music2.getMusicStyle());
            assertFalse(music2.getIsFavoriteMusic());
            assertEquals("",music2.getAudioPath());



        } catch (ParseException e) {
            fail("IOException should not have been thrown");
        }

    }

    @Test
    void testParseMusicFile2() {
        try {
            List<Music> musics = Reader.readMusics(new File("./data/musicError.json"));
            fail("ParseException should be thrown");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



    @Test
    void testIOException() {
        try {
            Reader.readMusics(new File("./path/does/not/exist/testAccount.txt"));
        } catch (ParseException e) {
            // expected
            fail();
        }
    }


}
