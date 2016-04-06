import java.util.ArrayList;

public class BinarySearchTree {
	Node root = null;
	
	public ArrayList<String> inOrderList = new ArrayList<String>();
	public ArrayList<String> preOrderList = new ArrayList<String>();
	public ArrayList<String> postOrderList = new ArrayList<String>();
	
	public BinarySearchTree(){

	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node find(Node node){
		if (root.getValue().equals(node.getValue()))
			return root;
		else
			return find(root,node);
	}

	private Node find(Node node1, Node node2){
		if (node1.getLeftChild() != null){
			if (node1.getLeftChild().getValue().equals(node2.getValue())){
				return node1.getLeftChild();
			}
		}

		if (node1.getRightChild() != null){
			if (node1.getRightChild().getValue().equals(node2.getValue())){
				return node1.getRightChild();
			}
		}

		if (node1.greaterOrNot(node2.getValue()) && node1.getLeftChild() != null){
			return find(node1.getLeftChild(),node2);
		}
		else if (!node1.greaterOrNot(node2.getValue()) && node1.getRightChild() != null){
			return find(node1.getRightChild(), node2);
		}
		else 
			return null;

	}

	public void insert(Node node){
		if (root == null){
			root = node;
		}
		else{
			insert(root,node);
		}
	}

	private void insert(Node node1, Node node2){
		if (node1.greaterOrNot(node2.getValue()) && node1.getLeftChild() == null){
			node1.setLeftChild(node2);
			node2.setParent(node1);
		}
		else if (node1.greaterOrNot(node2.getValue()) && node1.getLeftChild() != null){
			insert(node1.getLeftChild(),node2);
		}
		else if (!node1.greaterOrNot(node2.getValue()) && node1.getRightChild() == null){
			node1.setRightChild(node2);
			node2.setParent(node1);
		}
		else if (!node1.greaterOrNot(node2.getValue()) && node1.getRightChild() != null){
			insert(node1.getRightChild(),node2);
		}
	}

	public void delete(Node node){
		int childNumber = 0;

		if (node.getLeftChild() != null)
			childNumber++;
		if (node.getRightChild() != null)
			childNumber++;

		//System.out.println("Child number of "+ node.getValue() + " :" + childNumber);
		if (childNumber == 0){
			if (root.equals(node)){
				root = null;
			}
			else{
				if (node.getParent().getRightChild() != null){
					if (node.getParent().getRightChild().getValue().equals(node.getValue())){
						node.getParent().setRightChild(null);
					}
				}
				if (node.getParent().getLeftChild()!= null){
					if (node.getParent().getLeftChild().getValue().equals(node.getValue())){
						node.getParent().setLeftChild(null);
					}
				}
			}
		}

		if (childNumber == 1){
			if (root.equals(node)){
				if (root.getRightChild()!= null){
					root.getRightChild().setParent(null);
					root = root.getRightChild();
				}
				else if (root.getLeftChild() != null){
					root.getLeftChild().setParent(null);
					root = root.getLeftChild();
				}
			}
			else{
				if (node.getParent().getRightChild() != null){
					if (node.getLeftChild() != null && node.getParent().getRightChild().equals(node)){
						node.getParent().setRightChild(node.getLeftChild());
						node.getLeftChild().setParent(node.getParent());
					}
					else if (node.getRightChild() != null && node.getParent().getRightChild().equals(node)){
						node.getParent().setRightChild(node.getRightChild());
						node.getRightChild().setParent(node.getParent());
					}
				}
				if (node.getParent().getLeftChild() != null){
					if (node.getLeftChild() != null && node.getParent().getLeftChild().equals(node)){
						node.getParent().setLeftChild(node.getLeftChild());
						node.getLeftChild().setParent(node.getParent());
					}
					else if (node.getRightChild() != null && node.getParent().getLeftChild().equals(node)){
						node.getParent().setLeftChild(node.getRightChild());
						node.getRightChild().setParent(node.getParent());
					}
				}
			}
		}

		if (childNumber == 2){
			Node heir = node.getLeftChild();
			
			while(heir.getRightChild()!= null){
				heir = heir.getRightChild();
			}
			
			String temp = heir.getValue();
			this.delete(heir);
			node.setValue(temp);
		}

	}

	public void displayinOrderPrint(){
		displayinOrderPrint(root);
	}
	
	public void displaypostOrderPrint(){
		displaypostOrderPrint(root);
	}
	
	public void displaypreOrderPrint(){
		displaypreOrderPrint(root);
	}
	
	private void displaypreOrderPrint(Node n){
		if (n == null)
			return;
		
		System.out.print(findAllInfo(n.getValue()));
		displaypreOrderPrint(n.getLeftChild());
		displaypreOrderPrint(n.getRightChild());
		
	}
	
	private void displaypostOrderPrint(Node n){
		if (n == null)
			return;
		
		displaypostOrderPrint(n.getLeftChild());
		displaypostOrderPrint(n.getRightChild());
		System.out.print(findAllInfo(n.getValue()));
	}

	private void displayinOrderPrint(Node n){
		if (n == null)
			return;

		displayinOrderPrint(n.getLeftChild());
		System.out.print(findAllInfo(n.getValue()));
		displayinOrderPrint(n.getRightChild());
	}
	
	
	
	//starting of create array orders
	public void createinOrderPrint(){
		createinOrderPrint(root);
	}
	
	public void createpostOrderPrint(){
		createpostOrderPrint(root);
	}
	
	public void createpreOrderPrint(){
		createpreOrderPrint(root);
	}
	
	private void createpreOrderPrint(Node n){
		if (n == null)
			return;
		
		preOrderList.add(n.getValue());
		createpreOrderPrint(n.getLeftChild());
		createpreOrderPrint(n.getRightChild());
		
	}
	
	private void createpostOrderPrint(Node n){
		if (n == null)
			return;
		
		createpostOrderPrint(n.getLeftChild());
		createpostOrderPrint(n.getRightChild());
		postOrderList.add(n.getValue());
	}

	private void createinOrderPrint(Node n){
		if (n == null)
			return;

		createinOrderPrint(n.getLeftChild());
		inOrderList.add(n.getValue());
		createinOrderPrint(n.getRightChild());
	}
	
	private String findAllInfo(String xData){
		
		String result = "Could not be found in list.\n";
		
		for (String line: Test.fullInsertedList){
			if (line.contains(xData)){
				result = line+"\n";
				break;
			}
		}
		
		return result;
	}
}
