package model;

import java.util.Date;

//contain individual artist information
public class Individual extends Artist {


    public Individual(String artistName, ComeFrom comeFrom, boolean favoriteArtist) {
        super(artistName, comeFrom, favoriteArtist);
    }


    //EFFECTS: individual has no member information, return ""
    @Override
    public String getMemberInfo() {
        return "";
    }

}
