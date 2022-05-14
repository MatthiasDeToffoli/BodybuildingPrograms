package fr.matthiasdetoffoli.bodybuildingprograms.Datas;

import java.util.UUID;

/**
 * Created by Matthias de Toffoli on 16/02/2022.
 * http://matthiasdetoffoli.fr/.
 * <p>
 * Item which will be saved with a name and an Unique ID
 */
public abstract class ANamedDataItem
{
    /**
     * Unique ID of the item
     */
    public String id;

    /**
     * Name of the item
     */
    public String name;

    /**
     * Constructor of an ANamedDataItem
     * @param pName name of the Item
     */
    public ANamedDataItem(String pName)
    {
        id = UUID.randomUUID().toString();
        name = pName;
    }

    /**
     * Constructor of an ANamedDataItem
     * @param pId ID of the item
     * @param pName name of the Item
     */
    public ANamedDataItem(String pId, String pName)
    {
        id = pId;
        name = pName;
    }
}
