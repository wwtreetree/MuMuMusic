package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IndividualTest  {
    Individual i1;
    Individual i2;

    @BeforeEach
    void runBefore() {
        i1 = new Individual("GEM",ComeFrom.ASIA,true);
        i2 = new Individual("Lay",ComeFrom.ASIA,false);
    }

    @Test
    void testConstructor() {
        assertEquals("GEM",i1.getArtistName());
        assertEquals(ComeFrom.ASIA,i1.getComeFrom());
        assertTrue(i1.isFavoriteArtist());
        assertFalse(i2.isFavoriteArtist());
    }


    @Test
    void getMemberInfo() {
        assertEquals("",i1.getMemberInfo());
    }
}
