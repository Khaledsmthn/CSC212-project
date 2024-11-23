import java.io.File;
import java.util.Scanner;
public class SearchEngine{
    int tokens = 0;
    int vocab = 0;
    Index index;
    IndexRanked indexRanked;
    Inverted_Index invertedIndex;
    Inverted_Index_Ranked invertedIndexRanked;
    InvertedIndexBST invertedIndexBST;
    rankedInvertedIndexBST InvertedIndexBSTRanked;
    Inverted_Index_AVL invertedIndexAVL;
    Inverted_Index_AVLRanked invertedIndexAVLRanked;

    public SearchEngine(){
        index = new Index();
        indexRanked = new IndexRanked();
        invertedIndex = new Inverted_Index();
        invertedIndexRanked = new Inverted_Index_Ranked();
        invertedIndexBST = new InvertedIndexBST();
        InvertedIndexBSTRanked = new rankedInvertedIndexBST();
        invertedIndexAVL = new Inverted_Index_AVL();
        invertedIndexAVLRanked = new Inverted_Index_AVLRanked();
    }
    //Read documents from a CSV file. 
    //Split the text into a list of words based on whitespace. 
    //Convert all text to lowercase 
    // Preprocess the text by removing stop-words (e.g., "the," "is," "and"). The list of 
    // stop-words will be given to you. 
    // Remove all punctuations and non-alphanumerical characters 
    // Proceed to build the index
    public void LoadData(String stopFile, String fileName){
        try{
            File stopWords = new File(stopFile);
            Scanner read = new Scanner(stopWords).useDelimiter("\\Z");
            String stopList = read.next(); 
            stopList = stopList.replaceAll("\n", " ");

            File documents = new File(fileName);
            Scanner readDoc = new Scanner(documents);
            String line = readDoc.nextLine(); //skip the first line
            for(int i=0; i<50; i++){
                line = readDoc.nextLine().toLowerCase();
                int position = line.indexOf(","); //find the position of the first comma.
                int docID = Integer.parseInt(line.substring(0, position)); //get the document ID
                String text = line.substring(position+1, line.length() - position).trim(); //get the text
                text = text.substring(0, text.length()-1); //remove the last comma
                text = text.toLowerCase();
                text = text.replaceAll("[\']", " "); //remove apostrophes
                text = text.replaceAll("[^a-zA-Z0-9]", " ").trim(); //remove special characters
                String[] words = text.split(" "); //split the text into words
                for(int j=0; j<words.length; j++){
                    String word = words[j].trim(); //remove leading and trailing whitespaces
                    if(word.compareToIgnoreCase("") != 0){
                        tokens++; }
                        if(!stopList.contains(word + " ")){
                            this.index.addDocument(docID, word);
                            this.indexRanked.addDocument(docID, word);
                            this.invertedIndex.addNew(docID, word);
                            this.invertedIndexRanked.addNew(docID, word);
                            this.invertedIndexBST.addNew(docID, word);
                            this.InvertedIndexBSTRanked.insert(docID, word);
                            this.invertedIndexAVL.addWord(docID, word);
                            this.invertedIndexAVLRanked.addNew(docID, word);

                        }
                }

                }
                vocab = invertedIndexAVL.getSize(); //get the size of the vocabulary
                System.out.println("Total number of tokens: " + tokens);
                System.out.println("Total number of unique words: " + vocab);
                read.close();
                readDoc.close();
            } catch (Exception e){
                System.out.println(e.getMessage()); //print the error message
            }

        }
    public boolean[] BooleanRetrieval(String query, int type){
        boolean[] result = new boolean[50];
        switch(type){
            case 1: //Index
                result = index.booleanQuery(query);
                break;
            case 2: //Inverted Index
                result = invertedIndex.booleanQuery(query);
                break;
            case 3: //Inverted Index BST
                result = invertedIndexBST.booleanQuery(query);
                break;
            case 4: //Inverted Index AVL
                result = invertedIndexAVL.processBooleanQuery(query);
                break;
            default:
            System.out.println("Invalid type");
            break;            
        }
        return result;
    }     
    public void rankedIndex(String query){
        indexRanked.TF(query);
    } 
    public void rankedInvertedIndex(String query){
        invertedIndexRanked.TF(query);
    }
    public void rankedInvertedIndexBST(String query){
        InvertedIndexBSTRanked.TermFrequency(query);
    }
    public void rankedInvertedIndexAVL(String query){
        invertedIndexAVLRanked.TF(query);
    }
    public boolean[] TermRetrieval(String query, int type){
        boolean[] result = new boolean[50];
        switch(type){
            case 1: //Index
                result = index.getDocs(query);
                break;
            case 2: //Inverted Index
                result = invertedIndex.InvertIndex.retrieve().getDocs();
                break;
            case 3: //Inverted Index BST
                result = invertedIndexBST.InvertedIndexBST.retrieve().getDocs();
                break;
            case 4: //Inverted Index AVL
                result = invertedIndexAVL.invertedIndexTree.retrieve().getDocs();
                break;
        }
        return result;
    }
    public void AllDocuments(){
        for(int i=0; i<50; i++){
            int size = index.Indexes[i].index.size;
            System.out.println("Document " + i + " contains " + size + " words");
        }
    }
    public void AllTokens(){
        invertedIndexBST.InvertedIndexBST.PrintData();
        invertedIndexAVL.invertedIndexTree.PrintData();
    }
}