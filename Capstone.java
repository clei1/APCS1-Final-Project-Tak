public class Capstone extends Piece{

    /*~~~~~~~~~~~~~OVERLOADED CONSTRUCTOR~~~~~~~~~~~~~*/
    public Capstone(int color, int x, int y){
	super(color,x,y);
    }

    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    public static void flattenWall(Board b, Stone s,int row, int column){
	if (b.isEmpty(row, column)){
	    setPos(row, column);
	}
	else{
	    s.wallToStone();
	    setPos(row, column);
	}
    }
}
