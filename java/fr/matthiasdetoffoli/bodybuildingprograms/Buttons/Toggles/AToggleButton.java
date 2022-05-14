package fr.matthiasdetoffoli.bodybuildingprograms.Buttons.Toggles;

import android.content.Context;
import android.widget.ToggleButton;

import androidx.appcompat.widget.AppCompatToggleButton;

/**
 * Created by Matthias de Toffoli on 15/02/2022.
 * http://matthiasdetoffoli.fr/.
 * <p>
 * Parent of all toggle buttons
 *
 * @see ToggleButton
 */
public abstract class AToggleButton extends AppCompatToggleButton
{
    /**
     * Constructor of an AToggleButton
     *
     * @param pContext context of creation
     */
    public AToggleButton(Context pContext)
    {
        super(pContext);
        setLayoutParams();

        setOnCheckedChangeListener((pCompoundButton, pIsChecked) -> AToggleButton.this.onCheckedChanged(pIsChecked));
    }

    /**
     * Set the LayoutParams of the button
     */
    protected abstract void setLayoutParams();

    /**
     * when the checked state change
     * @param pIsChecked if it's checked or not
     */
    protected void onCheckedChanged(boolean pIsChecked)
    {
        if (pIsChecked)
        {
            onChecked();
        } else
        {
            onUnchecked();
        }
    }

    /**
     * when the button is checked
     */
    protected abstract void onChecked();

    /**
     * when the button is unchecked
     */
    protected abstract void onUnchecked();
}
