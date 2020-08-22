package com.zhangwen.learn.zhangwenit.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description 有效的数独
 * <p>
 * ```
 * https://leetcode-cn.com/problems/valid-sudoku/
 * ```
 * @Author ZWen
 * @Date 2020/6/2 5:28 PM
 * @Version 1.0
 **/
public class Case36 {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        boolean flag = isValidSudoku(board);
        System.out.println(flag);

    }

    //Set
    public static boolean isValidSudoku(char[][] board) {
        //每一行数字不重复，key = x-n , add value 到 key = x-n-value 时，该key一定不存在
        //每一列数字不重复，key = y-n , add value 到 key = y-n-value 时，该key一定不存在
        //每个小九宫格不重复，key = (x % 3)-(y % 3), add value 到 key = (x % 3)-(y % 3)-value 时，该key一定不存在
        int num = 0;
        Set<String> includeSet = new HashSet<>();
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                num = board[x][y] - 48;
                if(num > -1){
                    //为数字
                    if(!includeSet.add("x-" + x + "-" + num)){
                        return false;
                    }
                    if(!includeSet.add("y-" + y + "-" + num)){
                        return false;
                    }
                    if(!includeSet.add((x / 3) + "-" + (y / 3) + "-" + num)){
                        return false;
                    }
                }
                // . 对应char - 48 = -2
            }
        }
        return true;
    }
}