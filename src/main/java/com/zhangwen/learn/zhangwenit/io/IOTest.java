package com.zhangwen.learn.zhangwenit.io;

import com.zhangwen.learn.zhangwenit.io.dto.Student;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.*;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/1/25 10:06 AM
 * @Version 1.0
 **/
public class IOTest implements Serializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("zw", "http", 18);

//        String jsonString = JSON.toJSONString(student);
//
//        System.out.println(jsonString);

        //将对象序列化到文件中
//        FileOutputStream fileOutputStream = new FileOutputStream("/Users/zhangwen/work/code/zhangwenit/src/main/resources/io/student.ser");
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//
//        objectOutputStream.writeObject(student);
//
//        objectOutputStream.close();
//        fileOutputStream.close();

        //将文件反序列化到对象中
//        FileInputStream fileInputStream = new FileInputStream("/Users/zhangwen/work/code/zhangwenit/src/main/resources/io/student.ser");
//        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//        Object readObject = objectInputStream.readObject();
//        Student readStu = (Student) readObject;
//        System.out.println(readStu.toString());
//        objectInputStream.close();
//        fileInputStream.close();


        //FileInputStream
//        InputStream inputStream = new FileInputStream("/Users/zhangwen/work/code/zhangwenit/src/main/resources/io/file1");
//        int i;
//        while ((i = inputStream.read()) != -1) {
//            System.out.print((char)i);
//        }
//        inputStream.close();

        //FileOutputStream
//        OutputStream outputStream = new FileOutputStream("/Users/zhangwen/work/code/zhangwenit/src/main/resources/io/fileOutputStream1");
        //OutputStreamWriter 默认file.encoding,没有就是utf-8
//        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
//        outputStreamWriter.append(" append3 \n");
//        outputStreamWriter.append(" append4 \t");
//        outputStreamWriter.getEncoding();
//        outputStreamWriter.close();
//        outputStream.close();

        //file
//        File file = new File("/Users/zhangwen/work/code/zhangwenit/src/main/resources/io/file/fileDir1");
//        boolean mkdir = file.mkdir();
//        boolean mkdirs = file.mkdirs();
//        System.out.println(mkdir);
//        System.out.println(mkdirs);

        //统计某个file下有多少个文件
//        File file = new File("/Users/zhangwen/work/code/zhangwenit/src/main/resources/io");
//        int count = countFile(file, 0);
//        System.out.println("文件数量为： " + count);

        //过滤以 .java结尾的文件
//        File file = new File("/Users/zhangwen/work/code/zhangwenit/src/main/resources/io");
//        File[] listFiles = file.listFiles(IOTest::filterJava);
//        Arrays.asList(listFiles).forEach(e -> System.out.println(e.getAbsolutePath()));


        //ByteArrayInputStream ByteArrayOutputStream
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(10);
//        while (outputStream.size() < 10) {
//            outputStream.write(System.in.read());
//        }
//        byte[] bytes = outputStream.toByteArray();
//
//        InputStream inputStream = new ByteArrayInputStream(bytes);
//        int i;
//        System.out.println("输出 大写 字母");
//        while ((i=inputStream.read()) != -1){
//            System.out.print(Character.toUpperCase((char)i) + " ");
//        }

        //DataInputStream DataOutputStream
//        File file = new File("/Users/zhangwen/work/code/zhangwenit/src/main/resources/io/data/input");
//        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
//        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("/Users/zhangwen/work/code/zhangwenit/src/main/resources/io/data/output"));
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
//        String line;
//        while ((line = bufferedReader.readLine()) != null){
//            dataOutputStream.writeBytes(line.toUpperCase() + "   ");
//        }
//        bufferedReader.close();
//        dataInputStream.close();
//        dataOutputStream.close();

//        FileInputStream inputStream = new FileInputStream(new File("/Users/zhangwen/work/code/zhangwenit/src/main/resources/io/test"));
//
//        byte[] bytes = StreamUtils.copyToByteArray(inputStream);
//
//        System.out.println(bytes.length);

        DefaultUriBuilderFactory uriFactory = new DefaultUriBuilderFactory();
        uriFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);
        Map<String,Object> params = new HashMap<>(4);
        params.put("name","张文");
        params.put("age",18);
        params.put("birthday","1992-01-07");
        URI expand = uriFactory.expand("http://localhost:8080", params);
        System.out.println(expand);

    }

    public static int countFile(File file, int count) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File item : files) {
                    if (!file.isDirectory()) {
                        count++;
                    } else {
                        count = countFile(item, count);
                    }
                }
                return count;
            }
            return count;
        }
        return count + 1;
    }

    public static boolean filterJava(File file,String fileName){
//        System.out.println(file.getName());
        return fileName.contains(".java");
    }
}