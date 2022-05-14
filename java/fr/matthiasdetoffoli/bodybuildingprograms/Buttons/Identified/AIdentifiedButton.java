package fr.matthiasdetoffoli.bodybuildingprograms.Buttons.Identified;

import android.content.Context;

import fr.matthiasdetoffoli.bodybuildingprograms.Buttons.AButton;

/**
 * Created by Matthias de Toffoli on 16/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see AButton with an ID
 */
public abstract class AIdentifiedButton extends AButton
{
    /**
     * Unique ID of the button
     */
    public String uID;

    /**
     * Constructor of an AIdentifiedButton
     * @param pContext context of the creation
     * @param pUID Unique ID of the Button
     * @param pText text of the button
     */
    public AIdentifiedButton(Context pContext, String pUID, String pText)
    {
        super(pContext);
        uID = pUID;
        setText(pText);
    }
}
