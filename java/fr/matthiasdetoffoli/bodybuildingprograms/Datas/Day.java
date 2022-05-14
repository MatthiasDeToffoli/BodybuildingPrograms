package fr.matthiasdetoffoli.bodybuildingprograms.Datas;

import java.util.ArrayList;

/**
 * Created by Matthias de Toffoli on 25/01/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * Item representing a Day
 */
public class Day
{
    //region Properties
    /**
     * Number of the day
     */
    public int number;

    /**
     * Array of exercises of the day
     */
    public ArrayList<Exercise> exercises;
    //endregion Properties

    //region Constructors
    /**
     * Constructor of a Day
     * @param pNumber number of the day
     */
    public Day(int pNumber)
    {
        number = pNumber;
        exercises = new ArrayList<>();
    }
    //endregion Constructors

    //region Methods
    /**
     * Create a new exercise
     * @return the new exercise
     */
    public Exercise CreateNewExercise()
    {
        Exercise lExercise = new Exercise("New exercise " + (exercises.size() + 1));
        exercises.add(lExercise);
        return lExercise;
    }

    /**
     * Get an exercise
     * @param pRef the exercise's ID
     * @return the exercise if we find it, null otherwise
     */
    public Exercise getExercise(String pRef)
    {
        Exercise lToReturn = null;
        for (Exercise lExercise : exercises)
        {
            if (lExercise.id.equals(pRef))
            {
                lToReturn = lExercise;
                break;
            }
        }
        return lToReturn;
    }
    //endregion Methods
}
