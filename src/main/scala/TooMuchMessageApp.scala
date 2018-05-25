import java.text.SimpleDateFormat

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import java.util.Date

import scala.util.Random

class HeavyWorkActor extends Actor {
  val MIN_WAIT = 1000
  val RANDOM_MAX = 1000
  val random = new Random()
  val logging = Logging(context.system, this)

  override def receive: Receive = {
    case date: Date => {
      val formatter = new SimpleDateFormat("YYYY年MM月dd日 HH時mm分ss秒")
      logging.info(formatter.format(date))
      // 1000ms〜2000msのwait
      val waitTime = random.nextInt(RANDOM_MAX) + MIN_WAIT
      Thread.sleep(waitTime)
    }
  }
}

object TooMuchMessageApp extends App {
  val system = ActorSystem("tooMuchMessage")
  val heavyActor = system.actorOf(Props[HeavyWorkActor])

  while (true) {
    heavyActor ! new Date()
  }
}
