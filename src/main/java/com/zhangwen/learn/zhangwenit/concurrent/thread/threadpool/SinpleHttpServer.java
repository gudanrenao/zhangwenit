package com.zhangwen.learn.zhangwenit.concurrent.thread.threadpool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description java并发编程的艺术 4.4.4 基于线程池技术的简单web服务器
 * todo：构建socket并发请求
 * @Author ZWen
 * @Date 2020/4/13 10:57 PM
 * @Version 1.0
 **/
public class SinpleHttpServer {

    private static int port = 8080;

    private static ServerSocket serverSocket;

    private static String basePath = "/Users/zhangwen/work/code/zhangwenit/src/main/java/com/zhangwen/learn/zhangwenit/concurrent/thread/threadpool";

    private static ThreadPool<HttpRequestHandler> threadPool;

    private static void listen() throws IOException {
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null) {
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        threadPool = new DefaultThreadPool<>(10);
        listen();
    }

    private static class HttpRequestHandler implements Runnable {

        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            //todo:doSomeThing
            BufferedReader reader = null;
            InputStream in = null;
            PrintWriter out = null;
            BufferedReader br = null;
            String line = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
                String header = reader.readLine();
                //拼接请求资源路径
                String filePath = basePath + header.split(" ")[1];
                //判断是图片还是html
                if (filePath.endsWith("png") || filePath.endsWith("jpg") || filePath.endsWith("jpeg")) {
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int i = 0;
                    while ((i = in.read()) != -1) {
                        bos.write(i);
                    }
                    byte[] array = bos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: " + array.length);
                    out.println("");
                    out.flush();
                    socket.getOutputStream().write(array, 0, array.length);
                } else {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println();
                    while ((line = br.readLine()) != null) {
                        out.println(line);
                    }
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                if (out == null) {
                    try {
                        out = new PrintWriter(socket.getOutputStream());
                        out.println("HTTP/1.1 500");
                        out.println();
                        out.flush();
                    } catch (IOException e1) {

                    }
                }
            } finally {
                close(reader, in, out, br, socket);
            }
        }

        private void close(Closeable... closeables) {
            for (Closeable closeable : closeables) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}