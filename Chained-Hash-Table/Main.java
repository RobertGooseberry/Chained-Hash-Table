import java.util.Scanner;

class ChainedHashNode { //Class used for creating and getting elements within the hash table.
	private int element; //Values for each integer in the hash table.
	private int key; //Value that signifies location of it's linked value 'element'.
	private ChainedHashNode link; //Declares ChainedHashNode of name 'link'.
	
	public ChainedHashNode(int element, int key) { //Creates a chained value of both 'element' and 'key'.
		this.element = element;
		this.key = key;
	}
	
	
	public int getElement() { //Gets the designated value of 'element' at current location.
		return this.element;
	}
	public int getKey() { //Gets the designated value of 'key' at current location.
		return this.key;
	}
	
	public void setElement(int element) { //Sets the designated value of 'element' at current location.
		this.element = element;
	}
	public void setKey(int key) { //Sets the designated value of 'key' at current location.
		this.key = key;
	}
	
	public ChainedHashNode getNext() {//Gets the next interger in the hash node.
		return this.link;
	}
	public void setNext(ChainedHashNode link) {//Sets the next integer in the hashn node.
		this.link = link;
	}
}

class HashTable { //Class responsible for functions dealing with the hash table.
	
	static ChainedHashNode[] userHash; //Creates an array with the ChainedHashNode class known as 'userHash'.
	static int tableSize = 10; //Sets the size of the hash table to 10.
	
	public static void ChainedTable() { //Function that initialises the chained hash table.
		userHash = new ChainedHashNode[tableSize];
		
		for (int i = 0; i < tableSize; ++i) { //For loop that sets each node in the table the size of tableSize equal to null.
			userHash[i] = null;
		}
	}
	
	public static void Add(int key) { //Function that lets user add values to the hash table.
		int hashVal = key % tableSize; //Declares hashVal equal to the key value modulo of tableSize, creating a bucket.
		
		if (userHash[hashVal] == null) { //If no value exists at location of hashVal in table userHash...
			userHash[hashVal] = new ChainedHashNode(hashVal, key); //Creates new node at location of hashVal, with int value of 'key'.
			System.out.printf("\nCreated %d key in Bucket %d.\n", key, hashVal);
		}
		else { //If value does exist at location of hashVal in table userHash...
			ChainedHashNode userIns = userHash[hashVal]; //Creates new ChainedHashNode object of userIns equal to userHash[hashVal].
			
			while (userIns.getNext() != null && userIns.getKey() != key) {//While value of userIns.getNext is null AND the that same value is not the same as 'key'...
				userIns = userIns.getNext(); //Set userIns equal to the next value of userIns.
			}
			if (userIns.getKey() == key) { //If the 'key' of userIns = the 'key' of the user input...
				userIns.setElement(hashVal);
				System.out.println("Key already in the table.\n"); //Tell user that key is already in the table.
			}
			else { //If userIns.getKey() does not equal 'key' of the user input...
				userIns.setNext(new ChainedHashNode(hashVal, key));
				System.out.printf("Added %d key to Bucket %d.\n", key, hashVal); //Sets the next value in the node equal to the 'key'.
			}
		}
	}

	public static int Get(int key) { //Function that retrives user value of 'key' in the hash table.
		int hashVal = key % tableSize; //Defines int value of hashVal as the value of 'key' modulo of the tableSize.
		if (userHash[hashVal] == null) { //If there is a null value at the location of hashVal in the userHash object...
			return -1; //Return -1 to the main function.
		}
		else { //If there is not a null value...
			ChainedHashNode userIns = userHash[hashVal]; //Set userIns of ChainedHashNode equal to the position of userHash[hashVal].
			
			while (userIns.getNext() != null && userIns.getKey() != key) { //While the value of userIns.getNext is null and not equal to the user input of 'key'.
				userIns = userIns.getNext(); //Set userIns to the next value in the table.
			}
			if (userIns == null) { //If value of userIns is null, return -1.
				return -1;
			}
			else { //If value of userIns is not null, call the getElement function.
				return userIns.getElement();
			}
		}
	}
	
	public static void Print() { //Function that prints out the hash table.
		int i;
		
		for (i = 0; i < tableSize; ++i) { //For loop that iterates for the size of the table.
			ChainedHashNode userIns = userHash[i]; //Sets object userIns of ChainedHashNode equal to userHash at i.
			System.out.printf("Bucket %d: ", i);
			while (userIns != null) { //While the value of userIns is not null...
				System.out.printf("%d ", userIns.getKey()); //Prints the value of userIns.
				userIns = userIns.getNext(); //Sets userIns equal to the next value in the table.
			}
			System.out.println();
		}
	}
	
	public static void Remove(int key) { //Function for removing values from the hash table.
		int hashVal = key % tableSize; //Defines int value of hashVal as the value of 'key' modulo of the tableSize.
		if (userHash[hashVal] != null) { //Defines int value of hashVal as the value of 'key' modulo of the tableSize.
			ChainedHashNode prevNode = null; //Sets the previous node in the list as null.
			ChainedHashNode userIns = userHash[hashVal]; //Set userIns of ChainedHashNode equal to the position of userHash[hashVal].
			
			while (userIns.getNext() != null && userIns.getKey() != key) { //While the value of userIns.getNext is null and not equal to the user input of 'key'.
				prevNode = userIns; //Sets the previous node in the list as userIns.
				userIns = userIns.getNext(); //Sets userIns equal to the next value in the list.
			}
			
			if (userIns.getKey() == key) { //If the value of userIns.getKey is equal to the user input 'key'.
				if (prevNode == null) { //And if the previous node in the list is null...
					userHash[hashVal] = userIns.getNext(); //Sets the value of userHash at hashVal equal to the vale of the next value in the list.
				}
				else { //If the previos node is not null...
					prevNode.setNext(userIns.getNext()); //Sets the previous node equal to the value of the next in the list.
				}
				System.out.println("Key Removed.\n");
			}
		}
	}

  public void Clear(){ //Function that clears the hash table.
    for (int i = 0; i < tableSize; i++){ //For loop that iterates for the size of tableSize.
        userHash[i] = null; //Makes userHash at i = to null.
    }
  }
}

class Main {
  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);
    HashTable hash = new HashTable(); //Creates a new object 'hash' for the HashTable class.

    hash.ChainedTable(); //Calls the ChainedTable function from the HashTable class.

    char option = ' ';
    int userInput;
    while(option != 'q'){ //While loop that prints a menu until the user hits 'q'.
      System.out.println("\nHash Table Operations\n");
      System.out.println("a. Add ");
      System.out.println("r. Remove");
      System.out.println("g. Get");
      System.out.println("c. Clear");
      System.out.println("q. Quit\n");
      hash.Print(); //Calls the Print function of ChainedTable.

      option = scnr.next().charAt(0); //Takes in user character input as option.     
      switch (option){ //Switch case for option.
        case 'a' : //Insert Case
          System.out.println("Enter a positive integer to add to the list.");//Prompts user to enter number to add to the list.

          userInput = scnr.nextInt(); //Takes user's int input into userInput.
          if (userInput > 0) { //Checks if userInput is a positive number.
            hash.Add(userInput); //Calls the Add function of ChainedTable.
          }
          else { //Tells user that their number was not correct.
            System.out.println("Number must be more than zero.\n");
          }
          break;                          
        case 'r' : //Remove Case        
          System.out.println("Enter a positive integer to add to the list.\n");//Prompts user to enter a number to remove from the list.

          userInput = scnr.nextInt(); //Takes user's int input into userInput.
          if (userInput > 0) { //Chekcs if userInput is a positive number.
            hash.Remove(userInput); //Calls the Remove function of ChainedTable.
          }
          else { //Tells user that their number was not correct.
            System.out.println("Number must be more than zero.\n");
          }
          break;                        
        case 'g' : //Get Case
          System.out.println("Enter a positive integer to find in the list.\n");//Prompts user to enter a number to get from the list.
          userInput = scnr.nextInt(); //Takes user's int input into userInput.
          int getVal = hash.Get(userInput); //Callas the Get function of ChainedTable with parameter userInput and sets it equal to getVal.
            
          if (getVal == -1) { //If a number was not found for userInput.
            System.out.println("Number wasn't found\n");
          }
          else { //If a number was found for userInput, prints location.
            System.out.printf("%d found at Bucket %d\n", userInput, getVal);
          }
          break;                                   
        case 'c' : //Clear Case
          hash.Clear(); //Calls the Clear function of ChainedTable.
          System.out.println("Hash Table Cleared\n");
          break;
        case 'q' : //Quit Case
          System.out.println("Goodbye."); //Ends the program.
          break;
        default : //If no right choice was entered.
          System.out.println("Wrong Entry \n ");
          break;
      }  
    }
  }
}