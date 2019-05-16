package se.su.dsv.pvt.helloworldapp.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Denna klass finns för att representera en Challenge.
 * NOTERA: denna klass är tom, och bör kopplas ihop med det Fragment som representerar... de olika
 * Challenge-fragmenten.
 */

public class Challenge {
    /**
     * alla variabler av typen Object är sådana som backendsidan inte verkar ha bestämt sig för.
     * ArrayListen participantList innehåller hos backend User, men känns inte som att det är något
     * vi behöver i frontend när ändå bara namn ska visas? Alternativt att vi hämtar typ namn, bild,
     *  å länk till usern. Men ja. tbd.
     */
    private String challenge, description;
    private int participants, challengeID, locationID;
    private Date timeAndDate;

    public Challenge(String challenge, String description, int participants, int challengeID, int locationID, Date timeAndDate){
        this.challenge = challenge;
        this.description = description;
        this.participants = participants;
        this.challengeID = challengeID;
        this.locationID = locationID;
        this.timeAndDate = timeAndDate;
    }

    public String getChallenge(){
        return challenge;
    }

    public String getDescription(){
        return description;
    }

    public int getParticipants(){
        return participants;
    }

    public int getChallengeID(){
        return challengeID;
    }

    public int getLocationID(){
        return locationID;
    }

    public Date getTimeAndDate(){
        return timeAndDate;
    }

}
