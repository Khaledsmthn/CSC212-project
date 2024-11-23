//import.java.util.NoSuchElementException; //in case we use the additonal methods such as remove_maximum and Search_maximum 

public class AVL<K extends Comparable<K>, T>   {

	public class AVLNode<K extends Comparable<K>, T>{
		
	public K key;
	public T data;
	public AVLNode<K,T> parent ,left , right;
	 int BF; //balance factor
	
	
	public AVLNode() {
		this.key = null;
		this.data =null;
		parent = left = right = null;
		BF =0;
	}
	
	public AVLNode(K key , T data) { //for insert method
		this.key = key;
		this.data =data;
		parent = left = right = null;
		BF =0;
	}
	
	public AVLNode(K key, T data, AVLNode<K,T> p, AVLNode<K,T> l, AVLNode<K,T> r) {
		this.key = key;
		this.data =data;
		p = this.parent;
		left = l; 
		right = r;
		BF =0;
	}

	public T getData() {
		return data;
	}

	public AVLNode<K, T> getLeft() {
		return left;
	}

	public AVLNode<K, T> getRight() {
		return right;
	}

	
	public String toString() {
		return "AVL Node [data=" + data + ", left=" + left + ", right=" + right + "]";
	}

	}
	
	private AVLNode<K,T> root;  
    private AVLNode<K,T>  current;  
    private int size;  
	
	public AVL() {
		root = current = null;
		size =0;
	}
	
	
	public boolean empty() {
		 return root == null;  
	}
	
	
	 public int size() {  
         return size;  
     }
	
	 public void clear()  
     {  
         root = current = null;  
         size = 0;  
     }  
	
	 public T retrieve()  {  
         T val =null;  
         if (current != null)  
             val = current.data;  
         return val;  
     }  

	 public boolean full(){
	        return false;
	    }
	 
	    public void findFirst(){
	        current = root;
	    }
	
	    public void update(T e)   {  
            if (current != null)  
            	current.data = e;  
        }  
	    
	    //check this method, it searches for the key in the AVL and return data if found, else return null
	    private T searchAVL(AVLNode<K,T> node, K key) {  
             if (node == null)  
                 return null;  
             else if (node.key.compareTo(key) ==0)   
             {  
                 current = node;  
                 return node.data;  
             }   
             else if (node.key.compareTo(key) >0)  
                  return searchAVL(node.left, key);  
             else  
                  return searchAVL(node.right, key);  
 }  
 
	    public boolean find(K key) {  
            T val = searchAVL(root, key);  
            if ( val != null)  {
                return true;  
            }
            return false;  
    }  

	    public AVLNode<K,T> leftRotate(AVLNode<K,T> a) {
			// Rotate
			AVLNode<K,T> b = a.right;
			a.right = b.left;
			if (b.left != null) {
				b.left.parent = a; // Update parent of b.left
			}
		
			b.left = a;
		
			// Update parents
			b.parent = a.parent;
			if (a.parent == null) {
				this.root = b; // Update root if a was the root
			} else if (a == a.parent.left) {
				a.parent.left = b; // Update parent's left child
			} else {
				a.parent.right = b; // Update parent's right child
			}
			a.parent = b;
		
			//Update balance factors
			a.BF = Math.max(a.left != null ? a.left.BF : -1, a.right != null ? a.right.BF : -1) + 1;
			b.BF = Math.max(b.right != null ? b.right.BF : -1, a.BF) + 1;
		
			//Return the new root of the rotated subtree
			return b;
		}
		

	    public AVLNode<K, T> rightRotate(AVLNode<K, T> a) {
			//Perform the rotation
			AVLNode<K, T> b = a.left;
			a.left = b.right;
			if (b.right != null) {
				b.right.parent = a; // Update parent of b.right
			}
		
			b.right = a;
		
			//Update parent pointers
			b.parent = a.parent;
			if (a.parent == null) {
				this.root = b; // Update root if a was the root
			} else if (a == a.parent.right) {
				a.parent.right = b; // Update parent's right child
			} else {
				a.parent.left = b; // Update parent's left child
			}
			a.parent = b;
		
			//Update balance factors
			a.BF = Math.max(a.left != null ? a.left.BF : -1, a.right != null ? a.right.BF : -1) + 1;
			b.BF = Math.max(b.left != null ? b.left.BF : -1, a.BF) + 1;
		
			//Return the new root of the rotated subtree
			return b;
		}
		
	  //same as slides
	    public  AVLNode<K,T> lr_Rotation(AVLNode<K,T> a){
	    	a.left = rightRotate(a.left);
	    	return leftRotate(a);
	    }

	  //same as slides
	    public  AVLNode<K,T> rl_Rotation(AVLNode<K,T> a){
	    	a.right = leftRotate(a.right);
	    	return rightRotate(a);
	    }
	    
		//different from slides
	    public boolean insert(K key, T data) {  
            AVLNode<K,T> node = new AVLNode<K,T>(key, data);  
  
            AVLNode<K,T> p = null;  
            AVLNode<K,T> current = root;  
  
            while (current != null) {  
                    p = current;  
                    if (node.key.compareTo(current.key) ==0) {  
                            return false;  
                    }
                    else if (node.key.compareTo(current.key) <0 ) {  
                            current = current.left;  
                    }
                    else {  
                            current = current.right;  
                    }  
            }  
        
            node.parent = p;  
            if (p == null) {  
                    root = node;  
                    current = node;  
            } 
            else if (node.key.compareTo(p.key) < 0 ) {  
                    p.left = node;  
            } 
            else {  
                    p.right = node;  
            }  
            size ++;  
  
            // re-balance the node if necessary (check if we need to do it)
            updateBalance(node); 
            return true;          
        }  
	   
	    public boolean delete(K key) {  
	        K key1 = key;  
	        AVLNode<K,T>  p = root;  
	        AVLNode<K,T>  q = null;  
	  
	        while (p != null) {  
	                if (key1.compareTo(p.key) <0)  {  
	                    q =p;  
	                    p = p.left;  
	                }   
	                
	                else if (key1.compareTo(p.key) >0)  {      
	                    q = p;  
	                    p = p.right;  
	                }  
	                
	                else  {  
	                    if ((p.left != null) && (p.right != null))   {   
	                        
	                        AVLNode<K,T> min = p.right;  
	                        q = p;  
	                        while (min.left != null) {  
	                            q = min;  
	                            min = min.left;  
	                        }  
	                        
	                        p.key = min.key;  
	                        p.data = min.data;  
	                        key1 = min.key;  
	                        p = min;  
	                        
	                    }  
	                    if (p.left != null)   {   
	                        p = p.left;  
	                    }   
	                   
	                    else  {   
	                        p = p.right;  
	                    }  
	                    if (q == null) {   
	                        root = p;  
	                        this.updateBalance(p);  
	                    }   
	                    
	                    else {  
	                        if (key1.compareTo(q.key) <0)  
	                            q.left = p;  
	                        else   
	                            q.right = p;  
	                        this.updateBalance(q);  
	                    }  
	                    size--;  
	                    current = root;  
	                    return true;      
	            }   
	        }   
	        return false;  
	    } 
	    //additional methods
	    private void updateBalance(AVLNode<K,T> node) {  
            if (node.BF < -1 || node.BF > 1) {  
                    rebalance(node);  
                    return;  
            }  

            if (node.parent != null) {  
                    if (node == node.parent.left) {  
                            node.parent.BF -= 1;  
                    }   

                    if (node == node.parent.right) {  
                            node.parent.BF += 1;  
                    }  

                    if (node.parent.BF != 0) {  
                            updateBalance(node.parent);  
                    }  
            }  
    }  
	    private  void rebalance(AVLNode<K,T> node) {  
            if (node.BF > 0) {  
                if (node.right.BF < 0) {  
                        rightRotate(node.right);  
                        leftRotate(node);  
                } else {  
                        leftRotate(node);  
                }  
        } else if (node.BF < 0) {  
                if (node.left.BF > 0) {  
                        leftRotate(node.left);  
                        rightRotate(node);  
                } else {  
                        rightRotate(node);  
                }  
    }  
}  

public void Traverse()  
	        {  
	            if (root != null)  
	                traverseTree(root);  
	        }  
	          
	        private void traverseTree (AVLNode<K,T> node  )  
	        {  
	            if (node == null)  
	                return;  
	            traverseTree( node.left);  
	            System.out.println(node.data);  
	            traverseTree( node.right);  
	              
	        }  
public void TraverseT()  
	        {  
	            if (root != null)  
	                traverseTreeT(root);  
	        }  
	          
	        private void traverseTreeT (AVLNode<K,T> node)  
	        {  
	            if (node == null)  
	                return;  
	            traverseTreeT( node.left );  
	            if (node.getData() instanceof AVL )  
				try {
	            {  
	                System.out.println( "Node key ==== "+ node.key);  
	                ((AVL<?,?>) node.getData()).Traverse();  //word and freq/rank.
	            }  }catch(Exception e) {
	            	System.out.println("incompatible AVL type");
	            }
	            else  
	                System.out.println(node.data);  
	              
	            traverseTreeT( node.right);  
	              
	        }
	        public void PrintData()
	        {
	            if (root != null)
	                PrintData_(root);
	        }
	        
	        private void PrintData_ (AVLNode<K,T> node)
	        {
	            if (node == null)
	                return;
	            PrintData_( node.left );
	           
	           System.out.print(node.key);
	            if (node.getData() instanceof Terms )
	            {
	                System.out.print("   docs: ");
	                boolean [] docs = ((Terms) node.data).getDocs();
	                for ( int i  = 0 ; i < 50 ; i++)
	                    if ( docs[i])
	                        System.out.print( " " + i + " " );
	                System.out.println("");
	            }
	                
	            
	            PrintData_( node.right);
	            
	        }
	  
	    /*
	    public T  remove_maximum() {  
	            if (root == null) throw new NoSuchElementException("Empty AVL");  
	            AVLNode<K,T> n = this.recAVL_Search_maximum(root);  
	            T v = n.data;  
	            this.remove (n.key );  
	            return v;  
	        }  
	  
	            public T Search_maximum() {  
	            if (root == null )   
	                throw new NoSuchElementException("Empty AVL");  
	            return recAVL_Search_maximum(root).data;  
	        }   
	  
	        private AVLNode<K,T>  recAVL_Search_maximum(AVLNode<K,T>  x) {  
	            if (x.right == null)   
	                return x;   
	            else                   
	                return recAVL_Search_maximum(x.right);   
	        }   
	  
	     
	       public void Traverse()  
	        {  
	            if (root != null)  
	                traverseTree(root);  
	        }  
	          
	        private void traverseTree (AVLNode<K,T> node  )  
	        {  
	            if (node == null)  
	                return;  
	            traverseTree( node.left);  
	            System.out.println(node.data);  
	            traverseTree( node.right);  
	              
	        }  
	  
	          
	       //===========================================================================   
	       public void TraverseT()  
	        {  
	            if (root != null)  
	                traverseTreeT(root);  
	        }  
	          
	        private void traverseTreeT (AVLNode<K,T> node)  
	        {  
	            if (node == null)  
	                return;  
	            traverseTreeT( node.left );  
	            if (node.getData() instanceof AVLTree )  
	            {  
	                System.out.println( "Node key ==== "+ node.key);  
	                ((AVLTree <String,Rank>) node.getData()).Traverse();  
	            }  
	            else  
	                System.out.println(node.data);  
	              
	            traverseTreeT( node.right);  
	              
	        }  
	  
	} 
*/
}
