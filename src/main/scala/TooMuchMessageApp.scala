import java.text.SimpleDateFormat
import java.util.Date

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

class HeavyReceiver extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case d: String =>
      Thread.sleep(1000)
      log.info(d)
  }
}

object TooMuchMessageApp extends App {
  val system = ActorSystem("tooMuchMessageApp")

  val heavyReceiver = system.actorOf(Props[HeavyReceiver], "heavyReceiver")

  val formatPattern = new SimpleDateFormat("yyyy年MM月dd日E曜日H時mm分ss秒")

  while (true) {
    heavyReceiver ! formatPattern.format(new Date())
  }

  system.terminate()
}
