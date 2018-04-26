import java.util.LinkedList;

/**
 * @author nct217   
 * @version 2018.04.23
 * @param <T> usually a Point, generic object
 */
public class Node<T> {
    private T pData;
    private LinkedList<String> pList;

    /**
     * @param point Point object to be used as coords
     * @param list a LinkedList of descriptions
     * 
     * Creates a node based on the point coords and list
     * Big O: 1
     */
    public Node(T point, LinkedList<String> list) {
        this.setpData(point);
        this.setpList(list);
        
    }

    /**
     * @return point data
     * 
     * Returns point data
     * Big O: 1
     */
    public T getpData() {
        return pData;
    }

    /**
     * @param pData point to be set
     * 
     * Sets point based on pData
     * Big O: 1
     */
    public void setpData(T pData) {
        this.pData = pData;
    }

    /**
     * @return a list of descriptions for the present node
     * 
     * Returns description data
     * Big O: 1
     */
    public LinkedList<String> getpList() {
        return pList;
    }

    /**
     * @param pList the LinkedList the node should use
     * 
     * Sets the LinkedList based on pList
     * Big O: 1
     */
    public void setpList(LinkedList<String> pList) {
        this.pList = pList;
    }
    
    /**
     * @return toString representation of the description ("[Store, Church, Restaurant]")
     * 
     * Returns a toString representation of the description ("[Store, Church, Restaurant]")
     * Big O: 1
     */
    public String toString() {
        return pList.toString();
    }
}
