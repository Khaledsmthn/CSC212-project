
public class Inverted_Index_Ranked {
	
	public class Frequency{
		int DocID = 0;
		int freq =0; //  a frequency score
	}
	
	LinkedList<TermRank> InvertedIndex;
	Frequency[] Freqs;

	public Inverted_Index_Ranked() {
		InvertedIndex = new LinkedList<TermRank>();
		Freqs = new Frequency[50];
	}
	
	public int size() {
		return InvertedIndex.size;
	}

	
	public boolean addNew(int docID, String word) { //adds new term to the index
		
		if(InvertedIndex.empty()) {
			
			TermRank t = new TermRank();
			t.setVocabulary(new Vocab(word)); 
			t.add_docID(docID);
			InvertedIndex.insert(t);
			return true;
		}
		else {
			InvertedIndex.findFirst();
			
			while(!InvertedIndex.last()) {
				
				if(InvertedIndex.retrieve().word.word.compareTo(word)== 0) {
					
					TermRank t1 =  InvertedIndex.retrieve();
					t1.add_docID(docID);
					InvertedIndex.update(t1); //update the existing term with new docID
					return false; //because the word already exist
				}
				InvertedIndex.findNext();	
			}
			
			if(InvertedIndex.retrieve().word.word.compareTo(word)== 0) { //to check on last element after the itreation
				
				TermRank t1 =  InvertedIndex.retrieve();
				t1.add_docID(docID);
				InvertedIndex.update(t1);
				return false;
		}
			else { //If the word wasn't found in the index add it
				TermRank t2 = new TermRank();
				t2.setVocabulary(new Vocab(word));
				t2.add_docID(docID);
				InvertedIndex.insert(t2);
			}
			return true;
	
			}
		}


	public boolean findWord(String word) { //checks if a word exists in the invertedindex
		if(InvertedIndex.empty())
			return false;
		
		InvertedIndex.findFirst();
		
		for(int i = 0; i<InvertedIndex.size;i++) {
			if(InvertedIndex.retrieve().word.word.compareTo(word) == 0) {
				return true;
			}
			InvertedIndex.findNext();
		}
		return false;
	}


	public void DisplayDocument() {
		if(InvertedIndex.empty())
	        System.out.println("the Inverted Index is empty");  
		else {
			this.InvertedIndex.findFirst();
			while(!this.InvertedIndex.last()) {
				System.out.println(InvertedIndex.retrieve());
				this.InvertedIndex.findNext();
			}
			System.out.println(InvertedIndex.retrieve()); //display last document
		}
	}

	
	

	// term frequency to rank documents based on the frequency of query terms within them (Check it again later)
	public void TF(String s) {
		s = s.toLowerCase().trim(); //remove whitespace 
		String[] terms = s.split(" "); // split words
		Freqs = new Frequency[50];
		
		for(int i=0; i< 50 ; i++) {
			Freqs[i] = new Frequency();
			Freqs[i].DocID = i;
			Freqs[i].freq = 0;
		}
		for(int i=0 ; i< terms.length ;i++) { 
			
			if(findWord(terms[i])) {
				int[] Docs = InvertedIndex.retrieve().getDocs(); //the frequency of the word in the document(S)( i used the getfreq from terms class)
				
				for(int j=0 ; j < Docs.length; j++) {
					
					if(Docs[j] != 0) {
						
						int Index = j;
						Freqs[Index].DocID = Index;
						Freqs[Index].freq += Docs[j];
					}
				}
			}
		}
		//sorting the result
		mergeSort(Freqs , 0 , Freqs.length-1);
        System.out.println("\nDoc_IDt\tScore");
        
        for(int i=0 ; Freqs[i].freq != 0 ; i++) {
          System.out.println(Freqs[i].DocID + "\t\t" + Freqs[i].freq);

        }
		} 	
	
	
	
// we will use merge sort to sort the documents by their scores	
	
	public static void mergeSort(Frequency[] f , int l , int r){ 
	
	if(l >= r)
	return ;
	
	int m = (l + r)/2;
	
	mergeSort(f , l , m);
	mergeSort(f, m+1 ,r);
	merge (f, l , m , r);
	
	}		
	
	public static void merge(Frequency[] f , int l , int m , int r) {
		
	Frequency[] v = new Frequency[r - l + 1];	
	int i = l , j = m+1 ,k = 0;	
	
	while(i <= m && j <= r) {
		if( f[i].freq >= f[j].freq)
			v[k++] = f[i++];
		else
			v[k++] = f[j++];
	}
		if(i > m)
			while( j <= r)
				v[k++] = f[j++];
		else
			while(i <= m)
				v[k++] = f[i++];
		
		for(k=0; k < v.length ; k++)
			f[k+1] = v[k];
	
	}
}
