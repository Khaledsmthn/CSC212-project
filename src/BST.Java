public class BST<K extends Comparable<K>,T>{
    public class BSTnode<K extends Comparable<K>, T>{
        public K key;
        public T data;
        public BSTnode<K, T> left, right;

        public BSTnode(K key, T data){ //leaf node
            this.key = key;
            this.data = data;
            left = right = null;
        }
        public BSTnode(K key, T data, BSTnode<K, T> left, BSTnode<K, T> right){ //inner node
            this.key = key;
            this.data = data;
            this.left = left;
            this.right = right;
        }


    }
    private BSTnode<K, T> root;
    private BSTnode<K, T> current;
    public int size; 
    public BST(){
        root = current = null;
        size = 0;
    }   
    public boolean empty(){
        return root == null;
    }
    public boolean full(){
        return false;
    }
    public void findFirst(){
        current = root;
    }
    public void clear(){
        root = current = null;
    }
    public int size(){
        return size;
    }
    public T retrieve(){
        T tmp = null;
        if(current != null){
            tmp = current.data;
        }
        return tmp;
    }
    public boolean update(T val){
        boolean tmp = false;
        if(current != null){
            current.data = val;
            tmp = true;
        }
        return tmp;
    }
    public boolean Findkey(K k)//same as slides, sets current when found and returns true.
    {
        BSTnode<K,T> tmp = root;
        if(empty())
        	return false;
        
        while(tmp != null){
            if(tmp.key.compareTo(k) == 0){
                current = tmp;
                return true;
            }else if(k.compareTo(tmp.key) < 0){
                tmp = tmp.left;
            }else{
                tmp = tmp.right;
            }
        }
        return false;
    }
    public boolean insert(K k, T val) // Findkey usage in this method will be redundant, it only assigns current and not current's parent.
    {
    	if(empty()) {
            root = current = new BSTnode<K, T>(k, val);
            }
        BSTnode<K, T> tmp, tmp2 = null;
        tmp = root; 
        while(tmp != null){
        	if(tmp.key.compareTo(k) < 0){
            	tmp2 = tmp;
                tmp = tmp.left;
            }else if(k.compareTo(tmp.key) > 0){
            	tmp2 = tmp;
                tmp = tmp.right;
            }else{
                return false; //element found, duplicate key is not allowed.
            }
        }
        if(k.compareTo(tmp2.key) < 0){ //tmp2 here is the parent of the new node.
            tmp2.left = new BSTnode<K, T>(k, val);
            tmp = tmp2.left;
        }else{
            tmp2.right = new BSTnode<K, T>(k, val);
            tmp = tmp2.right;
        }
        size++;
        return true;
    }

    public boolean Removekey(K k) {
        root = removeRec(root, k);
        return root != null;
    }

    private BSTnode<K, T> removeRec(BSTnode<K, T> root, K k) {
        if (root == null) {// base case, key not found.
            return root;
        }

        if (k.compareTo(root.key) < 0) {
            root.left = removeRec(root.left, k);
        } else if (k.compareTo(root.key) > 0) {
            root.right = removeRec(root.right, k);
        } else {//key found, three cases.
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            BSTnode<K, T> minNode = findMin(root.right);
            root.key = minNode.key;
            root.data = minNode.data;
            root.right = removeRec(root.right, minNode.key);
        }

        return root;
    }

    private BSTnode<K, T> findMin(BSTnode<K, T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

} 