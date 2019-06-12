package com.zhangwen.learn.zhangwenit.basic.java.lang;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/6/11 10:30 AM
 * @Version 1.0
 **/
public class SystemDemo {

    public static void main(String[] args) {
        properties();
    }

    /**
     * gopherProxySet : false
     * awt.toolkit : sun.lwawt.macosx.LWCToolkit
     * http.proxyHost : localhost
     * java.specification.version : 10
     * file.encoding.pkg : sun.io
     * sun.cpu.isalist :
     * sun.jnu.encoding : UTF-8
     * java.class.path : /Users/zhangwen/work/code/zhangwenit/target/classes:/Users/zhangwen/.m2/repository/io/springfox/springfox-swagger-ui/2.9.2/springfox-swagger-ui-2.9.2.jar:...等等
     * https.proxyPort : 63376
     * java.vm.vendor : "Oracle Corporation"
     * sun.arch.data.model : 64
     * java.vendor.url : http://java.oracle.com/
     * user.timezone :
     * os.name : Mac OS X
     * java.vm.specification.version : 10
     * sun.java.launcher : SUN_STANDARD
     * user.country : CN
     * sun.boot.library.path : /Library/Java/JavaVirtualMachines/jdk-10.0.2.jdk/Contents/Home/lib
     * sun.java.command : com.zhangwen.learn.zhangwenit.basic.java.lang.SystemDemo
     * jdk.debug : release
     * sun.cpu.endian : little
     * user.home : /Users/zhangwen
     * user.language : zh
     * java.specification.vendor : Oracle Corporation
     * java.version.date : 2018-07-17
     * java.home : /Library/Java/JavaVirtualMachines/jdk-10.0.2.jdk/Contents/Home
     * file.separator : /
     * https.proxyHost : localhost
     * java.vm.compressedOopsMode : Zero based
     * line.separator :
     *
     * java.specification.name : Java Platform API Specification
     * java.vm.specification.vendor : Oracle Corporation
     * java.awt.graphicsenv : sun.awt.CGraphicsEnvironment
     * user.script : Hans
     * sun.management.compiler : HotSpot 64-Bit Tiered Compilers
     * java.runtime.version : 10.0.2+13
     * user.name : zhangwen
     * path.separator : :
     * os.version : 10.14.3
     * java.runtime.name : Java(TM) SE Runtime Environment
     * file.encoding : UTF-8
     * java.vm.name : Java HotSpot(TM) 64-Bit Server VM
     * java.vendor.version : 18.3
     * java.vendor.url.bug : http://bugreport.java.com/bugreport/
     * java.io.tmpdir : /var/folders/ln/sfgrght924x9j09my0mdysb80000gn/T/
     * java.version : 10.0.2
     * user.dir : /Users/zhangwen/work/code/zhangwenit
     * os.arch : x86_64
     * java.vm.specification.name : Java Virtual Machine Specification
     * java.awt.printerjob : sun.lwawt.macosx.CPrinterJob
     * sun.os.patch.level : unknown
     * java.library.path : /Users/zhangwen/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:.
     * java.vendor : Oracle Corporation
     * java.vm.info : mixed mode
     * java.vm.version : 10.0.2+13
     * sun.io.unicode.encoding : UnicodeBig
     * java.class.version : 54.0
     * http.proxyPort : 63376
     */
    private static void properties() {
        System.getProperties().forEach((k, v) -> System.out.println(k + " : " + v));
    }
}