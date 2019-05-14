package se.su.dsv.pvt.helloworldapp.model;

import java.util.ArrayList;

public abstract class Place {
    private Location location; // detta är en egen klass i backend-sidan, men detta är vad appen behöver.
    private String name;
    private int id;
    ArrayList<Challenge> challengeList = new ArrayList<>();

    public Place(Location location, String name, int id) {
        this.location = location;
        this.name = name;
        this.id = id;

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

}
