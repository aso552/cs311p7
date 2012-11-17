In this assignment I chose to use a Trie for my dictionary data structure.  The reason I implemented a Trie is because it would help me prune during my backtracking algorithm.  
This pruning is vital for the speed of my application.  Most of the permeutations that the algorithm will examine will not be in the dictionary.  I implented my pruning technique
by stoping the search once I have found that their are no possible words that begin with the permutation that I have found thus far. Essentially imagine if I were examing  a word with many characters
and that during this search one subtree started with xt.  Lets also imagine that there are no words in the dictionary that start with xt, this means instead of searching all other possible
combinations constrcuted from xt appended with other letters, I might as well stop there.  How this would be done is while searching I will traverse my Trie with each recursive step of the algorithm.  If my Trie
has run out of nodes (meaning it does not contain anything begining with this sequence) then my enumerateAnagramsUnderE will stop looking in this area of the search space. I also pruned based on if there were duplicate letters
in the word.  If two backtracking nodes are siblings, and they are the same letter, then they will be mirrors of eachother.  This means there is no need to calculate both.

For the second part of the assignment, enumerateAnagramsUnderBagE, I chose to prune based on the number of letters in my current set guess. Basically, if the combination of words possible is larger in size (of total letters),
 then we know it cannot be an anagram. I also used a generate possible subwords method which used pruning techniques similar to enumerateAnagramsUnderE(). This method seemed to still be quick and responsive;
 however, it was not near as instantaneous as the method above.

This method seems to work very well and I get a very quick response even with long words.  This may be somewhat due to my CPU power (core i7 [6 core,  12 threads] @ 3.24Ghz);
 however, performance was < 10 seconds for word tests (including dictionary intialization).