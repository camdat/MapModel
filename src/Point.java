
/**
 * @author nct217
 * @author rmb221
 * @author bal221
 * @version 2018.04.23
 */
public class Point {
    private int x, y;
    
    /**
     * @param xCord x coordinate of new Point
     * @param yCord y coordinate of new Point
     * 
     * Creates a Point based on the coordinates (x, y)
     * Big O: 1
     */
    public Point(int xCord, int yCord) {
        setX(xCord);
        setY(yCord);     
    }
    
    /**
     * @return x value of Point
     * 
     * Big O: 1
     */
    public int getX() {
        return this.x; 
    }
    
    /**
     * @return y value of Point
     * 
     * Big O: 1
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * @param xCord new x coordinate
     * 
     * Sets x coordinate of Point
     * 
     * Big O: 1
     */
    public void setX(int xCord) {
        this.x = xCord;
    }
    
    /**
     * @param yCord new y coordinate
     * 
     * Sets y coordinate of Point
     * 
     * Big O: 1
     */
    public void setY(int yCord) {
        this.y = yCord;
    }
    
    /**
     * @param o object to test equality
     * 
     * Overridden equals function
     * 
     * Big O: 1
     */
    public boolean equals(Object o) {
        return (o instanceof Point) 
                && (this.x == ((Point) o).getX()) 
                && (this.y == ((Point) o).getY());
    }
    
    /**
     * Overridden toString function
     * 
     * Big O: 1
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
