package se.su.dsv.pvt.helloworldapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import se.su.dsv.pvt.helloworldapp.R;

/**
 * @author Niklas Edström
 */
public class TopActionBar extends AppCompatActivity {

    /**
     *
     * @param title is the chosen title for the view.
     * @param viewId is the id you get via findViewById(R.id.xxx), with xxx in this case being
     *               my_toolbar.
     * @return a Toolbar-object which is customized to one's liking.
     */
    public static Toolbar getToolbar(String title, View viewId) {
        Toolbar toolbar = (Toolbar) viewId;
        toolbar.setTitle(title);
        return toolbar;
    }

    public void buttonClicked(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back_button:
                this.findViewById(R.id.parent);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.back_button:
                System.out.println("loool");
                return true;

            default:
                System.out.println("defullllllet");

                return super.onOptionsItemSelected(item);
        }
    }
}
