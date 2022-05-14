package fr.matthiasdetoffoli.bodybuildingprograms.Datas;

import java.util.ArrayList;

/**
 * Created by Matthias de Toffoli on 25/01/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * @see ANamedDataItem representing a Program
 */
public class Program extends ANamedDataItem
{
    //region Properties
    /**
     * Array of Days of the Program
     */
    public ArrayList<Day> days;
    //endregion Properties

    //region Constructors
    /**
     * Constructor of a Program
     * @param pName name of the program
     */
    public Program(String pName)
    {
        super(pName);
        days = new ArrayList<>();
        days.add(new Day(1));
    }

    /**
     * Constructor of a Program
     * @param pId ID of the program
     * @param pName name of the program
     */
    public Program(String pId, String pName)
    {
        super(pId, pName);
        days = new ArrayList<>();
    }
    //endregion Constructors

    //region Methods
    /**
     * get a day
     * @param pNum number of the day
     * @return the day found or null
     */
    public Day getDay(int pNum)
    {
        Day lToReturn = null;
        for (Day lDay : days)
        {
            if (lDay.number == pNum)
            {
                lToReturn = lDay;
                break;
            }
        }
        return lToReturn;
    }

    /**
     * Create a new exercise
     * @param pDayNum number of the day containing the exercise
     * @return the exercise if we find it, null otherwise
     */
    public Exercise CreateNewExercise(int pDayNum)
    {
        Day lDay = getDay(pDayNum);

        if (lDay != null)
        {
            return lDay.CreateNewExercise();
        }
        return null;
    }
    //endregion Methods
}
