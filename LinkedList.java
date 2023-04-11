public class LinkedList<T>{
   
    private Node<T> Head;
    private Node<T> current;
    private int size;
    
    public LinkedList() { 
    Head = current = null;
    size = 0;
    }
    public boolean empty(){ 
    return Head == null;
}
    public boolean full(){ 
    return false;
}
    public boolean last(){ 
    return current.next == null; 
}
    public void update(T dat ){ 
         current.data = dat;
}
    public T retrieve () { 
    return current.data;
}
    public void findNext() { 
    current = current.next;
}
    public void findFirst() { 
    current = Head;
}
    public void insert(T val) { 
    Node<T> temp;
    Node<T> newNode = new Node<T>(val);
    if(Head == null) 
         current = Head = newNode;
    //1->2->4->5
    else{ 
        temp = current.next;
        current.next = newNode;
        current = current.next;
        current.next = temp;
    }
    size++; 
}
    public void remove() { 
   
    if(current == Head){
        Head = Head.next;   
    }
    else{ 
        Node<T> temp = Head;
        while(temp.next!=current)
            temp = temp.next;
            temp.next = current.next;
    }
    if(current.next == null)
        current = Head;
    else
        current = current.next;
    size--;
}
    public void print() { 
  
    Node<T> temp = Head;
    while(temp!= null){
        System.out.print(temp.data+" ");
        temp = temp.next;
    }
    System.out.println("");
}
    public int getSize() { 
    return size;
    }
    public void  setSize(int size ) { 
    this.size = size;
}

}
