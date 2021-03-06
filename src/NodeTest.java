import java.util.LinkedList;

import student.TestCase;

/**
 * @author nct217
 * @author rmb221
 * @author bal221
 * @version 2018.04.26
 */
public class NodeTest extends TestCase {
    private Node<Point> n1;
    private Point p1, p2;
    private LinkedList<String> l1, l2;

    public void setUp() {
        l1 = new LinkedList<String>();
        
        l1.add("Chinese");
        l1.add("Groceries");
        l1.add("Church");
        
        l2 = new LinkedList<String>();

        l2.add("Mosque");
        l2.add("Italian");
        l2.add("Bookstore");

        p1 = new Point(2, 2);
        p2 = new Point(1, 1);

        n1 = new Node<Point>(p1, l1);
    }

    /**
     * test pData getters and setters
     */
    public void testGetSetpData() {
        assertEquals(n1.getpData(), p1);
        n1.setpData(p2);
        assertEquals(n1.getpData(), p2);
    }
    
    /**
     * test pList getters and setters
     */
    public void testGetSetpList() {
        assertEquals(n1.getpList(), l1);
        n1.setpList(l2);
        assertEquals(n1.getpList(), l2);
    }
    
    /**
     * test toString representations for elements
     */
    public void testToString() {
        assertEquals(n1.toString(), "[Chinese, Groceries, Church]");
        n1.setpList(l2);
        assertEquals(n1.toString(), "[Mosque, Italian, Bookstore]");
    }
}
