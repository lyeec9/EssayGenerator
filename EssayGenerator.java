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
		
		EssayReader parser = new EssayReader(query, "/home/lychee/programs/Essay Generator/textFiles");
		

		//store all chosen strings used in the essay to print and update Markov chain later
		try{
			parser.readFiles();
			ArrayList<String> chosenWords = generateEssay(parser.getStartingWord(), length);
			print(chosenWords, query, "/home/lychee/programs/Essay Generator/output");
		}
		catch(Exception e){
			//
		}
		//update(chosenWords);
		//store(query, chosenWords);
	}

	public ArrayList<String> generateEssay(Word startingWord, int length) throws Exception{
		Word currWord = startingWord;
		ArrayList<String> wordsUsed = new ArrayList<>(length);
		for(int wordCount = 0; wordCount < length && !currWord.getWord().equals(".") ; wordCount++){
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
			wordsUsed.add(currWord.getWord());
			if(currWord.equals(".") || currWord.equals(" ")){ //change to regex later to support additional punctuation
				wordCount--;
			}
		}
		return wordsUsed;
	}

	public void print(ArrayList<String> words, String query, String outputDest) throws Exception {
		File outputFolder = new File(outputDest);
		Date date = new Date();
		PrintWriter writer = new PrintWriter(query + " essay :" + (date.getTime()), "UTF-8");
		boolean noPrevSpace = true;
		for(int i = 1; i < words.size(); i++){
			String word = words.get(i);
			if(noPrevSpace){
				writer.print(word);
				noPrevSpace = false;
			}
			if(!word.equals(" ") && !word.equals(".")){
				writer.print(" " + word);
			}
			else{
				if(word.equals(" ")){
					writer.print("\n\n\t");
					noPrevSpace = false;
				}else{
					writer.print(word);
				}
			}
		}
		writer.close();
	}
}

