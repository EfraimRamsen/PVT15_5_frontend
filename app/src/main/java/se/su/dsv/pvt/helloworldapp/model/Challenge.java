package se.su.dsv.pvt.helloworldapp.model;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Challenge {

    private String name, description;
    private int numberOfParticipants, challengeID, workoutSpotID;
    private Date timeAndDate; // datum och tid nedan måste omvandlas till Date datatyp och sparas i denna variabel
    private String date; //datum i JSON
    private long time; // tid i JSON

//    public Challenge(String challenge, String description, int numberOfParticipants, int challengeID, int workoutSpotID, String date, long time) {
//        this.name = challenge;
//        this.description = description;
//        this.numberOfParticipants = numberOfParticipants;
//        this.challengeID = challengeID;
//        this.workoutSpotID = workoutSpotID;
//        this.date = date;
//        this.time = time;
//    }

//    public Challenge(String challenge, String description, int numberOfParticipants, int challengeID, int workoutSpotID){
//        this.name = challenge;
//        this.description = description;
//        this.numberOfParticipants = numberOfParticipants;
//        this.challengeID = challengeID;
//        this.workoutSpotID = workoutSpotID;
//        timeAndDate = new Date(Calendar.getInstance().getTimeInMillis());
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfParticipants(){
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public int getChallengeID(){
        return challengeID;
    }

    public void setChallengeID(int challengeID) {
        this.challengeID = challengeID;
    }

    public int getWorkoutSpotID(){
        return workoutSpotID;
    }

    public void setWorkoutSpotID(int workoutSpotID) {
        this.workoutSpotID = workoutSpotID;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getTimeAndDate(){
        return timeAndDate;
    }

    public void setTimeAndDate(Date timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public void setTimeAndDate() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            timeAndDate = dateFormat.parse(date);
            timeAndDate.setTime(time);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        return "Challenge: " + name + " " + description + " " + numberOfParticipants + " " + challengeID + " " + workoutSpotID + " " + timeAndDate + " " + timeAndDate.getTime() + " " + time;
    }
}
