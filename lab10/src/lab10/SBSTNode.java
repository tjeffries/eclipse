/* Author: Thomas Jeffries
 * This is a Binary Search Tree node with internal recursive traversal implementations.
 * */
package lab10;


public class SBSTNode<T extends Comparable<T>> {
	
	private static final int LEFT_CHILD = 0;
	private static final int RIGHT_CHILD = 1;
	public T data;
	public SBSTNode<T> leftChild;
	public SBSTNode<T> rightChild;
	public SBSTNode<T> parent;
	
	public void insert(T x){
		if(x.compareTo(data) <= 0){
			if(leftChild == null){
				leftChild = new SBSTNode<T>();
				leftChild.data = x;
				leftChild.parent = this;
				return;
			}
			leftChild.insert(x);
		} else if(x.compareTo(data) > 0){
			if(rightChild == null){
				rightChild = new SBSTNode<T>();
				rightChild.data = x;
				rightChild.parent = this;
				return;
			} 
			rightChild.insert(x);
		}
	}
	
	public boolean lookup(T x){
		if(x.compareTo(data) == 0)//if x is in this node, return true
			return true;
		if(leftChild != null && x.compareTo(data) < 0)
			return leftChild.lookup(x);//if x less than this node value, call on left child
		else if(rightChild != null && x.compareTo(data) >0)
			return rightChild.lookup(x);//if x greater than this node value, call on right child
		return false;//if x not in this node and no children, return false
	}
	
	public void delete(T x){
		if(x.compareTo(data) == 0){//search value found in this node
			if(leftChild==null){
				if(rightChild==null){//no right or left child (leaf node)
					switch (parentChild()){
						case LEFT_CHILD: parent.leftChild = null;
						return;
						case RIGHT_CHILD: parent.rightChild = null;
						return;
					}
				}
				//has right child but no left child
				switch (parentChild()){
					case LEFT_CHILD: parent.leftChild = rightChild;
					return;
					case RIGHT_CHILD: parent.rightChild = rightChild;
					return;
				}
			}
			else if(rightChild==null){//has left child but no right child
				switch (parentChild()){
					case LEFT_CHILD: parent.leftChild = leftChild;
					return;
					case RIGHT_CHILD: parent.rightChild = leftChild;
					return;
				}
			}
			//replace data with left most child of right child, delete that node:
			SBSTNode<T> tmpNode = rightChild;
			while(tmpNode.leftChild != null)
				tmpNode = tmpNode.leftChild;
			data = tmpNode.data;//set this n7ode's data to next highest node's data
			switch (tmpNode.parentChild()){
				case LEFT_CHILD: tmpNode.parent.leftChild = null;
				return;
				case RIGHT_CHILD: tmpNode.parent.rightChild = null;
				return;
			}
			
				
		} else if(x.compareTo(data) < 0){//if searched for value should be to left
			if(leftChild != null)//left child exists
				leftChild.delete(x);
			return;//left child does not exist (search value not in tree)
		} else if(x.compareTo(data) > 0){//if searched for value should be to the right
			if(rightChild != null)//right child exists
				rightChild.delete(x);
			return;//right child does not exist(search value not in tree)
		}
	}
	
	public String strStructPreOrder(){
		String tmp = "";
		tmp += 1;
		tmp += (leftChild != null) ? leftChild.strStructPreOrder() : 0;
		tmp += (rightChild != null) ? rightChild.strStructPreOrder() : 0;
		return tmp;
	}
	
	public String strStructInOrder(){
		String tmp = "";
		tmp += (leftChild != null) ? leftChild.strStructInOrder() : 0;
		tmp += 1;
		tmp += (rightChild != null) ? rightChild.strStructInOrder() : 0;
		return tmp;
	}
	
	public String strStructPostOrder(){
		String tmp = "";
		tmp += (leftChild != null) ? leftChild.strStructPostOrder() : 0;
		tmp += (rightChild != null) ? rightChild.strStructPostOrder() : 0;
		tmp += 1;
		return tmp;
	}
	
	public String strDataPreOrder(){
		String tmp = "";
		tmp += data.toString();
		if(leftChild != null)
			tmp += ","+leftChild.strDataPreOrder();
		if(rightChild != null)
			tmp += ","+rightChild.strDataPreOrder();
		return tmp;
	}
	
	public String strDataInOrder(){
		String tmp = "";
		if(leftChild != null)
			tmp += leftChild.strDataInOrder()+",";
		tmp += data.toString();
		if(rightChild != null)
			tmp += ","+rightChild.strDataInOrder();
		return tmp;
	}
	
	public String strDataPostOrder(){
		String tmp = "";
		if(leftChild != null)
			tmp += leftChild.strDataPostOrder()+",";
		if(rightChild != null)
			tmp += rightChild.strDataPostOrder()+",";
		tmp += data.toString();
		return tmp;
	}
	
	public void printPreOrder(){
		System.out.printf("%s ", data.toString());
		if(leftChild != null)
			leftChild.printPreOrder();
		if(rightChild != null)
			rightChild.printPreOrder();
	}
	
	public void printInOrder(){
		if(leftChild != null)
			leftChild.printInOrder();
		System.out.printf("%s ", data.toString());
		if(rightChild != null)
			rightChild.printInOrder();
	}
	
	public void printPostOrder(){
		if(leftChild != null)
			leftChild.printPostOrder();
		if(rightChild != null)
			rightChild.printPostOrder();
		System.out.printf("%s ", data.toString());
	}
	
	private int parentChild(){//determines whether this node is right or left child of parent
		if(data.compareTo(parent.data)<=0){
			//this node is left child of parent:
			return LEFT_CHILD;
		}
		//this node is right child of parent:
		return RIGHT_CHILD;
	}
} 