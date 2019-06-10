package com.zhangwen.learn.zhangwenit.basic.reuse_class.delegation.auto_generate;

/**
 * @Description SpaceShip 代理类  idea自动生成代理方法
 * @Author ZWen
 * @Date 2019/5/30 11:15 AM
 * @Version 1.0
 **/
public class SpaceShipDelegate {

    private SpaceShipControls controls = new SpaceShipControls();

    public void up(int i) {
        controls.up(i);
    }

    public void down(int i) {
        controls.down(i);
    }

    public void left(int i) {
        controls.left(i);
    }

    public void right(int i) {
        controls.right(i);
    }

    public void forward(int i) {
        controls.forward(i);
    }

    public void back(int i) {
        controls.back(i);
    }
}