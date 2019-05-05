# Spark 基础
## spark 入门 java 
1.[spark完整入门](https://blog.csdn.net/m0_37601109/article/details/81252410)<br/>
**注意:**<br/>
（1）spark的版本和开发版本一致否则报错:**Caused by: java.lang.AbstractMethodError: com.spark.java.spark.TestSpark$3.c**<br/>
（2）打包插件不对会导致找不到主类:**java.lang.ClassNotFoundException: com.spark.java.spark.TestS**<br/>
（3）必须配置java环境，否则启动报错<br/>
（4）运行命令：spark-submit --class "com.spark.java.spark.TestSpark" ./java-wordcount-1.0.0.jar<br/>