package model;

import persistence.Reader;
import persistence.Savable;

import java.io.PrintWriter;
import java.util.List;

//contain music information
public class Music {
    Artist artist;

    protected int musicNo;
    private String musicName;
    private MusicStyle musicStyle;
    private Boolean favoriteMusic;
    private String audioPath;


    //EFFECT: all field set up
    public Music(Artist artist, String musicName, MusicStyle musicStyle, Boolean favoriteMusic) {
        this.artist = artist;
        this.musicName = musicName;
        this.musicStyle = musicStyle;
        this.favoriteMusic = favoriteMusic;
        this.musicNo = 0;
        this.audioPath = "";
    }



    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getAudioPath() {
        return this.audioPath;
    }

    public boolean getIsFavoriteMusic() {
        return favoriteMusic;
    }


    //MODIFIES: this
    //EFFECT: set favorite music to false and set favorite music to true otherwise
    public void changeIsFavoriteMusic() {
        if (this.favoriteMusic == true) {
            this.favoriteMusic = false;
        } else {
            this.favoriteMusic = true;
        }
    }

    public String getMusicName() {
        return musicName;
    }


    public MusicStyle getMusicStyle() {
        return musicStyle;
    }


    //EFFECTS: get music ID for all music in the list
    public int getMusicID() {
        return musicNo;

    }

    public Artist getArtist() {
        return artist;
    }



}
