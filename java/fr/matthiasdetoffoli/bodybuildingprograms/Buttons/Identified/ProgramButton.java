package fr.matthiasdetoffoli.bodybuildingprograms.Buttons.Identified;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.widget.GridLayout;

import fr.matthiasdetoffoli.bodybuildingprograms.Activities.MainActivity;

/**
 * Created by Matthias de Toffoli on 24/01/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see AIdentifiedButton which used for editing a program
 */
@SuppressLint("ViewConstructor")
public class ProgramButton extends AIdentifiedButton
{
    /**
     * Constructor of an ProgramButton
     *
     * @param pActivity the Main activity which will show the button
     * @param pUID      Unique ID of the button
     * @param pText     text of the button
     */
    public ProgramButton(MainActivity pActivity, String pUID, String pText)
    {
        super(pActivity, pUID, pText);
    }

    /**
     * Set the LayoutParams of the button
     */
    @Override
    protected void setLayoutParams()
    {
        GridLayout.LayoutParams lParams = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f));
        lParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
        lParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        lParams.setGravity(Gravity.FILL_HORIZONTAL);
        this.setLayoutParams(lParams);
    }

    /**
     * call when the button is clicked
     */
    @Override
    protected void onClick()
    {
        Context lContext = getContext();

        if (lContext != null && lContext.getClass() == MainActivity.class)
        {
            ((MainActivity) lContext).goToUpdateProgramActivity(uID);
        }
    }
}
