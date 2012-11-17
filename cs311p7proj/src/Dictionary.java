import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Dictionary{
	
	private TreeNode root;
	
	
	public void initializeDictionary(Set<String> s){

		this.root  = new TreeNode(null, null);
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
					current.getChildren().add(gonnaAdd);
					gonnaAdd.setParent(current);
					current = gonnaAdd;
				}else{
					current = possibleNext;
				}
			}
			current.setIsWord(true);			
			//General alg, keep adding nodes in order as children (if they dont exist)
			//Once you hit the end of a word, set the last node's isWord = true;
		}
	}
	
	public void printDictionary(TreeNode current, String upTillNow){
		if(current.getIsWord()){
			System.out.println(upTillNow+current.getData());
		}
		for(TreeNode t : current.getChildren()){
			if(current.getData() != null){
			printDictionary(t, upTillNow+current.getData());
			}else{
			printDictionary(t, upTillNow);
			}
		}
	}
	
	public TreeNode getRoot(){
		return this.root;
	}
	
	
	public static class TreeNode{
		
		private TreeNode parent;
		private Character data;
		private boolean isWord;
		private ArrayList<TreeNode> children;
		
		public TreeNode(TreeNode parent, Character data){
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
		
		public boolean getIsWord(){
			return this.isWord;
		}
		
		public Character getData(){
			return this.data;
		}
		
		public void setParent(TreeNode p){
			this.parent = p;
		}
		
		public TreeNode getParent(){
			return this.parent;
		}
		
		public ArrayList<TreeNode> getChildren(){
			return this.children;
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