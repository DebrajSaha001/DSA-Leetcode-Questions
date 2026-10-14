class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> nextRow = new ArrayList<>();
            nextRow.add(1);

            for (int j = 1; j < i; j++)
                nextRow.add(row.get(j - 1) + row.get(j));

            nextRow.add(1);
            row = nextRow;
        }

        return row;
    }
}
