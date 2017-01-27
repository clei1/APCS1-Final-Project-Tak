import java.util.ArrayList;

public class Board{
    
    /*~~~~~~~~~~~~~INSTANCE VARIABLES~~~~~~~~~~~~~*/
    ArrayList<Piece>[][] board; //the board is represented by a 2D array
    int size;//size is the length and width of the board, they are equal because it is a square
    int[][] checked;

     /*~~~~~~~~~~~~~DEFAULT CONSTRUCTOR~~~~~~~~~~~~~*/
    //Input: Takes a player inputted number between 3 to 8, defining the size
    public Board(int s){
	board = new ArrayList[s][s]; //created board with player defined size
	for(int col = 0; col < s; col ++){ //iterates through the columns
	    for(int row = 0; row < s; row ++){ //for each column, create rows
		board[row][col] = new ArrayList<Piece>(); //sets each array to contain an ArrayList, which will contain stacks
	    }
	}
	size = s; //sets size to player defined size 
    }

    /*~~~~~~~~~~~~~OVERLOADED TOSTRING()~~~~~~~~~~~~~*/
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

    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    /*
      void displayStack(int x, int y)
      precondition: instantiated board
      postcondition: prints out the stack at a specified tile, with the bottom piece on the bottom, the top piece on the top, and everything in between
    */
    public void displayStack(int x, int y){
	System.out.println("TOP");
	for (int i = board[x][y].size()-1; i >= 0; i-- ) {
	    System.out.println(board[x][y].get(i));
	}
	System.out.println("BOTTOM");
    }

    /*
      boolean hasStacks(int color)
      precondition: color input and instantiated board with pieces on it
      postcondition: returns true if there is a stack that is cotrolled by the inputted color
    */

    public boolean hasStacks(int color){
	for(int x = 0; x < size; x ++){
	    for(int y = 0; y < size; y ++){
		if(stackOwner(x, y) == color){
		    //if tile is empty, it inputs -1
		    //if tile is controlled by black, it inputs 0
		    //if tile is controlled by white, it inputs 1
		    return true;
		}
	    }
	}
	return false;
    }

    /*
      boolean hasStacks()
      precondition: instantiated board
      postcondition: returns true if there is at least one piece on the board, false otherwise
    */
    public boolean hasStacks(){
	for(int x = 0; x < size; x ++){
	    for(int y = 0; y < size; y ++){
		if(isOccupied(x, y)){
		    //if tile is not empty, it inputs TRUE
		    //if tile is empty, it inputs FALSE
		    return true;
		}
	    }
	}
	return false;
    }

    /*
      boolean isEmpty(int col, int row)
      precondition: instatiated board
      postcondition: returns true if the tile at the specified position has no pieces on it, false otherwise
    */

    public boolean isEmpty(int x, int y){
	return board[x][y].size() == 0;
    }

    /*
      boolean isOccupied(int col, int row)
      precondition: an instantiated board
      postcondition: returns true if tile at the specified position has at least one piece on it, false otherwise
    */

    public boolean isOccupied(int x, int y){
	return (!isEmpty(x, y));
    }

    public boolean isBoardFull(){
	for(int x = 0; x < size; x ++){
	    for(int y = 0; y < size; y ++){
		if(isEmpty(x, y))
		    return false;
	    }
	}
	return true;
    }
    
    /*
      int stackOwner(int x, int y)
      precondition: an instantiated board
      postcondition: returns -1 if there are no pieces on the tile, 0 if the topmost stone is black, 1 if the topmost stone is white
    */
    
    public int stackOwner(int x, int y){
	ArrayList<Piece> p = board[x][y];
        int lastPos = p.size()-1;
	if (p.isEmpty())
	    return  -1;
	else
	    return p.get(lastPos).getColor();
    }

    /*
      boolean isTopPieceColor(int col, int row, int color)
      precondition: instantiated board with pieces already placed
      postcondition: returns true if 'color' owns the stack at the tile, false otherwise
    */
    
    public boolean isTopPieceColor(int x , int y, int color){
	return stackOwner(x, y) == color;
    }

    /*
      boolean isTopPieceWall(int col, int row)
      precondition: an instantiated board with pieces placed
      postcondition: returns true if the top piece of a stack is a wall, false otherwise
    */
    public boolean isTopPieceWall(int col, int row){
	int lastPos = board[col][row].size() - 1;
	return board[col][row].get(lastPos).isWall();
    
    }
    /*
      boolean isTopPieceNotWall(int col, int row)
      precondition: an instantiated board with pieces placed
      postcondition: returns true if the top piece of a stack is not a wall, false otherwise
    */
    public boolean isTopPieceNotWall(int col, int row){
	return (!isTopPieceWall(col, row));
    }

    /*
      boolean isCapstone(int col, int row)
      precondition: instantiated board with pieces on it
      postcondition: returns true if the top piece of a stack is a capstone, false otherwise
    */
    public boolean isCapstone(int col, int row){
	return (board[col][row].get(board[col][row].size() - 1) instanceof Capstone);
    }

    public boolean isTopPieceNotCapstone(int col, int row){
	return (!isCapstone(col, row));
    }

    public boolean playerCap(int x, int y, int color){
	return (isCapstone(x, y) && board[x][y].get( board[x][y].size() - 1).getColor() == color);
    }

    public int getSize(){
	return size;
    }
    
    public boolean capMoveStack(int x, int y){
	return((x >= 0) &&
	       (x <= (size - 1)) &&
	       (y <= (size - 1)) &&
	       (y >= 0) &&
	       (isEmpty(x, y) ||
		isTopPieceNotCapstone(x, y)));
    }

    public boolean capMovingStack(int x, int y){
	return((x >= 0) &&
	       (x <= (size - 1)) &&
	       (y <= (size - 1)) &&
	       (y >= 0) &&
	       (isEmpty(x, y) || 
		isTopPieceNotCapstone(x, y)));
    }
    
    public boolean stoneMoveStack(int x, int y){
	return((x >= 0) &&
	       (x <= (size - 1)) &&
	       (y <= (size - 1)) &&
	       (y >= 0) &&
	       (isEmpty(x, y) ||
		(isTopPieceNotCapstone(x, y) &&
		 isTopPieceNotWall(x, y))));
    }

    public boolean stoneMovingStack(int x, int y){
	return((x >= 0) &&
	       (x <= (size - 1)) &&
	       (y <= (size - 1)) &&
	       (y >= 0) &&
	       (isEmpty(x, y) ||
		(isTopPieceNotCapstone(x, y) &&
		 isTopPieceNotWall(x, y))));
    }
    /*
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
    */

    /*
      boolean isRoad(int color)
      precondition: an instantiated board
      postcondition: returns true if a road is present, false otherwise
    */
    public boolean isRoad(int color){
	checked = populate(color);
	//System.out.println(printChecked());
	
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
	//System.out.println(printChecked());
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
		     (((y + 1) < size) && road(x, y + 1)) );
	}
	return false;	
    }

    public int stackWinner(Player player1, Player player2){
	int p1 = player1.getColor();
	int p1color = 0;
	int p2color = 0;
	for(int x = 0; x < size; x ++){
	    for(int y = 0; y < size; y ++){
		if(isOccupied(x, y)){
		    if (isTopPieceNotWall(x, y)){
			if(stackOwner(x, y) == p1)
			    p1color += 1;
			else
			    p2color += 1;
		    }
		}
	    }
	}
	if(p2color > p1color)
	    return 2;
	if(p1color > p2color)
	    return 1;
	return -1;
    }

    public int getStackSize(int x, int y){
	if(board[x][y].size() > size){
	    return size;
	}
	else{
	    return board[x][y].size();
	}
    }
    
    public ArrayList<Piece> getStack(int x, int y, int stackSize){
	int lastPos = board[x][y].size() - 1;
	ArrayList<Piece> temp = new ArrayList<Piece>(stackSize);
	for(int a = 0; stackSize > a; a ++){
	    temp.add(0, board[x][y].get(board[x][y].size() - 1));
	    board[x][y].remove(board[x][y].size() - 1);
	}
	return temp;
    }
    
    public boolean isTopPieceStone(int x, int y){
       return (isTopPieceNotWall(x, y) && isTopPieceNotCapstone(x, y));
    }
    /*    
    public boolean square(int x, int y){
	return ( (((x - 1) >= 0) && isOccupied(x - 1, y) && isTopPieceStone(x - 1, y)) ||
		 (((x + 1) < size ) && isOccupied(x + 1, y) && isTopPieceStone(x + 1, y)) ||
		 (((y - 1) >= 0) && isOccupied(x, y - 1) && isTopPieceStone(x, y - 1)) ||
		 (((y + 1 ) < size) && isOccupied(x, y + 1) && isTopPieceStone(x, y + 1)));
    }
    
    public int getStackSize(int x, int y){
	return (board[x][y].length - 1);
    }
    */

    public void flattenWall(int x, int y){
	int lastPos = board[x][y].size() - 1;
	board[x][y].get(lastPos).setStone();
    }
    
    /*
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
    */
}
