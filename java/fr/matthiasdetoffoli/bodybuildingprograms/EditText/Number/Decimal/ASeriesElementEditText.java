package fr.matthiasdetoffoli.bodybuildingprograms.EditText.Number.Decimal;

import android.widget.LinearLayout;

import fr.matthiasdetoffoli.bodybuildingprograms.Activities.Secondary.UpdateExerciseActivity;

/**
 * Created by Matthias de Toffoli on 22/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see ADecimalNumberEditText used in a Series
 */
public abstract class ASeriesElementEditText extends ADecimalNumberEditText
{
    //region Constructors
    /**
     * Constructor of an ASeriesElementEditText
     * @param pActivity UpdateExerciseActivity containing the EditText
     * @param pMaxCharacter number max of character
     * @param pValue default value
     */
    public ASeriesElementEditText(UpdateExerciseActivity pActivity, int pMaxCharacter, float pValue)
    {
        super(pActivity, pMaxCharacter, pValue);
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
