package binarySearch;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/10/14 下午5:02
 * @Version: 1.0
 */
public class bsSolutions {

    //由于数组里只有一个元素是只出现一次的，所以数组长度一定是奇数
    public int singleNonDuplicate(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            //将区间一分为2 时，一定一边是偶，一边是奇
            if (m % 2 == 1) {
                m--;   // 保证 l/h/m 都在偶数位，使得查找区间大小一直都是奇数
            }
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                h = m;
            }
        }
        return nums[l];
    }

    /*public int firstBadVersion(int n) {
        int l = 1, h = n;
        while (l < h) {
            int mid = l + (h - l) / 2;
            if (isBadVersion(mid)) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }*/
/**
 * @Description  旋转数组的最小数字
 * @Param [nums]
 * @return int
 */
    public int findMin(int[] nums) {
        //若数组并未旋转，数组必定严格升序排列，最后一个元素必定>首元素
        if(nums[0]<nums[nums.length-1]) return nums[0];
        int l=0;
        int h=nums.length-1;
        while(l<h){
            int mid=l+(h-l)/2;
            if(nums[mid]>nums[h])
                l=mid+1;
            else{
                h=mid-1;
            }
        }
        return nums[l];

    }
    /**
     * @Description Find First and Last Position of Element in Sorted Array
     * @Param [nums, target]
     * @return int[]
     */
    public int[] searchRange(int[] nums, int target) {
        int l=0;
        int h= nums.length-1;
        while(l<h){
            int mid=l+(h-l)/2;
            if(nums[mid]<target)
                l=mid+1;
            else{
                h=mid-1;
            }
        }
        if(nums[l]!=target)
            return new int[]{-1,-1};
        if(nums[l-1]==target){
            return new int[]{l-1,l};
        }
        else{
            return new int[]{l,l+1};
        }


    }



    }
