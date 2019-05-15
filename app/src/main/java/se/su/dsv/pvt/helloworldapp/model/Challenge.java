package se.su.dsv.pvt.helloworldapp.model;

import java.util.ArrayList;
import java.util.Date;

public class Challenge {
    /**
     * alla variabler av typen Object är sådana som backendsidan inte verkar ha bestämt sig för.
     * ArrayListen participantList innehåller hos backend User, men känns inte som att det är något
     * vi behöver i frontend när ändå bara namn ska visas? Alternativt att vi hämtar typ namn, bild,
     *  å länk till usern. Men ja. tbd.
     */
    private String name;
    private int numberOfParticipants, challengeID, workoutSpotID;
    //private Object level;
    //private Object workoutType;
    private Date eventTimeAndDate = new Date();
    private String description;
    private ArrayList<String> participantList = new ArrayList<>();

    public Challenge(String name, int numberOfParticipants, Date eventTimeAndDate, String description,
                     int challengeID, int workoutspotID){
        this.name = name;
        this.numberOfParticipants = numberOfParticipants;
        this.challengeID = challengeID;
        this.workoutSpotID = workoutspotID;
        //this.level = level; //backend får bestämma.
        //this.workoutType = workoutType; //backend får bestämma.
        this.eventTimeAndDate = eventTimeAndDate;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }
    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
    public int getChallengeID() {
        return challengeID;
    }
    public void setChallengeID(int challengeID) {
        this.challengeID = challengeID;
    }
    public int getWorkoutSpotID() {
        return workoutSpotID;
    }
    public void setWorkoutSpotID(int workoutSpotID) {
        this.workoutSpotID = workoutSpotID;
    }
    public Date getEventTimeAndDate() {
        return eventTimeAndDate;
    }
    public void setEventTimeAndDate(Date eventTimeAndDate) {
        this.eventTimeAndDate = eventTimeAndDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ArrayList<String> getParticipantList() {
        return participantList;
    }
    public void setParticipantList(ArrayList<String> participantList) {
        this.participantList = participantList;
    }
}
