package fr.matthiasdetoffoli.bodybuildingprograms.Datas;

/**
 * Created by Matthias de Toffoli on 25/01/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * Item representing a Series
 */
public class Series
{
    //region Properties
    /**
     * number of repetition of the Series
     */
    public int repetition;

    /**
     * weight lifted on this Series
     */
    public float weight;

    /**
     * rest time after the effort
     */
    public float restTime;
    //endregion Properties

    //region Constructors

    /**
     * Constructor of a Series
     * @param pRep number of repetition of the Series
     * @param pWeight weight lifted on this Series
     * @param pRestTime rest time after the effort
     */
    public Series(int pRep, float pWeight, float pRestTime)
    {
        repetition = pRep;
        weight = pWeight;
        restTime = pRestTime;
    }
}
