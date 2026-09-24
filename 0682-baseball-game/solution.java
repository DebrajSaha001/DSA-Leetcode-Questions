class Solution {
    public int calPoints(String[] operations) {
        List<Integer> record = new ArrayList<>();

        for (String op : operations) {
            if (op.equals("C"))
                record.remove(record.size() - 1);

            else if (op.equals("D")) {
                int last = record.get(record.size() - 1);
                record.add(last * 2);
            }

            else if (op.equals("+")) {
                int n = record.size();
                int last = record.get(n - 1);
                int secondLast = record.get(n - 2);

                record.add(last + secondLast);
            }

            else
                record.add(Integer.parseInt(op));
        }

        int sum = 0;

        for (int score : record)
            sum += score;

        return sum;
    }
}

