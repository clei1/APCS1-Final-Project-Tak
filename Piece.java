public class Piece{

    protected int color; //-1 means not instantiated, 0 means black, 1 means white
    protected boolean isWall;


    //Overloaded constructor
    //takes the color, location, and wall
    public Piece(int colores, boolean wall){
	color = colores;
	isWall = wall;
    }
	
    public int getColor(){
	return color;
    }
    
    public boolean isWall(){
	return isWall;
    }
    
    /*~~~~~~~~~~~~~MUTATORS~~~~~~~~~~~~~*/
    public void setStone(){
	isWall = false;
    }
}
