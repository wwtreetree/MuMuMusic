package persistence;

import model.*;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/music_output.json";
    private Writer testWriter;
    private Music m1;
    private List<Music> musicList;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
//        testWriter = new Writer(new File(TEST_FILE));
        musicList = new ArrayList<>();

        m1 = new Music(new Individual("Lady GaGa", ComeFrom.NORTH_AMERICA,false),
                "Poker Face", MusicStyle.POP,false);

        Group g = new Group("Happy",ComeFrom.NORTH_AMERICA,false);
        Individual i1 = new Individual("Happy1",ComeFrom.AUSTRALIA,true);
        Individual i2 = new Individual("Happy2",ComeFrom.SOUTH_AMERICA,false);
        Individual i3 = new Individual("Happy3",ComeFrom.ASIA,false);
        g.addToGroupMemberList(i1);
        g.addToGroupMemberList(i2);
        g.addToGroupMemberList(i3);

        musicList.add(m1);
        musicList.add(new Music(g, "Song One", MusicStyle.ELECTRONIC, false));
    }

    @Test
    void testWriteWithNoFile() {
        testWriter.writeMusics(musicList, new File("../"));
    }

    @Test
    void testWriteMusics() {

        // now read them back in and verify that the accounts have the expected values
        try {
            testWriter.writeMusics(musicList, new File(TEST_FILE));

            List<Music> musics = Reader.readMusics(new File(TEST_FILE));

            Music music = musics.get(0);
            assertEquals("Lady GaGa", music.getArtist().getArtistName());
            assertEquals(ComeFrom.NORTH_AMERICA, music.getArtist().getComeFrom());
            assertFalse(music.getArtist().isFavoriteArtist());

            Music music2 = musics.get(1);
            assertEquals("Happy", music2.getArtist().getArtistName());
            assertEquals(ComeFrom.NORTH_AMERICA, music2.getArtist().getComeFrom());
            assertFalse(music2.getArtist().isFavoriteArtist());

            Group group = (Group) music2.getArtist();

            Individual i1 = group.getGroup().get(0);
            assertEquals("Happy1",i1.getArtistName());
            assertEquals(ComeFrom.AUSTRALIA,i1.getComeFrom());
            assertTrue(i1.isFavoriteArtist());

            Individual i2 = group.getGroup().get(1);
            assertEquals("Happy2",i2.getArtistName());
            assertEquals(ComeFrom.SOUTH_AMERICA,i2.getComeFrom());
            assertFalse(i2.isFavoriteArtist());

            Individual i3 = group.getGroup().get(2);
            assertEquals("Happy3",i3.getArtistName());
            assertEquals(ComeFrom.ASIA,i3.getComeFrom());
            assertFalse(i3.isFavoriteArtist());

            // verify that ID of next account created is 3 (checks that nextAccountId was restored)

        } catch (ParseException e) {
            fail("IOException should not have been thrown");
        }
    }

}
