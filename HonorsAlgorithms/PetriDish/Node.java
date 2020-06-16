public class Node {
    private int x; //height
    private int y; //width
    private boolean visited;
    private char letter;
    Node(int x,int y){
        this.x = x;
        this.y = y;
        visited = false;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public boolean isVisited(){
        return visited;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }
}
