package fr.matthiasdetoffoli.bodybuildingprograms.LinearLayout;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by Matthias de Toffoli on 22/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see LinearLayout with additional features
 */
public abstract class ALinearLayout extends LinearLayout
{
    //region Constructors

    /**
     * Constructor of an ALinearLayout
     * @param pContext context of creation
     * @param pOrientation Orientation of the Layout
     */
    public ALinearLayout(Context pContext, int pOrientation)
    {
        super(pContext);
        setLayoutParams();
        setOrientation(pOrientation);
    }
    //endregion Constructors

    //region Methods
    /**
     * Set the LayoutParams of the EditText
     */
    protected abstract void setLayoutParams();
    //endregion Methods
}
