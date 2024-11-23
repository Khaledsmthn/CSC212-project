
public class Inverted_Index_AVL {

	    AVL<String, Terms> invertedIndexTree;

	    
	    public Inverted_Index_AVL() {
	        invertedIndexTree = new AVL<>();
	    }

	    //number of words
	    public int getSize() {
	        return invertedIndexTree.size();
	    }

	    // Add a word and its document ID
	    public boolean addWord(int documentID, String word) {
	        word = word.toLowerCase();  
	        word = word.trim();        

	        if (invertedIndexTree.empty()) {
	            Terms newTerm = new Terms();
	            newTerm.setWord(word); 
	            newTerm.addDoc(documentID); 
	            invertedIndexTree.insert(word, newTerm); // Add to the tree
	            return true; 
	        } else {
	            // Check if the word is in the tree
	            if (invertedIndexTree.find(word)) {
	                // update the word if its exist
	                Terms existingTerm = invertedIndexTree.retrieve();
	                existingTerm.addDoc(documentID);
	                invertedIndexTree.update(existingTerm);
	                return false; // Word was already in the tree
	            }

	            // creat a new if the word doesn't exist
	            Terms newTerm = new Terms();
	            newTerm.setWord(word);
	            newTerm.addDoc(documentID);
	            invertedIndexTree.insert(word, newTerm);
	            return true; // New word added
	        }
	    }

	    public boolean isWordFound(String word) {
	        word = word.toLowerCase();
	        word = word.trim();
	        return invertedIndexTree.find(word); // Return true or false
	    }

	    public void displayInvertedIndex() {
	        invertedIndexTree.Traverse(); 
	    }

	    public boolean[] processBooleanQuery(String query) {
	        if (!query.contains(" OR ") && !query.contains(" AND ")) {
	            boolean[] result = new boolean[50];
	            query = query.toLowerCase();
	            query = query.trim();

	            if (isWordFound(query)) {
	                result = invertedIndexTree.retrieve().getDocs();
	            }
	            return result;
	        }

	        // Query with OR and AND
	        else if (query.contains(" OR ") && query.contains(" AND ")) {
	            String[] orParts = query.split(" OR ");
	            boolean[] combinedResult = processAndQuery(orParts[0]);

	            for (int i = 1; i < orParts.length; i++) {
	                boolean[] currentResult = processAndQuery(orParts[i]);
	                for (int j = 0; j < 500; j++) {
	                    combinedResult[j] = combinedResult[j] || currentResult[j];
	                }
	            }
	            return combinedResult;
	        }

	        // Query with only AND
	        else if (query.contains(" AND ")) {
	            return processAndQuery(query);
	        }

	        // Query with only OR
	        else {
	            return processOrQuery(query);
	        }
	    }

	    
	    public boolean[] processAndQuery(String query) {
	        String[] andParts = query.split(" AND ");
	        boolean[] combinedResult = new boolean[50];

	        
	        if (isWordFound(andParts[0].toLowerCase().trim())) {
	            combinedResult = invertedIndexTree.retrieve().getDocs();
	        }

	        for (int i = 1; i < andParts.length; i++) {
	            boolean[] currentResult = new boolean[500];
	            if (isWordFound(andParts[i].toLowerCase().trim())) {
	                currentResult = invertedIndexTree.retrieve().getDocs();
	            }

	            for (int j = 0; j < 50; j++) {
	                combinedResult[j] = combinedResult[j] && currentResult[j];
	            }
	        }
	        return combinedResult;
	    }

	    
	    public boolean[] processOrQuery(String query) {
	        String[] orParts = query.split(" OR ");
	        boolean[] combinedResult = new boolean[50];

	        //word 1
	        if (isWordFound(orParts[0].toLowerCase().trim())) {
	            combinedResult = invertedIndexTree.retrieve().getDocs();
	        }

	        for (int i = 1; i < orParts.length; i++) {
	            boolean[] currentResult = new boolean[50];
	            if (isWordFound(orParts[i].toLowerCase().trim())) {
	                currentResult = invertedIndexTree.retrieve().getDocs();
	            }

	            for (int j = 0; j < 50; j++) {
	                combinedResult[j] = combinedResult[j] || currentResult[j];
	            }
	        }
	        return combinedResult;
	    }
	}


