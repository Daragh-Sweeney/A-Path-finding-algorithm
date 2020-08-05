
import java.awt.Graphics;
import java.awt.Image;
import java.io.*; 
import java.lang.*; 
import java.util.*; 
import java.util.ArrayList; 
import java.util.Collections;

public class BadGuy {
	
	Image myImage;
	int x=0,y=0;
	List<node> openList = new ArrayList<node>();
	List<node> closedList = new ArrayList<node>();
	

	public BadGuy( Image i ) {myImage=i;x = 30;y = 10;}
	
	public void reCalcPath(boolean map[][],int targx, int targy) {
		
		//clear out the open and closed arrayLists
		openList = new ArrayList<node>(); closedList = new ArrayList<node>();
		
		//create the starting node and add to open list, H value is calculated using manhattan value
		node start = new node(targx,targy,null, 0 , java.lang.Math.abs(x-targx) + java.lang.Math.abs(y-targy));
		openList.add(start);

		//The first node to be evaluated is the start node 
		node currentNode = openList.get(0);


		while(true){


			//Every time we run the loop we will take the node with the lowest F value 
			//this comparator sorts the list by FVal
			Collections.sort(openList, new Comparator<node>() {
				@Override
				public int compare(node p1, node p2) {return p1.getFVal() - p2.getFVal();}
			});

			//Add the node being inspected to the openlist
			currentNode = openList.get(0);

			//Remove node from openlist and add to closed list
			openList.remove(0); closedList.add(currentNode);

			//if current is the target node you have found your path and can exit loop
			if((currentNode.getXVal() == x) && (currentNode.getYVal() == y)){System.out.println("x and y found");return;}

			for(int i = -1; i<=1; i++){		//double for loop to reate new nodes and calc values for them 
				for(int j = -1; j<=1; j++){

					boolean runThisTime = true;
					int myNodeX = currentNode.getXVal() + i;
					int myNodeY = currentNode.getYVal() + j;

					//Check if the node is in the closed list do not use
					for (int d = 0; d < closedList.size(); d++) {if((closedList.get(d).getXVal() == myNodeX) && (closedList.get(d).getYVal() == myNodeY)){System.out.println("id not create a node with x="+(currentNode.getXVal()+i)+" and y="+(currentNode.getYVal()+j));runThisTime=false;}}


					if(	(currentNode.getXVal() + i <40 && currentNode.getXVal() + i >=0) && 		//dont go off x axis
						(currentNode.getYVal() + j <40 && currentNode.getYVal() + j >=0) &&			//dont go off y axis
						!(i==0 && j ==0) &&															//dont include current node 
						runThisTime){																//dont include nodes in closed list
						if((map[currentNode.getXVal() + i][currentNode.getYVal() + j]  == false)){	//dont include nodes that are walls(note seperate if loop)
																				
							//H value of new node being calculated 
							int hValue = java.lang.Math.abs(x-(currentNode.getXVal() + i)) + java.lang.Math.abs(y-(currentNode.getYVal() + j));

							//create a new node and add to the openlist
							node myNode = new node(currentNode.getXVal() + i, currentNode.getYVal() + j, currentNode, currentNode.getGVal()+1, hValue);
							openList.add(myNode);
							System.out.println("created a node with x="+myNode.getXVal()+" and y="+myNode.getYVal());
						}	
					}			
				}
			}
		}
	}
	
	public void move(boolean map[][],int targx, int targy) {
System.out.println("im working on it");
		//we know the badgu{y location corresponds to a node location in closed list (just move the badguy to the parent location)
		if(closedList.size() > 0 ){
			for (int d = 0; d < closedList.size(); d++) {

				if(closedList.get(d).getXVal() == x && closedList.get(d).getYVal() == y){
					int newx = closedList.get(d).getParent().getXVal();
					int newy = closedList.get(d).getParent().getYVal();
					if (!map[newx][newy]) {x=newx;y=newy;}
					System.out.println("im working");
				}
			}
		}
	}

	public void paint(Graphics g) {g.drawImage(myImage, x*20, y*20, null);}
}