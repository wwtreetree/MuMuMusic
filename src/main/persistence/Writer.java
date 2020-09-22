package persistence;


import model.Group;
import model.Individual;
import model.Music;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.List;

//a writer that can write artist and music data to a file
public class Writer {

    private static JSONObject convertIndividual(Individual obj) {
        JSONObject individualObj = new JSONObject();
        individualObj.put("artistName", obj.getArtistName());
        individualObj.put("comeFrom", obj.getComeFrom().toString());
        individualObj.put("favoriteArtist", obj.isFavoriteArtist());
        return individualObj;
    }

    private static JSONObject convertGroup(Group obj) {
        JSONObject groupObj = new JSONObject();
        groupObj.put("artistName", obj.getArtistName());
        groupObj.put("comeFrom", obj.getComeFrom().toString());
        groupObj.put("favoriteArtist", obj.isFavoriteArtist());
        JSONArray members = new JSONArray();
        for (Individual individual : obj.getGroup()) {
            JSONObject member = convertIndividual(individual);
            members.add(member);
        }
        groupObj.put("group", members);
        return groupObj;
    }

    // MODIFIES: this
    // EFFECTS: writes savable to file
    public static void writeMusics(List<Music> musicList, File file) {
        JSONArray musicArray = new JSONArray();

        for (Music m : musicList) {
            JSONObject musicObj = new JSONObject();
            // convert one music to json
            musicObj.put("musicName", m.getMusicName());
            musicObj.put("musicStyle", m.getMusicStyle().toString());
            musicObj.put("favoriteMusic", m.getIsFavoriteMusic());
            musicObj.put("audioPath", m.getAudioPath());

            JSONObject artistObj = new JSONObject();
            if (m.getArtist() instanceof Individual) {
                artistObj = convertIndividual((Individual) m.getArtist());
            } else {
                artistObj = convertGroup((Group) m.getArtist());
            }
            musicObj.put("Artist", artistObj);

            musicArray.add(musicObj);
        }

        try (FileWriter fileWriter = new FileWriter(file.getPath())) {
            fileWriter.write(musicArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
