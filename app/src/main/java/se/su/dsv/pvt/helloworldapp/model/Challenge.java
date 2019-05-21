package se.su.dsv.pvt.helloworldapp.model;

import java.util.Date;

public class Challenge {

    private String name, description;
    private int numberOfParticipants, challengeID, workoutSpotID;
    private Date timeAndDate; // datum och tid nedan måste omvandlas till Date datatyp och sparas i denna variabel
    private String date; //datum i JSON
    private long time; // tid i JSON

    public Challenge(String challenge, String description, int numberOfParticipants, int challengeID, int workoutSpotID, String date, long time){
        this.name = challenge;
        this.description = description;
        this.numberOfParticipants = numberOfParticipants;
        this.challengeID = challengeID;
        this.workoutSpotID = workoutSpotID;
        this.date = date;
        this.time = time;
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

    public Date getTimeAndDate(){
        return timeAndDate;
    }

    @Override
    public String toString() {
        return "Challenge: " + name + " " + description + " " + numberOfParticipants + " " + challengeID + " " + workoutSpotID + " " + date + " " + time;
    }
}
