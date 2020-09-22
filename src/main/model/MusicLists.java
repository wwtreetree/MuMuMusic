package model;

import org.json.simple.parser.ParseException;
import persistence.Reader;
import persistence.Savable;
import persistence.Writer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//contain music list information so that I can sort my music
public class MusicLists implements Savable {
    List<Music> musicList = new ArrayList<>();

    public List<Music> getMusicList() {
        return musicList;
    }

    //EFFECTS: add music to list and add a musicID in order
    public void addMusicToMusicList(Music m) {
        musicList.add(m);
        m.musicNo = musicList.indexOf(m) + 1;
    }

    //EFFECTS: remove music from music list and let the rest of music in the list musicID minus one
    public void removeMusicFromMusicList(Music m) {
        int position = musicList.indexOf(m);
        musicList.remove(m);
        for (Music mm: musicList) {
            if (musicList.indexOf(mm) >= position) {
                mm.musicNo = mm.musicNo - 1;
            }
        }
    }

    //EFFECTS: produce music list with a given artist name
    public ArrayList getMusicListWithArtistName(String artistName) {
        ArrayList<Music> musicListWithArtistName = new ArrayList<>();

        for (Music m : musicList) {
            if (m.getArtist().getArtistName().equals(artistName)) {
                musicListWithArtistName.add(m);
            }
        }
        return musicListWithArtistName;
    }



    //EFFECTS: produce music list with a given specific style
    //         if the music is ...this generate their own music list
    public ArrayList getMusicListWithMusicStyle(MusicStyle ms) {
        ArrayList<Music> musicListWithMusicStyle = new ArrayList<>();

        for (Music m: musicList) {
            if (m.getMusicStyle().equals(ms)) {
                musicListWithMusicStyle.add(m);
            }
        }
        return musicListWithMusicStyle;
    }


    //EFFECT: generate FavoriteMusicList
    public ArrayList getFavoriteMusicList() {
        ArrayList<Music> myFavoriteMusicList = new ArrayList<>();

        for (Music m : musicList) {
            if (m.getIsFavoriteMusic()) {
                myFavoriteMusicList.add(m);
            }
        }
        return myFavoriteMusicList;
    }


    //EFFECTS: produce music list with a given specific style and music should be favorite
    //         if the music is ...this generate their own music list
    public ArrayList getFavoriteMusicListWithMusicStyle(MusicStyle ms) {
        ArrayList<Music> favoriteMusicListWithMusicStyle = new ArrayList<>();

        for (Music m: musicList) {
            if (m.getIsFavoriteMusic() && m.getMusicStyle().equals(ms)) {
                favoriteMusicListWithMusicStyle.add(m);
            }
        }
        return favoriteMusicListWithMusicStyle;
    }


    @Override
    public void save() {
        Writer.writeMusics(musicList, new File("./data/MuMuMusic.json"));
    }


    //MODIFIES: this
    //EFFECTS: loadMusic from file
    public void loadMusics() {
        try {
            musicList = Reader.readMusics(new File("./data/MuMuMusic.json"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}




