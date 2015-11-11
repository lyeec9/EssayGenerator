import java.util.*;
import java.io.*;


 /**
  * Refactor this code to use an API call from MAS in the future, as soon as the key is acquired
  *
  */
class EssayReader{
	private Word startingWord; //Used to keep track of the initial possible starting words
	private String query;
	private File folder;


	public EssayReader(String q, String folderDest){
		query = q;
		folder = new File(folderDest);
	}

	public void readFiles() throws IOException{
	    Scanner sc;
	    startingWord = new Word();
	    for (File file : folder.listFiles()) {
	        sc = new Scanner(file);
	        Word currWord = startingWord;
		    while(sc.hasNext()){
		        while(sc.hasNextLine()){
		        	//change later to include punctuation
					String[] line = sc.nextLine().split("\\w+([.,]\\w+)*|\\S+");
		        	for(int i = 1; i < line.length; i++){
	        			Word nextWord = new Word(line[i]);
	        			currWord.setNextWord(nextWord);
	        			currWord = nextWord;
	        		}
		        }
		        //add paragraph line here to currword, since there is nothing on the line but still words later
		        if(currWord != null){ //Skip over all blank lines at the start of the essay
		        	Word nextWord = new Word(" ");
        			currWord.setNextWord(nextWord);
        			currWord = nextWord;
		        }
	        	sc.nextLine();
		    }
    	}
    }

	public Word getStartingWord(){
		return startingWord;
	}
}