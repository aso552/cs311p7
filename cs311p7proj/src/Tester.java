import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class Tester {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		AnagramEnumerator ae = new AnagramEnumerator();
		File f = new File("src/EnglishWordList.txt");
		Scanner s = new Scanner(f);
		HashSet<String> dict = new HashSet<String>();
		while(s.hasNext()){
			dict.add(s.nextLine());
		}
		ae.initialize(dict);
		
		Set<String> toIter = ae.enumerateAnagramsUnderE("elvis");
		Iterator<String> iter = toIter.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		
		
	}

}
