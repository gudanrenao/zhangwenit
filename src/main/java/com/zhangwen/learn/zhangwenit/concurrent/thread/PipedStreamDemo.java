package com.zhangwen.learn.zhangwenit.concurrent.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Description 管道输入/输出流，用于线程之间的数据传输，传输媒介是内存
 * @Author ZWen
 * @Date 2019/4/4 6:26 PM
 * @Version 1.0
 **/
public class PipedStreamDemo {

    /**
     * {@link java.io.PipedOutputStream} 字节输出流
     * {@link java.io.PipedInputStream} 字节输入流
     * {@link java.io.PipedWriter}  字符输出流
     * {@link java.io.PipedReader}  字符输入流
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        PipedWriter pipedWriter = new PipedWriter();
        PipedReader pipedReader = new PipedReader();
        //将输出流和输入流进行连接，否则在使用是会抛出IOException
        pipedWriter.connect(pipedReader);

        Thread printThread = new Thread(new Print(pipedReader), "printThread");
        printThread.start();
        try {
            int receive = 0;
            while ((receive = System.in.read()) != -1) {
                pipedWriter.write(receive);
                System.out.println("receive is " + (char) receive);
            }
        } finally {
            pipedWriter.close();
        }
    }

    static class Print implements Runnable {

        private PipedReader pipedReader;

        public Print(PipedReader pipedReader) {
            this.pipedReader = pipedReader;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = pipedReader.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pipedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}