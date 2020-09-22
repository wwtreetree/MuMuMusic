package model;

import java.util.ArrayList;

//contain artistList information,like favoriate artistB

public class ArtistLists {
    ArrayList<Artist> artistList = new ArrayList<>();
    private int idCounter = 1;

    public ArrayList<Artist> getArtistList() {
        return artistList;
    }


    //EFFECTS: add artist to artistList and increment the artistID by 1
    public void addToArtistList(Artist a) {

        artistList.add(a);
        a.artistNo = artistList.indexOf(a) + 1;


    }


    //MODIFIES: this
    //EFFECTS: remove an artist from ArtistList
    public void removeFromArtistList(Artist a) {
        int position = artistList.indexOf(a);
        artistList.remove(a);
        for (Artist aa : artistList) {
            if (artistList.indexOf(aa) >= position) {
                aa.artistNo = aa.artistNo - 1;
            }
        }
    }


    //EFFECTS: return list of  favoriteArtists
    public ArrayList<Artist> getFavoriteArtistList() {
        ArrayList<Artist> favoriteArtistList = new ArrayList<>();
        for (Artist aa : artistList) {
            if (aa.isFavoriteArtist()) {
                favoriteArtistList.add(aa);
            }
        }
        return favoriteArtistList;
    }


    //EFFECTS: return a list of  artists based on the music name
    public ArrayList<Artist> getArtistWithMusicName(String mn) {
        ArrayList<Artist> artistWithMusicName = new ArrayList<>();
        for (Artist i : artistList) {
            if (mn.equals(i.getArtistName())) {
                artistWithMusicName.add(i);
            }
        }
        return artistWithMusicName;
    }

}


