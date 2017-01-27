public class Piece{

    protected int color; //-1 means not instantiated, 0 means black, 1 means white
    protected int x; //column, -1 means not on board
    protected int y; //row, -1 means not on board
    protected int stackPos; //-1 is not on board, 0 is bottom of stack, 1 is second from bottom, etc.
    protected boolean isWall;


    //Overloaded constructor
    //takes the color, location, and wall
    public Piece(int colores, int ex, int why, boolean wall){
	color = colores;
	x = ex;
	y = why;
	stackPos = 0;
	isWall = wall;
    }
    
    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public int getStack(){
	return stackPos;
    }

    public int getColor(){
	return color;
    }
    
    public boolean isWall(){
	return isWall;
    }
    
    /*~~~~~~~~~~~~~MUTATORS~~~~~~~~~~~~~*/
    public  void setPos(int ex, int why){
	x = ex;
	y = why;
    }

    public void setStone(){
	isWall = false;
    }
}
