package fr.matthiasdetoffoli.bodybuildingprograms.Datas;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import fr.matthiasdetoffoli.bodybuildingprograms.Utils.Constants;

/**
 * Created by Matthias de Toffoli on 25/01/2022.
 * http://matthiasdetoffoli.fr/.
 *
 * Singleton containing all data elements of the application, it also save and load them in an xml file
 */
public class Data
{
    //region Members
    /**
     * Unique instance of the singleton
     */
    private static Data mInstance;

    /**
     * Physical save file
     */
    private File mSaveFile;
    //endregion Members

    //region Properties
    /**
     * Array containing all programs
     */
    public ArrayList<Program> programs;
    //endregion Properties

    //region Constructors
    /**
     * Default constructor
     */
    public Data()
    {
        programs = new ArrayList<>();
    }
    //endregion Constructors

    //region Methods
    /**
     * Get the Unique instance of the singleton
     * @return Unique instance of the singleton
     */
    public static Data getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new Data();
        }
        return mInstance;
    }

    //region Load
    /**
     * load the xml file and file all data
     * @param pDirFile directory containing the save file
     */
    public void load(File pDirFile)
    {
        DocumentBuilderFactory lDBF = DocumentBuilderFactory.newInstance();
        try
        {
            mSaveFile = new File(pDirFile, Constants.SAVE_FILE);

            if (!mSaveFile.exists())
            {
                mSaveFile.createNewFile();
            } else
            {
                Document lDB = lDBF.newDocumentBuilder().parse(mSaveFile);
                loadPrograms(lDB.getElementsByTagName(Constants.PROGRAM));
            }

        } catch (ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace();

        }
    }

    /**
     * Load all programs
     * @param pNodes NodeList containing all programs in the xml file
     */
    private void loadPrograms(NodeList pNodes)
    {
        Program lProgram;
        Element lElm;

        //clear programs array for avoiding to add the programs many times
        programs.clear();

        for (int i = 0, l = pNodes.getLength(); i < l; i++)
        {
            lElm = (Element) pNodes.item(i);
            lProgram = new Program(lElm.getAttribute(Constants.ID), lElm.getAttribute(Constants.NAME));
            lProgram.days = loadDays(lElm.getElementsByTagName(Constants.DAY));
            programs.add(lProgram);
        }
    }

    /**
     * Load all days of a program
     * @param pNodes NodeList get from a Program's Node
     * @return Array of Days
     */
    private ArrayList<Day> loadDays(NodeList pNodes)
    {
        ArrayList<Day> lDays = new ArrayList<>();
        Day lDay;
        Element lElm;

        for (int i = 0, l = pNodes.getLength(); i < l; i++)
        {
            lElm = (Element) pNodes.item(i);
            lDay = new Day(Integer.parseInt(lElm.getAttribute(Constants.NUMBER)));
            lDay.exercises = loadExercises(lElm.getElementsByTagName(Constants.EXERCISE));
            lDays.add(lDay);
        }

        return lDays;
    }

    /**
     * Load all exercises of a day
     * @param pNodes NodeList get from a Day's Node
     * @return array of Exercises
     */
    private ArrayList<Exercise> loadExercises(NodeList pNodes)
    {
        ArrayList<Exercise> lExs = new ArrayList<>();
        Exercise lEx;
        Element lElm;

        for (int i = 0, l = pNodes.getLength(); i < l; i++)
        {
            lElm = (Element) pNodes.item(i);
            lEx = new Exercise(lElm.getAttribute(Constants.ID), lElm.getAttribute(Constants.NAME));
            lEx.Series = loadSeries(lElm.getElementsByTagName(Constants.Series));
            lExs.add(lEx);
        }

        return lExs;
    }

    /**
     * Load all Series of an exercise
     * @param pNodes NodeList get from an Exercise's Node
     * @return array of Series
     */
    private ArrayList<Series> loadSeries(NodeList pNodes)
    {
        ArrayList<Series> lSeriesArray = new ArrayList<>();
        Series lSeries;
        Element lElm;

        for (int i = 0, l = pNodes.getLength(); i < l; i++)
        {
            lElm = (Element) pNodes.item(i);
            lSeries = new Series(Integer.parseInt(lElm.getAttribute(Constants.REP)), Float.parseFloat(lElm.getAttribute(Constants.WEIGHT)), Float.parseFloat(lElm.getAttribute(Constants.REST)));
            lSeriesArray.add(lSeries);
        }

        return lSeriesArray;
    }
    //endregion Load

    //region Save
    /**
     * Save data in an XML File
     */
    public void save()
    {
        if (mSaveFile == null)
        {
            return;
        }
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try
        {
            Document lDoc = docFactory.newDocumentBuilder().newDocument();
            Element lProgramsRootElements = lDoc.createElement(Constants.PROGRAMS);
            Element lProgramRootElement;
            Element lDayRootElement;
            Element lExerciseRootElement;
            Element lSeriesRootElement;

            for (Program lProgram : programs)
            {
                lProgramRootElement = lDoc.createElement(Constants.PROGRAM);
                lProgramRootElement.setAttribute(Constants.ID, lProgram.id);
                lProgramRootElement.setAttribute(Constants.NAME, lProgram.name);

                for (Day lDay : lProgram.days)
                {
                    lDayRootElement = lDoc.createElement(Constants.DAY);
                    lDayRootElement.setAttribute(Constants.NUMBER, String.valueOf(lDay.number));

                    for (Exercise lExercise : lDay.exercises)
                    {
                        lExerciseRootElement = lDoc.createElement(Constants.EXERCISE);
                        lExerciseRootElement.setAttribute(Constants.ID, lExercise.id);
                        lExerciseRootElement.setAttribute(Constants.NAME, lExercise.name);

                        for (Series lSeries : lExercise.Series)
                        {
                            lSeriesRootElement = lDoc.createElement(Constants.Series);
                            lSeriesRootElement.setAttribute(Constants.REP, String.valueOf(lSeries.repetition));
                            lSeriesRootElement.setAttribute(Constants.WEIGHT, String.valueOf(lSeries.weight));
                            lSeriesRootElement.setAttribute(Constants.REST, String.valueOf(lSeries.restTime));
                            lExerciseRootElement.appendChild(lSeriesRootElement);
                        }
                        lDayRootElement.appendChild(lExerciseRootElement);
                    }
                    lProgramRootElement.appendChild(lDayRootElement);
                }
                lProgramsRootElements.appendChild(lProgramRootElement);
            }
            lDoc.appendChild(lProgramsRootElements);

            try (FileOutputStream output =
                         new FileOutputStream(mSaveFile.getPath()))
            {
                writeXml(lDoc, output);
            }
        } catch (ParserConfigurationException | IOException | TransformerException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Write an xml file with data
     * @param pDocument text of the xml contained all data
     * @param pOutput file to write in
     * @throws TransformerException error of writing
     */
    private void writeXml(Document pDocument, OutputStream pOutput)
            throws TransformerException
    {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(pDocument);
        StreamResult result = new StreamResult(pOutput);

        transformer.transform(source, result);
    }
    //endregion Save

    //region Program
    /**
     * Create a new program
     * @return the new program
     */
    public Program createNewProgram()
    {
        Program lProgram = new Program("New program " + (programs.size() + 1));
        programs.add(lProgram);
        save();
        return lProgram;
    }

    /**
     * Get a program with its ID
     * @param pRef ID of the program
     * @return the program if we find it, null otherwise
     */
    public Program getProgram(String pRef)
    {
        Program lToReturn = null;
        for (Program lProgram : programs)
        {
            if (lProgram.id.equals(pRef))
            {
                lToReturn = lProgram;
                break;
            }
        }
        return lToReturn;
    }

    /**
     * Update a program
     * @param pNewProgram new value of the program
     */
    public void updateProgram(Program pNewProgram)
    {
        Program lOldProgram = getProgram(pNewProgram.id);
        if (lOldProgram != null)
        {
            programs.set(programs.indexOf(lOldProgram), pNewProgram);
        }
        save();
    }

    /**
     * Delete a program
     * @param pProgramRef ID of the program to delete
     */
    public void deleteProgram(String pProgramRef)
    {
        Program lProgram = getProgram(pProgramRef);

        if(lProgram != null)
        {
            programs.remove(lProgram);
        }
    }
    //endregion Program

    //region Day
    /**
     * Get a Day
     * @param pProgramRef ID of the program containing the day
     * @param pDayNum number of the day to get
     * @return the day if we find it, null otherwise
     */
    private Day getDay(String pProgramRef, int pDayNum)
    {
        Program lProgram = getProgram(pProgramRef);

        if (lProgram != null)
        {
            return lProgram.getDay(pDayNum);
        }

        return null;
    }

    /**
     * Update a day
     * @param pProgramRef ID of the program containing the day
     * @param pDay new value of the Day
     */
    public void updateDay(String pProgramRef, Day pDay)
    {
        Program lProgram = getProgram(pProgramRef);
        Day lOldDay = getDay(pProgramRef, pDay.number);
        if (lProgram != null && lOldDay != null)
        {
            lProgram.days.set(lProgram.days.indexOf(lOldDay), pDay);
        }

        if(lProgram != null)
        {
            updateProgram(lProgram);
        }
    }
    //endregion Day

    //region Exercise
    /**
     * Create a new Exercise
     * @param pProgram the program which contain the exercise
     * @param pDayNum number of the day containing the exercise
     * @return the new exercise
     */
    public Exercise createNewExercise(Program pProgram, int pDayNum)
    {
        Exercise lExercise = pProgram.CreateNewExercise(pDayNum);
        save();
        return lExercise;
    }

    /**
     * Get an Exercise
     * @param pProgramRef ID of the program containing the exercise
     * @param pDayNum number of the day containing the exercise
     * @param pExerciseRef ID of the the Exercise
     * @return the exercise if we find it, null otherwise
     */
    public Exercise getExercise(String pProgramRef, int pDayNum, String pExerciseRef)
    {
        Day lDay = getDay(pProgramRef, pDayNum);
        if (lDay != null)
        {
            return lDay.getExercise(pExerciseRef);
        }
        return null;
    }

    /**
     * Update an Exercise
     * @param pProgramRef ID of the program containing the exercise
     * @param pDayNum number of the day containing the exercise
     * @param pExercise new value of the Exercise
     */
    public void updateExercise(String pProgramRef, int pDayNum, Exercise pExercise)
    {
        Day lDay = getDay(pProgramRef, pDayNum);
        Exercise lOldExercise = getExercise(pProgramRef, pDayNum, pExercise.id);
        if (lDay != null && lOldExercise != null)
        {
            lDay.exercises.set(lDay.exercises.indexOf(lOldExercise), pExercise);
        }

        if(lDay != null)
        {
            updateDay(pProgramRef, lDay);
        }
    }

    /**
     * Delete an Exercise
     * @param pProgramRef ID of the program containing the exercise
     * @param pDayNum number of the day containing the exercise
     * @param pExerciseRef ID of the the Exercise
     */
    public void deleteExercise(String pProgramRef, int pDayNum, String pExerciseRef)
    {
        Day lDay = getDay(pProgramRef, pDayNum);
        if(lDay != null)
        {
            Exercise lExercise = lDay.getExercise(pExerciseRef);

            if(lExercise != null)
            {
                lDay.exercises.remove(lExercise);
            }

            updateDay(pProgramRef, lDay);
        }

    }
    //endregion Exercise
    //endregion Methods
}
