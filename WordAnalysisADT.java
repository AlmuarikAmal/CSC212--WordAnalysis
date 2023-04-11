import java.io.*;
/*   
 *   PROJECT : WordAnalysis 
 *   AUTHOR : Amal Almuarik 
 *     
 */
public class WordAnalysisADT<T>{
    
    int n ;       //total number of words 
    int m ;      //total number of unique words
    int k = 49; // the maximum length word can reach(Based on the english dictionary = 49)

    private LinkedList<WordInformation> arrayOfDifferentLengths[];  //array of pointers
    private WordInformation sortedArray[];    
    
    public WordAnalysisADT(String f){ 
        arrayOfDifferentLengths = new LinkedList[k];
        readFileAndAnalyze(f);
    }
       
    //Operation 0
    private void readFileAndAnalyze(String f){
        
        String Line = ""; 
        int linenb = 0;
        boolean flag = false;
        n = 0 ;    // allwords 
        m = 0 ;   // uniquewords 
        
          //refrences to the linked lists 
           for(int s = 0; s < arrayOfDifferentLengths.length ; s++)
           arrayOfDifferentLengths[s] = new LinkedList<WordInformation>();
         
            try{
                 BufferedReader buf = new BufferedReader(new FileReader(new File(f)));
                 //first line read 
                 Line = buf.readLine();
                 do{ 
                    linenb++;
                    int position = 1 ;
                    Line = Line.toLowerCase();

                    String [] cleanwords = Line.split("[, : ; . ? ! _ \" \n “ ” ]");
                    //"in computer science, a data structure is a collection of data values, the relationships "
                    for( int i = 0 ; i < cleanwords.length ; i++)
                    {    
                        if(cleanwords[i].contains("\\n")){
                            linenb++;
                            position = 1;
                            cleanwords[i] = cleanwords[i].replace("\\n", "");
                        }
                     
                        String word = cleanwords[i].trim();
                        if(word.equalsIgnoreCase("")!= true) 
                        {
                            n++; 
                            WordInformation w = new WordInformation(word , linenb , position);
                            flag = w.add(arrayOfDifferentLengths[word.length()], word , linenb , position);
                            position++;
                            if(!flag)
                            m++;
                         }
                        } 

                    }while((Line = buf.readLine())!=null);
                        
                    Line = buf.readLine();   
                
                    buf.close(); 

                } catch(IOException e){ 
                    System.out.println("In Method M1: Error While Processing The File  : "+e.getMessage());
                }



                sortedArray = new WordInformation[m];       
                int j = 0 ;
                for ( int i = 0 ;  i < arrayOfDifferentLengths.length ; i++) {   
            
                   if(!arrayOfDifferentLengths[i].empty()) {
   
                   arrayOfDifferentLengths[i].findFirst();
   
                   while(!arrayOfDifferentLengths[i].last()){
                           sortedArray[j++] = arrayOfDifferentLengths[i].retrieve();
                           arrayOfDifferentLengths[i].findNext();
                   }
   
                        sortedArray[j++] = arrayOfDifferentLengths[i].retrieve();

                }
               }
               
               mergesort ( 0, m-1);

               
            }        
    //Operation 1
     public int documentLength(){
        return n; 
     }
    //Operation 2
    public int uniqueWords(){
        return m; 
    }
    // Operation 3
    // total number of occurences of a particular word
    public int totalWord(String w){ 
        int count = 0 ;
        if(arrayOfDifferentLengths[w.length()].getSize() > 0){   
           
            arrayOfDifferentLengths[w.length()].findFirst();

            while(!arrayOfDifferentLengths[w.length()].last()){

                if(arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w)== true)
                    count = arrayOfDifferentLengths[w.length()].retrieve().size;

                    arrayOfDifferentLengths[w.length()].findNext();
            }
        if(arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w)== true)
            count = arrayOfDifferentLengths[w.length()].retrieve().size;
       }
        return count;
  }
    // Operation 4
    // total number of words with a particular length 
    public int totalWordsForLength(int l) { 
    
    if(l<= 0 || l >= arrayOfDifferentLengths.length)   //validate
        return 0;
    return arrayOfDifferentLengths[l].getSize();
        
    }
    // Operation 5
    // display unique words sorted by total occurence of each word
    public void displayUniqueWords() { 
        for(int i = 0 ; i < m ; i++)
            System.out.println("( "+sortedArray[i].word + " , " + sortedArray[i].size + " )");
    }
    // Operation 6
    // return list of occurences
    public LinkedList<WordOccurrence> occurrences(String w) { 

        LinkedList<WordOccurrence> tmp = null;

        if(arrayOfDifferentLengths[w.length()].getSize() > 0){ 

            arrayOfDifferentLengths[w.length()].findFirst();

            while (!arrayOfDifferentLengths[w.length()].last()){
                if(arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w)==true)
                    tmp = arrayOfDifferentLengths[w.length()].retrieve().occList;
                    arrayOfDifferentLengths[w.length()].findNext();
            }
            //last
            if(arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w)== true)
                tmp = arrayOfDifferentLengths[w.length()].retrieve().occList;
        }
        return tmp;

    }
    // Operation 7
    // checks if two words are adjacent to each other 
    public boolean checkAdjacent(String w1 , String w2) { 
        //case 1 : 
        if ((arrayOfDifferentLengths[w1.length()].getSize()== 0) || (arrayOfDifferentLengths[w2.length()].getSize() == 0))
            return false;
        //case 2 : search for two words that are equal and adjacent  
        if(w1.equalsIgnoreCase(w2)){
            LinkedList<WordOccurrence> w = occurrences(w1);     
        if(w != null  && !w.empty()){
            if(w.getSize() > 1){
                w.findFirst();
                WordOccurrence pos1 = w.retrieve();
                 for(int i = 1; i < w.getSize() ; i++){
                    w.findNext();
                    //(1,3) --> (1,4)
                    //(1,3) --> (1,4)
                    WordOccurrence pos2 = w.retrieve();
                    if(pos1.LineNo == pos2.LineNo && (Math.abs(pos2.Position - pos1.Position) ==1) )
                        return true;
                        pos1=pos2;   
                }
            }
        }
            return false;
        }

         LinkedList<WordOccurrence> L1 = occurrences(w1);
         LinkedList<WordOccurrence> L2 = occurrences(w2);
        
        if(L1 != null && L2 != null ){
            L1.findFirst();
            L2.findFirst();
        
            while (!L1.last() && !L2.last()){

                //must be in same line , difference = 1
                if(L1.retrieve().LineNo == L2.retrieve().LineNo){

                    //if(L2.retrieve().Position - L1.retrieve().Position) == -1 || L2.retrieve().Position - L1.retrieve().Position) == 1)
                    if(Math.abs(L2.retrieve().Position - L1.retrieve().Position) == 1) 
                        return true;
                    else if ((L2.retrieve().Position -  L1.retrieve().Position) > 1)
                          L1.findNext();
                    else
                        L2.findNext();
                }
                 else if(L1.retrieve().Position > L2.retrieve().Position){
                    L2.findNext();
                 }
                 else{
                     L1.findNext();
                }
            }
            
            while(L1.last() && !L2.last()){
                    if(L1.retrieve().LineNo == L2.retrieve().LineNo) {
                        if (Math.abs(L2.retrieve().Position - L1.retrieve().Position) == 1)
                            return true;
                    }
              L2.findNext();
            }
                    
            while(!L1.last() && L2.last()) {
                    if(L1.retrieve().LineNo == L2.retrieve().LineNo){
                        if(Math.abs(L2.retrieve().Position - L1.retrieve().Position) == 1)
                            return true;
                    }
                L1.findNext();
            }

            if(L1.last() && L2.last()){
                if(L1.retrieve().LineNo == L2.retrieve().LineNo ){
                    if(Math.abs(L2.retrieve().Position - L1.retrieve().Position) == 1)
                        return true;
                }
            }
        }   
        return false;
    }    
         
    private void mergesort(int left , int right ) {
            
            if (left >= right) // base case 
                return;

            //recursive case 
            int middle = (left + right) / 2;     //first half 
            mergesort (left , middle);          //second half     
            mergesort (middle + 1 ,right);     //merge
            merge (left , middle ,right);          
       }

    private void merge(int left , int middle , int right ) {
            WordInformation[] B = new WordInformation[right - left + 1];
            int i = left ;
            int j = middle + 1 ;
            int k = 0;

            while (i <= middle && j <= right) {
            if (sortedArray[i].size >= sortedArray[j].size)
                B[k++] = sortedArray[i++];
            else
                B[k++] = sortedArray[j++];
             }
        
            if ( i > middle )

            while(j <= right )
                B[k++] = sortedArray[j++];
             else
                while(i <= middle )
                    B[k++] = sortedArray[i++];
        
            for (k = 0; k < B.length ; k++)
                sortedArray[k + left] = B[k];
            }
        
    
        





}//end class 
