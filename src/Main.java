import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static SearchEngine searchEngine = new SearchEngine();

    public static void printBoolean(boolean[] result){
        Terms t = new Terms("", result);
        System.out.println(t);
    }
    public static void TermRetrieval(){
        int type; 
        System.out.println("--------------------\n");
        System.out.println("1. Index");
        System.out.println("2. Inverted Index");
        System.out.println("3. Inverted Index BST");
        System.out.println("4. Inverted Index AVL");
        System.out.println("Enter the type of the search engine: ");
        type = in.nextInt();
        System.out.println("Enter the query: ");
        String query = in.nextLine();
        query = in.nextLine(); // to consume the newline character
        System.out.print("--------------------\n");
        printBoolean(searchEngine.TermRetrieval(query.trim().toLowerCase(), type));
        System.out.print("--------------------\n");
    }
    public static void BooleanRetrievalmenu(){
        int type; 
        System.out.println("--------------------\n");
        System.out.println("1. Index");
        System.out.println("2. Inverted Index");
        System.out.println("3. Inverted Index BST");
        System.out.println("4. Inverted Index AVL");  
        System.out.println("Enter the type of the search engine: ");
        type = in.nextInt();
        System.out.println("Enter the boolean query: ");
        String query = in.nextLine();
        query = in.nextLine(); // to consume the newline character
        System.out.println("Query: " + query); 
        printBoolean(searchEngine.BooleanRetrieval(query.trim().toUpperCase(), type));
        System.out.println("\n");
        System.out.print("--------------------\n");
    }
    public static void RankedRetrievalmenu(){
        int type;
        System.out.println("--------------------\n");
        System.out.println("1. Index");
        System.out.println("2. Inverted Index");
        System.out.println("3. Inverted Index BST");
        System.out.println("4. Inverted Index AVL");  
        System.out.println("Enter the type of the search engine: ");
        type = in.nextInt();
        System.out.println("Enter the query: ");
        String query = in.nextLine();
        query = in.nextLine(); // to consume the newline character
        System.out.println("Query: " + query);
        switch(type){
            case 1:
                searchEngine.rankedIndex(query);
                break;
            case 2:
                searchEngine.rankedInvertedIndex(query);
                break;
            case 3:
                searchEngine.rankedInvertedIndexBST(query);
                break;
            case 4:
                searchEngine.rankedInvertedIndexAVL(query);
                break;
        }
        System.out.println("\n");
        System.out.print("--------------------\n");
    }    
    public static void IndexedDocuments(){
        System.out.println("--------------------\n");
        searchEngine.AllDocuments();
        System.out.println("--------------------\n");
    }
    public static void IndexedTokens(){
        System.out.println("--------------------\n");
        System.out.println("Tokens: ");
        searchEngine.AllTokens();
        System.out.println("--------------------\n");
    }
    public static void main(String[] args){
        searchEngine.LoadData("data/stop.txt", "data/dataset.csv");
        int choice;
        System.out.println("Welcome to the xyz Search Engine!");
        do{
        System.out.println("What would you like to do?");
        System.out.println("1. Term Retrieval. ");
        System.out.println("2. Boolean Retrieval. ");
        System.out.println("3. Ranked Retrieval.");
        System.out.println("4. Indexed Documents.");
        System.out.println("5. Indexed Tokens.");
        System.out.println("6. Exist.");
        
        System.out.print("enter choice: ");
        choice = in.nextInt();
        
        switch(choice){
            case 1: //Term Retrieval
                TermRetrieval();
                break;
            case 2: //Boolean Retrieval
                BooleanRetrievalmenu();
                break;
            case 3: //Ranked Retrieval
                RankedRetrievalmenu();
                break;
            case 4: //Indexed Documents
                IndexedDocuments();
                break;
            case 5: //Indexed Tokens
                IndexedTokens();
                break;
            case 6: //Exit
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid choice, please choose again.");
                break;                        
        }
        }while(choice != 6);
        


    }
    
}
