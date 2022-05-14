package fr.matthiasdetoffoli.bodybuildingprograms.LinearLayout;

import android.widget.LinearLayout;

import java.util.UUID;

import fr.matthiasdetoffoli.bodybuildingprograms.Activities.Secondary.UpdateExerciseActivity;

/**
 * Created by Matthias de Toffoli on 22/02/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see AHorizontalLayout representing a Series
 */
public class SeriesLayout extends AHorizontalLayout
{
    //region Properties
    /**
     * Unique ID of the SeriesLayout
     */
    public String id;
    //endregion Properties

    //region Constructors

    /**
     * Constructor of a SeriesLayout
     * @param pActivity UpdateExerciseActivity containing the Layout
     */
    public SeriesLayout(UpdateExerciseActivity pActivity)
    {
        super(pActivity);
        id = UUID.randomUUID().toString();
    }
    //endregion Constructors

    //region Methods
    /**
     * Set the LayoutParams of the EditText
     */
    @Override
    protected void setLayoutParams()
    {
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(lParams);
    }
    //endregion Methods
}
