import java.util.ArrayList; 
import java.util.Collections;

public class node {

	node parent;
	int xVal, yVal, gVal, hVal, fVal;

	//create the node
	public node(int xVal,int yVal, node parent, int gVal, int hVal) {
		this.xVal = xVal;
		this.yVal = yVal;
		this.parent = parent;
		this.gVal = gVal;
		this.hVal = hVal;
		this.fVal = hVal + gVal;
	}

	//add getters 
	public int getGVal(){return gVal;}
	public int getHVal(){return hVal;}
	public int getFVal(){return fVal;}
	public int getXVal(){return xVal;}
	public int getYVal(){return yVal;}
	public node getParent(){return parent;}

}