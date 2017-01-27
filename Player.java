import java.util.ArrayList;
import cs1.Keyboard;

public class Player {

    /*~~~~~~~~~~~~~INSTANCE VARIABLES~~~~~~~~~~~~~*/
    String name; 
    int numStones;
    int numCap;
    int color;

    /*~~~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~*/
    //certain number of stones and capstones needed because the max number varies with the board size
    public Player( String n, int c, int size ) {
	name = n;
	color = c;

	if(size == 3){
	    numStones = 10;
	    numCap = 0;
	}
	if(size == 4){
	    numStones = 15;
	    numCap = 0;}
	if(size == 5){
	    numStones = 21;
	    numCap = 1;
	}
	if(size == 6){
	    numStones = 30;
	    numCap = 1;
	}
	if(size == 7){
	    numStones = 40;
	    numCap = 2;
	}
        if(size == 8){
	    numStones = 50;
	    numCap = 2;
	}
    }

    public String getName(){
	return name;
    }
    public int getColor(){
	return color;
    }
    public boolean hasStones(){
	return (numStones +  numCap > 0);
    }
    public boolean noPiecesLeft(){
	return ! hasStones();
    }
    public boolean hasStacks(Board woah){
	return woah.hasStacks(color);
    }
    public void firstTurn(Board woah){
	System.out.println("During the first turn, players will place one of their opponent's flatstones down on the board. You are allowed to place their piece on any tile on the board. Pick a location!");
	int x = Keyboard.readInt();
	int y = Keyboard.readInt();
	while((x < 0) ||
	      (x >= woah.getSize()) ||
	      (y < 0) ||
	      (y >= woah.getSize())){
	    System.out.println("Your chosen location doesn't exist. Have you been eating plum bob? If so, that was a bad decision.");
	     x = Keyboard.readInt();
	     y = Keyboard.readInt();
	}

	while(woah.isOccupied(x, y)){
	    System.out.println("That space is occupied already. Pieces cannot be placed on already occupied pieces!");
	    x = Keyboard.readInt();
	    y = Keyboard.readInt();
	    while((x < 0) ||
		  (x >= woah.getSize()) ||
		  (y < 0) ||
		  (y >= woah.getSize())){
		System.out.println("Your chosen location doesn't exist. Have you been eating plum bob? That was a bad decision.");
		x = Keyboard.readInt();
		y = Keyboard.readInt();
	    }
	}
	int tempC = 0;
	if(color == 0)
	    tempC = 1;
	woah.board[x][y].add(new Stone(tempC, x, y, false));	
    }
    
    public void turn(Board woah){
	System.out.println(name + ", it's now your turn.");
	int counter = 1;
	int moveStone = -1;
	int moveStack = -1;
	int viewStack = -1;
	int move = -1;
	
	if(hasStones()){
	    moveStone = counter;
	    System.out.println(counter + ": Place a piece.");
	    counter += 1;
	}
	if(hasStacks(woah)){
	    moveStack = counter;
	    System.out.println(counter + ": Move a stack.");
	    counter += 1;
	}
	if(woah.hasStacks()){
	    viewStack = counter;
	    System.out.println(counter + ": Display a stack.");
	    counter += 1;
	}
        
        move = Keyboard.readInt();
	
	while ((move <= 0) || (move >= counter)) {
	    System.out.println("There are three things all wise men fear: the sea in storm, a night with no moon, and the anger of a gentle man... Please use your eyes and brain.");
	    move = Keyboard.readInt();
	}

	while(move == viewStack){
	    displayStack(woah);
	    System.out.println("What is you next move?");
	    move = Keyboard.readInt();
	    while ((move <= 0) || (move >= counter)) {
		System.out.println("There are three things all wise men fear: the sea in storm, a night with no moon, and the anger of a gentle man... Please use your eyes and brain.");
		move = Keyboard.readInt();
	    }
	}
	
        if(move == moveStone)
	    placeStone (woah);
	if(move == moveStack)
	    moveStack (woah);
    }
    
    public static void displayStack(Board woah ) {
	System.out.print("Location: ");
	int x = Keyboard.readInt();
	int y = Keyboard.readInt();
	try {
	    woah.displayStack(x, y);
	}
	catch (Exception e) {
	    System.out.println("Where is this unknown land?");
	    System.out.print("Location: ");
	    x = Keyboard.readInt();
	    y = Keyboard.readInt();
	}
    }
    
    public void placeStone(Board woah){
	System.out.println("Choose a piece to place: ");
	int counter = 1;
	int stone = 0;
	int wall = 0;
	int capstone = 0;
	int move = -1;
	
	if(numStones > 0){
	    stone = counter;
	    System.out.println(counter + ": Stone");
	    counter ++;
	    wall = counter;
	    System.out.println(counter + ": Wall");
	    counter ++;
	}
	if(numCap > 0){
	    capstone = counter;
	    System.out.println(counter + ": Capstone");
	    counter ++;
	}

        move = Keyboard.readInt();
	while (move <= 0 || move >= counter) {
	    System.out.println("There are three things all wise men fear: the sea in storm, a night with no moon, and the anger of a gentle man... Please use your eyes and brain. \nIf a piece type is not showing up, it is because you don't have enough of that piece.");
	    move = Keyboard.readInt();
	}

	System.out.println("Location: (format is '0 0' where the first integer is the row and the second the column)");
	int x = Keyboard.readInt();
	int y = Keyboard.readInt();
	while((x < 0) ||
	      (x >= woah.getSize()) ||
	      (y < 0) ||
	      (y >= woah.getSize())){
	    System.out.println("Your chosen location doesn't exist. Have you been eating plum bob? If so, that was a bad decision.");
	     x = Keyboard.readInt();
	     y = Keyboard.readInt();
	}

	while(woah.isOccupied(x, y)){
	    System.out.println("That space is occupied already. Pieces cannot be placed on already occupied pieces!");
	    x = Keyboard.readInt();
	    y = Keyboard.readInt();
	    while((x < 0) ||
		  (x >= woah.getSize()) ||
		  (y < 0) ||
		  (y >= woah.getSize())){
		System.out.println("Your chosen location doesn't exist. Have you been eating plum bob? If so, that was a bad decision.");
		x = Keyboard.readInt();
		y = Keyboard.readInt();
	    }
	}

	if(move == stone)
	    woah.board[x][y].add(new Stone(color, x, y, false));
	if(move == wall)
	    woah.board[x][y].add(new Stone(color, x, y, true));
	if(move == capstone)
	    woah.board[x][y].add(new Capstone(color, x, y, false));
    }
    
    public void moveStack(Board woah){
	//=======================================================================================================================================================================
	
	System.out.println("Which stack would you like to move? Location:");
	int x = Keyboard.readInt();
	int y = Keyboard.readInt();
	
	while((x < 0) ||
	      (x >= woah.getSize()) ||
	      (y < 0) ||
	      (y >= woah.getSize())){
	    System.out.println("Your chosen location doesn't exist. Have you been eating plum bob? That was a bad decision.");
	     x = Keyboard.readInt();
	     y = Keyboard.readInt();
	}

	while(woah.isEmpty(x, y)){
	    System.out.println("This tile is empty! There is no stack here. Remember, the first column and row is 0, not 1. You also have to input row first!");
	    x = Keyboard.readInt();
	    y = Keyboard.readInt();
	    while((x < 0) ||
		  (x >= woah.getSize()) ||
		  (y < 0) ||
		  (y >= woah.getSize())){
		System.out.println("Your chosen location doesn't exist. Have you been eating plum bob? If so, that was a bad decision.");
		x = Keyboard.readInt();
		y = Keyboard.readInt();
	    }
	}

	while(woah.stackOwner(x, y) != color){
	    System.out.println("This stack is not under your control so you cannot move it! In order for you to control the stack, the top piece of the stack must be your color!");
	    x = Keyboard.readInt();
	    y = Keyboard.readInt();
	    while(woah.isEmpty(x, y)){
		System.out.println("This tile is empty! There is no stack here. Remember, the first column and row is 0, not 1. You also have to input row first!");
		x = Keyboard.readInt();
		y = Keyboard.readInt();
		while((x < 0) ||
		      (x >= woah.getSize()) ||
		      (y < 0) ||
		      (y >= woah.getSize())){
		    System.out.println("Your chosen location doesn't exist. Have you been eating plum bob? If so, that was a bad decision.");
		    x = Keyboard.readInt();
		    y = Keyboard.readInt();
		}
	    }
	}

	while((!woah.playerCap(x, y, color)) &&
	      (! (woah.stoneMoveStack(x - 1, y) ||
		  woah.stoneMoveStack(x + 1, y) ||
		  woah.stoneMoveStack(x, y - 1) ||
		  woah.stoneMoveStack(x, y + 1)))){
	    System.out.println("This stack is surrounded by immovable parts. Since your stack does not have capstone at the top, you cannot move. If you had a capstone, you could break down the walls surrounding you.");
	    x = Keyboard.readInt();
	    y = Keyboard.readInt();
	    while(woah.stackOwner(x, y) != color){
		System.out.println("This stack is not under your control so you cannot move it! In order for you to control the stack, the top piece of the stack must be your color!");
		x = Keyboard.readInt();
		y = Keyboard.readInt();
		while(woah.isEmpty(x, y)){
		    System.out.println("This tile is empty! There is no stack here. Remember, the first column and row is 0, not 1. You also have to input row first!");
		    x = Keyboard.readInt();
		    y = Keyboard.readInt();
		    while((x < 0) ||
			  (x >= woah.getSize()) ||
			  (y < 0) ||
			  (y >= woah.getSize())){
			System.out.println("Your chosen location doesn't exist. Have you been eating plum bob? If so, that was a bad decision.");
			x = Keyboard.readInt();
			y = Keyboard.readInt();
		    }
		}
	    }
	}
	
	//=======================================================================================================================================================================
	
	String direction = "";
	System.out.println("Which direction would you like to move?");
	int counter = 1;
	int left = -1;
	int right = -1;
	int up = -1;
	int down = -1;
	if(woah.isCapstone(x, y)){
	    System.out.println("made it past here");
	    if(woah.capMoveStack(x - 1, y)){
		System.out.println(counter + ": up");
		up = counter;
		counter ++;
	    }
	    if(woah.capMoveStack(x + 1, y)){
		System.out.println(counter + ": down");
		down = counter;
		counter ++;
	    }
	    if(woah.capMoveStack(x, y + 1)){
		System.out.println(counter + ": right");
		right = counter;
		counter ++;
	    }
	    if(woah.capMoveStack(x, y - 1)){
		System.out.println(counter + ": left");
		left = counter;
		counter ++;
	    }
	}
	else {
	    if(woah.stoneMoveStack(x - 1, y)){
		System.out.println(counter + ": up");
		up = counter;
		counter ++;
	    }
	    if(woah.stoneMoveStack(x + 1, y)){
		System.out.println(counter + ": down");
		down = counter;
		counter ++;
	    }
	    if(woah.stoneMoveStack(x, y + 1)){
		System.out.println(counter + ": right");
		right = counter;
		counter ++;
	    }
	    if(woah.stoneMoveStack(x, y - 1)){
		System.out.println(counter + ": left");
		left = counter;
		counter ++;
	    }
	}
	    
	int move;
	System.out.println("Which direction would you like to move?");
	move = Keyboard.readInt();
	while(move < 1 || move >= counter){
	    System.out.println("You will meet Chandrian on that path. Choose more wisely.");
	    move = Keyboard.readInt();
	}

	if(move == left)
	    direction = "left";
	if(move == right)
	    direction = "right";
	if(move == up)
	    direction = "up";
	if(move == down)
	    direction = "down";
	
	//=======================================================================================================================================================================

	int stackSize = woah.getStackSize(x, y);
	ArrayList<Piece> stack = woah.getStack(x, y, stackSize);
	int startX = x;
	int startY = y;
        
	int leftB = 0;
	//if the stack is controlled by the player's capstone
	if(stack.get(stack.size() - 1) instanceof Capstone){
	    //while the next position is not the edge of the board or occupied by a capstone
	    while(woah.capMoveStack(updateX(x, direction), updateY(y, direction)) && stackSize > 0){
		//if the next piece is not a wall
		if(woah.isTopPieceNotWall(updateX(x, direction), updateY(y, direction))){
		    //if your current position is your starting position
		    if(startX == x && startY == y){
			System.out.println("How many stones would you like to leave behind?");
			leftB = Keyboard.readInt();
			while(leftB < 0 || leftB >= stackSize){
			    System.out.println("You can't leave negative stones or all stones behind!");
			    leftB = Keyboard.readInt();
			}
			//subtract that many stones from your stack
			stackSize -= leftB;
			/*case capstone:
			  b.board[x][y].add(new Capstone(color, x, y, false));
			  break;*/	
			stack = addStack(woah, x, y, stack, leftB);
		    }
		    //if your current position is not your starting position
		    else{
			System.out.println("How many stones would you like to leave behind? Since you have moved already, you must leave at least one stone behind!");
			leftB = Keyboard.readInt();
			while((leftB < 1 || leftB > stackSize)){
			    System.out.println("You can't leave negative stones or no stones behind!");
			    leftB = Keyboard.readInt();
			}
			//subtract that many stones from your stack
			stackSize -= leftB;
			stack = addStack(woah, x, y, stack, leftB);
		    }
		    //since you have left pieces behind and your stack has been updated, you move one place in that direction
		    x = updateX(x, direction);
		    y = updateY(y, direction);
		}
		//if the next piece is a wall
		else{
		    System.out.println("The next piece is a wall! Only your capstone can flatten it meaning you have to leave the rest of your stack at your current position.");
		    System.out.println("1: Flatten wall");
		    System.out.println("2: Stay at current position");
		    int flat = Keyboard.readInt();
		    while( flat != 1 || flat != 2){
			System.out.println("A wall makes Tak great, but a road will connect our worlds! Flatten or leave it standing?");
			flat = Keyboard.readInt();
		    }
		    if(flat == 1){
			stack = addStack(woah, x, y, stack, stackSize - 2);
			stackSize = 1;
			x = updateX(x, direction);
			y = updateY(y, direction);
			woah.flattenWall(x, y);
			addStack(woah, x, y, stack);
			return;
		    }
		    else {
			addStack(woah, x, y, stack);
			return;
		    }
		}
	    }
	    System.out.println("You can't move anymore! Your stack is deposited at your current position!");
	    addStack(woah, x, y, stack);
	    return;
	}
	//if the stack is not controlled by the player's capstone
	else{
	    //while the next position is not the edge of the board or occupied by a capstone
	    while(woah.stoneMoveStack(updateX(x, direction), updateY(y, direction)) && stackSize > 0){
		//if your current position is your starting position
		if(startX == x && startY == y){
		    System.out.println("How many stones would you like to leave behind?");
		    leftB = Keyboard.readInt();
		    while(leftB < 0 || leftB >= stackSize){
			System.out.println("You can't leave negative stones or all stones behind!");
			leftB = Keyboard.readInt();
		    }
		    //subtract that many stones from your stack
		    stackSize -= leftB;
		    stack = addStack(woah, x, y, stack, leftB);
		}
		//if your current position is not your starting position
		else{
		    /*System.out.println("How many stones would you like to leave behind? Since you have moved already, you must leave at least one stone behind!");
		    leftB = Keyboard.readInt();
		    System.out.println("Stack size:" + stackSize);
		    System.out.println("Input:" + leftB);
		    System.out.println("first boolean:" + (leftB < 1));
		    System.out.println("second boolean: " + ( leftB > stackSize));*/
		    while(leftB < 1 || leftB > stackSize){
			System.out.println("You can't leave negative stones or no stones behind!");
			leftB = Keyboard.readInt();
		    }
		    //subtract that many stones from your stack
		    stackSize -= leftB;
		    stack = addStack(woah, x, y, stack, leftB);
		}
		//since you have left pieces behind and your stack has been updated, you move one place in that direction
		x = updateX(x, direction);
		y = updateY(y, direction);
	    }
	    System.out.println("You can't move anymore! Your stack is deposited at your current position!");
	    addStack(woah, x, y, stack);
	    return;
	}
    }

    public int updateX(int x, String direction){
	if(direction == "right" || direction == "left")
	    return x;
	if(direction == "up")
	    return (x - 1);
	else
	    return (x + 1);
    }

    public int updateY(int y, String direction){
	if(direction == "up" || direction == "down")
	    return y;
	if(direction == "left")
	    return (y - 1);
	return (y + 1);
    }

    public ArrayList<Piece> addStack(Board woah, int x, int y, ArrayList<Piece> stack, int leftB){
	ArrayList<Piece> temp = new ArrayList<Piece>(stack.size() - leftB);
	for(int a = 0; a < leftB; a ++){
	    woah.board[x][y].add(stack.get(a));
	}    
        for(int a = leftB; a < stack.size(); a ++){
	    temp.add(stack.get(a));
	}
	return temp;
    }

    public void addStack(Board woah, int x, int y, ArrayList<Piece> stack){
	for(int count = 0; count < stack.size(); count ++){
	    woah.board[x][y].add(stack.get(count));
	} 
    }
    
    public void moveStack( int x, int y, int stackSize, String direction, Board b ) {
	ArrayList<Piece> stack =  b.board[x][y];
	int size = stack.size();
	
	//temporary ArrayList for the stack the user is moving
	ArrayList<Piece> holder = new ArrayList<Piece>();
	for (int i = size-1; i > size-1-stackSize; i--) {
	    holder.add( stack.remove(i) );
	}
	if (direction.equals("up")) {
	    for (Piece p : holder) {
		b.board[x-1][y].add(p);
	    }
	    x--;
	}
	else if (direction.equals("left")) {
	    for (Piece p : holder) {
		b.board[x][y-1].add(p);
	    }
	    y--;
	}
	else if (direction.equals("down")) {
	    for (Piece p : holder) {
		b.board[x+1][y].add(p);
	    }
	    x++;
	}
	else if (direction.equals("right")) {
	    for (Piece p : holder) {
		b.board[x][y+1].add(p);
	    }
	    y++;
	}

	stack = b.board[x][y];
	size = stack.size();
	
	//if the top of the tile that the player is moving a capstone to is a wall, flatten it
	if ( (stack.get(size-1) instanceof Capstone) && (stackSize == 1) ) {
	    try {
		((Capstone)(stack.get(size-1))).flattenWall((Stone)stack.get(size-2));
	    }
	    catch (Exception e) {}
	}

    }
}
