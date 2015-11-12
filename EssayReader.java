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
	    	System.out.println("file name :: " + file.getName());
	    	System.out.println("file path :: " + file.getAbsolutePath());
	        //sc = new Scanner(new File("sherlock_holmes.txt"));
	        sc = new Scanner(new BufferedReader(new FileReader(file)));

	        Word currWord = startingWord;
		    while(sc.hasNext()){
		        while(sc.hasNextLine()){
		        	//change later to include punctuation
					String[] line = sc.nextLine().split("\\s+|(?=\\p{Punct})|(?<=\\p{Punct})");
					for(int i = 0; i < line.length; i++){
	        			Word nextWord = new Word(line[i]);
	        			//System.out.println(nextWord);
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
		        if(sc.hasNext()){
	        		sc.nextLine();
		        }
		    }
    	}
    	System.out.println("Starting word count after readfiles" + startingWord.getTotalNextWords());
    }

	public Word getStartingWord(){
		return startingWord;
	}
}