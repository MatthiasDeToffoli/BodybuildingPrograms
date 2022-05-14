package fr.matthiasdetoffoli.bodybuildingprograms.EditText.Number.Decimal;

import android.annotation.SuppressLint;

import fr.matthiasdetoffoli.bodybuildingprograms.Activities.Secondary.UpdateExerciseActivity;

/**
 * Created by Matthias de Toffoli on 22/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see ASeriesElementEditText for rest time
 */
@SuppressLint("ViewConstructor")
public class RestTimeEditText extends ASeriesElementEditText
{
    /**
     * Constructors of a RestTimeEditText
     * @param pActivity UpdateExerciseActivity containing the EditText
     * @param pValue default value
     */
    public RestTimeEditText(UpdateExerciseActivity pActivity, float pValue)
    {
        super(pActivity, 5, pValue);
    }
}
