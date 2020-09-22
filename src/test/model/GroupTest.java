package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest{
    Group g;
    Individual i1;
    Individual i2;
    Individual i3;


    @BeforeEach
    public void runBefore() {
       g = new Group("Happy",ComeFrom.NORTH_AMERICA,false);
       i1 = new Individual("Happy1",ComeFrom.AUSTRALIA,true);
       i2 = new Individual("Happy2",ComeFrom.SOUTH_AMERICA,false);
       i3 = new Individual("Happy3",ComeFrom.ASIA,false);
    }

    @Test
    void testConstructor() {
        assertEquals("Happy",g.getArtistName());
        assertEquals(ComeFrom.NORTH_AMERICA,g.getComeFrom());
        assertFalse(g.isFavoriteArtist());
        assertTrue(i1.isFavoriteArtist());
    }

    @Test
    void testGetMemberNum1(){
        assertEquals(0,g.getMemberNum());
    }




    @Test
    void testGetMemberNum2() {
        g.addToGroupMemberList(i1);
        g.addToGroupMemberList(i2);
        g.addToGroupMemberList(i3);
        assertEquals(3,g.getMemberNum());

    }

    @Test
    void addToMemberList() {
        g.addToGroupMemberList(i1);
        assertEquals(i1,g.getGroup().get(0));
        assertEquals(1,g.getGroup().size());
        g.addToGroupMemberList(i2);
        assertEquals(i2,g.getGroup().get(1));
        assertEquals(2,g.getGroup().size());
    }

    @Test
    //base case after remove is an empty list
    void removeFromMemberList1() {
        g.addToGroupMemberList(i1);
        g.removeFromGroupMemberList(i1);
        assertEquals(0,g.getGroup().size());
    }

    @Test
    //add to group
    void removeFromMemberList2() {
        g.addToGroupMemberList(i1);
        g.addToGroupMemberList(i2);
        g.removeFromGroupMemberList(i1);
        assertEquals(i2,g.getGroup().get(0));
        assertEquals(1,g.getGroup().size());
        g.addToGroupMemberList(i3);
        assertEquals(i2,g.getGroup().get(0));
        assertEquals(i3,g.getGroup().get(1));
        assertEquals(2,g.getGroup().size());
    }


    @Test
    void testGetMemberInfo() {
        g.addToGroupMemberList(i1);
        g.addToGroupMemberList(i2);
        g.addToGroupMemberList(i3);
        assertEquals("Happy1" + " " +
                "Happy2" + " " +
                "Happy3" + " " , g.getMemberInfo());
    }





}