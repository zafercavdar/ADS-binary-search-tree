
public class Node {
	private String value = null;
	private Node parent = null;
	private Node leftChild = null;
	private Node rightChild = null;
	
	public Node(String value){
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
	
	public boolean greaterOrNot(String secondVal){
		
		int len1 = this.value.length();
		int len2 = secondVal.length();
		int minLen = Math.min(len1, len2);
		boolean result = true;
		
		for (int i=0; i < minLen; i++){
			if (value.toLowerCase().charAt(i)< secondVal.toLowerCase().charAt(i)){
				result = false;
				break;
			}
			else if (value.toLowerCase().charAt(i)> secondVal.toLowerCase().charAt(i)){
				result = true;
				break;
			}
		}
		
		return result;
	}
}
