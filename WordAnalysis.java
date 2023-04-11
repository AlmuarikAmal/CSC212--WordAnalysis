import java.util.*;
public class WordAnalysis {
    public static void main(String[] args) throws Exception {
        
        Scanner input = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("                             The Word Analysis Tool                                 ");
        System.out.println("------------------------------------------------------------------------------------");
        
        System.out.println("Please , Enter the text file name followed by (.txt) : ");
        String file  = input.next();
        WordAnalysisADT tool = new WordAnalysisADT(file);
        //OP1
        System.out.print("The output of operation (1) would be: ");
        System.out.println(tool.documentLength());
        System.out.println("------------------------------------------------------------------------------------");
        //OP2 
        System.out.print("The output of operation (2) would be: ");
        System.out.println(tool.uniqueWords());
        System.out.println("-----------------------------------------------------------------------------------");
        //OP3
        System.out.print("The output of operation (3) for the word ‘the’ would be: ");
        System.out.println(tool.totalWord("the"));
        System.out.print("The output of operation (3) for the word ‘data’ would be: ");
        System.out.println(tool.totalWord("data"));
        System.out.println("-----------------------------------------------------------------------------------");
        //OP4
        System.out.println("The output of operation (4) for word length 1 would be: " );
        System.out.println(tool.totalWordsForLength(1));
        //System.out.println("The output of operation (4) for word length 4 would be: " );
        //System.out.println(tool.totalWordsForLength(4));
        System.out.println("-----------------------------------------------------------------------------------");
        //OP5
        System.out.println("The output of operation (5) would be: ");
        tool.displayUniqueWords();
        System.out.println("");
        System.out.println("-----------------------------------------------------------------------------------");
        //OP6
        System.out.print("The output of operation (6) for the word ‘data’ would be: " );
        LinkedList <WordOccurrence> L = tool.occurrences("data");
        PrintList(L);
        System.out.println("");
        System.out.println("-----------------------------------------------------------------------------------");
        //OP7
        System.out.println("The output of operation (7) would be: ");
        System.out.print("The output of operation (7) for the two words ‘A’ and ‘Graphs’ would be " ); 
        System.out.println(tool.checkAdjacent("A", "Graphs"));
        System.out.print("The output of operation (7) for the two words ‘to and ‘up’ would be ");
        System.out.println(tool.checkAdjacent("to", "up"));
        /*System.out.print("The output of operation (7) for the two words ‘In’ and ‘computer’ would be ");
        System.out.println(tool.checkAdjacent("In", "computer"));
        System.out.print("The output of operation (7) for the two words ‘computer’ and ‘collection’ would be "); 
        System.out.println(tool.checkAdjacent("computer", "collection"));
        System.out.println("-----------------------------------------------------------------------------------");*/

    }

        public static void PrintList(LinkedList<WordOccurrence> L) {
        if(!L.empty()){
            L.findFirst();
        while (!L.last())
        {
            System.out.print(L.retrieve().toString() + "  ");
            L.findNext();
        }
        System.out.print(L.retrieve().toString() + "  ");
        }
    }

    
}
    