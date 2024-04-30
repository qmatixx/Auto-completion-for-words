import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Data structure copied from geeksforgeeks
// Tree structure made from multiple nodes that hold letters and references to the next letters from a word
// Starting from the root we can find words by just finding the path to the leafs
public class Trie {

    // Single node in our Trie that holds a letter and all the references to the next nodes that will eventually create a word
    private class TrieNode {
        // Map holding references to all possible next nodes
        Map<Character, TrieNode> children;
        char c;
        boolean isWord;

        public TrieNode(char c) {
            this.c = c;
            children = new HashMap<>();
        }

        public TrieNode() {
            children = new HashMap<>();
        }

        // Function to insert new world
        // Usually starting from the root and then recursively from the next nodes
        public void insert(String word) {
            if (word == null || word.isEmpty())
                return;

            // Get the first letter of given world, create a new Node from it and give current Node a reference to this newly created
            char firstChar = word.charAt(0);
            TrieNode child = children.get(firstChar);
            if (child == null) {
                child = new TrieNode(firstChar);
                children.put(firstChar, child);
            }

            // If the world isn't fully added do the same for newly created Node
            if (word.length() > 1)
                child.insert(word.substring(1));
            else
                child.isWord = true;
        }
    }

    // First Node of our Trie
    // It doesn't hold any char
    TrieNode root;

    public Trie(List<String> words) {
        root = new TrieNode();
        for (String word : words)
            root.insert(word);
    }

    // Add to the given list all words starting with prefix
    public void addWords(TrieNode root, List<String> list, StringBuffer curr) {
        // If our prefix is a world then add it to the list
        if (root.isWord) {
            list.add(curr.toString());
        }

        // If there are no other words starting with prefix then end the function
        if (root.children == null || root.children.isEmpty())
            return;

        // Iterate through all children (prefix + next possible letter) and find possible words for them
        for (TrieNode child : root.children.values()) {
            addWords(child, list, curr.append(child.c));
            // After recursive finding other children we have to go back to the previous word
            curr.setLength(curr.length() - 1);
        }
    }

    // Find the Node with last letter of the prefix
    public List<String> find(String prefix) {
        List<String> list = new ArrayList<>();
        TrieNode lastNode = root;
        StringBuffer curr = new StringBuffer();
        // Iterate through all chars from prefix and find the node for the last letter of a prefix
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.children.get(c);
            // If we got to the end of a tree then there is no word starting with prefix - return empty list
            if (lastNode == null)
                return list;
            curr.append(c);
        }
        // Add to the list all possible words starting with prefix
        addWords(lastNode, list, curr);
        return list;
    }

}