package fr.matthiasdetoffoli.bodybuildingprograms.Buttons.Toggles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.LinearLayout;

import fr.matthiasdetoffoli.bodybuildingprograms.Activities.Secondary.UpdateProgramActivity;

/**
 * Created by Matthias de Toffoli on 15/02/2022.
 * http://matthiasdetoffoli.fr/.
 * <p>
 * Button for select a day to update
 *
 * @see AToggleButton
 * @see fr.matthiasdetoffoli.bodybuildingprograms.Datas.Day
 */
@SuppressLint("ViewConstructor")
public class DayButton extends AToggleButton
{
    /**
     * number of the day
     */
    public int number;

    /**
     * Constructor of a DayButton
     *
     * @param pActivity  the UpdateProgramActivity containing this button
     * @param pNumber    number of the button
     * @param pIsChecked if the ToggleButton is checked or not
     */
    public DayButton(UpdateProgramActivity pActivity, int pNumber, boolean pIsChecked)
    {
        super(pActivity);
        number = pNumber;
        String lNumStr = String.valueOf(number);
        setText(lNumStr);
        setTextOff(lNumStr);
        setTextOn(lNumStr);
        setChecked(pIsChecked);
    }

    /**
     * Set the LayoutParams of the button
     */
    @Override
    protected void setLayoutParams()
    {
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParams.weight = 1;
        setLayoutParams(lParams);
    }

    /**
     * when the button is checked
     */
    @Override
    protected void onChecked()
    {
        Context lContext = getContext();

        if (lContext != null && lContext.getClass() == UpdateProgramActivity.class)
        {
            ((UpdateProgramActivity) lContext).onDayChecked(this);
        }
        setEnabled(false);

    }

    /**
     * when the button is unchecked
     */
    @Override
    protected void onUnchecked()
    {
        if (!isEnabled())
        {
            setEnabled(true);
        }
    }
}
