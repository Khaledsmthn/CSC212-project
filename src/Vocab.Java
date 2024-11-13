//words class, every document's word will be split into a Vocab object for use on indexing and inverted indexing.
public class Vocab{
    public String word;

    public Vocab() {
        word = "";
    }

    public Vocab(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }
    
    
    
}