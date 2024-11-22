
public class Inverted_Index {

	LinkedList <Terms> InvertIndex;
	
	public Inverted_Index() {
		 InvertIndex = new LinkedList <Terms>() ;
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
			else { //If the word wasn't found in the index add it
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
	
	public int size(){
        return InvertIndex.size();
    }
	
	
// AND , OR  QUERIES 
	
	public boolean[] booleanQueryAND(String s){
		String[] words = s.split(" AND ");
		boolean[] temp = new boolean[50];
		
		if(this.findWord(words[0].toLowerCase().trim())) //Check if the first word exists
			temp = InvertIndex.retrieve().getDocs();
		
		for(int i=1; i<words.length; i++){
			boolean[] temp2 =  new boolean[50];
			
			if(this.findWord(words[i].toLowerCase().trim()))
				temp2 = InvertIndex.retrieve().getDocs();
			
			for(int j=0; j<temp.length; j++){
				temp[j] = temp[j] && temp2[j];
			}
		}
		return temp;
	}

	public boolean[] booleanQueryOR(String s){
		String[] words = s.split(" OR ");
		boolean[] temp = new boolean [50];
		
		if(this.findWord(words[0].toLowerCase().trim()))
			temp = InvertIndex.retrieve().getDocs();
		
		for(int i=1; i<words.length; i++){
			boolean[] temp2 = new boolean [50];
			
			if(this.findWord(words[i].toLowerCase().trim()))
				temp2 = this.InvertIndex.retrieve().getDocs();
			
			for(int j=0; j<temp.length; j++){
				temp[j] = temp[j] || temp2[j];
			}
		}
		return temp;
	}

	
	
	public boolean[] booleanQuery(String s){
		if(!s.contains("AND") && (!s.contains("OR"))) {
			boolean[] tmp = new boolean[50];
			s = s.toLowerCase().trim();
			
			if(this.findWord(s))
				tmp = InvertIndex.retrieve().getDocs();
			
			return tmp; //return list of documents contains the word if there is no binary operators
		}
		else if(s.contains("AND") && s.contains("OR")) {	
			
			String[] words = s.split("OR"); //split or and process AND first because the priority of AND > OR
			boolean[] tmp1 = booleanQueryAND(words[0]);
			
			for(int i = 1; i < words.length ; i++) {
				boolean[] tmp2 = booleanQueryAND(words[i]);
				
				for(int j=0 ; j < 50 ; j++)
					tmp1[j] = tmp1[j] || tmp2[j]; //combine the result with OR 
			}
			return tmp1;
		}
		else if(s.contains("AND")) {
			return booleanQueryAND(s); //if the query contains AND only 
		}
		else {
		return booleanQueryOR(s); //otherwise
		}
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
