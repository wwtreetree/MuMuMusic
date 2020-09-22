package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//contain artist information
public abstract class Artist {

    protected String artistName;
    protected int artistNo;
    protected ComeFrom comeFrom;
    protected Boolean favoriteArtist;
    Date currentDate;


    public Artist(String artistName, ComeFrom comeFrom, Boolean favoriteArtist) {
        this.comeFrom = comeFrom;
        this.favoriteArtist = favoriteArtist;
        this.artistName = artistName;
        this.artistNo = 0;
        this.currentDate = new Date();
    }


    //MODIFIES: artistID
    //EFFECTS: get ArtistID in order when it is add in or remove
    public int getArtistID() {
        return artistNo;
    }


    public String getArtistName() {
        return artistName;
    }

//    public void setArtistName(String artistName) {
//        this.artistName = artistName;
//    }


    public ComeFrom getComeFrom() {
        return comeFrom;
    }


    public boolean isFavoriteArtist() {
        return favoriteArtist;
    }

    //MODIFIES: change favorite value
    //EFFECTS: set artist favorite
    public void changeIsFavoriteArtist() {
        if (this.favoriteArtist == true) {
            this.favoriteArtist = false;
        } else {
            this.favoriteArtist = true;
        }
    }

    public abstract String getMemberInfo();

//    public String getMemberInfo() {
//        return "";
//    }

//    public String toString() {
//        String oneArtistInfo = "\nARTISTINFO " + "\t#" + this.artistNo
//                + "\tname: " + this.artistName
//                + "\tcome from: " + this.comeFrom
//                + "\tfavoriteA: " + this.favoriteArtist
//                + "\n";
//        return oneArtistInfo;
//    }
    //public abstract int getAge(Artist a);
}


