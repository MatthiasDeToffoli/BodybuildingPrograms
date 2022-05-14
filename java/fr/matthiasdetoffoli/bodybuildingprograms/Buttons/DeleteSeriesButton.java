package fr.matthiasdetoffoli.bodybuildingprograms.Buttons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.LinearLayout;

import fr.matthiasdetoffoli.bodybuildingprograms.Activities.Secondary.UpdateExerciseActivity;

/**
 * Created by Matthias de Toffoli on 23/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see AButton which used for delete a Series
 */
@SuppressLint("ViewConstructor")
public class DeleteSeriesButton extends AButton
{
    /**
     * Unique ID of the Series linked to this button
     */
    public String SeriesRef;

    /**
     * Constructor of a DeleteSeriesButton
     * @param pActivity the UpdateProgramActivity containing this button
     * @param pSeriesRef Unique ID of the Series linked to this button
     */
    public DeleteSeriesButton(UpdateExerciseActivity pActivity, String pSeriesRef)
    {
        super(pActivity);
        SeriesRef = pSeriesRef;
        setText("-");
    }

    /**
     * Set the LayoutParams of the button
     */
    @Override
    protected void setLayoutParams()
    {
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(100, 100);
        lParams.topMargin = 25;
        this.setLayoutParams(lParams);
    }

    /**
     * call when the button is clicked
     */
    @Override
    protected void onClick()
    {
        Context lContext = getContext();

        if (lContext != null && lContext.getClass() == UpdateExerciseActivity.class)
        {
            ((UpdateExerciseActivity) lContext).onRemoveSeriesClick(SeriesRef);
        }
    }
}
