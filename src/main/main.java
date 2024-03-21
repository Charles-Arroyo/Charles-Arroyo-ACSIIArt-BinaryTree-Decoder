package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author Charles Arroyo
 *
 */

public class main {
	public static void main(String[] args) {
		// Please enter file name

		Scanner scnr = new Scanner(System.in);

		System.out.println("Please enter a file name to decode:  ");

		String fileName = scnr.nextLine();

		File file = new File(fileName);

		try {
			// Decode file
			String encoding; 
			boolean readTheSecondLine = false;
			BufferedReader br = new BufferedReader(new FileReader(file)); //buffered reader for reading lines

			// Read first line
			String encodingString = br.readLine(); // First Line
			

			String tempString = br.readLine(); // Second Line 

			
				if (tempString.charAt(0) == '^') { // if second line first character == '^'
					encodingString += '\n';
					encodingString += tempString;
					readTheSecondLine = true;
				}
				
			if(readTheSecondLine == true) { // if you read the second line, read third.
				 encoding = br.readLine(); // read third
			} else {
				 encoding = tempString; // assign second to encoding
			}

			br.close();
			MsgTree tree = new MsgTree(encodingString); // Create new tree
			System.out.println("Character code");
			System.out.println("-------------------");
			tree.printCodes(tree, encoding);
			System.out.println("Message: ");
			tree.decode(tree, encoding);
			
			
		
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	

	}
}
