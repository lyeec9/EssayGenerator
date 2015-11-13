import java.util.*;
import java.lang.Math.*;
import java.io.*;

class EssayGenerator{
	
	public static void main(String[] args){
		new EssayGenerator();
	}

	public EssayGenerator()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("What would you like to write about?");
		String query = in.nextLine();
		System.out.println("How many words would you like the essay to be?");
		int length = Integer.parseInt(in.nextLine());
		
		EssayReader parser = new EssayReader(query, "textFiles");
		

		//store all chosen strings used in the essay to print and update Markov chain later
		try{
			parser.readFiles();
			ArrayList<Word> chosenWords = generateEssay(parser.getStartingWord(), length);
			print(chosenWords, query, "output/");
			System.out.println("Did you like your essay?");
			boolean positive = in.nextLine().toLowerCase().charAt(0) == 'y';
			machineLearning(chosenWords, positive, query, "cachedSearches/");
		}
		catch(Exception e){
			System.out.println("here's the exception:: " + e.getMessage());
			//
		}
		//update(chosenWords);
		//store(query, chosenWords);
	}

	public ArrayList<Word> generateEssay(Word startingWord, int length) throws Exception{
		Word currWord = startingWord;
		ArrayList<Word> wordsUsed = new ArrayList<>(length);
		for(int wordCount = 0; wordCount < length || !currWord.getWord().equals(".") ; wordCount++){
			currWord = currWord.getRandomNextWord();
			/*Do we want to trim the markov chain for only cycles?
			while(currWord == null){ //hit a word at the end with no other option
				prevWord = wordsUsed.get(wordsUsed.size()-1);
				prevWord.removeWord(currWord);
			}*/

			/*Or do we want to start over from the beginning?*/
			if(currWord == null){
				//however, this should never happen since we always hit periods at the end
				throw new Exception("something went wrong");
			}
			wordsUsed.add(currWord);
			if(currWord.getWord().matches("\\p{Punct}") || currWord.getWord().equals(" ")){ //change to regex later to support additional punctuation
				wordCount--;
			}
		}
		return wordsUsed;
	}

	public void print(ArrayList<Word> words, String query, String outputDest) throws Exception {
		File outputFolder = new File(outputDest);
		PrintWriter writer = new PrintWriter(outputDest + query + " essay", "UTF-8");
		boolean noPrevSpace = true;
		for(int i = 1; i < words.size(); i++){
			String word = words.get(i).getWord();
			if(noPrevSpace){
				writer.print(word);
				noPrevSpace = false;
			}
			if(!word.matches("\\p{Punct}") && !word.equals(" ") && !word.equals("\"1") && !word.equals("\"2")  && !word.equals("\'")){
				writer.print(" " + word);
			}
			else{
				if(word.equals("\"1")){
					writer.print("\"");
					noPrevSpace = true;
				}
				else if(word.equals("\"2")){
					writer.print("\"");
				}
				else if(word.equals("\'")){
					writer.print(word);
					noPrevSpace = true;	
				}
				else if(word.equals(" ")){
					writer.print("\r\n\n\t");
					noPrevSpace = true;
				}else{
					writer.print(word);
				}
			}
		}
		writer.close();
	}

	public void machineLearning(
	ArrayList<Word> words, 
	boolean positive, 
	String query, 
	String outputDest) throws IOException{
		File outputFolder = new File(outputDest);
		PrintWriter writer = new PrintWriter(outputDest + query, "UTF-8");
		writer.println(words.size());
		for(int i = 0; i < words.size()-1; i++){
			Word currWord = words.get(i);
			Word nextWord = words.get(i+1);
			writer.println(currWord.setMachineLearning(nextWord, positive));
		}
	}
}

