package fr.matthiasdetoffoli.bodybuildingprograms.EditText.Number;

import android.content.Context;
import android.text.InputType;
import android.view.View;

import fr.matthiasdetoffoli.bodybuildingprograms.EditText.AEditText;

/**
 * Created by Matthias de Toffoli on 22/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see AEditText with a number value
 */
public abstract class ANumberEditText extends AEditText
{
    //region Constructors

    /**
     * Constructor of an ANumberEditText
     * @param pContext context of creation
     * @param pMaxCharacter max number of characters of the EditText
     * @param pValue Default value of the EditText
     */
    public ANumberEditText(Context pContext, int pMaxCharacter, int pValue)
    {
        super(pContext, InputType.TYPE_CLASS_NUMBER, pMaxCharacter, View.TEXT_ALIGNMENT_CENTER);
        setText(String.valueOf(pValue));
    }
    //endregion Constructors

    //region Methods
    /**
     * get the text in integer value
     * @return the text in integer value
     */
    public int getIntValue()
    {
        return Integer.parseInt(getText().toString());
    }
    //endregion Methods
}
