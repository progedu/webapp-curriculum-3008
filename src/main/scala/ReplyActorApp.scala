import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.concurrent.duration._

class Bot1 extends Actor {
  def receive = {
    case message =>
      Thread.sleep(1000)
      println(s"受信Box1: $message")
      sender() ! "せやね"
  }
}

class Bot2(bot1: ActorRef) extends Actor {
  def receive = {
    case message =>
      Thread.sleep(1000)
      println(s"受信Box2: $message")
      bot1 ! "並行処理難しいよね"
  }
}

object ReplyActorApp extends App {
  val system = ActorSystem("replyActor")

  val bot1 = system.actorOf(Props[Bot1], "bot1")

  val bot2 = system.actorOf(Props(classOf[Bot2], bot1), "bot2")

  import system.dispatcher

  system.scheduler.scheduleOnce(500.milliseconds) {
    bot2 ! "無限チャットを開始"
  }
}
