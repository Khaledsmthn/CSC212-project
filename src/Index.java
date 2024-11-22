
//class index is a class that contains a list of documents and each document contains a list of words, linked list in a linked list.
public class Index {

	public class Document{
		
	int DocID;		
	LinkedList <String>	index ;
	
	public Document() {
		this.DocID =0;
		index = new LinkedList <String>();
	}
	
	public void addWord(String word) { //adds new word to the list of words
		index.insert(word);
	}
	
	public boolean foundWord(String word) {
		if(index.empty())
			return false;
		
		index.findFirst();
		for(int i=0 ; i < index.size ;i++) {
			if(index.retrieve().compareTo(word) == 0)
				return true;
			index.findNext();
		}
		return false;
	}

	}
	
	//end of class Document
	Document[] Indexes;

	public Index() {
		Indexes = new Document[150]; //check the size later
		
		for(int i=0 ; i< Indexes.length; i++) {
			Indexes[i] = new Document();
			Indexes[i].DocID = i;
		}
	}
	
	
	public void addAllDocuments(int docID , String[] word) { 
		for(int i=0 ; i< Indexes.length ; i++) {
			Indexes[docID].addWord(word[i]);;
		}
	}
	
	
	public void addDocument(int docID , String word) {
		Indexes[docID].addWord(word);
	}
	 
	public void DisplayDocuments(int docID) {
		if(Indexes[docID].index.empty())
            System.out.println("the Document is Empty");  
	
	else {
		Indexes[docID].index.findFirst();
		for(int i=0; i<Indexes[docID].index.size; i++) {
			 System.out.println(Indexes[docID].index.retrieve() + "");
			 Indexes[docID].index.findNext();
		}
		}
	
	}
	public boolean[] getDocs(String word) {
		boolean[] temp = new boolean[Indexes.length];
		for(int i=0 ; i< Indexes.length ; i++) {
			temp[i] = Indexes[i].foundWord(word);
		}
		return temp;
	}
	//boolean query function that takes a string (document data), splits document based on "AND" then checks for the given words in query in which documents they eixst in.
	public boolean[] booleanQueryAND(String str){
		String[] words = str.split(" AND ");
		boolean[] temp = getDocs(words[0].toLowerCase().trim());
		for(int i=1; i<words.length; i++){
			boolean[] temp2 = getDocs(words[i].toLowerCase().trim());
			for(int j=0; j<temp.length; j++){
				temp[j] = temp[j] && temp2[j];
			}
		}
		return temp;
	}
	//boolean query function that takes a string (document data), splits document based on "OR" then checks for the given words in query in which documents they eixst in.
	public boolean[] booleanQueryOR(String str){
		String[] words = str.split(" OR ");
		boolean[] temp = getDocs(words[0].toLowerCase().trim());
		for(int i=1; i<words.length; i++){
			boolean[] temp2 = getDocs(words[i].toLowerCase().trim());
			for(int j=0; j<temp.length; j++){
				temp[j] = temp[j] || temp2[j];
			}
		}
		return temp;
	}
	//boolean query function that takes a string (document data), splits document based on "NOT" then checks for the given words in query in which documents they eixst in.
	public boolean[] booleanQueryNOT(String str){
		String[] words = str.split(" NOT ");
		boolean[] temp = getDocs(words[0].toLowerCase().trim());
		boolean[] temp2 = getDocs(words[1].toLowerCase().trim());
		for(int j=0; j<temp.length; j++){
			temp[j] = temp[j] && !temp2[j];
		}
		return temp;
	}
	//Boolean query function handler that takes a complex or simple query string and calls the appropriate query function based on the query type with respect to priority (ie. AND > OR)
	public boolean[] booleanQuery(String str){
		if(str.contains(" AND ") && str.contains(" OR ")){
			String[] words = str.split(" OR ");
			boolean[] temp = booleanQueryAND(words[0]);
			for(int i=1; i<words.length; i++){
				boolean[] temp2 = booleanQueryAND(words[i]);
				for(int j=0; j<temp.length; j++){
					temp[j] = temp[j] || temp2[j];
				}
			}
			return temp;
		}else if(str.contains(" OR ")){
			return booleanQueryOR(str);
	}else if(str.contains(" AND ")){
			return booleanQueryAND(str);
	}else{ //contains one word not in a query
			return getDocs(str);

	}
}
 public boolean[] NOTquery(String str){ //handles NOT queries when alone, cannot handle complex queries, there could be a workaround by storing the word after the NOT, deleting the NOT from the query with the word, running the normal query function, then running the NOT query function on the result.
	 if(str.contains(" NOT ")){
		 return booleanQueryNOT(str);
	 }else{
		 return getDocs(str);
	 }
 }
 //complex query handler with not, takes string, stores the word after the NOT, deletes the NOT from the query with the word, runs the normal query function, then runs the NOT query function on the result.
 public boolean[] NOTqueryComplex(String str){
	 if(str.contains(" NOT ")){
		 String[] words = str.split(" NOT ");
		 String word = words[1];
		 String query = words[0];
		 boolean[] temp = booleanQuery(query);
		 boolean[] temp2 = booleanQueryNOT(word);
		 for(int i=0; i<temp.length; i++){
			 temp[i] = temp[i] && !temp2[i];
		 }
		 return temp;
	 }else{
		 return booleanQuery(str);
	 }
 }
}