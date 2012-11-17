import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException {

		AnagramEnumerator ae = new AnagramEnumerator();
		File f = new File("src/EnglishWordList.txt");
		Scanner s = new Scanner(f);
		HashSet<String> dict = new HashSet<String>();
		while (s.hasNext()) {
			dict.add(s.nextLine());
		}
		ae.initialize(dict);

		Set<Map<String, Integer>> toIter = ae.enumerateAnagramsUnderBagE("cat");
		Iterator<Map<String, Integer>> iter = toIter.iterator();
		
		
//		Set<String> toIter = ae.enumerateAnagramsUnderE("cat");
//		Iterator<String> iter = toIter.iterator();
		
		while (iter.hasNext()) {
			System.out.print("{ ");
			for(String toPrint : iter.next().keySet()){
				System.out.print(" " +  toPrint + " ");
			}
			System.out.print("} \n");
			
			
//			System.out.println(iter.next());

			
		}

	}

}
