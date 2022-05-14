package fr.matthiasdetoffoli.bodybuildingprograms.Activities.Secondary;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import fr.matthiasdetoffoli.bodybuildingprograms.Buttons.DeleteSeriesButton;
import fr.matthiasdetoffoli.bodybuildingprograms.Datas.Data;
import fr.matthiasdetoffoli.bodybuildingprograms.Datas.Exercise;
import fr.matthiasdetoffoli.bodybuildingprograms.Datas.Series;
import fr.matthiasdetoffoli.bodybuildingprograms.EditText.Number.Decimal.RestTimeEditText;
import fr.matthiasdetoffoli.bodybuildingprograms.EditText.Number.Decimal.WeightEditText;
import fr.matthiasdetoffoli.bodybuildingprograms.EditText.Number.RepetitionsEditText;
import fr.matthiasdetoffoli.bodybuildingprograms.LinearLayout.SeriesLayout;
import fr.matthiasdetoffoli.bodybuildingprograms.R;
import fr.matthiasdetoffoli.bodybuildingprograms.Utils.Constants;

/**
 * Created by Matthias de Toffoli on 16/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * Activity for updating an exercise
 * @see ASecondaryActivity
 */
public class UpdateExerciseActivity extends ASecondaryActivity
{
    //region [Members}
    /**
     * @see Exercise we are about to update
     */
    private Exercise mCurrentExercise;

    /**
     * @see String value representing the ID of the current program edited
     */
    private String mCurrentProgramID;

    /**
     * @see int value representing the number of the current day edited
     */
    private int mCurrentDayNum;

    /**
     * @see Button for add a new Series
     */
    private Button mAddSeriesButton;

    /**
     * @see LinearLayout containing all Series of the current exercise
     */
    private LinearLayout mSeriesLayout;

    /**
     * @see Button for delete the exercise
     */
    private Button mDeleteButton;
    //endregion [Members}

    //region [Methods]
    /**
     * creation of the activity
     */
    @Override
    protected void init()
    {
        super.init(R.layout.activity_update_exercise);
        mCurrentProgramID = getStringExtra(Constants.ID_PROGRAM_KEY);
        mCurrentDayNum = getIntExtra(Constants.NUMBER_DAY_KEY);

        mCurrentExercise = Data.getInstance().getExercise(mCurrentProgramID, mCurrentDayNum, getStringExtra(Constants.ID_EXERCISE_KEY));
        setName(R.id.ExerciseName, mCurrentExercise.name);

        //Show all series of the current exercise
        for (Series lSeries : mCurrentExercise.Series)
        {
            addSeries(lSeries.repetition, lSeries.weight, lSeries.restTime);
        }
    }

    /**
     * get all views set in the xml file and put them in in the members of the activity
     */
    @Override
    protected void getAllViews()
    {
        mSeriesLayout = findViewById(R.id.Series);
        mAddSeriesButton = findViewById(R.id.NewExerciseButton);
        mDeleteButton = findViewById(R.id.deleteExercise);
    }

    /**
     * set all listeners needed of the activity's members events
     */
    @Override
    protected void setListeners()
    {
        mAddSeriesButton.setOnClickListener(pView -> onAddSeriesClick());

        mDeleteButton.setOnClickListener(pView -> {
            Data.getInstance().deleteExercise(mCurrentProgramID, mCurrentDayNum, mCurrentExercise.id);
            finish();
        });
    }

    /**
     * Call when we click on the button mAddSeriesButton
     */
    private void onAddSeriesClick()
    {
        addSeries(0, 0, 0);
    }

    /**
     * add a new Series view
     * @param pRep number of repetition
     * @param pWeight weight we lift on the Series
     * @param pRestTime the rest time we have to take after the effort
     */
    private void addSeries(int pRep, float pWeight, float pRestTime)
    {
        //Create elements of the series
        SeriesLayout lSeriesLayout = new SeriesLayout(this);
        RepetitionsEditText lRepEditText = new RepetitionsEditText(this, pRep);
        WeightEditText lWeightEditText = new WeightEditText(this, pWeight);
        RestTimeEditText lRestTimeEditText = new RestTimeEditText(this, pRestTime);
        DeleteSeriesButton lDeleteBtn = new DeleteSeriesButton(this, lSeriesLayout.id);

        //Add elements in the series
        lSeriesLayout.addView(lRepEditText);
        lSeriesLayout.addView(lWeightEditText);
        lSeriesLayout.addView(lRestTimeEditText);
        lSeriesLayout.addView(lDeleteBtn);

        //Add the series in the view
        mSeriesLayout.addView(lSeriesLayout, mSeriesLayout.getChildCount() - 1);
    }

    /**
     * when we click on the Series's remove button
     * remove the Series
     * @param pSeriesRef ID of the Series layout
     */
    public void onRemoveSeriesClick(String pSeriesRef)
    {
        View lChild = null;

        //Find the correct series to remove
        for (int i = 0, l = mSeriesLayout.getChildCount(); i < l; i++)
        {
            lChild = mSeriesLayout.getChildAt(i);
            if (lChild.getClass().equals(SeriesLayout.class) && ((SeriesLayout) lChild).id.equals(pSeriesRef))
            {
                break;
            }
            lChild = null;
        }

        if (lChild != null)
        {
            mSeriesLayout.removeView(lChild);
        }
    }

    /**
     * finish the activity and go to the previous one
     */
    @Override
    public void finish()
    {
        View lView;
        View lSecondView;
        SeriesLayout lLayout;
        WeightEditText lWeight = null;
        RepetitionsEditText lRep = null;
        RestTimeEditText lRest = null;
        int lLayoutCount = 0;

        //Save all series in the Data
        for (int i = 0, l = mSeriesLayout.getChildCount(); i < l; i++)
        {
            lView = mSeriesLayout.getChildAt(i);

            if (lView.getClass().equals(SeriesLayout.class))
            {
                lLayout = (SeriesLayout) lView;

                for (int i2 = 0, l2 = lLayout.getChildCount(); i2 < l2; i2++)
                {
                    lSecondView = lLayout.getChildAt(i2);

                    if (lSecondView.getClass().equals(WeightEditText.class))
                    {
                        lWeight = (WeightEditText) lSecondView;
                    } else if (lSecondView.getClass().equals(RepetitionsEditText.class))
                    {
                        lRep = (RepetitionsEditText) lSecondView;
                    } else if (lSecondView.getClass().equals(RestTimeEditText.class))
                    {
                        lRest = (RestTimeEditText) lSecondView;
                    }
                }

                if (lWeight != null && lRep != null && lRest != null)
                {
                    mCurrentExercise.addSeries(lRep.getIntValue(), lWeight.getFloatValue(), lRest.getFloatValue(), lLayoutCount);
                }
                lLayoutCount++;
            }
        }

        mCurrentExercise.name = mName.getText().toString();
        Data.getInstance().updateExercise(mCurrentProgramID, mCurrentDayNum, mCurrentExercise);

        super.finish();
    }
    //endregion [Methods]
}

