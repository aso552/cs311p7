import java.util.Map;
import java.util.Set;

public interface IAnagramEnumerator
{
	//Initializes the IAnagramEnumerator with the dictionary.
	//This is where you should set up the data structures you are storing the dictionary in
	public void initialize(Set<String> dictionary);
	
	//Generates the set of anagrams of s that are consistent with the dictionary provided by initialize
	public Set<String> enumerateAnagramsUnderE(String s);
	
	//Generates the set of anagrams of s that are consistent with bag(E).
	public Set<Map<String, Integer>> enumerateAnagramsUnderBagE(String s);
}