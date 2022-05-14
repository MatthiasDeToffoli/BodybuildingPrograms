package fr.matthiasdetoffoli.bodybuildingprograms.EditText.Number.Decimal;

import android.content.Context;
import android.text.InputType;

import fr.matthiasdetoffoli.bodybuildingprograms.EditText.Number.ANumberEditText;

/**
 * Created by Matthias de Toffoli on 22/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see ANumberEditText with a decimal value
 */
public abstract class ADecimalNumberEditText extends ANumberEditText
{
    //region Constructors
    /**
     * Constructor of an ADecimalNumberEditText
     * @param pContext context of creation
     * @param pMaxCharacter max number of characters of the EditText
     * @param pValue Default value of the EditText
     */
    public ADecimalNumberEditText(Context pContext, int pMaxCharacter, float pValue)
    {
        super(pContext, InputType.TYPE_NUMBER_FLAG_DECIMAL, pMaxCharacter);
        setText(String.valueOf(pValue));
    }
    //endregion Constructors

    //region Methods

    /**
     * get the text in float value
     * @return the text in float value
     */
    public float getFloatValue()
    {
        return Float.parseFloat(getText().toString());
    }
}
