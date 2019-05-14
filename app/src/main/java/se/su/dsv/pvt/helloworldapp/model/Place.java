package se.su.dsv.pvt.helloworldapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public abstract class Place {
    @SerializedName("location")
    @Expose
    private Location location; // detta är en egen klass i backend-sidan, men detta är vad appen behöver.
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;
    ArrayList<Challenge> challengeList = new ArrayList<>();

    public Place(Location location, String name, Integer id) {
        super();
        this.location =  location;
        this.name = name;
        this.id = id;
    }

    public Place() {
    }

    public void addChallange(Challenge newChallenge){
        challengeList.add(newChallenge);
    }
    public void removeChallenge(Challenge challenge){
        challengeList.remove(challenge);
    }
    public ArrayList<Challenge> getChallengeList() {
        return challengeList;
    }
    public void setChallengeList(ArrayList<Challenge> challengeList) {
        this.challengeList = challengeList;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " " + id + " " + location;
    }
}
