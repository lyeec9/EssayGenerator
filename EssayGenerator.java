import java.util.*;

class EssayGenerator{
	
	public static void main(String[] args){
		this();
	}

	public EssayGenerator()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("What would you like to write about?");
		String line = in.nextLine();
		System.out.println("How many words would you like the essay to be?");
		int length = Integer.parseInt(in.nextLine());
		
		EssayReader parser = new EssayReader(line, "/home/lychee/programs/Essay\ Generator/textFiles");
		Map<Word, Integer> startingWords = parser.readFiles();
		ArrayList<Word> chosenWords = generateEssay
	}

	
}

