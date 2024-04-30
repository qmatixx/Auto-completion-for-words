import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> words = List.of("car", "carpet", "java", "javascript", "internet");
        Trie trie = new Trie(words);

        System.out.println(trie.find("c"));
        System.out.println(trie.find("car"));
        System.out.println(trie.find("jav"));
        System.out.println(trie.find("intern"));
        System.out.println(trie.find("foo"));
    }
}