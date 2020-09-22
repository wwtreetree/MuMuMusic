package model;

//contain group artist information
import model.Individual;

import java.util.ArrayList;
import java.util.Date;

public class Group extends Artist {
    ArrayList<Individual> group = new ArrayList<>();
    int memberNum;


    public Group(String artistName, ComeFrom comeFrom, boolean favoriteArtist) {
        super(artistName, comeFrom, favoriteArtist);
        this.memberNum = 0;
    }

    public ArrayList<Individual> getGroup() {
        return group;
    }

    //MODIFIES: this
    //EFFECTS: get the member num from a given group
    public int getMemberNum() {
        return memberNum;
    }



    //EFFECTS: add a member to group
    public void addToGroupMemberList(Individual i) {
        group.add(i);
        memberNum++;
    }

    //EFFECTS: remove member from group
    public void removeFromGroupMemberList(Individual i) {
        group.remove(i);
        memberNum--;
    }

    //EFFECTS: return String to represent member Name from group
    @Override
    public String getMemberInfo() {
        String info = "";
        for (Individual i: group) {
            info += i.getArtistName() + " ";
        }
        return info;
    }



}
