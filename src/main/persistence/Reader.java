package persistence;

import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//A reader that can read account data from a file
public class Reader {

    public static final String DELIMITER = ",";


    private static void parseMusic(List<Music> musics, JSONArray musicList, int i)  {
        JSONObject musicObj = (JSONObject) musicList.get(i);

        JSONObject artistObj = (JSONObject) musicObj.get("Artist");
        Artist artist = null;
        if (artistObj.containsKey("group")) {
            artist = parseGroup(artistObj);
        } else {
            artist = parseIndividual(artistObj);
        }

        String musicName = (String) musicObj.get("musicName");
        boolean isFavoriteMusic = (boolean) musicObj.get("favoriteMusic");
        String musicStyle = (String) musicObj.get("musicStyle");

        Music m = new Music(artist, musicName, MusicStyle.valueOf(musicStyle), isFavoriteMusic);


        if (musicObj.containsKey("audioPath")) {
            m.setAudioPath((String) musicObj.get("audioPath"));
        } else {
            m.setAudioPath("");
        }


        musics.add(m);

    }


    private static Group parseGroup(JSONObject obj) {
        String artistName = (String) obj.get("artistName");
        String comeFrom = (String) obj.get("comeFrom");
        boolean isFavoriteArtist = (boolean) obj.get("favoriteArtist");
        Group group = new Group(artistName, ComeFrom.valueOf(comeFrom), isFavoriteArtist);
        JSONArray members = (JSONArray) obj.get("group");
        for (int i = 0; i < members.size(); i++) {
            JSONObject memberObj = (JSONObject) members.get(i);
            group.addToGroupMemberList(parseIndividual(memberObj));
        }
        return group;
    }


    private static Individual parseIndividual(JSONObject obj) {
        String artistName = (String) obj.get("artistName");
        String comeFrom = (String) obj.get("comeFrom");
        boolean isFavoriteArtist = (boolean) obj.get("favoriteArtist");
        return new Individual(artistName, ComeFrom.valueOf(comeFrom), isFavoriteArtist);
    }

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Music> readMusics(File f) throws ParseException {
        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        List<Music> musics = new ArrayList<>();
        try (FileReader reader = new FileReader(f.getPath())) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray musicList = (JSONArray) obj;

            for (int i = 0; i < musicList.size(); i++) {
                parseMusic(musics, musicList, i);
            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return musics;
    }


//    // EFFECTS: returns content of file as a list of strings, each string
//    // containing the content of one row of the file
//    private static List<String> readFile(File f) throws IOException {
//        return Files.readAllLines(f.toPath());
//    }

    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one music
//    private static List<Music> parseContent(List<String> fileContent) {
//        List<Music> musics = new ArrayList<>();
//
//        for (String line : fileContent) {
//            ArrayList<String> lineComponents = splitString(line);
//            musics.add(parseMusic(lineComponents));
//        }
//        return musics;
//    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
//    private static ArrayList<String> splitString(String line) {
//        String[] splits = line.split(DELIMITER);
//        return new ArrayList<>(Arrays.asList(splits));
//    }

    // REQUIRES: components has size 4 where element 0 represents the
    // id of the next account to be constructed, element 1 represents
    // the id, elements 2 represents the name and element 3 represents
    // the balance of the account to be constructed
    // EFFECTS: returns an account constructed from components
//    private static Music parseMusic(List<String> components) {
//
//
//        String musicName = components.get(1);
//        MusicStyle musicStyle = MusicStyle.valueOf(components.get(2));
//        Boolean favoriteMusic = Boolean.parseBoolean(components.get(3));
//        Artist parsedArtist = Reader.parseArtist(components.get(0));
//
//        return new Music(parsedArtist, musicName, musicStyle, favoriteMusic);
//    }

//    private static Artist parseArtist(String component) {
////
////        Artist parseIndividual = Reader.parseIndividual(component);
////        Artist parsedGroup = Reader.parseGroup(component);
//
//        String str = component;
//        String[] arr = str.split("\\s+");
//        List<String> a = new ArrayList<>();
//        for (int i = 0; i < arr.length; i++) {
//            a.add(arr[i]);
//        }
//
//        String artistName = a.get(0);
//        ComeFrom comeFrom = ComeFrom.valueOf(a.get(1));
//        Boolean favoriteArtist = Boolean.parseBoolean(a.get(2));
//
//        return new Individual(artistName, comeFrom, favoriteArtist);
//    }

//    private static Artist parseArtist(List<String> components) {
//        //artistName,ComeFrom comeFrom,boolean favoriteArtist
//
//        String artistName = components.get(0);
//        ComeFrom comeFrom = ComeFrom.valueOf(components.get(1));
//        Boolean favoriteArtist = Boolean.parseBoolean(components.get(2));
//
//        return new Individual(artistName, comeFrom, favoriteArtist);
//    }


//    private static Group parseGroup(String c) {
//        String str = c;
//        String[] arr = str.split("\\s+");
//        List<String> a = new ArrayList<>();
//        for (int i = 0; i < arr.length; i++) {
//            a.add(arr[i]);
//        }
//
//        String artistName = a.get(0);
//        ComeFrom comeFrom = ComeFrom.valueOf(a.get(1));
//        Boolean favoriteArtist = Boolean.parseBoolean(a.get(2));
//
//        return new Group(artistName, comeFrom, favoriteArtist);
//    }


}
