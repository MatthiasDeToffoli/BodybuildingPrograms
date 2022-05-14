package fr.matthiasdetoffoli.bodybuildingprograms.LinearLayout;

import android.content.Context;

/**
 * Created by Matthias de Toffoli on 22/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see ALinearLayout Horizontal
 */
public abstract class AHorizontalLayout extends ALinearLayout
{
    /**
     * Constructor of an AHorizontalLayout
     * @param pContext context of creation
     */
    public AHorizontalLayout(Context pContext)
    {
        super(pContext, HORIZONTAL);
    }
}
