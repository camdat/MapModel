import java.util.LinkedList;

public class Node<T> {
    private T pData;
    private LinkedList<String> pList;

    public Node(T point, LinkedList<String> list) {
        this.setpData(point);
        this.setpList(list);
        
    }

    public T getpData() {
        return pData;
    }

    public void setpData(T pData) {
        this.pData = pData;
    }

    public LinkedList<String> getpList() {
        return pList;
    }

    public void setpList(LinkedList<String> pList) {
        this.pList = pList;
    }
    
    public String toString() {
        return pList.toString();
    }
}
