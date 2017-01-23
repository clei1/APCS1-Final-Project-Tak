import java.util.ArrayList;

public class Board{
    
    ArrayList<Piece>[][] board; //the board is represented by a 2D array

    int size;        //size is the length and width of the board, they are equal because it is a square
    
    int[][] checked;

    
    //Default constructor
    //Input: Takes a player inputted number between 3 to 8, defining the size
    public Board(int s){
	board = new ArrayList[s][s]; //created board with player defined size
	for(int col = 0; col < s; col ++){ //iterates through the columns
	    for(int row = 0; row < s; row ++){ //for each column, create rows
		board[row][col] = new ArrayList<Piece>(); //sets each array to contain an ArrayList
	    }
	}
	size = s; //sets size to player defined size 
    }


    
    //Overwritten toString()
    //Prints the board, converts everything in the 2D Array into a string
    public String toString(){
	String s = "";
	for (ArrayList[] i : board) {
	    for (int j = 0; j < i.length; j++) {
		ArrayList list = i[j];
		if (list.size() == 0) {
		    s += "[EMPTY SPACE] ";
		}
		else {
		    s += "[" + list.get( list.size() - 1 ).toString() + "] ";
		}
	    }
	    s += "\n";
	}
	return s;
    }


    
    //prints out a stack at a tile, with the bottom piece on the bottom
    public void displayStack(int x, int y){
	System.out.println("TOP");
	for (int i = board[x][y].size()-1; i >= 0; i-- ) {
	    System.out.println(board[x][y].get(i));
	}
	System.out.println("BOTTOM");
    }


    
    //takes a color and checks if there are any stacks of that color
    //returns TRUE if there is a stack that controlled by that color
    public boolean hasStacks(int color){
	for(int col = 0; col < size; col ++){
	    for(int row = 0; row < size; row ++){
		if(stackOwner(col, row) == color){
		    //if tile is empty, it inputs -1
		    //if tile is controlled by black, it inputs 0
		    //if tile is controlled by white, it inputs 1
		    return true;
		}
	    }
	}
	return false;
    }


    
    //returns TRUE if there are any stacks/pieces on the board
    //returns FALSE if the board has no stacks/pieces on it
    public boolean hasStacks(){
	for(int col = 0; col < size; col ++){
	    for(int row = 0; row < size; row ++){
		if(isOccupied(col, row)){
		    //if tile is not empty, it inputs TRUE
		    //if tile is empty, it inputs FALSE
		    return true;
		}
	    }
	}
	return false;
    }


    
    //returns TRUE if the tile at position is empty
    //returns FALSE if the tile has any pieces on it
    public boolean isEmpty(int col, int row){
	return board[col][row].size() == 0;
    }


    
    //returns TRUE if the tile at position is not empty
    //returns FALSE if the tile is empty
    public boolean isOccupied(int col, int row){
	return (!isEmpty(col, row));
    }


    
    //returns -1 if tile doesn't have any pieces
    //returns 0 if the top stone of the stack is black
    //returns 1 if the top stone of the stack is white
    public int stackOwner(int x, int y){
	ArrayList<Piece> p = board[x][y];
        int lastPos = p.size()-1;
	if (p.isEmpty())
	    return  -1;
	else
	    return p.get(lastPos).getColor();
    }



    //returns TRUE if the inputted color owns the stack at tile
    //returns FALSE if the inputted color does not own the stack at tile
    public boolean isTopPieceColor(int col , int row, int color){
	return stackOwner(col, row) == color;
    }



    //returns TRUE if the top piece of a stack is a wall
    //returns FALSE if the top piece of a stack is not a wall
    public boolean isTopPieceWall(int col, int row){
	int lastPos = board[col][row].size() - 1;
	return board[col][row].get(lastPos).isWall();
    }



    //returns TRUE if the top piece of a stack is not a wall
    //returns FALSE if the top piece of a stack is a wall
    public boolean isTopPieceNotWall(int col, int row){
	return (!isTopPieceWall(col, row));
    }



    //returns TRUE if the top piece of a stack is a capstone
    //returns FALSE if the top piece of a stack is not a capstone
    public boolean isCapstone(int col, int row){
	int lastPos = board[col][row].size() - 1;
	if(board[col][row].get(lastPos).toString() == "BLACK CAPSTONE" ||
	   board[col][row].get(lastPos).toString() == "WHITE CAPSTONE"){
	    return true;
	}
	else{
	    return false;
	}
    }

    public String printChecked(){
	String temp = "[";
	for(int x = 0; x < size; x ++){
	    for(int y = 0; y < size; y ++){
		temp += checked[x][y] + ", ";
	    }
	    temp += "\n";
	}
	temp += "]";
	return temp;
    }
    
    public boolean isRoad(int color){
	checked = populate(color);
	System.out.println(printChecked());
	
	boolean topRow = false;
	boolean botRow = false;
	boolean leftCol = false;
	boolean rightCol = false;
	for(int x = 0; x < size && !topRow; x ++){
	    if(checked[0][x] == 1){
		topRow = true;
	    }
	    if(checked[size - 1][x] == 1){
		checked[size - 1][x] = 2;
		botRow = true;
	    }
	}

	if(topRow && botRow){
	    for(int x = 0; x < size ; x ++){
		if((checked[0][x] == 1) && road(0, x)){
		    return true;
		}
	    }
	}

	checked = populate(color);
	for(int x = 0; x < size; x ++){
	    if(checked[x][0] == 1 && !leftCol){
		leftCol = true;
	    }
	    if(checked[x][size - 1] == 1){
		checked[x][size - 1] = 2;
		rightCol = true;
	    }
	}
	
	if(leftCol && rightCol){
	    for(int x = 0; x < size; x ++){
		if((checked[x][0] == 1) && road(x, 0)){
		    
		}
	    }
	}
	
	return false;
    }



    public int[][] populate(int color){
	int[][] temp = new int[size][size];
	for(int x = 0; x < size; x ++){
	    for(int y = 0; y < size; y ++){
		if(isOccupied(x, y) &&
		   isTopPieceNotWall(x, y) &&
		   isTopPieceColor(x, y, color)){
		    temp[x][y] = 1;
		}
	    }
	}
	return temp;
    }

    public boolean road(int x, int y){
	if(checked[x][y] == 2){
	    return true;
	}

	if(checked[x][y] <= 0){
	    return false;
	}
    
	if(checked[x][y] == 1){
	    checked[x][y] = 0;
	    return ( (((x - 1) >= 0) && road(x - 1, y)) ||
		     (((x + 1) < size) && road(x + 1, y)) ||
		     (((y - 1) >= 0) && road(x, y - 1)) ||
		     (((y + 1 ) < size && road(x, y + 1))) );
	}
	return false;	
    }

    public static boolean road(int[][] checked, int x, int y){
	if(checked[x][y] == 2){
	    return true;
	}

	if(checked[x][y] <= 0){
	    return false;
	}
    
	if(checked[x][y] == 1){
	    checked[x][y] = 0;
	    return ( (((x - 1) >= 0) && road(checked, x - 1, y)) ||
		     (((x + 1) < checked.length) && road(checked, x + 1, y)) ||
		     (((y - 1) >= 0) && road(checked, x, y - 1)) ||
		     (((y + 1 ) < checked.length) && road(checked, x, y + 1)));
	}
	return false;	
    }

    public static void main(String[] args){
	int[][] checked = {{0, 0, 1, 0, 0},
			   {0, 0, 1, 0, 0},
			   {0, 1, 1, 1, 1},
			   {1, 1, 0, 0, 0},
			   {2, 0, 0, 0, 0}};
	
	System.out.print(road(checked, 0, 2));
    }
}
