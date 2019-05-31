package se.su.dsv.pvt.helloworldapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import se.su.dsv.pvt.helloworldapp.R;
import se.su.dsv.pvt.helloworldapp.activity.MainActivity;
import se.su.dsv.pvt.helloworldapp.model.Challenge;
import se.su.dsv.pvt.helloworldapp.model.Place;

public class ChallengeDialog extends DialogFragment {

    private static final String TAG = "ChallengeDialog";
    String name;
    String description;
    int workOutSpot;
    int participants;
    Date date;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        //MainActivity mainActivity = (MainActivity) getActivity();

        //HÄR KAN VI NÅ TEXTFÄLTEN FRÅN XML-FILEN SOM BEHÖVER FYLLAS I MED KORREKT DATA (DVS OBJEKTET SOM KLICKATS PÅ)
        //VI KAN DOCK INTE NÅ CHALLENGE-OBJEKTET SOM VI BEHÖVER HÄMTA DATA IFRÅN
        View view = inflater.inflate(R.layout.dialog_challenge, container, false);
        TextView TextTimeAndDate = view.findViewById(R.id.timeAndDate);
        TextView TextName = view.findViewById(R.id.name);
        TextView TextDescription = view.findViewById(R.id.description);
        TextView TextParticipants = view.findViewById(R.id.participants);



        Button join = view.findViewById(R.id.join);
        ImageButton closeDialog = view.findViewById(R.id.closeBtn);
        ImageButton share = view.findViewById(R.id.shareBtn);
        ImageButton twitter = view.findViewById(R.id.twitterBtn);
        TwitterPost twitterPost = new TwitterPost();


        TextTimeAndDate.setText("Tid och datum: " + date + workOutSpot);
        TextName.setText("Utmaning: " + name);
        TextDescription.setText("Beskrivning: " + description );
        TextParticipants.setText("Antal deltagare: " + participants);


        join.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick (View v){
              CharSequence text = join.getText();
              if (text.equals("Gå med")){
                  //TODO: add functionality to join a challenge
                  join.setText("Gå ur");
              } else if (text.equals("Gå ur")){
                  //TODO: add functionality to leave a challenge
                  join.setText("Gå med");
              }
          }
        });

        closeDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getDialog().dismiss();
            }
        });

        share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = description + "\n" + date;
                String shareSub = "Jag har skapat utemaningen : " + name + " med appen Utemaning. Kom och hurta med mig!";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myIntent, "Dela med: "));

               // getDialog().dismiss();
            }
        });

        twitter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String challengeInfo = "Jag är intresserad av INSERT_CHALLENGE_HERE i appen Utemaning - Möt mig och anta utmaningen du med!";
                System.out.println("twitter");
                startActivity(twitterPost.getTwitterIntent(getContext(), challengeInfo));
            }
        });

        return view;
    }

    // HÄR KAN VI NÅ CHALLENGE-OBJEKTET SOM BLIVIT KLICKAT PÅ
    // VI KAN DÄREMOT ÄN SÅ LÄNGE INTE NÅ TEXTFÄLTEN I XML-FILEN SOM BEHÖVER UPPDATERAS MED DATAT
    public void updateView(Challenge c){

        name = c.getName();
        description =c.getDescription();
        workOutSpot = c.getWorkoutSpotID();
        date = c.getTimeAndDate();
        participants = c.getNumberOfParticipants();
    }

    public class TwitterPost
    {
        public Intent getTwitterIntent(Context ctx, String shareText)
        {
            Intent shareIntent;

            if(isPackageInstalled(ctx, "com.twitter.android"))
            {
                shareIntent = new Intent(Intent.ACTION_SEND);
//                shareIntent.setClassName("com.twitter.android",
//                        "com.twitter.android.PostActivity");
                shareIntent.setPackage("com.twitter.android");
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareText);
                return shareIntent;
            }
            else
            {
                String tweetUrl = "https://twitter.com/intent/tweet?text=" + shareText;
                Uri uri = Uri.parse(tweetUrl);
                shareIntent = new Intent(Intent.ACTION_VIEW, uri);
                return shareIntent;
            }
        }

        public boolean isPackageInstalled(Context context, String packageName) {
            final PackageManager packageManager = context.getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage(packageName);
            if (intent == null) {
                return false;
            }
            List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            return list.size() > 0;
        }
    }
}
