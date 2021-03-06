import student.TestCase;

/**
 * @author nct217
 * @author rmb221
 * @author bal221
 * @version 2018.04.25
 */
public class PointTest extends TestCase {
    private Point p1, p2, p3, p4;
    private String s;
    
    public void setUp() {
        p1 = new Point(2, 4);
        p2 = new Point(2, 3);
        p3 = new Point(3, 4);
        p4 = new Point(3, 3);
        s  = "This is a non-point object";

    }
    
    /**
     * test x coord getters and setters
     */
    public void testGetSetX() {  
        assertEquals(p1.getX(), 2);
        p1.setX(4);
        assertEquals(p1.getX(), 4);      
    }
    
    /**
     * test y coord getters and setters
     */
    public void testGetSetY() {
        assertEquals(p1.getY(), 4);
        p1.setY(8);
        assertEquals(p1.getY(), 8);       
    }
    
    /**
     * test equals()
     */
    public void testEquals() {
        assertTrue(p1.equals(p1));
        assertFalse(p1.equals(p2));
        assertFalse(p1.equals(p3));
        assertFalse(p1.equals(p4));
        assertFalse(p1.equals(s));
    }
    
    /**
     * test the tostring method
     */
    public void testToString() {
        assertEquals(p1.toString(), "(2,4)");
        assertEquals(p2.toString(), "(2,3)");
        p1.setX(0);
        p1.setY(0);
        assertEquals(p1.toString(), "(0,0)");
    }
}
