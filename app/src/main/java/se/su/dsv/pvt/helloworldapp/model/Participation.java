package se.su.dsv.pvt.helloworldapp.model;

public class Participation {
    private int challengeID;
    private int participationID;
    private boolean completed;
    private String userName;

    @Override
    public String toString() {
        return challengeID + " " + participationID + " " + completed + " " + userName;
    }

    public int getChallengeID() {
        return challengeID;
    }

    public int getParticipationID() {
        return participationID;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getUserName() {
        return userName;
    }
}
