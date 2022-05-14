package fr.matthiasdetoffoli.bodybuildingprograms.Buttons.Identified;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.LinearLayout;

import fr.matthiasdetoffoli.bodybuildingprograms.Activities.Secondary.UpdateProgramActivity;

/**
 * Created by Matthias de Toffoli on 16/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see AIdentifiedButton which used for editing an exercise
 */
@SuppressLint("ViewConstructor")
public class ExerciseButton extends AIdentifiedButton
{

    /**
     * Constructor of an ExerciseButton
     *
     * @param pActivity the update program activity which will show the button
     * @param pUID      Unique ID of the button
     * @param pText     text of the button
     */
    public ExerciseButton(UpdateProgramActivity pActivity, String pUID, String pText)
    {
        super(pActivity, pUID, pText);
    }

    /**
     * Set the LayoutParams of the button
     */
    @Override
    protected void setLayoutParams()
    {
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(lParams);
    }

    /**
     * call when the button is clicked
     */
    @Override
    protected void onClick()
    {
        Context lContext = getContext();

        if (lContext != null && lContext.getClass() == UpdateProgramActivity.class)
        {
            ((UpdateProgramActivity) lContext).goToUpdateExerciseActivity(uID);
        }
    }
}
