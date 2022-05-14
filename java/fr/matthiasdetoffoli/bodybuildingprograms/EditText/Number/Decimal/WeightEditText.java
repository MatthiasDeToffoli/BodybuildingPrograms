package fr.matthiasdetoffoli.bodybuildingprograms.EditText.Number.Decimal;

import android.annotation.SuppressLint;

import fr.matthiasdetoffoli.bodybuildingprograms.Activities.Secondary.UpdateExerciseActivity;

/**
 * Created by Matthias de Toffoli on 22/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see ASeriesElementEditText for weight
 */
@SuppressLint("ViewConstructor")
public class WeightEditText extends ASeriesElementEditText
{
    /**
     * Constructor of a WeightEditText
     * @param pActivity UpdateExerciseActivity containing the EditText
     * @param pValue default value
     */
    public WeightEditText(UpdateExerciseActivity pActivity, float pValue)
    {
        super(pActivity, 3, pValue);
    }
}
