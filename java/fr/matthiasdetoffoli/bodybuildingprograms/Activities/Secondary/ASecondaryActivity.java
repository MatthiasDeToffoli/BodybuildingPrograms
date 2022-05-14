package fr.matthiasdetoffoli.bodybuildingprograms.Activities.Secondary;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;

import fr.matthiasdetoffoli.bodybuildingprograms.Activities.AActivity;

/**
 * Created by Matthias de Toffoli on 24/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * Parent of the activities which are not the MainActivity
 * @see AActivity
 */
public abstract class ASecondaryActivity extends AActivity
{
            //region [Members]
    /**
     * @see EditText which contains the name of the current activity
     */
    protected EditText mName;

    /**
     * @see Intent for get parameters from previous activity
     */
    private Intent mIntent;
    //endregion [Members]

    //region [Methods]
    /**
     * creation of the activity
     * @param pContentVIewID content view's ID for load the xml layout
     */
    @Override
    protected void init(int pContentVIewID)
    {
        super.init(pContentVIewID);

        //Show the back arrow in the toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mIntent = getIntent();
    }

    /**
     * Get string extra set in the previous activity with the member mIntent
     * @param pKey Key for get the string extra
     * @return the string extra if the intent is not null, otherwise return empty string
     */
    protected String getStringExtra(String pKey)
    {
        if (mIntent != null)
        {
            return mIntent.getStringExtra(pKey);
        }
        return "";
    }

    /**
     * Get int extra set in the previous activity with the member mIntent
     * @param pKey Key for get the int extra
     * @return the int extra if the intent is not null, 0 otherwise
     */
    protected int getIntExtra(String pKey)
    {
        if (mIntent != null)
        {
            return mIntent.getIntExtra(pKey, 0);
        }
        return 0;
    }

    /**
     * Find the name's EditText and set it's value
     * @param pRef ID of the EditText
     * @param pText text of the EditText
     */
    protected void setName(int pRef, String pText)
    {
        mName = findViewById(pRef);
        mName.setText(pText);
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     *
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param pItem The menu item that was selected.
     *
     * @return boolean Return false to allow normal menu processing to
     *         proceed, true to consume it here.
     *
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem pItem)
    {
        finish();
        return true;
    }
    //endregion [Methods]
}
