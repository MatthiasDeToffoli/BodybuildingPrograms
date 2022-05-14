package fr.matthiasdetoffoli.bodybuildingprograms.Activities;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * Created by Matthias de Toffoli on 17/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * Parent of all Activities
 * @see Activity
 */
public abstract class AActivity extends AppCompatActivity
{
    /**
     * creation of the activity
     * @param savedInstanceState the saved instance
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * initialize the activity
     */
    protected abstract void init();

    /**
     * initialize the activity
     * @param pContentVIewID layout activity ID
     */
    protected void init(int pContentVIewID)
    {
        setContentView(pContentVIewID);
        getAllViews();
        setListeners();
    }

    /**
     * get all views set in the xml file and put them in in the members of the activity
     */
    protected abstract void getAllViews();

    /**
     * set all listeners needed of the activity's members events
     */
    protected abstract void setListeners();
}
