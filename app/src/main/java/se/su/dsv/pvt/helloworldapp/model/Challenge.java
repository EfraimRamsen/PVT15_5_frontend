package se.su.dsv.pvt.helloworldapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Challenge {

    private String name, description;
    private int numberOfParticipants, challengeID, workoutSpotID;
    private long time;

    // Används ej för tillfället
    private Date timeAndDate;
    private String date;

    // Gamla konstruktorn
//    public Challenge(String challenge, String description, int numberOfParticipants, int challengeID, int workoutSpotID, Date timeAndDate){
//        this.name = challenge;
//        this.description = description;
//        this.numberOfParticipants = numberOfParticipants;
//        this.challengeID = challengeID;
//        this.workoutSpotID = workoutSpotID;
//        this.timeAndDate = timeAndDate;
//
////        prepareOutgoingDateTime();
//        setTime();
//    }

    public Challenge(String challenge, String description, int numberOfParticipants, int challengeID, int workoutSpotID, long time){
        this.name = challenge;
        this.description = description;
        this.numberOfParticipants = numberOfParticipants;
        this.challengeID = challengeID;
        this.workoutSpotID = workoutSpotID;
        this.time = time;
    }

    // ********************************************************************************

    public String getName() {
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

    // ********************************************************************************

    // INKOMMANDE JSON

    /**
     * Denna metod deklarerar innehållet i Date-objektet med det datum och tid som challenge-objektet har.
     * @author JD
     */
//    public void setTimeAndDate() {
//        try {
//            timeAndDate = new Date(0);
//            timeAndDate.setTime(time);
//        } catch (Exception e) {
//            System.out.println("Fel: " + e);
//        }
//    }

    // UTGÅENDE JSON

//    public void setTime() {
//        this.time = timeAndDate.getTime();
//    }

    @Override
    public String toString() {
//        return "Challenge: " + name + " " + description + " " + numberOfParticipants + " " + challengeID + " " + workoutSpotID + " " + date + " " + time + " " + timeAndDate;
        return "Challenge: " + name + " " + description + " " + numberOfParticipants + " " + challengeID + " " + workoutSpotID + " " + date + " " + time;
    }
}
