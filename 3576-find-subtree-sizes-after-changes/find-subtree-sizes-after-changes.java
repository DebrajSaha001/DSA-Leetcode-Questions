class Solution {
    List<Integer>[] tree;
    List<Integer>[] newTree;
    int[] newParent;
    int[] answer;
    char[] s;
    int[] last;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s.toCharArray();

        tree = new ArrayList[n];
        newTree = new ArrayList[n];

        newParent = new int[n];
        answer = new int[n];
        last = new int[26];

        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++)
            tree[parent[i]].add(i);

        // First DFS: Find new parents
        findNewParents(0, parent);

        // Build modified tree
        for (int i = 1; i < n; i++)
            newTree[newParent[i]].add(i);

        // Second DFS: Calculate subtree sizes
        calculateSizes(0);

        return answer;
    }

    private void findNewParents(int node, int[] parent) {
        int c = s[node] - 'a';

        // Save previous closest ancestor of this character
        int previous = last[c];

        // Determine the new parent
        if (last[c] != -1)
            newParent[node] = last[c];
        
        else
            newParent[node] = parent[node];

        // Current node becomes the closest ancestor
        last[c] = node;

        // Visit children in the original tree
        for (int child : tree[node])
            findNewParents(child, parent);

        // Restore previous state for sibling branches
        last[c] = previous;
    }

    private int calculateSizes(int node) {
        int size = 1;

        for (int child : newTree[node])
            size += calculateSizes(child);

        answer[node] = size;

        return size;
    }
}