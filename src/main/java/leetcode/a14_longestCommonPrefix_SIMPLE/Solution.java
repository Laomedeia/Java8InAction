package leetcode.a14_longestCommonPrefix_SIMPLE;

/**
 *  最长公共前缀
 *  写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 *
 * @author neptune
 * @create 2020 06 15 7:59 下午
 */
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs==null||strs.length==0) return "";
        for(int i=1;i<strs.length;i++){
            //单次求取每个元素的公共前缀，将第一个元素作为参照，循环将第一个元素长度从后缩短一个判断
            while(strs[i].indexOf(strs[0])!=0){
                strs[0]=strs[0].substring(0, strs[0].length()-1);
                if(strs[0].isEmpty()) return "";
            }
        }
        return strs[0];
    }
}
