package project;

public class IndexRanked {
	
	public class Frequency{
		
		int DocID = 0;
		int freq =0; //  a frequency score
	}

	public class Document{
		int docID;		
		LinckedList <String> index ;
		
		public Document() {
			docID = 0 ;
			index = new LinckedList<String>();
		}
		
		public void addWord(String word) { 
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
	
		Document[] Indexes;
		Frequency[] Freqs;
		
		public IndexRanked() {
			Indexes = new Document[50];
			Freqs  = new Frequency[50]; 
			for(int i=0; i< Indexes.length ; i++) {
				Indexes[i] = new Document();
				Indexes[i].docID = i;
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
				 System.out.print(Indexes[docID].index.retrieve() + "");
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
		
		// term frequency to rank documents based on the frequency of query terms within them
		public void TF(String s) {
			s = s.toLowerCase().trim(); //remove whitespace 
			String[] terms = s.split(" "); // split words
			Freqs = new Frequency[50];
			
			for(int i=0; i< Freqs.length ; i++) {
				Freqs[i] = new Frequency();
				Freqs[i].DocID = i;
				Freqs[i].freq = 0;
			}
			for(int j=0 ; j<50 ;j++) { // Iterates through all 50 documents in the index.
				
				for(int k=0 ; k< terms.length ; k++) { // Iterates through each word in the query
					
					Indexes[j].index.findFirst();
					int count = 0; //word counter
					
					for(int n = 0; n < Indexes[j].index.size; n++) { //Iterates through the indexed words of the current document to count occurrences of the query word
						
						if(Indexes[j].index.retrieve().compareTo(terms[k])==0)
							count++;
						Indexes[j].index.findNext();
					}
					if(count > 0)
						Freqs[j].freq += count; //document score is incremented
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
