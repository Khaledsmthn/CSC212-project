package csc212x;

public class Inverted_Index_AVLRanked {
  	class frequency
    {
        int docID = 0;  // id of the document
        int f = 0;     // the frequency of a word in the document
    }

   AVL <Integer, AVL <String,Rank>> AVLrank; 
   frequency [] freqs = new frequency[50]; // array stores frequency
    
    
    
    public Inverted_Index_AVLRanked() {
        AVLrank = new AVL <Integer, AVL <String,Rank>>();
        
    }

    public boolean addNew (int docID, String word)
    {
       if (AVLrank.empty())
       {
           AVL <String,Rank> miniRank= new AVL <String,Rank>();
           miniRank.insert(word, new Rank (word,1));
           
           AVLrank.insert(docID, miniRank);
           return true;
       }
       else
       {
            if (AVLrank.find(docID))
            {
                AVL <String,Rank> miniRank= AVLrank.retrieve();
                if (miniRank.find(word))
                {
                 
                    Rank rank = miniRank.retrieve();
                    rank.add_Rank();
                    miniRank.update(rank);
                    AVLrank.update(miniRank);
                    return false;
                }
               
                miniRank.insert(word, new Rank (word , 1));
                AVLrank.update(miniRank);
                return true;
            }
       
           AVL <String,Rank> miniRank= new AVL <String,Rank>();
           miniRank.insert(word, new Rank (word,1));
           
           AVLrank.insert(docID, miniRank);
           return true;
       }
}

public boolean found(int docID, String word)
{
       if (AVLrank.find(docID) )
          if (AVLrank.retrieve().find(word))
              return true;
       return false;
}

public int getrank (int docID, String word)
{
    int value = 0;
       if (AVLrank.find(docID) )
          if (AVLrank.retrieve().find(word))
              return AVLrank.retrieve().retrieve().getRank();
       return value;
    
}
public void printDocument()
{
        AVLrank.TraverseT();
}

//----
public void TF(String str) // TF stands for term frequencies
{
    str = str.toLowerCase().trim();
    String [] words = str.split(" ");
    
    int index = 0;
    for ( int docID = 0 ; docID < 50 ; docID++ )
    {
        int count = 0 ;
        for ( int j = 0 ;j < words.length ; j++ )
            count += this.getrank(docID, words[j]);
        if (count > 0)
        {
            freqs[index] = new frequency();
            freqs[index].docID = docID;
            freqs[index].f = count;
            index ++;
        }
    }
    
    mergesort(freqs, 0, index-1 );
        
    for ( int x = 0 ; x < index ; x++)
        System.out.println(freqs[x].docID + "\t\t" + freqs[x].f);
}

 //----
public static void mergesort ( frequency [] A , int l , int r ) 
{
if ( l >= r )
    return;
int m = ( l + r ) / 2;
mergesort (A , l , m ) ;          // Sort first half
mergesort (A , m + 1 , r ) ;    // Sort second half
merge (A , l , m , r ) ;            // Merge
}

private static void merge ( frequency [] A , int l , int m , int r ) 
{
frequency [] B = new frequency [ r - l + 1];
int i = l , j = m + 1 , k = 0;

while ( i <= m && j <= r )
{
    if ( A [ i ].f >= A [ j ].f) // .f stands for the frequency of the word  
        B [ k ++] = A [ i ++];
    else
        B [ k ++] = A [ j ++];
}

if ( i > m )
    while ( j <= r )
        B [ k ++] = A [ j ++];
else
    while ( i <= m )
        B [ k ++] = A [ i ++];

for ( k = 0; k < B . length ; k ++)
    A [ k + l ] = B [ k ];
}

}

