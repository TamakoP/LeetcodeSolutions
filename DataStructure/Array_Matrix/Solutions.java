package Array_Matrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/8/15 下午5:03
 * @Version: 1.0
 */
public class Solutions {
    /**
     * @Description 把数组中的 0 移到末尾:一般的解决方案是遇 0则进行一次数组前移，嵌套循环，时间复杂度为 o(n^2)
     * @Param [nums]
     * @return void
     */
    public void moveZeroes(int[] nums){
        //记录数组中0的个数
        int index=0;
        for(int num:nums){
            //重新排列数组：从下标0开始，若当前元素不为0，则将其放置index的位置，让全部不为0的元素排列在数组前面
            if(num!=0){
                nums[index++]=num;
            }
        }
        //for循环结束之后，index指向下一个位置
        while(index<nums.length){
            nums[index++]=0;
        }
        System.out.println(Arrays.toString(nums));
    }

    public int[][] matrixReshape(int[][] nums, int r, int c){
        int row= nums.length;
        int column=nums[0].length;
        int[][] reshapedM=new int[r][c];
        int index=0;
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                reshapedM[i][j]=nums[index/column][index%column];
                index++;
            }
        }
        return reshapedM;
    }
    /**
     * @Description 有序矩阵(每行每列都是有序的，但不保证行与行，列与列之间是有序的)查找：一般解决方案是两层for循环，高效的做法一个循环，数组名作为指针
     * @Param [matrix, target]
     * @return boolean
     */
    public boolean searchMatrix(int[][] matrix, int target){
        int row=matrix.length,column=matrix[0].length;
        int i=0,j=column-1;
        while(i<row&&j>0){
            if (target == matrix[i][j]) return true;
            else if (target < matrix[i][j]) j--;
            else i++;
        }
        return  false;

    }

    public int kthSmallest(int[][] matrix, int k){
        int row=matrix.length,column=matrix[0].length;
        int low=matrix[0][0],high=matrix[row-1][column-1];
        while(low<=high){
            int mid=(low+high)/2;
            int cnt=0;
            for(int i=0;i<row;i++){
                for(int j=0;j<column&&matrix[i][j]<=mid;j++)
                    cnt++;
            }
            if(cnt>=k)
                high=mid-1;
            else
                low=mid+1;
        }
        return low;
    }
    /**
     * @Description 数组的度：任一元素出现频数的最大值
     * @Param [nums]
     * @return int
     */
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer,Integer> map=new HashMap<>();
        // i)统计出现的频度
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        // ii)找出频度最大值和对应的元素值
        int freq=1;
        int num=nums[0];
       for(Map.Entry<Integer,Integer> entry:map.entrySet()){
           if(entry.getValue()>freq){
               freq= entry.getValue();
               num= entry.getKey();
           }
       }
       // Arrays.toString方法只能以字符串形式作为输出，并不能调用new创建对象，所以本质上并不是字符串
        // iii)将数组转换为字符串：方便利用indexOf的方法提取索引
       String numsArray="";
       for(int n:nums)
           numsArray+=Integer.toString(n);

       int firstIndex=numsArray.indexOf(String.valueOf(num));
       int lastIndex=numsArray.lastIndexOf(String.valueOf(num));
       String sub=numsArray.substring(firstIndex,lastIndex+1);
       System.out.println(sub);
       return sub.length();
    }

    public int arrayNesting(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int cnt = 0;
            for (int j = i; nums[j] != -1; ) {
                cnt++;
                int t = nums[j];
                nums[j] = -1; // 标记该位置已经被访问
                j = t;

            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}
