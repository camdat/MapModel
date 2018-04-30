import student.TestCase;

/**
 * @author nct217
 * @author rmb221
 * @author bal221
 * @version 2018.04.25
 */
public class QuadTest extends TestCase {
    private Quad quadTree;

    private Point p1, p2;
    
    public void setUp() {
        p1 = new Point(0, 0);
        p2 = new Point(4, 4);
        
        quadTree = new Quad(p1, p2);
        
    }

    /**
     *  Tests the isInside() method
     */
    public void testIsInside() {
        // points that exist
        assertTrue(quadTree.isInside(new Point(2, 2)));
        assertTrue(quadTree.isInside(new Point(1, 3)));

        // points that can't exist (null pointer test)
        assertFalse(quadTree.isInside(new Point(-1, 4)));
        assertFalse(quadTree.isInside(new Point(4, -1)));

        // points that are outside the bounds (edge cases)
        assertFalse(quadTree.isInside(new Point(0, 0)));
        assertFalse(quadTree.isInside(new Point(5, 5)));

    }
    
    /**
     * Tests the insert() method
     */
    public void testInsert() {     
        // top left tree test
        quadTree.insert(1, 1, "School");
        assertEquals(quadTree.search(1, 1).toString(), "[School]");
        
        // bot left tree test
        quadTree.insert(1, 4, "Hospital");
        assertEquals(quadTree.search(1, 4).toString(), "[Hospital]");
        
        // top right tree test
        quadTree.insert(4, 1, "Church");
        assertEquals(quadTree.search(4, 1).toString(), "[Church]");
        
        // bot right tree test
        quadTree.insert(4, 4, "Restaurant");
        assertEquals(quadTree.search(4, 4).toString(), "[Restaurant]");
        
        // test search with null conditions
        assertNull(quadTree.search(2, 2));
        assertNull(quadTree.search(-1, -1));

                
    }
    
    /**
     * tests the search() method with a string
     */
    public void testStringSearch() {
        // top left tree test
        quadTree.insert(1, 1, "School");
        quadTree.insert(2, 2, "School");
        quadTree.insert(2, 2, "Church");
        quadTree.insert(1, 4, "Hospital");
        quadTree.insert(4, 1, "Church");
        quadTree.insert(4, 4, "Restaurant");
        
        assertEquals(quadTree.search("School").get(0).getpData().toString(), "(1,1)");
        assertEquals(quadTree.search("School").get(1).getpData().toString(), "(2,2)");
        
        assertEquals(quadTree.search("School").get(0).toString(), "[School]");
        assertEquals(quadTree.search("School").get(1).toString(), "[School, Church]");
        
        // test string search with nonexistent value
        assertEquals(quadTree.search("Movie Theatre").size(), 0);


    }
}
