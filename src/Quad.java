import java.util.LinkedList;

/**
 * @author nct217
 * @author rmb221
 * @author bal221
 * @version 2018.04.23
 * 
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
     * 
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
     * 
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
     * 
     * Big O: n log n
     */
    private void insert(Node<Point> newNode) {
        System.out.println("newInsert");
        // base case: null values
        if (newNode == null) {
            return;
        }
        // base case: outside of quads
        if (!isInside(newNode.getpData())) {
            return;
        }
        // base case: when size is 1, insert at location
        if (Math.abs(botRight.getX() - topLeft.getX()) == 1
                && (Math.abs(botRight.getY() - topLeft.getY()) == 1)) {
            if (this.info == null) {
                this.info = newNode;
                System.out.println("inserted at "+this.topLeft.getX()+this.topLeft.getY()+":"+this.botRight.getX()+this.botRight.getY());

            } else {
                this.info.getpList().addAll(newNode.getpList());
                System.out.println("inserted at "+this.info.getpData().getX());

            }
            return;
        }
        
        // recursive function
        // top left tree
        if ((((topLeft.getX() + botRight.getX()) / 2) >= newNode.getpData().getX())
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
        else if ((topLeft.getX() + botRight.getX()) / 2 >= newNode.getpData().getX()
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
    }
    
    /**
     * @param x x value to search in
     * @param y y value to search in
     * @return return the Node at that location, if it exists
     * 
     * wrapper for search(Point p) which creates point from coords
     * 
     * Big O: 1
     */
    public Node<Point> search(int x, int y) {
        Point newPoint = new Point(x, y);
        return search(newPoint);
    }
    
    /**
     * @param p the point to search for
     * @return the Node at that locaiton, if it exists
     * 
     * searches for a value in the quadTree
     * 
     * Big O: n log n
     */
    public Node<Point> search(Point p) {
        
        // base case: outside of quads
        if (!isInside(p)) {
            System.out.println("triggered isinside");
            return null;
        }
        // base case: found the location
        if (this.info != null) {
            return this.info;
        }
        
        // recursive function
        // top left tree
        if (((topLeft.getX() + botRight.getX()) / 2 >= p.getX())
                && ((topLeft.getY() + botRight.getY()) / 2 >= p.getY())) {
            if (topLeftTree == null) {
                return null;
            } else {
                return topLeftTree.search(p);
            }
        }
        // bot right tree
        else if ((topLeft.getX() + botRight.getX()) / 2 < p.getX()
                && ((topLeft.getY() + botRight.getY()) / 2 < p.getY())) {
            if (botRightTree == null) {
                return null;
            } else {
                return botRightTree.search(p);
            }
        }
        // top right tree
        else if ((topLeft.getX() + botRight.getX()) / 2 < p.getX()
                && ((topLeft.getY() + botRight.getY()) / 2 >= p.getY())) {
            if (topRightTree == null) {
                return null;
            } else {
                return topRightTree.search(p);
            }
        }
        // bot left tree
        else if ((topLeft.getX() + botRight.getX()) / 2 >= p.getX()
                && ((topLeft.getY() + botRight.getY()) / 2 < p.getY())) {
            if (botLeftTree == null) {
                return null;
            } else {
                return botLeftTree.search(p);
            }
        }
        return null;
    }
    
    /**
     * @param type_of_place the description of the place to be found
     * @return the points which contain the description
     * 
     * wrapper for search(String, LinkedList)
     * 
     * Big O: 1
     */
    public LinkedList<Node<Point>> search(String type_of_place) {
        LinkedList<Node<Point>> newList = new LinkedList<Node<Point>>();
        
        return search(type_of_place, newList);
    }
    
    /**
     * @param type_of_place the description of the place to be found
     * @param l1 the list of places currently found
     * @return return the list of places which contain the description
     * 
     * searches for a node with the given description
     * 
     * Big O: n
     */
    public LinkedList<Node<Point>> search(String type_of_place, LinkedList<Node<Point>> l1) {
        if (info != null) {
            if (info.getpList().contains(type_of_place) && !(l1.contains(info))) {
                l1.add(info);
                return l1;
            }
        }
        if (topLeftTree != null) {
            l1 = topLeftTree.search(type_of_place, l1);

        }
        if (botLeftTree != null) {
            l1 = botLeftTree.search(type_of_place, l1);
            
        }
        if (topRightTree != null) {
            l1 = (topRightTree.search(type_of_place, l1));
            
        }
        if (botRightTree != null) {
            l1 = (botRightTree.search(type_of_place, l1));
            
        }
        return l1;
    }
    
    
    /**
     * @param p point which will be tested
     * @return true/false based on point coords
     * 
     * Tests whether a point is inside the current Quad
     * Big O: 1
     */
    public boolean isInside(Point p) {
        return 
                (p.getX() > this.topLeft.getX()) 
                && (p.getX() <= this.botRight.getX()) 
                && (p.getY() > this.topLeft.getY()) 
                && (p.getY() <= this.botRight.getY());
    }
}
