//package com.zhangwen.learn.zhangwenit.temp.service;
//
//import com.zhangwen.learn.zhangwenit.temp.entities.TempOpenId;
//import org.apache.commons.lang3.time.DateUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//import java.util.concurrent.CopyOnWriteArraySet;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TempOpenIdServiceTest {
//
//    @Autowired
//    private TempOpenIdService tempOpenIdService;
//
//    private CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
//
//
//    @Test
//    public void insert() throws IOException {
//        List<String> baseDirs = new ArrayList<>();
//        baseDirs.add("/Users/zhangwen/Desktop/prod-1");
//        baseDirs.add("/Users/zhangwen/Desktop/prod-2");
//
//        for (String baseDir : baseDirs){
//            File baseFile = new File(baseDir);
//            File[] files = baseFile.listFiles();
//            for (File file : files) {
//                BufferedReader reader = new BufferedReader(new FileReader(file));
//                String str = null;
//                while ((str = reader.readLine()) != null){
//                    if(str.contains("消息发送成功")){
//                        int begin = str.indexOf("[");
//                        int end = str.indexOf("]");
//                        set.add(str.substring(begin + 1,end));
//                    }
//                }
//                reader.close();
//            }
//        }
//
//        System.out.println(set.size());
//
//        for (String openId : set){
//            tempOpenIdService.insert(openId);
//        }
//
//    }
//
//    @Test
//    public void count() throws Exception{
//        CopyOnWriteArrayList<TempOpenId> list = new CopyOnWriteArrayList<>();
//        List<String> baseDirs = new ArrayList<>();
//        baseDirs.add("/Users/zhangwen/Desktop/prod-1");
//        baseDirs.add("/Users/zhangwen/Desktop/prod-2");
//        String pattern = "yyyy-MM-dd HH:mm:ss";
//
//        for (String baseDir : baseDirs){
//            File baseFile = new File(baseDir);
//            File[] files = baseFile.listFiles();
//            for (File file : files) {
//                BufferedReader reader = new BufferedReader(new FileReader(file));
//                String str = null;
//                while ((str = reader.readLine()) != null){
//                    if(str.contains("TemplateMsgSendService|消息发送成功")){
//                        TempOpenId tempOpenId = new TempOpenId();
//                        int begin = str.indexOf("[");
//                        int end = str.indexOf("]");
//                        tempOpenId.setOpenId(str.substring(begin + 1,end));
//                        int time = str.indexOf(".");
//                        String date = str.substring(0,time);
//                        Date sendTime = DateUtils.parseDate(date,pattern);
//                        tempOpenId.setSendTime(sendTime);
//                        int typeBegin = str.lastIndexOf("[");
//                        int typeEnd = str.lastIndexOf("]");
//                        String temId = str.substring(typeBegin + 1, typeEnd);
//
//                        switch (temId) {
//                            case "ciDCCSv5A6HsDVVshz5A9KKpEjRv6W6LaKF3i6HJfz0":
//                                tempOpenId.setType("预约成功");
//                                break;
//                            case "KGeKJT1aQxKQmjVA2JH8m4SE3joGGsaDOmFSC7emsOo":
//                                tempOpenId.setType("挂号成功");
//                                break;
//                            case "lQ5KEbiir73_KeVd_I_ofE3D8wQN79tmVsU8PoUMuAA":
//                                tempOpenId.setType("缴费提醒");
//                                break;
//                            case "iyeN5oUDPLMDImnivaqaBgeU2LK8CwAUm1g4bEOO5ww":
//                                tempOpenId.setType("报告结果");
//                                break;
//                            case "xtaASe6efTG1B6wvb8gztBN-zUGxlWY11uU2JhW86yQ":
//                                tempOpenId.setType("就诊排队");
//                                break;
//                            case "-be_TvByXndVuAI8lPAvQcknVY2DARWAxY8bawte7hY":
//                                tempOpenId.setType("住院押金");
//                                break;
//                            default:
//                                tempOpenId.setType("未知");
//                        }
//                        list.add(tempOpenId);
//                    }
//                }
//                reader.close();
//            }
//        }
//
//        System.out.println(list.size());
//
//        tempOpenIdService.insertAll(list);
//    }
//
//}