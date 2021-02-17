## 本项目默认配置
-XX:-BytecodeVerificationLocal 
-XX:-BytecodeVerificationRemote 
-XX:G1ConcRefinementThreads=10 
-XX:GCDrainStackTargetSize=64 
-XX:InitialHeapSize=536870912 
-XX:+ManagementServer 
-XX:MaxHeapSize=8589934592 
-XX:+PrintCommandLineFlags 
-XX:ReservedCodeCacheSize=251658240 
-XX:+SegmentedCodeCache 
-XX:TieredStopAtLevel=1 
-XX:+UseCompressedClassPointers 
-XX:+UseCompressedOops -XX:+UseG1GC 


## 参考线上项目配置

JAVA_OPTS="$JAVA_OPTS  
-XX:NewRatio=2  
-XX:G1HeapRegionSize=8m  
-XX:MetaspaceSize=256m  
-XX:MaxMetaspaceSize=256m  
-XX:MaxTenuringThreshold=10  
-XX:+UseG1GC  
-XX:InitiatingHeapOccupancyPercent=45  
-XX:MaxGCPauseMillis=200  
-verbose:gc
-XX:+PrintGCDetails  
-XX:+PrintGCTimeStamps  
-XX:+PrintReferenceGC
-XX:+PrintAdaptiveSizePolicy  
-XX:+UseGCLogFileRotation  
-XX:NumberOfGCLogFiles=6
-XX:GCLogFileSize=32m  
-Xloggc:./var/run/gc.log.$(date+%Y%m%d%H%M)  
-XX:+HeapDumpOnOutOfMemoryError  
-XX:HeapDumpPath=./var/run/heap-dump.hprof  
-Dfile.encoding=UTF-8  
-Dcom.sun.management.jmxremote  
-Dcom.sun.management.jmxremote.port=${JMX_PORT:-0}  
-Dcom.sun.management.jmxremote.ssl=false  
-Dcom.sun.management.jmxremote.authenticate=false  

## 释义
### 垃圾回收期
* -XX:+UseSerialGC 年轻代和老年代都用串行收集器
* -XX:+UseParNewGC 年轻代使用 ParNew，老年代使用 Serial Old
* -XX:+UseParallelGC 年轻代使用 ParallelGC，老年代使用 Serial Old
* -XX:+UseParallelOldGC 新生代和老年代都使用并行收集器
* -XX:+UseConcMarkSweepGC，表示年轻代使用 ParNew，老年代的用 CMS
* -XX:+UseG1GC 使用 G1垃圾回收器
* -XX:+UseZGC 使用 ZGC 垃圾回收器

### 日志
* -verbose:gc 打印GC日志
* -XX:+PrintGCDetails 打印详细 GC 日志
* -XX:+PrintTenuringDistribution 打印对象年龄分布，对调优 MaxTenuringThreshold 参数帮助很大
* -Xloggc:gc.log.$(date+%Y%m%d%H%M) 将以上 GC 内容输出到文件中
* -XX:-OmitStackTraceInFastThrow 缩简日志输出（默认+，-号表示去掉，这样日志会变多）

### OOM
* -XX:+HeapDumpOnOutOfMemoryError OOM 时 Dump 信息，非常有用
* -XX:HeapDumpPath=/var/run/heap-dump.hprof  Dump 文件保存路径
* -XX:ErrorFile=/tmp/logs/hs_error_pid%p.log 错误日志存放路径


