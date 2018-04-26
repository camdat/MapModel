import java.util.LinkedList;

/**
 * @author nct217
 * @version 2018.04.23
 */
public class Quad {
    
    private Point topLeft, botRight;
    private Node<Point> info;
    private Quad topLeftTree, topRightTree, 
            botLeftTree, botRightTree;

    /**
     * @param inTopLeft construct the QuadTree using this as top left point
     * @param inBotRight construct the QuadTree using this as top right point
     * 
     * Creates the QuadTree based on given coords
     * Big O: 1
     */
    public Quad(Point inTopLeft, Point inBotRight) {
        this.topLeft = inTopLeft;
        this.botRight = inBotRight;
    }
    
    /**
     * @param x x value to insert description at
     * @param y y value to insert description at
     * @param description the description to be inserted
     * 
     * Inserts a description into a node at the given (x, y) position
     * Big O: 1
     */
    public void insert(int x, int y, String description) {
        Point newPoint = new Point(x, y);
        LinkedList<String> newList = new LinkedList<String>();
        newList.add(description);
        
        Node<Point> newNode = new Node<Point>(newPoint, newList);
        insert(newNode);
    }
    
    /**
     * @param newNode node to be inserted
     * 
     * Internal function to insert new node after it's been created
     * Big O: n log n
     */
    private void insert(Node<Point> newNode) {
        // base case: null values
        if (newNode == null) {
            return;
        }
        // base case: outside of quads
        if (!isInside(newNode.getpData())) {
            return;
        }
        // base case: when size is 1, insert at location
        if ((topLeft.getX() == botRight.getX()) 
                && (topLeft.getY() == botRight.getY())) {
            if (this.info == null) {
                this.info = newNode;
            } else {
                this.info.getpList().addAll(newNode.getpList());
            }
            return;
        }
        
        // recursive function
        // top left tree
        if (((topLeft.getX() + botRight.getX()) / 2 >= newNode.getpData().getX())
                && ((topLeft.getY() + botRight.getY()) / 2 >= newNode.getpData().getY())) {
            
            if (topLeftTree == null) {
                // define a new botRight coord that changes the bounds to the top left
                Point newBotRight = new Point((topLeft.getX() + botRight.getX()) / 2, 
                        (topLeft.getY() + botRight.getY()) / 2);
                // set that bounds as the new quad
                topLeftTree = new Quad(topLeft, newBotRight);
            }
            // recurse
            topLeftTree.insert(newNode);
        }
        // bot left tree
        else if ((topLeft.getX() + botRight.getX()) / 2 < newNode.getpData().getX()
                && ((topLeft.getY() + botRight.getY()) / 2 < newNode.getpData().getY())) {
            
            if (botLeftTree == null) {
                // define a new topLeft and botRight coord that changes
                // the bounds to the bot left
                Point newTopLeft = new Point(topLeft.getX(),
                        (topLeft.getY() + botRight.getY()) / 2);
                Point newBotRight = new Point((topLeft.getX() + botRight.getX()) / 2, 
                        botRight.getY());
                // set that bound as the new quad
                botLeftTree = new Quad(newTopLeft, newBotRight);
            }
            // recurse
            botLeftTree.insert(newNode);
        }
        // top right tree
        else if ((topLeft.getX() + botRight.getX()) / 2 < newNode.getpData().getX()
                && ((topLeft.getY() + botRight.getY()) / 2 >= newNode.getpData().getY())) {
            
            if (topRightTree == null) {
                // define a new topLeft and botRight coord that changes
                // the bounds to the top right
                Point newTopLeft = new Point((topLeft.getX() + botRight.getX()) / 2,
                        topLeft.getY());
                Point newBotRight = new Point(botRight.getX(),
                        (topLeft.getY() + botRight.getY()) / 2);
                // set that bound as the new quad
                topRightTree = new Quad(newTopLeft, newBotRight);
            }
            // recurse
            topRightTree.insert(newNode);
        }
        // bot right tree
        else if ((topLeft.getX() + botRight.getX()) / 2 < newNode.getpData().getX()
                && ((topLeft.getY() + botRight.getY()) / 2 < newNode.getpData().getY())) {
            
            if (botRightTree == null) {
                // define a new topLeft that changes the bounds to the bot right
                Point newTopLeft = new Point((topLeft.getX() + botRight.getX()) / 2,
                        (topLeft.getY() + botRight.getY()) / 2);
                // set that bound as new quad
                botRightTree = new Quad(newTopLeft, botRight);
            }
            // recurse
            botRightTree.insert(newNode);
        }
    }
    
    /**
     * @param p point which will be tested
     * @return true/false based on point coords
     * 
     * Tests whether a point is inside the current Quad
     * Big O: 1
     */
    public boolean isInside(Point p) {
        return (p.getX() < this.topLeft.getX()) 
                || (p.getX() > this.botRight.getX()) 
                || (p.getY() > this.topLeft.getY()) 
                || (p.getY() < this.botRight.getY());
    }
}
