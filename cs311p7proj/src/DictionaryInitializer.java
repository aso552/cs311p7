import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class DictionaryInitializer{
	
	public static TreeNode initializeDictionary(Set<String> s){
		TreeNode root  = new TreeNode(null, '~');
		Iterator<String> iter = s.iterator();
		//For every word in s
		while(iter.hasNext()){
			String toExamine = iter.next();
			//Start from root
			TreeNode current = root;
			for(int i = 0; i < toExamine.length(); i++){
				TreeNode possibleNext = current.getChildContaining(toExamine.charAt(i));
				if(possibleNext == null){
					TreeNode gonnaAdd = new TreeNode(current, toExamine.charAt(i));
					current = gonnaAdd;
				}else{
					current = possibleNext;
				}
			}
			current.setIsWord(true);
			//General alg, keep adding nodes in order as children (if they dont exist)
			//Once you hit the end of a word, set the last node's isWord = true;
		}
		return root;
	}
	
	
	public static class TreeNode{
		
		private TreeNode parent;
		private char data;
		private boolean isWord;
		private ArrayList<TreeNode> children;
		
		public TreeNode(TreeNode parent, char data){
			this.data = data;
			this.parent = parent;
			this.children = new ArrayList<TreeNode>();
			this.isWord = false;
		}
		
		//Does not change toAdd's parent to this node!
		public void addChild(TreeNode toAdd){
			this.children.add(toAdd);
		}
		
		public void setIsWord(boolean toSet){
			this.isWord = toSet;
		}
		
		public char getData(){
			return this.data;
		}
		
		public TreeNode getChildContaining(char gData){
			for(TreeNode t : children){
				if(t.getData() == gData){
					return t;
				}
			}
			//Not found
			return null;
		}
	
	}

}