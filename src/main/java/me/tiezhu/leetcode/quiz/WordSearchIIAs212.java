package me.tiezhu.leetcode.quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 *
 * [
 *  ['o','a','a','n'],
 *  ['e','t','a','e'],
 *  ['i','h','k','r'],
 *  ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 */
public class WordSearchIIAs212 {

    // 搜索核心是dfs
    // 一开始我就想到如果能把words放到一个类似霍夫曼的树里就好了，这样同一个前缀的只需要找一次
    // 不过我没想到多个节点的tree怎么表示。。。
    // 后来看了别人15ms的答案，发现有个trie（字典树），直接数组26就行了。。。
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        Trie root = buildTrie(words);

        for (int x = 0;x < board.length;x++) {
            for (int y = 0;y < board[0].length;y++) {
                dfs(board, x, y, root, result);
            }
        }

        return result;
    }

    void dfs(char[][] board, int x, int y, Trie node, List<String> result) {
        char c = board[x][y];
        int idx = c - 'a';
        if (idx >= 26 || node.children[idx] == null) {
            return;
        }

        node = node.children[idx];

        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }

        board[x][y] ^= 256;

        if (x > 0) {
            dfs(board, x - 1, y, node ,result);
        }

        if (y > 0) {
            dfs(board, x, y - 1, node, result);
        }

        if (x + 1 < board.length) {
            dfs(board, x + 1, y, node, result);
        }

        if (y + 1 < board[0].length) {
            dfs(board, x, y + 1, node, result);
        }

        board[x][y] ^= 256;
    }

    Trie buildTrie(String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            Trie node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new Trie();
                }
                node = node.children[c - 'a'];
            }
            node.word = word;
        }
        return root;
    }

    class Trie {
        Trie[] children = new Trie[26];
        String word;
    }
}
