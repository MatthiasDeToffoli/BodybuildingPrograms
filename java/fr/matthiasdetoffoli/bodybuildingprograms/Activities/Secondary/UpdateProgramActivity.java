package fr.matthiasdetoffoli.bodybuildingprograms.Activities.Secondary;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import fr.matthiasdetoffoli.bodybuildingprograms.Buttons.Identified.ExerciseButton;
import fr.matthiasdetoffoli.bodybuildingprograms.Buttons.Toggles.DayButton;
import fr.matthiasdetoffoli.bodybuildingprograms.Datas.Data;
import fr.matthiasdetoffoli.bodybuildingprograms.Datas.Day;
import fr.matthiasdetoffoli.bodybuildingprograms.Datas.Exercise;
import fr.matthiasdetoffoli.bodybuildingprograms.Datas.Program;
import fr.matthiasdetoffoli.bodybuildingprograms.R;
import fr.matthiasdetoffoli.bodybuildingprograms.Utils.Constants;

/**
 * Created by Matthias de Toffoli on 24/01/2022.
 * http://matthiasdetoffoli.fr/.
 * Activity for updating a program
 *
 * @see ASecondaryActivity
 */
public class UpdateProgramActivity extends ASecondaryActivity
{
    //region [Members]
    /**
     * @see Program we are about to update
     */
    private Program mCurrentProgram;

    /**
     * @see LinearLayout containing all days of this program
     */
    private LinearLayout mDaysLayout;

    //The new day btn
    /**
     * @see Button for create a new Day in the program
     */
    private Button mNewDayBTN;

    //The layout contained all exercises of this day
    /**
     * @see LinearLayout containing all exercises
     */
    private LinearLayout mExercisesLayout;

    /**
     * @see Button which add a new exercise
     */
    private Button mNewExerciseButton;

    /**
     * @see int representing the number of the current day selected
     */
    private int mCurrentDayNum;

    /**
     * @see Button for delete the program
     */
    private Button mDeleteButton;
    //endregion [Members]

    //region [Methods]

    /**
     * creation of the activity
     */
    @Override
    protected void init()
    {
        super.init(R.layout.activity_update_program);
        mCurrentProgram = Data.getInstance().getProgram(getStringExtra(Constants.ID_PROGRAM_KEY));
        setName(R.id.ProgramName, mCurrentProgram.name);

        //Can't have more than 9 days
        if (mCurrentProgram.days.size() >= 9)
        {
            mNewDayBTN.setVisibility(View.INVISIBLE);
        }

        for (Day lDay : mCurrentProgram.days)
        {
            addDayButton(lDay);
        }

        mCurrentDayNum = 1;

        addExerciseButtons(mCurrentProgram.days.get(0));
    }

    /**
     * Called after {@link #onStop} when the current activity is being
     * re-displayed to the user (the user has navigated back to it).  It will
     * be followed by {@link #onStart} and then {@link #onResume}.
     * <p>
     * <p>For activities that are using raw {@link Cursor} objects (instead of
     * creating them through
     * {@link #managedQuery(android.net.Uri, String[], String, String[], String)},
     * this is usually the place
     * where the cursor should be required (because you had deactivated it in
     * {@link #onStop}.
     * <p>
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
        cleanExercisesLayout();
        Day lDay = mCurrentProgram.getDay(mCurrentDayNum);

        if (lDay != null && lDay.exercises.size() > 0)
        {
            addExerciseButtons(lDay);
        }
    }

    /**
     * get all views set in the xml file and put them in in the members of the activity
     */
    @Override
    protected void getAllViews()
    {
        mDaysLayout = findViewById(R.id.Days);
        mNewDayBTN = findViewById(R.id.NewDayBTN);
        mExercisesLayout = findViewById(R.id.Exercises);
        mNewExerciseButton = findViewById(R.id.NewExerciseButton);
        mDeleteButton = findViewById(R.id.deleteProgram);
    }

    /**
     * set all listeners needed of the activity's members events
     */
    @Override
    protected void setListeners()
    {
        mNewDayBTN.setOnClickListener(pView -> addDay());

        mNewExerciseButton.setOnClickListener(pView -> onAddExerciseClick());

        mDeleteButton.setOnClickListener(pView -> {
            Data.getInstance().deleteProgram(mCurrentProgram.id);
            finish();
        });
    }

    //region [Days]

    /**
     * Add a new day
     */
    private void addDay()
    {
        Day lDay = new Day(mCurrentProgram.days.size() + 1);
        DayButton lButton = new DayButton(this, lDay.number, true);
        for (int i = 0, l = mDaysLayout.getChildCount() - 1; i < l; i++)
        {
            ((DayButton) mDaysLayout.getChildAt(i)).setChecked(false);
        }

        mDaysLayout.addView(lButton, mDaysLayout.indexOfChild(mNewDayBTN));
        mCurrentProgram.days.add(lDay);

        if (lDay.number == 9)
        {
            mNewDayBTN.setVisibility(View.INVISIBLE);
        }

        cleanExercisesLayout();
        mCurrentDayNum = lDay.number;
    }

    /**
     * add a day button in the DaysLayout
     *
     * @param pDay Day linked to the button
     */
    private void addDayButton(Day pDay)
    {
        DayButton lButton = new DayButton(this, pDay.number, mDaysLayout.getChildCount() == 1);
        mDaysLayout.addView(lButton, mDaysLayout.indexOfChild(mNewDayBTN));
    }

    /**
     * When a DayButton is checked
     *
     * @param pDayButton the DayButton checked
     */
    public void onDayChecked(DayButton pDayButton)
    {
        DayButton lDayButtonOnView;
        for (int i = 0, l = mDaysLayout.getChildCount(); i < l; i++)
        {
            if (mDaysLayout.getChildAt(i).getClass() == DayButton.class)
            {
                lDayButtonOnView = (DayButton) mDaysLayout.getChildAt(i);
                if (lDayButtonOnView.number != pDayButton.number)
                {
                    ((DayButton) mDaysLayout.getChildAt(i)).setChecked(false);
                }
            }

        }

        cleanExercisesLayout();
        //Add the exercises linked to the day (if it's a loaded day)
        for (Day lDay : mCurrentProgram.days)
        {
            if (lDay.number == pDayButton.number)
            {
                addExerciseButtons(lDay);
                break;
            }
        }

        mCurrentDayNum = pDayButton.number;
    }
    //endregion [Days]

    //region [Exercises]

    /**
     * Add all exercise buttons of a day
     *
     * @param pDay the day containing the exercises
     */
    private void addExerciseButtons(Day pDay)
    {
        if (pDay != null)
            for (Exercise lExercise : pDay.exercises)
            {
                addExerciseButton(lExercise);
            }
    }

    /**
     * Listener of mNewExerciseButton's click
     */
    private void onAddExerciseClick()
    {
        Exercise lExercise = Data.getInstance().createNewExercise(mCurrentProgram, mCurrentDayNum);
        goToUpdateExerciseActivity(lExercise.id);
    }

    /**
     * add an exercise button for update an existing exercise
     *
     * @param pExercise the exercise linked to the button
     */
    private void addExerciseButton(final Exercise pExercise)
    {
        View lChild;
        //Check if the exercise button already exist in the current view
        for (int i = 0, l = mExercisesLayout.getChildCount(); i < l; i++)
        {
            lChild = mExercisesLayout.getChildAt(i);

            if (lChild.getClass() == ExerciseButton.class && ((ExerciseButton) lChild).uID.equals(pExercise.id))
            {
                return;
            }

        }

        ExerciseButton lExerciseBtn = new ExerciseButton(this, pExercise.id, pExercise.name);
        mExercisesLayout.addView(lExerciseBtn, mExercisesLayout.getChildCount() - 1);
    }

    /**
     * Clean the exercises layout removing all the ExerciseButton in it
     */
    private void cleanExercisesLayout()
    {
        View lChild;
        for (int i = 0, l = mExercisesLayout.getChildCount() - 1; i <= l; l--)
        {
            lChild = mExercisesLayout.getChildAt(l);
            if (lChild != null && lChild.getClass().equals(ExerciseButton.class))
            {
                mExercisesLayout.removeView(lChild);
            }
        }
    }
    //endregion [Exercises]

    /**
     * Go to the activity UpdateExerciseActivity with the program ID, the day's number and the exercise's ID in extra parameters
     *
     * @param pExRef the exercise's ID
     */
    public void goToUpdateExerciseActivity(String pExRef)
    {
        Intent lIntent = new Intent(this, UpdateExerciseActivity.class);
        lIntent.putExtra(Constants.ID_PROGRAM_KEY, mCurrentProgram.id);
        lIntent.putExtra(Constants.NUMBER_DAY_KEY, mCurrentDayNum);
        lIntent.putExtra(Constants.ID_EXERCISE_KEY, pExRef);
        startActivity(lIntent);

    }

    /**
     * finish the activity and go to the previous one
     */
    @Override
    public void finish()
    {
        mCurrentProgram.name = mName.getText().toString();
        Data.getInstance().updateProgram(mCurrentProgram);
        super.finish();
    }
    //endregion [Methods]
}
