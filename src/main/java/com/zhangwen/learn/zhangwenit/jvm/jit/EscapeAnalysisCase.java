package com.zhangwen.learn.zhangwenit.jvm.jit;

/**
 * @Description 逃逸分析
 * 用户也可以使用参数-XX:+DoEscapeAnalysis来手动开启逃逸分析，
 * 开启之后可以通过参数-XX:+PrintEscapeAnalysis来查看分析结果。
 * 有了逃逸分析支持之后，用户可以使用参数-XX:+EliminateAllocations来开启标量替换，
 * 使用-XX:+EliminateLocks来开启同步消除，
 * 使用参数-XX:+PrintEliminateAllocations查看标量的替换情况
 * @Author ZWen
 * @Date 2020/4/8 6:44 PM
 * @Version 1.0
 **/
public class EscapeAnalysisCase {

    /**
     * -XX:+DoEscapeAnalysis -XX:+PrintEscapeAnalysis -XX:+PrintEliminateAllocations -XX:+EliminateLocks -XX:+EliminateAllocations
     * @param args
     */
    public static void main(String[] args) {
        int x = test(100);
        System.out.println(x);
    }

    private static int test(int x) {
        int xx = x + 2;
        Point point = new Point(xx, 24);
        return point.getX();
    }

    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}