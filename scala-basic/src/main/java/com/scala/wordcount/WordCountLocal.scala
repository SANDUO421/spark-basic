package com.scala.wordcount

import org.apache.spark._

/**
  *
  * @author 三多
  * @Time 2019/5/13
  */
object WordCountLocal {
  def main(args: Array[String]): Unit = {
//    System.setProperty("hadoop.home.dir","D:\\devsoft\\env\\hadoop-common-2.2.0-bin-master")
    /**
      * SparkContext 的初始化需要一个SparkConf对象
      * SparkConf 包含了Spark集群的各种参数
      */
     val conf  = new SparkConf()
       .setAppName("wordCountTest")//设置程序名称
       .setMaster("local")//启动本地计算
    //Spark 程序的编写都是SparkContext开始的
    val sc = new SparkContext(conf);
    sc.textFile("G:\\workspace\\idea\\git\\tourism\\spark-basic\\scala-basic\\src\\main\\resources\\hello.txt") //读取本地文件
      .flatMap(_.split(" "))//下划线是占位符，flatMap 是对行操作的方法，对读取的数据进行分割
      .map((_,1))//将每一项转换为key-value，数据是key，value 是1
      .reduceByKey(_+_)//将相同key向相加合并一个
      .collect()//将分布式的RDD返回一个单机的scala array，在这个数组上运行scala的函数操作，并返回结果到驱动程序
      .foreach(println)//循环打印

  }

}
