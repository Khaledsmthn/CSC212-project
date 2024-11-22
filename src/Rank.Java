public class Rank {
    Vocab word;
    int rank ;

    public Rank() {
        rank = 0;
        word = new Vocab("");
    }

    public Rank(String word, int rank) {
        this.word = new Vocab(word);
        this.rank = rank ;
    }
    
    public int add_Rank ()
    {
        return ++rank;
    }

    public void setVocabulary(Vocab word)
    {
        this. word = word; 
    }
    
    public Vocab getVocabulary()
    {
         return word;
    }
    
    public int getRank ()
    {
        return this.rank;
    }
    
    @Override
    public String toString() {
        return "(" + word + ", " + rank + ")" ;
    }
    
    
}