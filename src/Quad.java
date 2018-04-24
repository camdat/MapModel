import java.util.LinkedList;

public class Quad {
    private Point topLeft, botRight;
    private Node<Point> info;
    private Quad topLeftTree, topRightTree, 
            botLeftTree, botRightTree;

    public Quad(Point inTopLeft, Point inBotRight) {
        this.topLeft = inTopLeft;
        this.botRight = inBotRight;
    }
    
    public void insert(int x, int y, String description) {
        Point newPoint = new Point(x, y);
        LinkedList<String> newList = new LinkedList();
        newList.add(description);
        
        Node<Point> newNode = new Node<Point>(newPoint, newList);
        insert(newNode);
    }
    
    private void insert(Node<Point> newNode) {
        // base case null
        if (newNode == null) {
            return;
        }
        // base case if outside
        if (!isInside(newNode.getpData())) {
            return;
        }
        // base case if size is 1;
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
                Point newBotRight = new Point((topLeft.getX() + botRight.getX()) / 2, 
                        (topLeft.getY() + botRight.getY()) / 2);
                topLeftTree = new Quad(topLeft, newBotRight);
            }
            topLeftTree.insert(newNode);
        }
        // bot left tree
        else if ((topLeft.getX() + botRight.getX()) / 2 < newNode.getpData().getX()
                && ((topLeft.getY() + botRight.getY()) / 2 < newNode.getpData().getY())) {
            if (botLeftTree == null) {
                Point newTopLeft = new Point(topLeft.getX(),
                        (topLeft.getY() + botRight.getY()) / 2);
                Point newBotRight = new Point((topLeft.getX() + botRight.getX()) / 2, 
                        botRight.getY());
                botLeftTree = new Quad(newTopLeft, newBotRight);
            }
            botLeftTree.insert(newNode);
        }
        // top right tree
        else if ((topLeft.getX() + botRight.getX()) / 2 < newNode.getpData().getX()
                && ((topLeft.getY() + botRight.getY()) / 2 >= newNode.getpData().getY())) {
            if (topRightTree == null) {
                Point newTopLeft = new Point((topLeft.getX() + botRight.getX()) / 2,
                        topLeft.getY());
                Point newBotRight = new Point(botRight.getX(),
                        (topLeft.getY() + botRight.getY()) / 2);
                topRightTree = new Quad(newTopLeft, newBotRight);
            }
            topRightTree.insert(newNode);
        }
        // bot right tree
        else if ((topLeft.getX() + botRight.getX()) / 2 < newNode.getpData().getX()
                && ((topLeft.getY() + botRight.getY()) / 2 < newNode.getpData().getY())) {
            if (botRightTree == null) {
                Point newTopLeft = new Point((topLeft.getX() + botRight.getX()) / 2,
                        (topLeft.getY() + botRight.getY()) / 2);
                botRightTree = new Quad(newTopLeft, botRight);
            }
            botRightTree.insert(newNode);
        }
    }
    
    public boolean isInside(Point p) {
        return (p.getX() < this.topLeft.getX()) 
                || (p.getX() > this.botRight.getX()) 
                || (p.getY() > this.topLeft.getY()) 
                || (p.getY() < this.botRight.getY());
    }
}
