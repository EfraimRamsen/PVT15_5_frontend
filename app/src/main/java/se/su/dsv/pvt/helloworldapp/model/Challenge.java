package se.su.dsv.pvt.helloworldapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Challenge {

    private String name, description;
    private int numberOfParticipants, challengeID, workoutSpotID;
    private Date timeAndDate;
    private long time;
    private String date;

// from create_challenge_to_api branch
//    public Challenge(String challenge, String description, int numberOfParticipants, int challengeID, int workoutSpotID, String date, long time) {
//        this.name = challenge;
//        this.description = description;
//        this.numberOfParticipants = numberOfParticipants;
//        this.challengeID = challengeID;
//        this.workoutSpotID = workoutSpotID;
//        this.date = date;
//        this.time = time;
//    }

  
// from create_challenge_to_api branch
//    public Challenge(String challenge, String description, int numberOfParticipants, int challengeID, int workoutSpotID){
//        this.name = challenge;
//        this.description = description;
//        this.numberOfParticipants = numberOfParticipants;
//        this.challengeID = challengeID;
//        this.workoutSpotID = workoutSpotID;
//        timeAndDate = new Date(Calendar.getInstance().getTimeInMillis());
//    }

    public Challenge(String challenge, String description, int numberOfParticipants, int challengeID, int workoutSpotID, Date timeAndDate){
        this.name = challenge;
        this.description = description;
        this.numberOfParticipants = numberOfParticipants;
        this.challengeID = challengeID;
        this.workoutSpotID = workoutSpotID;
        this.timeAndDate = timeAndDate;

        prepareOutgoingDateTime();
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


//    public String getDate() {
//        return date;
//    }

    public Date getTimeAndDate() {
        return timeAndDate;
    }

//    public long getTime() {
//        return timeAndDate.getTime();
//    }

    //    public String getDate() {
//        date = timeAndDate.toString();
//        return date;
//    }
//
//    public Date getDate() {
//        System.out.println("test i getDate");
//        return timeAndDate;
//    }

    // INKOMMANDE JSON

    // KORTA NER?
    /**
     * Denna metod deklarerar innehållet i Date-objektet med det datum och tid som challenge-objektet har.
     * @author JD
     */
    public void setTimeAndDate() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            timeAndDate = dateFormat.parse(date);
            timeAndDate.setTime(time);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // UTGÅENDE JSON

    // ÄNDRAS
    public void prepareOutgoingDateTime() {
        setTime();
        setDate();
    }

    // ANVÄNDS
    public void setTime() {
        this.time = timeAndDate.getTime();
    }

    // KAN TAS BORT?
    public void setDate() {
        String dateText = new SimpleDateFormat("yyyy-MM-dd").format(timeAndDate);
        this.date = dateText;
    }

    @Override
    public String toString() {
        return "Challenge: " + name + " " + description + " " + numberOfParticipants + " " + challengeID + " " + workoutSpotID + " " + date + " " + "time" + " " + timeAndDate;
    }
}
