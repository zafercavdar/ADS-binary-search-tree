import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Test {

	static String databaseFileName = "database.txt";
	static String queryFileName = "query.txt";
	
	static BinarySearchTree nameTree = new BinarySearchTree();
	static BinarySearchTree surnameTree = new BinarySearchTree();
	static BinarySearchTree phoneTree = new BinarySearchTree();
	
	public static ArrayList<String> fullInsertedList = new ArrayList<String>();

	
	public static void main(String[] args) {
		
		readDatabaseAndInsertItToBST();	
		readQueriesAndDoIt();
	}
	
	public static void readDatabaseAndInsertItToBST(){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(databaseFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(true){
			String line = null;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (line == null)
				break;
			
			StringTokenizer tokenizer = new StringTokenizer(line," ");
			
			String name = "";
			String surname = "";
			String phoneNumber = "";
			boolean emptyCheck = true;
			
			if (tokenizer.hasMoreTokens())
				name = tokenizer.nextToken();
			if (tokenizer.hasMoreTokens())
				surname = tokenizer.nextToken();
			if (tokenizer.hasMoreTokens())
				phoneNumber = tokenizer.nextToken();
			
			
			emptyCheck = !name.equals("") && !surname.equals("") && !phoneNumber.equals("");
			
			if (emptyCheck){
				Node nameNode = new Node(name);
				nameTree.insert(nameNode);
				
				Node surnameNode = new Node(surname);
				surnameTree.insert(surnameNode);
				
				Node phoneNode = new Node(phoneNumber);
				phoneTree.insert(phoneNode);
				
				fullInsertedList.add(name +" " + surname + " " + phoneNumber);
			}
			else{
				System.out.println("One of the inputs is empty!");
			}
		}
	}
	
	public static void readQueriesAndDoIt(){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(queryFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(true){
			String line = null;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (line == null)
				break;
			
			StringTokenizer tokenizer = new StringTokenizer(line," ");
			
			String command ="";
			
			if (tokenizer.hasMoreElements())
				command = tokenizer.nextToken().toLowerCase();
			
			if (command.toLowerCase().equals("delete") || command.toLowerCase().equals("insert")){
				
				String name="";
				String surname ="";
				String phoneNumber ="";
				boolean emptyCheck = true;
				
				if (tokenizer.hasMoreTokens()){
					name = tokenizer.nextToken();
				}
				if (tokenizer.hasMoreTokens())
					surname = tokenizer.nextToken();
				if (tokenizer.hasMoreTokens())
					phoneNumber = tokenizer.nextToken();
				
				emptyCheck = !name.equals("") && !surname.equals("") && !phoneNumber.equals("");
				
				if (command.toLowerCase().equals("insert")){
					nameTree.insert(new Node(name));
					surnameTree.insert(new Node(surname));
					phoneTree.insert(new Node(phoneNumber));
					fullInsertedList.add(name +" " + surname + " " + phoneNumber);
				}
				
				else if (command.toLowerCase().equals("delete")){
					Node n1 = nameTree.find(new Node(name));
					Node n2 = surnameTree.find(new Node(surname));
					Node n3 = phoneTree.find(new Node(phoneNumber));
					
					if (n1 != null){
						nameTree.delete(n1);
					}
					if (n2 != null){
						surnameTree.delete(n2);
					}
					if (n3 != null){
						phoneTree.delete(n3);
					}
				}
			}
			else if (command.toLowerCase().equals("display")){
				String treeName = "";
				
				if (tokenizer.hasMoreElements())
					treeName = tokenizer.nextToken();
				
				String orderType = "";
				
				if (tokenizer.hasMoreElements()){
					orderType = tokenizer.nextToken();
				}
				
				boolean emptyCheck = true;
				emptyCheck = !treeName.equals("") && !orderType.equals("");
				
				if (emptyCheck){
					treeName = treeName.toLowerCase();
					orderType = orderType.toLowerCase();
					
					System.out.println(line);
					
					if (treeName.equals("name") && orderType.equals("inorder")){
						nameTree.displayinOrderPrint();
					}
					else if (treeName.equals("name") && orderType.equals("preorder")){
						nameTree.displaypreOrderPrint();
					}
					else if (treeName.equals("name") && orderType.equals("postorder")){
						nameTree.displaypostOrderPrint();
					}
					else if (treeName.equals("surname") && orderType.equals("inorder")){
						surnameTree.displayinOrderPrint();
					}
					else if (treeName.equals("surname") && orderType.equals("preorder")){
						surnameTree.displaypreOrderPrint();
					}
					else if (treeName.equals("surname") && orderType.equals("postorder")){
						surnameTree.displaypostOrderPrint();
					}
					else if (treeName.equals("phone") && orderType.equals("inorder")){
						phoneTree.displayinOrderPrint();
					}
					else if (treeName.equals("phone") && orderType.equals("preorder")){
						phoneTree.displaypreOrderPrint();
					}
					else if (treeName.equals("phone") && orderType.equals("postorder")){
						phoneTree.displaypostOrderPrint();
					}
					
				}
				else{
					System.err.println("One of the parameters is empty.");
				}
			}
			else if (command.toLowerCase().equals("position")){
				
				System.out.println(line);
				
				String treeName = "";
				
				if (tokenizer.hasMoreElements())
					treeName = tokenizer.nextToken();
				
				String orderType = "";
				
				if (tokenizer.hasMoreElements()){
					orderType = tokenizer.nextToken();
				}
				
				String value = "";
				
				if (treeName.toLowerCase().equals("name")){
					value = tokenizer.nextToken();
					
					if (orderType.toLowerCase().equals("inorder")){
						nameTree.createinOrderPrint();
						System.out.println(calcPosition(nameTree.inOrderList,value));
					}
					else if (orderType.toLowerCase().equals("preorder")){
						nameTree.createpreOrderPrint();
						System.out.println(calcPosition(nameTree.preOrderList,value));
					}
					else if (orderType.toLowerCase().equals("postorder")){
						nameTree.createpostOrderPrint();
						System.out.println(calcPosition(nameTree.postOrderList,value));
					}
				}
				else if (treeName.toLowerCase().equals("surname")){
					value = tokenizer.nextToken();
					value = tokenizer.nextToken();
					if (orderType.toLowerCase().equals("inorder")){
						surnameTree.createinOrderPrint();
						System.out.println(calcPosition(surnameTree.inOrderList,value));
					}
					else if (orderType.toLowerCase().equals("preorder")){
						surnameTree.createpreOrderPrint();
						System.out.println(calcPosition(surnameTree.preOrderList,value));
					}
					else if (orderType.toLowerCase().equals("postorder")){
						surnameTree.createpostOrderPrint();
						System.out.println(calcPosition(surnameTree.postOrderList,value));
					}
				}
				else if (treeName.toLowerCase().equals("phone")){
					value = tokenizer.nextToken();
					value = tokenizer.nextToken();
					value = tokenizer.nextToken();
					if (orderType.toLowerCase().equals("inorder")){
						phoneTree.createinOrderPrint();
						System.out.println(calcPosition(phoneTree.inOrderList,value));
					}
					else if (orderType.toLowerCase().equals("preorder")){
						phoneTree.createpreOrderPrint();
						System.out.println(calcPosition(phoneTree.preOrderList,value));
					}
					else if (orderType.toLowerCase().equals("postorder")){
						phoneTree.createpostOrderPrint();
						System.out.println(calcPosition(phoneTree.postOrderList,value));
					}
				}
			}
			else{
				System.err.println("Command could not be recognized.");
			}
		}
	}
	
	
	public static int calcPosition(ArrayList<String> list, String searchItem){
		
		return list.indexOf(searchItem)+1;
	}
}
