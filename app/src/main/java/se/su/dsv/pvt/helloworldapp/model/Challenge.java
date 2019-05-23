package se.su.dsv.pvt.helloworldapp.model;

import java.util.Date;

public class Challenge {

    private String name, description;
    private int numberOfParticipants, challengeID, workoutSpotID;
    private Date date;

    public Challenge(String challenge, String description, int numberOfParticipants, int challengeID, int workoutSpotID, Date date){
        this.name = challenge;
        this.description = description;
        this.numberOfParticipants = numberOfParticipants;
        this.challengeID = challengeID;
        this.workoutSpotID = workoutSpotID;
        this.date = date;
    }

    public String getChallenge(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getNumberOfParticipants(){
        return numberOfParticipants;
    }

    public int getChallengeID(){
        return challengeID;
    }

    public int getWorkoutSpotID(){
        return workoutSpotID;
    }

    public Date getDate(){
        return date;
    }

    @Override
    public String toString() {
        return "Challenge: " + name + " " + description + " " + numberOfParticipants + " " + challengeID + " " + workoutSpotID + " " + date;
    }
}
