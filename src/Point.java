
public class Point {
    private int x, y;
    
    public Point(int xCord, int yCord) {
        setX(xCord);
        setY(yCord);     
    }
    
    public int getX() {
        return this.x; 
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setX(int xCord) {
        this.x = xCord;
    }
    
    public void setY(int yCord) {
        this.y = yCord;
    }
    
    public boolean equals(Object o) {
        return (o instanceof Point) 
                && (this.x == ((Point) o).getX()) 
                && (this.y == ((Point) o).getY());
    }
    
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
