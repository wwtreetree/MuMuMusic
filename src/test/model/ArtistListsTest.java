package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArtistListsTest {
    ArtistLists artistLists;
    Artist i1;
    Artist i2;
    Artist i3;
    Artist g1;
    Artist g2;

    @BeforeEach
    public void runBefore() {
        artistLists = new ArtistLists();
        i1 = new Individual("Peter",ComeFrom.ASIA,true);
        i2 = new Individual("Kate",ComeFrom.ASIA,false);
        i3 = new Individual("Kate",ComeFrom.AUSTRALIA,false);

        g1 = new Group("EXO",ComeFrom.AFRICA,false);
        g2 = new Group("Pink",ComeFrom.NORTH_AMERICA,true);
    }

    @Test
    void testConstructor() {

    }


    @Test
    //add individual to empty list
    void testAddToArtistList1() {
        artistLists.addToArtistList(i1);
        assertTrue(artistLists.getArtistList().contains(i1));
        assertEquals(1,artistLists.getArtistList().size());
        assertEquals(1,i1.getArtistID());
    }

    @Test
        //add individual to empty list and also can add two artist object in it
    void testAddToArtistList2() {
        artistLists.addToArtistList(g1);
        assertTrue(artistLists.getArtistList().contains(g1));
        assertEquals(1,artistLists.getArtistList().size());
        artistLists.addToArtistList(i1);
        assertTrue(artistLists.getArtistList().contains(i1));
        assertEquals(2,artistLists.getArtistList().size());
        assertEquals(1,g1.getArtistID());
        assertEquals(2,i1.getArtistID());

    }


    @Test
    //Base Case: after remove is a empty
    void testRemoveFromArtistList1() {
        artistLists.addToArtistList(g1);
        artistLists.removeFromArtistList(g1);
        assertEquals(0,artistLists.getArtistList().size());
    }

    @Test
    void testRemoveFromArtistList2() {
        artistLists.addToArtistList(g1);
        artistLists.addToArtistList(i1);
        artistLists.addToArtistList(i2);

        artistLists.removeFromArtistList(g1);
        assertEquals(i1,artistLists.getArtistList().get(0));
        assertEquals(i2,artistLists.getArtistList().get(1));
        assertEquals(2,artistLists.getArtistList().size());
        assertEquals(1,i1.getArtistID());
        assertEquals(2,i2.getArtistID());
    }

    @Test
    void testRemoveFromArtistList3() {
        artistLists.addToArtistList(g1);
        artistLists.addToArtistList(i1);
        artistLists.addToArtistList(i2);

        artistLists.removeFromArtistList(i1);
        assertEquals(g1,artistLists.getArtistList().get(0));
        assertEquals(i2,artistLists.getArtistList().get(1));
        assertEquals(2,artistLists.getArtistList().size());
        assertEquals(1,g1.getArtistID());
        assertEquals(2,i2.getArtistID());
    }

    @Test
    void testRemoveFromArtistList4() {
        artistLists.addToArtistList(g1);
        artistLists.addToArtistList(i1);
        artistLists.addToArtistList(i2);

        artistLists.removeFromArtistList(i2);
        assertEquals(g1,artistLists.getArtistList().get(0));
        assertEquals(i1,artistLists.getArtistList().get(1));
        assertEquals(2,artistLists.getArtistList().size());
        assertEquals(1,g1.getArtistID());
        assertEquals(2,i1.getArtistID());

    }


    @Test
        //base case don't have favorite artistList
    void testGetFavoriteArtistList1() {
        artistLists.addToArtistList(g1);
        assertEquals(0,artistLists.getFavoriteArtistList().size());

    }

    @Test
        //base case have favorite artistList
    void testGetFavoriteArtistList2() {
        artistLists.addToArtistList(i1);
        assertEquals(i1,artistLists.getFavoriteArtistList().get(0));
        assertEquals(1,artistLists.getArtistList().size());
    }

    @Test
        // have mixed favorite artistList
    void testGetFavoriteArtistList3() {
        artistLists.addToArtistList(i1);
        assertEquals(i1,artistLists.getFavoriteArtistList().get(0));
        artistLists.addToArtistList(g1);
        assertEquals(1,artistLists.getFavoriteArtistList().size());
        artistLists.addToArtistList(g2);
        assertEquals(g2,artistLists.getFavoriteArtistList().get(1));
        assertEquals(2,artistLists.getFavoriteArtistList().size());
    }


    @Test
    //Base case: empty list search for name
    void testGetArtistWithMusicName1() {
        assertEquals(0,artistLists.getArtistWithMusicName("Becky").size());
    }


    @Test
    //list search name doesn't exist
    void testGetArtistWithMusicName2() {
        artistLists.addToArtistList(i1);
        assertEquals(0,artistLists.getArtistWithMusicName("Becky").size());
    }

    @Test
    //list search name exist
    void testGetArtistWithMusicName3() {
        artistLists.addToArtistList(i1);
        assertEquals(1,artistLists.getArtistWithMusicName("Peter").size());
        artistLists.addToArtistList(g1);
        assertEquals(1,artistLists.getArtistWithMusicName("EXO").size());
    }

    @Test
    void testGetArtistWithMusicName4() {
        artistLists.addToArtistList(i2);
        artistLists.addToArtistList(i3);
        assertEquals(2,artistLists.getArtistList().size());
        assertEquals(i2,artistLists.getArtistList().get(0));
        assertEquals(i3,artistLists.getArtistList().get(1));
    }











}