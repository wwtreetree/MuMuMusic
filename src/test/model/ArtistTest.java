package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ArtistTest {
    ArtistLists artistLists;
    Artist a1;
    Artist a2;
    Group a3;

    @BeforeEach
    void runBefore() {
        artistLists = new ArtistLists();
        a1 = new Individual("Peter",ComeFrom.ASIA,true);
        a2 = new Individual("Kate",ComeFrom.ASIA,false);
        a3 = new Group("EXO",ComeFrom.AFRICA,false);
    }



    @Test
    void testConstructor() {
        assertEquals("Peter",a1.getArtistName());
        assertEquals(ComeFrom.ASIA,a1.getComeFrom());
        assertTrue(a1.isFavoriteArtist());
        assertFalse(a2.isFavoriteArtist());
    }


    @Test
    void testGetArtistID1() {
        artistLists.addToArtistList(a1);
        assertEquals(1,a1.getArtistID());
        artistLists.addToArtistList(a2);
        assertEquals(2,a2.getArtistID());
        artistLists.addToArtistList(a3);
        assertEquals(3,a3.getArtistID());

        artistLists.removeFromArtistList(a1);
        assertEquals(1,a2.getArtistID());
        assertEquals(2,a3.getArtistID());
    }

    @Test
    void testGetArtistID2() {

        artistLists.addToArtistList(a1);
        assertEquals(1,a1.getArtistID());
        artistLists.addToArtistList(a2);
        assertEquals(2,a2.getArtistID());
        artistLists.addToArtistList(a3);
        assertEquals(3,a3.getArtistID());

        artistLists.removeFromArtistList(a2);
        assertEquals(1,a1.getArtistID());
        assertEquals(2,a3.getArtistID());

    }

    @Test
    void testGetArtistID3() {

        artistLists.addToArtistList(a1);
        assertEquals(1,a1.getArtistID());
        artistLists.addToArtistList(a2);
        assertEquals(2,a2.getArtistID());
        artistLists.addToArtistList(a3);
        assertEquals(3,a3.getArtistID());

        artistLists.removeFromArtistList(a3);
        assertEquals(1,a1.getArtistID());
        assertEquals(2,a2.getArtistID());
    }


    @Test
    void testSetFavoriteArtist() {
        a1.changeIsFavoriteArtist();
        assertFalse(a1.isFavoriteArtist());
        a2.changeIsFavoriteArtist();
        assertTrue(a2.isFavoriteArtist());
    }


    @Test
    void testGetMemberInfo() {
        assertEquals("", a1.getMemberInfo());
    }
//
//    @Test
//    void testToString() {
//        "\nARTISTINFO " + "\t#" + this.artistNo
//                + "\tname: " + this.artistName
//                + "\tcome from: " + this.comeFrom
//                + "\tfavoriteA: " + this.favoriteArtist
//                + "\n";
//
//
//    }
}
