/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;

        while(left < right) {
            int mid = left + (right - left) / 2;

            if(isBadVersion(mid))
                // mid could be the best bad version
                right = mid;

            else
                // mid id definitely not the answer
                left = mid + 1;
        }

        return left;
    }
}
