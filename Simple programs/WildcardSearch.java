import java.util.List;
import java.util.ArrayList;
public class WildcardSearch
{
	public static void main(String args[])
	{
		String dictionary[] = { "intelligence", "intelligent", "intellectual", "interface", "mean", "moon", "paper", "news", "sunday", "like", "i" };
		String myinput = "*ce";
		String splittedWords[] = myinput.split("\\*");
		System.out.println(splittedWords.length);	
		String firstWord = splittedWords[0];
		String lastword = splittedWords[splittedWords.length - 1];
		if( splittedWords.length == 1 )
		{
			lastword = "";
		}
		System.out.println("firstWord : " + firstWord);
		System.out.println("lastword : " + lastword);
		List matchedWords = new ArrayList();
		for(String word : dictionary)
		{
			if( word.startsWith(firstWord) && word.endsWith(lastword) )
			{
				if(splittedWords.length > 2)
				{
					updateMatchedWord(word, splittedWords, matchedWords);
				}
				else
				{
					matchedWords.add(word);
				}
			}
			else if( lastword.equals("") && word.startsWith(firstWord) )
			{
				if(splittedWords.length > 2)
				{
					updateMatchedWord(word, splittedWords, matchedWords);
				}
				else
				{
					matchedWords.add(word);
				}
			}
			else if( firstWord.equals("") && word.endsWith(lastword) )
			{
				if(splittedWords.length > 2)
				{
					updateMatchedWord(word, splittedWords, matchedWords);
				}
				else
				{
					matchedWords.add(word);
				}
			}
		}
		System.out.println("MATCHED WORDS : " + matchedWords);
	}

	private static void updateMatchedWord(String word, String[] splittedWords, List matchedWords)
	{
		boolean wordMatched = true;

		for( int i=1; i<splittedWords.length-1; i++ )
		{
			if( word.indexOf(splittedWords[i]) < 0 )
			{
				wordMatched = false;
			}
		}

		if( wordMatched )
		{
			matchedWords.add(word);
		}
	}
}
