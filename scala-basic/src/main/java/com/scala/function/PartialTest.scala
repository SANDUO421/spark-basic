package com.scala.function;
import java.util.Date

/**
  * 偏函数
 * @author 三多
 * @Time 2019/5/12
 */
object PartialTest {
  def main(args: Array[String]) {
    val date = new Date
    log(date, "message1" )
    Thread.sleep(1000)
    log(date, "message2" )
    Thread.sleep(1000)
    log(date, "message3" )

    //偏函数
    val logWithDateBound = log(date, _ : String)
    logWithDateBound("message1" )
    Thread.sleep(1000)
    logWithDateBound("message2" )
    Thread.sleep(1000)
    logWithDateBound("message3" )
  }

  def log(date: Date, message: String)  = {
    println(date + "----" + message)
  }

}
