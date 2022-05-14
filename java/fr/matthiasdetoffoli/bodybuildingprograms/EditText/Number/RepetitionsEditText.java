package fr.matthiasdetoffoli.bodybuildingprograms.EditText.Number;

import android.annotation.SuppressLint;
import android.widget.LinearLayout;

import fr.matthiasdetoffoli.bodybuildingprograms.Activities.Secondary.UpdateExerciseActivity;

/**
 * Created by Matthias de Toffoli on 22/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see ANumberEditText for Repetitions
 */
@SuppressLint("ViewConstructor")
public class RepetitionsEditText extends ANumberEditText
{
    //region Constructors
    /**
     * Constructor of a RepetitionsEditText
     * @param pActivity UpdateExerciseActivity containing the EditText
     * @param pValue default value
     */
    public RepetitionsEditText(UpdateExerciseActivity pActivity, int pValue)
    {
        super(pActivity, 2, pValue);
    }
    //endregion Constructors
    //region Methods
    /**
     * Set the LayoutParams of the EditText
     */
    @Override
    protected void setLayoutParams()
    {
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParams.weight = 1;
        setLayoutParams(lParams);
    }
    //endregion Methods
}
