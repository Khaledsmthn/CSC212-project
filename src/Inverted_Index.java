
public class Inverted_Index {

	LinkedList<Terms> InvertIndex;
	
	public Inverted_Index() {
		LinkedList <Terms> InvertIndex = new LinkedList<Terms>() ;
	}
	
	public boolean addNew(int docID, String word) { //adds new term to the index
		if(InvertIndex.empty()) {
			Terms t = new Terms();
			t.setWord(word);
			t.addDoc(docID);
			InvertIndex.insert(t);
			return true;
		}
		else {
			InvertIndex.findFirst();
			while(!InvertIndex.last()) {
				if(InvertIndex.retrieve().word.word.compareTo(word)== 0) {
					Terms t1 =  InvertIndex.retrieve();
					t1.addDoc(docID);
					InvertIndex.update(t1); //update the existing term with new docID
					return false; //because the word already exist
				}
				InvertIndex.findNext();	
			}
			
			if(InvertIndex.retrieve().word.word.compareTo(word)== 0) { //to check on last element after the itreation
				Terms t1 =  InvertIndex.retrieve();
				t1.addDoc(docID);
				InvertIndex.update(t1);
				return false;
		}
			else { //If the word wasn't found in the index
				Terms t2 = new Terms();
				t2.setWord(word);
				t2.addDoc(docID);
				InvertIndex.insert(t2);
			}
			return true;
	
			}
		}

	
	public boolean findWord(String word) { //checks if a word exists in the invertedindex
		if(InvertIndex.empty())
			return false;
		
		InvertIndex.findFirst();
		while(!InvertIndex.last()) {
			if(InvertIndex.retrieve().word.word.compareTo(word)==0) {
				return true;
			}
			InvertIndex.findNext();		
		}
		
		if(InvertIndex.retrieve().word.word.compareTo(word)==0) { // to check on the last document after iteration
			return true;
		}
		//if the word wasn't found	
		return false;
	}

// AND , OR QUERIES 

	public boolean[] AND_Query(String s) {
		String[] AND = s.split("AND");
		boolean[] tmp = new boolean[50];
		
		if(findWord(AND[0].toLowerCase().trim())) // check if the 1st word exist
			tmp = InvertIndex.retrieve().getDocs(); //retrieve its docID
		
		for(int i=1 ; i < AND.length ; i++) { //the outer loop for finding the other words
			boolean[] tmp1 = new boolean[50];
			if(findWord(AND[i].toLowerCase().trim()))// remove spaces and convert it to lowercase
				tmp1 = InvertIndex.retrieve().getDocs();
			
			for(int j = 0; j< 50 ;j++) { //the inner loop for intersection
				tmp[j] = tmp[j]&&tmp1[j]; //perform AND operation between the words
			}
		}
		return tmp;
	}
	
	
	
	public boolean[] OR_Query(String s) {
		String[] OR = s.split("OR");
		boolean[] tmp = new boolean[50];
		
		if(findWord(OR[0].toLowerCase().trim())) // check if the 1st word exist
			tmp = InvertIndex.retrieve().getDocs();
		
		for(int i=1; i< OR.length ; i++) {
			boolean[] tmp2 = new boolean[50];
			if(findWord(OR[i].toLowerCase().trim()))
				tmp2 = InvertIndex.retrieve().getDocs();
			
			for(int j=0 ; j< 50 ; j++)
				tmp[j] = tmp[j]||tmp2[j]; //perform OR operation between the words 
		}
		return tmp;
	}
	
	

	public boolean[] AND_OR_Query(String s) {
		if(!s.contains("OR") && !s.contains("AND")) { //if there is no boolean operators
			boolean[] tmp1 = new boolean[50];
			s = s.toLowerCase().trim(); // remove spaces from the text and make it lower case
			
			if(findWord(s))
				tmp1 = InvertIndex.retrieve().getDocs(); //store the documents that contains the word
			return tmp1;
		}
		else if(s.contains("OR") && s.contains("AND")) {
			String[] AND_OR = s.split("OR"); // split the OR in the query and process the AND first
			boolean[] tmp2 = AND_Query(AND_OR[0]);
			
			for(int i=1; i< AND_OR.length ; i++) { 
				boolean[] tmp3 = AND_Query(AND_OR[i]);
				
				for(int j=0 ;j< 50 ; j++)
					tmp2[j] = tmp2[j]||tmp3[j]; //combine with OR operator 
			}
			return tmp2; 
		}
		else if(s.contains("AND")) //if the query contains only AND
			return AND_Query(s);
		
		return OR_Query(s); //otherwise return OR
	}
 
 

	public void DisplayDocument() {
	if(InvertIndex.empty())
        System.out.println("the Inverted Index is empty");  
	else {
		InvertIndex.findFirst();
		while(!InvertIndex.last()) {
			System.out.println(InvertIndex.retrieve());
			InvertIndex.findNext();
		}
		System.out.println(InvertIndex.retrieve()); //display last document
	}
}

}
