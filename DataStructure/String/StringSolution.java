package String;

import java.util.HashMap;

/**
 * @Author: Tamako
 * @Description: TODO
 * @Date: 2024/8/13 下午3:29
 * @Version: 1.0
 */
// 字符串题解：（1）从中间或者中部某位置开始往两边走或者一分为二；（2）翻转再翻转； （3）回文的一大特性：两边对称即左半部分翻转=右半部分
public class StringSolution {
    /**
     * @Description 字符串循环移位包含
     * @Param [s1, s2]
     * @return boolean
     */
    public boolean stringShiftContain(String s1,String s2){
//        int len=s1.length();
//        for(int i=0;i<len;i++){
//            String news=s1.substring(len-i)+s1.substring(0,len-i);
//            if(news.contains(s2))
//                return true;
//        }
//
        //直接concat
        String news = s1.concat(s1);
        return news.contains(s2);
    }
    /**
     * @Description 字符串循环移位
     * @Param [s, k]
     * @return java.lang.String
     */


    public String stringShiftK(String s,int k){
        if(k==0||k==s.length())
            return s;
        int len=s.length();
        k= k%len;
        String news=s.substring(len-k)+s.substring(0,len-k);
        return news;
    }
    /**
     * @Description 计算一组字符集合可以组成的回文字符串的最大长度
     * @Param [s]
     * @return int
     */
    public int longestPalindrome(String s){
        HashMap<Character,Integer> map=new HashMap<>();
        // String不能使用for-each直接遍历，先转为字符数组
        for(char c:s.toCharArray()){
            //由于哈希的键是唯一的，put方法会不断更新value
            map.put(c,map.getOrDefault(c,0)+1);

        }
        System.out.println(map);
        int longest=0;
        boolean oddFlag=false;
        for(int num:map.values()){
            // 有单独出现的字符就放在最中间，但只能有一个，所以用flag控制
            if(num==1&&!oddFlag) {
                longest += num;
                oddFlag = true;
                continue;
            }
            //若字符正好有偶数个，则全部添加；否则只拿其中的偶数个
            if(num%2==0)
                longest+=num;
            else
                longest+=(num/2)*2;
        }
        return longest;
    }
    /**
     * @Description 回文字符串个数
     * @Param [s]
     * @return int
     */
    //时间复杂度o(n^3)
    public int countSubstrings(String s){
        //单个字符也算
        int cnt=s.length();
        for(int i=0;i<s.length();i++){
            //不断朝着字符串尾扩充
            for(int j=i+1;j<s.length();j++){
                // i:start j:end
                int start=i,end=j;
                while(start<=end){
                    if(s.charAt(start)==s.charAt(end)){
                        start++;
                        end--;
                    }else
                        break;
                }
                //start>end说明截取的字符串是回文
                if(start>end)
                    cnt++;
            }
        }
        return cnt;
    }
    private int cnt = 0;

    public int countSubstrings1(String s) {
        for (int i = 0; i < s.length(); i++) {
            //遍历字符串，每个字符作为回文串的中心向两端扩展，扩展结果要么偶数长度要么奇数长度
            extendSubstrings(s, i, i);     // 奇数长度
            extendSubstrings(s, i, i + 1); // 偶数长度
        }
        return cnt;
    }
    //extend传入的是字符串中心，奇数长度，中心只有一个，偶数长度，双中心
    private void extendSubstrings(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            //两边扩展，所以start--,end++
            start--;
            end++;
            cnt++;
        }
    }
    public boolean integerisPalidrom(int x){
        if(x<0)
            return false;
        if(x<10)
            return true;
        int right=0;
        while(right<x){
            right=(x%10)+right*10;
            x/=10;
        }
        //偶数长度，左右两半应该刚好相等；奇数长度,右半部分比左半部分多了一个中心数
        return right==x||right/10==x;
    }
    private int total=0;
    public int countBinarySubstrings(String s) {
        if(s==null||s.length()<2)
            return 0;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)!=s.charAt(i-1)){
                extendInt(s,i-1,i);
            }

        }
        return total;
    }

    private void extendInt(String s,int start,int end){
        char left=s.charAt(start);
        char right=s.charAt(end);
        while(start>=0&&end<s.length()&&s.charAt(start)!=s.charAt(end)){
            //再加一层约束:如果传过来的是01，那么往两边扩展时必须保证左边是连续的0，而右边是连续的1；反之10
            if(s.charAt(start)==left&&s.charAt(end)==right)
                total++;
            start--;
            end++;

        }
    }
}
