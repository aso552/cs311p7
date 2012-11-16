import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class AnagramEnumerator implements IAnagramEnumerator {

	private HashSet<String> dict;
	
	@Override
	public void initialize(Set<String> dictionary) {
		this.dict = new HashSet<String>();
		Iterator<String> iter = dictionary.iterator();
		while(iter.hasNext()){
			String toAdd = iter.next();
			dict.add(toAdd);
		}
		
		//Element are now in a hash set
	}

	@Override
	public Set<String> enumerateAnagramsUnderE(String s) {
		HashSet<String> ret = new HashSet<String>();
		enumterateAnagramsUnderEHelper(s, "" , ret);
		return ret;
	}
	
	private void enumterateAnagramsUnderEHelper(String whatsLeft, String parent, Set<String> returnSet){
		if(whatsLeft.length() == 0){
			if(dict.contains(parent)){
			returnSet.add(parent);
			}
		}else{
			for(int i = 0; i < whatsLeft.length(); i++){
			//Make move
			//For each chracter left in whats left, remove that character from whats left, and append it to the end of parent
			String moveMadeWhatsLeft = whatsLeft;	
			if(i == 0){
				moveMadeWhatsLeft = moveMadeWhatsLeft.substring(1,moveMadeWhatsLeft.length());
			}else if(i == moveMadeWhatsLeft.length()-1){
				moveMadeWhatsLeft = moveMadeWhatsLeft.substring(0,moveMadeWhatsLeft.length()-1);
			}else{
				moveMadeWhatsLeft = moveMadeWhatsLeft.substring(0,i) + moveMadeWhatsLeft.substring(i+1, moveMadeWhatsLeft.length());
			}
			String moveMadeParent = parent + whatsLeft.charAt(i);
			//call recursive	
			enumterateAnagramsUnderEHelper(moveMadeWhatsLeft, moveMadeParent, returnSet);
			//unmakemove
			//put character back in the right place and remove it from the end of parent
			//Needed?
			}
		}
		
	}

	@Override
	public Set<Map<String, Integer>> enumerateAnagramsUnderBagE(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
