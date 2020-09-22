package model;

//is where the artist come from
public enum ComeFrom {
    AFRICA("AFRICA"),
    ASIA("ASIA"),
    NORTH_AMERICA("NORTH_AMERICA"),
    SOUTH_AMERICA("SOUTH_AMERICA"),
    EUROPE("EUROPE"),
    AUSTRALIA("AUSTRALIA"),
    ANTARCTICA("ANTARCTICA");

    private String comeFrom;
    private ComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
    }


}
