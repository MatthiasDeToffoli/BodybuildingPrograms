package fr.matthiasdetoffoli.bodybuildingprograms.Buttons;

import android.content.Context;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;

/**
 * Created by Matthias de Toffoli on 15/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * Parent of all buttons
 *
 * @see Button
 */
public abstract class AButton extends AppCompatButton
{
    /**
     * Constructor of an AButton
     *
     * @param pContext Context of creation
     */
    public AButton(Context pContext)
    {
        super(pContext);
        setLayoutParams();

        setOnClickListener(pView -> AButton.this.onClick());
    }

    /**
     * Set the LayoutParams of the button
     */
    protected abstract void setLayoutParams();

    /**
     * call when the button is clicked
     */
    protected abstract void onClick();
}

