class Solution {
    private int binaryS(int[] nums, int l, int r, int target) {
        while(l<=r) {
            int mid=l+(r-l)/2;
            if(nums[mid]==target)
                return mid;

            else if(nums[mid]>target)
                r=mid-1;

            else
                l=mid+1;
        }
        return l;
    }

    public int lengthOfLIS(int[] nums) {
        int[] res=new int[nums.length];
        int l=-1;
        res[++l]=nums[0];
        
        for(int n: nums) {
            if(res[l]<n)
                res[++l]=n;

            else {
                int id=binaryS(res, 0, l, n);
                res[id]=n;
            }
        }
        return l+1;
    }
}

