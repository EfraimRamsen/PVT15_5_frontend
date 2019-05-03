package se.su.dsv.pvt.helloworldapp.activity;

import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import se.su.dsv.pvt.helloworldapp.R;

public class Map extends AppCompatActivity {

    public static final String viewTitle = "Profile";
    public static final int titleIcon = R.drawable.ic_map;
    public static final int iconId = R.id.map_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Create, configure, and set the toolbar:
        Toolbar toolbar = (Toolbar) TopActionBar
                .getToolbar(viewTitle, findViewById(TopActionBar.getToolbarIntLink())
                        , false);
        setSupportActionBar(toolbar);
        ImageView iV = (ImageView) toolbar.findViewById(iconId);
        iV.setImageDrawable(ResourcesCompat.getDrawable(getResources(), titleIcon, null));
    }
    /*
     * example of how an menu should act when, for example, a button is pressed. This is atm.
     * unneccessary since we don't have anything but a back button; a functionality Android handles
     * for us.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

         * The only functionality we're using comes from calling super with the item. This sends us
         * to the parent-view, which is defined in AndroidManifest.xml, under each Views entry.

        switch(item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
