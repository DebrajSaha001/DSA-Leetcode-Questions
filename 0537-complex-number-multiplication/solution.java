class Solution {
    private int[] parse(String s) {
        s = s.substring(0, s.length() - 1);
        String parts[] = s.split("\\+");
        int real = Integer.parseInt(parts[0]);
        int imaginary = Integer.parseInt(parts[1]);

        return new int[]{real, imaginary};
    }

    public String complexNumberMultiply(String num1, String num2) {
        int a[] = parse(num1);
        int b[] = parse(num2);

        int real = a[0] * b[0] - a[1] * b[1];
        int imaginary = a[0] * b[1] + a[1] * b[0];

        return real + "+" + imaginary + "i";
    }
}
