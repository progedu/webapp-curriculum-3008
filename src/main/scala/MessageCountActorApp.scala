import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class CountUpActor extends Actor {
  val counter = new AtomicInteger(0)

  override def receive: Receive = {
    case "countUp" => {
      val count = counter.incrementAndGet()
      println(count)
    }
  }
}

object MessageCountActorApp extends App {
  val MAX_COUNT = 10000
  val system = ActorSystem("messageCountActor")
  val myActor = system.actorOf(Props[CountUpActor])

  for (i <- 1 to MAX_COUNT) {
    myActor ! "countUp"
    // スリープが無いと途中で死んでしまう
    Thread.sleep(1)
  }

  Await.ready(system.terminate(), Duration.Inf)
}
