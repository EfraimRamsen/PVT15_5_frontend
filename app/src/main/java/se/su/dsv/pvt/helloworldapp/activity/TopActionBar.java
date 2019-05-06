package se.su.dsv.pvt.helloworldapp.activity;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;

import se.su.dsv.pvt.helloworldapp.R;

/**
 * @author Niklas Edström
 */
public class TopActionBar extends AppCompatActivity {

    public static final int toolbarIntLink = R.id.my_toolbar;

    /**
     *
     * @param title is the chosen title for the view.
     * @param viewId is the id you get via findViewById(R.id.xxx), with xxx in this case being
     *               my_toolbar.
     * @return a Toolbar-object which is customized to one's liking.
     */

    /**
     *
     * @param viewId is the id you get via findViewById(R.id.xxx), with xxx in this case being
     *               my_toolbar.
     * @param isStartScreen if true, back button is unneccessary.
     * @return
     */
    public static Toolbar getToolbar(View viewId, boolean isStartScreen) {
        Toolbar toolbar = (Toolbar) viewId;
        if (!isStartScreen) {
            toolbar.setNavigationIcon(R.drawable.ic_back_button);
        }
        toolbar.hideOverflowMenu();
        return toolbar;
    }
    public static int getToolbarIntLink() {
        return toolbarIntLink;
    }

    public static int getWindowWidth(Display d) {
        Display display = d;
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        return width;
    }

}
