import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AnagramEnumerator implements IAnagramEnumerator {

	private Dictionary dict;

	@Override
	public void initialize(Set<String> dictionary) {
		this.dict = new Dictionary();
		dict.initializeDictionary(dictionary);
	}

	@Override
	public Set<String> enumerateAnagramsUnderE(String s) {
		HashSet<String> ret = new HashSet<String>();
		enumterateAnagramsUnderEHelper(s, "", ret, dict.getRoot(), null);
		return ret;
	}

	private void enumterateAnagramsUnderEHelper(String whatsLeft,
			String parent, Set<String> returnSet,
			TreeNode dictPointer, TreeNode myParent) {
		// Pruning strategy: Pass a tree node along. If the tree node is null,
		// and this Whats left isnt done... just stop
		if (whatsLeft.length() == 0 && dictPointer.getIsWord()) {
			returnSet.add(parent);
		} else {
			HashSet<Character> iveTriedSoFar = new HashSet<Character>();
			for (int i = 0; i < whatsLeft.length(); i++) {
				// Make move
				// For each chracter left in whats left, remove that character
				// from whats left, and append it to the end of parent
				String moveMadeWhatsLeft = whatsLeft;
				if (i == 0) {
					moveMadeWhatsLeft = moveMadeWhatsLeft.substring(1,
							moveMadeWhatsLeft.length());
				} else if (i == moveMadeWhatsLeft.length() - 1) {
					moveMadeWhatsLeft = moveMadeWhatsLeft.substring(0,
							moveMadeWhatsLeft.length() - 1);
				} else {
					moveMadeWhatsLeft = moveMadeWhatsLeft.substring(0, i)
							+ moveMadeWhatsLeft.substring(i + 1,
									moveMadeWhatsLeft.length());
				}
				String moveMadeParent = parent + whatsLeft.charAt(i);

				// call recursive
				if (dictPointer.getChildContaining(whatsLeft.charAt(i)) != null
						&& !iveTriedSoFar.contains(whatsLeft.charAt(i))) {
					iveTriedSoFar.add(whatsLeft.charAt(i));
					enumterateAnagramsUnderEHelper(
							moveMadeWhatsLeft,
							moveMadeParent,
							returnSet,
							dictPointer.getChildContaining(whatsLeft.charAt(i)),
							dictPointer);
				} else {
					// Do not call method (skip that subtree) PRUNING! .. like a
					// boss
				}
			}
		}

	}

	private Set<String> generateSubwords(String s) {
		HashSet<String> ret = new HashSet<String>();
		generateSubwordsHelper(s, "", ret, this.dict.getRoot(), null);
		return ret;

	}

	private void generateSubwordsHelper(String whatsLeft, String parent,
			Set<String> returnSet, TreeNode dictPointer,
			TreeNode myParent) {
		// Pruning strategy: Pass a tree node along. If the tree node is null,
		// and this Whats left isnt done... just stop

		if (whatsLeft.length() == 0 && dictPointer.getIsWord()) {
			returnSet.add(parent);
		} else {
			if (dictPointer.getIsWord()) {
				returnSet.add(parent);
			}
			HashSet<Character> iveTriedSoFar = new HashSet<Character>();
			for (int i = 0; i < whatsLeft.length(); i++) {
				// Make move
				// For each chracter left in whats left, remove that character
				// from whats left, and append it to the end of parent
				String moveMadeWhatsLeft = whatsLeft;
				if (i == 0) {
					moveMadeWhatsLeft = moveMadeWhatsLeft.substring(1,
							moveMadeWhatsLeft.length());
				} else if (i == moveMadeWhatsLeft.length() - 1) {
					moveMadeWhatsLeft = moveMadeWhatsLeft.substring(0,
							moveMadeWhatsLeft.length() - 1);
				} else {
					moveMadeWhatsLeft = moveMadeWhatsLeft.substring(0, i)
							+ moveMadeWhatsLeft.substring(i + 1,
									moveMadeWhatsLeft.length());
				}
				String moveMadeParent = parent + whatsLeft.charAt(i);

				// call recursive
				if (dictPointer.getChildContaining(whatsLeft.charAt(i)) != null
						&& !iveTriedSoFar.contains(whatsLeft.charAt(i))) {
					iveTriedSoFar.add(whatsLeft.charAt(i));
					generateSubwordsHelper(
							moveMadeWhatsLeft,
							moveMadeParent,
							returnSet,
							dictPointer.getChildContaining(whatsLeft.charAt(i)),
							dictPointer);
				} else {
					// Do not call method (skip that subtree) PRUNING! .. like a
					// boss
				}
			}
		}
	}

	@Override
	public Set<Map<String, Integer>> enumerateAnagramsUnderBagE(String s) {
		Set<Map<String, Integer>> ret = new HashSet<Map<String, Integer>>();
		enumerateAnagramsUnderBagEHelper(s, s, new HashMap<String, Integer>(),
				ret, 0);
		return ret;
	}

	private String cutOutSubWord(String removeFrom, String toRemove)
			throws Exception {
		ArrayList<Character> piecesToRemove = new ArrayList<Character>();
		String ret = new String();
		if (removeFrom.length() < toRemove.length()) {
			throw new Exception("cannot cut out subword!");
		}
		for (int i = 0; i < toRemove.length(); i++) {
			piecesToRemove.add(toRemove.charAt(i));
		}
		for (int i = 0; i < removeFrom.length(); i++) {
			if (piecesToRemove.contains(removeFrom.charAt(i))) {
				// Skip .. dont add
				piecesToRemove.remove(new Character(removeFrom.charAt(i)));
				// Remove it.. its been used now
			} else {
				ret += removeFrom.charAt(i);
			}
		}
		return ret;
	}

	private void enumerateAnagramsUnderBagEHelper(String targetDoNotChange,
			String whatsLeft, HashMap<String, Integer> parent,
			Set<Map<String, Integer>> ret, int runningTotal) {
		// Start at root word
		// Generate all subwords
		if (whatsLeft.length() == 0
				&& runningTotal == targetDoNotChange.length()) {
			ret.add(parent);
			return;
		} else {
			Set<String> toIter = generateSubwords(whatsLeft);
			for (String subWord : toIter) {
				// Do some pruning
				if (runningTotal + subWord.length() <= targetDoNotChange
						.length()) {
					String moveMadeWhatsLeft = null;
					try {
						moveMadeWhatsLeft = cutOutSubWord(whatsLeft, subWord);
					} catch (Exception e) {
						e.printStackTrace();
					}

					HashMap<String, Integer> mapCopy = new HashMap<String, Integer>();
					for (String s : parent.keySet()) {
						mapCopy.put(s, parent.get(s));
					}
					// Make move
					if (mapCopy.containsKey(subWord)) {
						mapCopy.put(subWord,
								mapCopy.get(subWord).intValue() + 1);
					} else {
						mapCopy.put(subWord, 1);
					}
					enumerateAnagramsUnderBagEHelper(targetDoNotChange,
							moveMadeWhatsLeft, mapCopy, ret, runningTotal
									+ subWord.length());

				} else {
					// Do nothing.. too long PRUNING
				}
			}
		}
	}

	public static class TreeNode {

		private TreeNode parent;
		private Character data;
		private boolean isWord;
		private ArrayList<TreeNode> children;

		public TreeNode(TreeNode parent, Character data) {
			this.data = data;
			this.parent = parent;
			this.children = new ArrayList<TreeNode>();
			this.isWord = false;
		}

		// Does not change toAdd's parent to this node!
		public void addChild(TreeNode toAdd) {
			this.children.add(toAdd);
		}

		public void setIsWord(boolean toSet) {
			this.isWord = toSet;
		}

		public boolean getIsWord() {
			return this.isWord;
		}

		public Character getData() {
			return this.data;
		}

		public void setParent(TreeNode p) {
			this.parent = p;
		}

		public TreeNode getParent() {
			return this.parent;
		}

		public ArrayList<TreeNode> getChildren() {
			return this.children;
		}

		public TreeNode getChildContaining(char gData) {
			for (TreeNode t : children) {
				if (t.getData() == gData) {
					return t;
				}
			}
			// Not found
			return null;
		}

	}

	public static class Dictionary {

		private TreeNode root;

		public void initializeDictionary(Set<String> s) {

			this.root = new TreeNode(null, null);
			Iterator<String> iter = s.iterator();
			// For every word in s
			while (iter.hasNext()) {
				String toExamine = iter.next();
				// Start from root
				TreeNode current = root;
				for (int i = 0; i < toExamine.length(); i++) {
					TreeNode possibleNext = current
							.getChildContaining(toExamine.charAt(i));
					if (possibleNext == null) {
						TreeNode gonnaAdd = new TreeNode(current,
								toExamine.charAt(i));
						current.getChildren().add(gonnaAdd);
						gonnaAdd.setParent(current);
						current = gonnaAdd;
					} else {
						current = possibleNext;
					}
				}
				current.setIsWord(true);
				// General alg, keep adding nodes in order as children (if they
				// dont exist)
				// Once you hit the end of a word, set the last node's isWord =
				// true;
			}
		}

		public void printDictionary(TreeNode current, String upTillNow) {
			if (current.getIsWord()) {
				System.out.println(upTillNow + current.getData());
			}
			for (TreeNode t : current.getChildren()) {
				if (current.getData() != null) {
					printDictionary(t, upTillNow + current.getData());
				} else {
					printDictionary(t, upTillNow);
				}
			}
		}

		public TreeNode getRoot() {
			return this.root;
		}

	}
}
