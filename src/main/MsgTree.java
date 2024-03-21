package main;


/**
 * Class that represents a MSG Tree for decoding  
 * @author Charles Arroyp
 *
 */
public class MsgTree {
	
	
	// Hint: The recursive solution is only about 6 lines long.
	// You will need to make sure you access every char in the
	// encodingString using the staticCharIdx variable. Since only characters
	// that are not '^' constitute leaf nodes you will have 2 cases
	// 2 consider whereby if one is true then recursion happens and you make left
	// and right subtrees as needed recursively.

	public MsgTree left;
	public MsgTree right;
	public static int staticCharIndex = 0; // tracks location within the tree string
	public char payloadChar;
	public static StringBuilder string = new StringBuilder();
	public static int index;
	

	/**
	 * Constructor for building a character tree from a string Root->left->right
	 * 
	 * @param encodingString
	 */
	public MsgTree(String encodingString) {
		//Base Case
		if(staticCharIndex > encodingString.length()) {
			return;
		}	
			//Save char 
			char temp = encodingString.charAt(staticCharIndex);
			//Increase index
			staticCharIndex++;
			
			//The two cases
			if(temp != '^') { //if character, assign payload char for this current tree
				payloadChar = temp;
				
			}else {//if you see a carrot, create new trees, with empty 
				this.left = new MsgTree(encodingString);
				this.right = new MsgTree(encodingString);
			}		
	}
	
	/**
	 * Constructor for single node with null children.
	 *  You don't need this for recursion.
	 * 
	 * @param payLoadChar
	 */
	public MsgTree(char payLoadChar) {
		this.payloadChar = payLoadChar; // This sets the payload.
		this.left = null;// set the child to null
		this.right = null;
	}

	/**
	 * Performs a preorder transversal of MSG tree and prints all Character and
	 * their binary codes 
	 * 
	 * @param root
	 * @param code
	 * 
	 * 
	 */
	public static void printCodes(MsgTree root, String encoding) {

		if (root.left == null && root.right == null) {
			System.out.print(root.payloadChar + "  ");
			System.out.println("      " + string);
			return;
		}
		
		string.append("0");
		printCodes(root.left, encoding);
		string.deleteCharAt(string.length() - 1);
		string.append("1");
		printCodes(root.right, encoding);
		string.deleteCharAt(string.length() - 1);

	}

	/**
	 * This prints the decoded message to the console 
	 * // Start at root 
	 * // Repeat until at leaf 
	 * // Scan one bit // 
	 * Go to left child if 0; 
	 * else go to right child 
	 * // Print leaf char
	 * 
	 * @param codes
	 * @param msg
	 */

	public static void decode(MsgTree codes, String message) {
		int index = 0;
		MsgTree rooTree = codes;
		while (index < message.length()) {
			if (codes.left == null && codes.right == null) {
				System.out.print(codes.payloadChar);
				codes = rooTree;
			} else if (message.charAt(index) == '0') {
				codes = codes.left;
				index++;
			} else {
				codes = codes.right;
				index++;
			}
		}
		// Prints the last node
		if (codes.left == null && codes.right == null) {
			System.out.print(codes.payloadChar);
		}
	}

}
