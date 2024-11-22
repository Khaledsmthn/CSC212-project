public class InvertedIndexBST{
    BST<String, Terms> InvertedIndexBST;
    int size = 0;
    public InvertedIndexBST(){
        InvertedIndexBST = new BST<String, Terms>();
    }
    public int size(){
        return size;
    }
    public boolean addNew(int docID, String word){
        if(InvertedIndexBST.empty()){
            Terms t = new Terms();
            t.setWord(word);
            t.addDoc(docID);
            InvertedIndexBST.insert(word, t);
            size++;
            return true;
    }else{
        if(InvertedIndexBST.Findkey(word)){
        Terms t1 = InvertedIndexBST.retrieve();
        t1.addDoc(docID);
        InvertedIndexBST.update(t1);
        return false;
}}
Terms t2 = new Terms();
t2.setWord(word);
t2.addDoc(docID);
InvertedIndexBST.insert(word, t2);
size++;
return true;
}

public boolean findword(String word){
    if(InvertedIndexBST.empty())
        return false;
    if(InvertedIndexBST.Findkey(word))
        return true;
    return false;
}
public void print(){
    InvertedIndexBST.Traverse();
}
public boolean[] booleanQuery(String str){
    if(!str.contains(" AND ") && (!str.contains(" OR "))){
        boolean [] tmp = new boolean[100];
        str = str.toLowerCase().trim();
        if(this.findword(str)){
            tmp = InvertedIndexBST.retrieve().getDocs();
        }
        return tmp;
    }else if(str.contains(" AND ") && str.contains(" OR ")){
        String[] words = str.split(" OR ");
        boolean[] tmp = booleanQueryAND(words[0]);
        for(int i=1; i<words.length; i++){
            boolean[] tmp2 = booleanQueryAND(words[i]);
            for(int j=0; j<tmp.length; j++){
                tmp[j] = tmp[j] || tmp2[j];
            }
        }
        return tmp;
}else if(str.contains(" AND ")){
    return booleanQueryAND(str);
}else{
    return booleanQueryOR(str);
}}

public boolean [] booleanQueryAND(String str){
    String[] words = str.split(" AND ");
    boolean[] tmp = new boolean[100];
    if(this.findword(words[0].toLowerCase().trim())){
        tmp = InvertedIndexBST.retrieve().getDocs();
    }
    for(int i=1; i<words.length; i++){
        boolean[] tmp2 = new boolean[100];
        if(this.findword(words[i].toLowerCase().trim())){
            tmp2 = InvertedIndexBST.retrieve().getDocs();
        }
        for(int j=0; j<tmp.length; j++){
            tmp[j] = tmp[j] && tmp2[j];
        }
    }
    return tmp;

}
public boolean [] booleanQueryOR(String str){
    String[] words = str.split(" OR ");
    boolean[] tmp = new boolean[100];
    if(this.findword(words[0].toLowerCase().trim())){
        tmp = InvertedIndexBST.retrieve().getDocs();
    }
    for(int i=1; i<words.length; i++){
        boolean[] tmp2 = new boolean[100];
        if(this.findword(words[i].toLowerCase().trim())){
            tmp2 = InvertedIndexBST.retrieve().getDocs();
        }
        for(int j=0; j<tmp.length; j++){
            tmp[j] = tmp[j] || tmp2[j];
        }
    }
    return tmp;
}
}



