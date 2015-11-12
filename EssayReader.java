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
		boolean startingQuotes = true;
	    Scanner sc;
	    startingWord = new Word();
	    Map<String,Word> words = new HashMap<>();
	    for (File file : folder.listFiles()) {
	    	//System.out.println("file name :: " + file.getName());
	    	//System.out.println("file path :: " + file.getAbsolutePath());
	        sc = new Scanner(new BufferedReader(new FileReader(file)));

	        Word currWord = startingWord;
		    while(sc.hasNextLine()){
	        	//change later to include punctuation
				String nextLine = sc.nextLine();
				if(nextLine.isEmpty() && !currWord.getWord().equals(" ")){
		        	if(words.get(" ") == null){
        				words.put(" ", new Word(" "));
        			}
    				Word nextWord = words.get(" ");
   	 				currWord.setNextWord(nextWord);
        			currWord = nextWord;
				}
				else{
					String[] line = nextLine.split("\\s+|(?=\\p{Punct})|(?<=\\p{Punct})");
					for(String word : line){
						if(word.equals("\"")){
							if(startingQuotes){
								word+= "1";
							}
							else{
								word+= "2";
							}
							startingQuotes = !startingQuotes;
						}
	        			if(words.get(word) == null){
	        				words.put(word, new Word(word));
	        			}
        				Word nextWord = words.get(word);
	        			currWord.setNextWord(nextWord);
	        			currWord = nextWord;
	        		}
				}
	        }
    	}
    }

	public Word getStartingWord(){
		return startingWord;
	}
}