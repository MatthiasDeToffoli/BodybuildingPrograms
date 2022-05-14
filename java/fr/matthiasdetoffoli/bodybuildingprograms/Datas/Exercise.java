package fr.matthiasdetoffoli.bodybuildingprograms.Datas;

import java.util.ArrayList;

/**
 * Created by Matthias de Toffoli on 25/01/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see ANamedDataItem representing an Exercise
 */
public class Exercise extends ANamedDataItem
{
    //region Properties
    /**
     * Array of Series of the Exercise
     */
    public ArrayList<Series> Series;
    //endregion Properties

    //region Constructors

    /**
     * Constructor of an Exercise
     * @param pName name of the exercise
     */
    public Exercise(String pName)
    {
        super(pName);
        Series = new ArrayList<>();
    }

    /**
     * Constructor of an Exercise
     * @param pId ID of the exercise
     * @param pName name of the exercise
     */
    public Exercise(String pId, String pName)
    {
        super(pId, pName);
        Series = new ArrayList<>();
    }
    //endregion Constructors

    //region Methods
    /**
     * add a new Series
     * @param pRep number of repetitions
     * @param pWeight weight lifted for this Series
     * @param pRestTime rest time after the effort
     * @param pPos position of the Series in the array
     */
    public void addSeries(int pRep, float pWeight, float pRestTime, int pPos)
    {
        Series lNewSeries = new Series(pRep, pWeight, pRestTime);

        if (pPos >= Series.size())
        {
            Series.add(lNewSeries);
        } else
        {
            Series.set(pPos, lNewSeries);
        }
    }
    //endregion Methods
}
