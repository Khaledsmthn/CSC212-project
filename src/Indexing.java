
public class Indexing {

	public class Document{
		
	int DocID;		
	LinckedList <Vocab>	index ;
	
	public Document() {
		this.DocID =0;
		index = new LinckedList <Vocab>();
	}
	
	public Document(int id ,String[] words) { //words is a list of words contained in the document
		this.DocID = id;
		index = new LinckedList <Vocab>();
		for(int i =0 ; i < words.length ; i++) {
			index.insert(new Vocab(words[i]));
		}
	}
	
	public void addWord(String word) { //adds new word to the list of words
		index.insert(new Vocab(word));
	}
	
	public boolean foundWord(String word) {
		if(index.empty())
			return false;
		
		index.findFirst();
		for(int i=0 ; i < index.size ;i++) {
			if(index.retrieve().word.compareTo(word) == 0)
				return true;
			index.findNext();
		}
		return false;
	}

	}
	//end of class Document
	Document[] Indexes;

	public Indexing() {
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
}
