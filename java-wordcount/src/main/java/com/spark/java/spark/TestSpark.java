package com.spark.java.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 测试 Spark
 *
 * @author 三多
 * @Time 2019/5/5
 */
public class TestSpark {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setAppName("wordCount");
        JavaSparkContext context = new JavaSparkContext(conf);

        List<String> list = new ArrayList<>();
        list.add("a b c d e f");
        list.add("a b c d e f g");
        JavaRDD<String> rdd = context.parallelize(list);

        JavaPairRDD<String, Integer> output = rdd.flatMap(new FlatMapFunction<String, String>() {
            //map 切分:先切分为单词，扁平化处理
            @Override
            public Iterator<String> call(String x) {
                //该处一定要注意****一定要转为Iterator，否则spark提交任务时，直接报错。我就被坑了。。。
                return Arrays.asList(x.split(" ")).iterator();

            }

        }).mapToPair(new PairFunction<String, String, Integer>() {
            //reduce ：再转化为键值对并计算
            @Override
            public Tuple2<String, Integer> call(String input) throws Exception {
                return new Tuple2<>(input, 1);
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            //reduce ：按照key分组聚合
            @Override
            public Integer call(Integer x, Integer y) throws Exception {
                return x + y;
            }
        });
        String outFile = "/root/zhanglu/data/clean_data";
        File file = new File(outFile);
        if(file.exists() && file.isDirectory()){
            file.delete();
        }
        output.saveAsTextFile(outFile);
        context.close();
    }
}
