package model;

//music style I have
public enum MusicStyle {
    CLASSICAL("CLASSICAL"),
    COUNTRY("COUNTRY"),
    POP("POP"),
    HIP_HOP("HIP_HOP"),
    JAZZ("JAZZ"),
    ROCK("ROCK"),
    ELECTRONIC("ELECTRONIC"),
    OTHER("OTHER");

    private String style;

    private  MusicStyle(String style) {
        this.style = style;
    }
}

