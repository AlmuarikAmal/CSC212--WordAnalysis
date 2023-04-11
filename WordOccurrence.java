public class WordOccurrence{ 
   
    public int LineNo;     //line number 
    public int Position;  //specific position

    public WordOccurrence (){ 
        LineNo = 0;
        Position = 0;
    }
    public WordOccurrence(int line , int pos){ 
        LineNo = line;
        Position = pos;
    }
    
    public String toString(){   
        return "("+LineNo+","+Position+")";      // to display line number , the position 
    }
}
