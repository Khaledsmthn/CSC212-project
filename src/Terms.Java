public class Terms{
    Vocab word; // Vocab object representing the word associated with this term.
    boolean[] docNo; //boolean array to store which documents the word is in.
    public Terms(){
        word = new Vocab();
        docNo = new boolean[50]; //500 is the maximum number of documents we can have, primitive so all values are false by default.
    }
    public Terms(String word, boolean[] docNo) {
    	this.word = new Vocab(word);
    	this.docNo = new boolean[docNo.length];
    }
    public void addDoc(int docNo){
        if(this.docNo[docNo] == false){
            this.docNo[docNo] = true;
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
  
    @Override
    public String toString(){ // to print list of documents the word is in.
        String temp = "";
        for(int i = 0, j=0; i < docNo.length; i++){
            if(j==0 && docNo[i] == true){
                temp += i + " " + String.valueOf(i);
                j++;
            }else if(docNo[i] == true) {
            	temp += ", " + String.valueOf(i);
            	j++;
            }
        }
        return this.word + "[" + temp+ ']' ;
    }
    


}