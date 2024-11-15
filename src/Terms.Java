public class Terms{
    Vocab word; // Vocab object representing the word associated with this term.
    boolean[] docNo; //boolean array to store which documents the word is in.
    int[] freq; //frequency of the word in each document, will be used for ranking, will think about this later.
    public Terms(){
        word = new Vocab();
        docNo = new boolean[500]; //500 is the maximum number of documents we can have, primitive so all values are false by default.
        freq = new int[500]; 
    }
    public Terms(String word, boolean[] docNo, int[] freq){
        this.word = new Vocab(word);
        this.docNo = new boolean[docNo.length];
        this.freq = new int[freq.length];
    }
    public void addDoc(int docNo){
        if(this.docNo[docNo] == false){
            this.docNo[docNo] = true;
        }else{
            freq[docNo]++; 
        }
    }
    public void removeDoc(int docNo){     //could be used for bonus.
    if(this.docNo[docNo] == true){
    if(freq[docNo] > 1){
    freq[docNo]--;}
    else{
    this.docNo[docNo] = false;
    freq[docNo] = 0;
    }
    }
}

    public void setWord(String word){
        this.word = new Vocab(word);
    }
    public Vocab getWord(){
        return word;
    }
    public boolean[] getDocs(){
        boolean[] temp = new boolean[docNo.length]; //to prevent the original array from being modified.
        for(int i = 0; i < docNo.length; i++){
            temp[i] = docNo[i];
        }
        return temp;
    }
    public int[] getFreq(){
         int[] temp = new int[freq.length];
         for(int i = 0; i < freq.length; i++){
             temp[i] = freq[i];
         }
         return temp;
     }
    @Override
    public String toString(){ // to print list of documents the word is in.
        String temp = "";
        for(int i = 0; i < docNo.length; i++){
            if(docNo[i] == true){
                temp += i + " ,";
            }
        }
        temp = temp.substring(0, temp.length() - 1); //to remove the last comma.
        return "[ " + temp+ " ]";
    }
    public String toStringFreq(int docNo){ // to print the frequency of the word in a specific document.
        return "The word " + word + " appears " + freq[docNo] + " times in document " + docNo;
    }


}