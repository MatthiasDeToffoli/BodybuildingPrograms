package fr.matthiasdetoffoli.bodybuildingprograms.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import fr.matthiasdetoffoli.bodybuildingprograms.Activities.Secondary.UpdateProgramActivity;
import fr.matthiasdetoffoli.bodybuildingprograms.Buttons.Identified.ProgramButton;
import fr.matthiasdetoffoli.bodybuildingprograms.Datas.Data;
import fr.matthiasdetoffoli.bodybuildingprograms.Datas.Program;
import fr.matthiasdetoffoli.bodybuildingprograms.R;
import fr.matthiasdetoffoli.bodybuildingprograms.Utils.Constants;

/**
 * Created by Matthias de Toffoli on 11/01/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * Main activity
 * @see AActivity
 */
public class MainActivity extends AActivity
{
    //region [Members]
    /**
     * @see GridLayout containing all programs
     */
    private GridLayout mGrid;

    /**
     * @see Button for add a new program
     */
    private Button mAddProgram;
    //endregion [Members]

    //region [Methods]
    /**
     * creation of the activity
     */
    @Override
    protected void init()
    {
        super.init(R.layout.activity_main);
        Data.getInstance().load(getFilesDir());
        addPrograms();
    }

    /**
     * Called after {@link #onStop} when the current activity is being
     * re-displayed to the user (the user has navigated back to it).  It will
     * be followed by {@link #onStart} and then {@link #onResume}.
     *
     * <p>For activities that are using raw {@link Cursor} objects (instead of
     * creating them through
     * {@link #managedQuery(android.net.Uri , String[], String, String[], String)},
     * this is usually the place
     * where the cursor should be required (because you had deactivated it in
     * {@link #onStop}.
     *
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onStop
     * @see #onStart
     * @see #onResume
     */
    @Override
    protected void onRestart()
    {
        super.onRestart();
        addPrograms();
    }

    /**
     * get all views set in the xml file and put them in in the members of the activity
     */
    @Override
    protected void getAllViews()
    {
        mGrid = findViewById(R.id.Grid);
        mAddProgram = findViewById(R.id.addProgram);
    }

    /**
     * set all listeners needed of the activity's members events
     */
    @Override
    protected void setListeners()
    {
        mAddProgram.setOnClickListener(pView -> onAddProgramClick());
    }

    /**
     * Add all programs saved in the grid
     */
    private void addPrograms()
    {
        View lChild;
        for (int i = 0, l = mGrid.getChildCount() - 1; i <= l; l--)
        {
            lChild = mGrid.getChildAt(l);

            if (lChild.getClass().equals(ProgramButton.class))
            {
                mGrid.removeView(lChild);
            }
        }

        for (Program lProgram : Data.getInstance().programs)
        {
            ProgramButton lButton = new ProgramButton(this, lProgram.id, lProgram.name);
            mGrid.addView(lButton, mGrid.getChildCount() - 1);
        }
    }

    /**
     * listener of mAddProgram's click
     */
    private void onAddProgramClick()
    {
        Program lProgram = Data.getInstance().createNewProgram();
        goToUpdateProgramActivity(lProgram.id);
    }

    /**
     * Go to the activity UpdateProgramActivity with the program ID in extra
     * @param pProgRef ID of the program to update
     */
    public void goToUpdateProgramActivity(String pProgRef)
    {
        Intent lIntent = new Intent(this, UpdateProgramActivity.class);
        lIntent.putExtra(Constants.ID_PROGRAM_KEY, pProgRef);
        startActivity(lIntent);
    }
    //endregion [Methods]
}