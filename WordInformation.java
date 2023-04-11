
public class WordInformation { 
    
    public String word;
    public LinkedList<WordOccurrence> occList;  
    public int size;  
    
    public  WordInformation() { 
        word ="";
        occList = new LinkedList<WordOccurrence>();
        size = 0;
    }
    public WordInformation(String w , int line , int pos ) { 
        word = w;
        occList = new LinkedList<WordOccurrence>();
        occList.insert(new WordOccurrence(line, pos));
        size = 1;
    }
    public void AddOcc(int line , int pos) {  
        occList.insert(new WordOccurrence(line, pos)); 
        size++;
    }
    
    public boolean add(LinkedList<WordInformation> list, String word , int linenb , int pos ) 
    { 
       
        boolean flag = false;    
               //case 1 : empty (x = 0)
               if(list.empty())
                    list.insert(new WordInformation(word ,linenb, pos));

               //case 2 : (x > 0) nodes  
               else{
                   //traverse
                   list.findFirst();
                   while(!list.last() && !flag)
                   {
                       WordInformation data = list.retrieve();
                       if(data.word.equalsIgnoreCase(word) == true)
                       {
                           data.AddOcc(linenb,(pos));
                           list.update(data);
                           flag = true;
                       }
                       else
                           list.findNext();
                    }
                
                       if(!flag){
                           WordInformation data = list.retrieve();
                           if(data.word.equalsIgnoreCase(word) == true){
                               data.AddOcc(linenb,(pos));
                               list.update(data);
                               flag = true;
                           }
                       } 
                       if(!flag)
                         list.insert(new WordInformation(word, linenb, (pos)));
               
                }
                        return flag;
         }           
    public String toString() {
        String str = "";
        str += "The word: "+word +"|Frequency: "+size+"|Length: "+word.length()+"|Occurence: ";
        str+= "\n";
        occList.findFirst();
        while(!occList.last()) {
        str+= occList.retrieve().toString();
        occList.findNext();
        }
       str+= occList.retrieve().toString();
    
       return str; 
    }

}