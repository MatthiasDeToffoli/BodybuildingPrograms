package fr.matthiasdetoffoli.bodybuildingprograms.EditText;

import android.content.Context;
import android.text.InputFilter;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * Created by Matthias de Toffoli on 22/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see EditText with additional features
 */
public abstract class AEditText extends AppCompatEditText
{
    //region Constructors
    /**
     * Constructor of an AEditText
     * @param pContext context of creation
     * @param pInputType type of text we set on the EditText
     * @param pMaxCharacter max number of characters of the EditText
     * @param pTextAlignment default text alignment
     */
    public AEditText(Context pContext, int pInputType, int pMaxCharacter, int pTextAlignment)
    {
        super(pContext);
        setInputType(pInputType);
        setTextAlignment(pTextAlignment);
        setEms(3);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(pMaxCharacter)});
        setLayoutParams();
    }
    //endregion Constructors

    //region Methods
    /**
     * Set the LayoutParams of the EditText
     */
    protected abstract void setLayoutParams();
    //endregion Methods
}
