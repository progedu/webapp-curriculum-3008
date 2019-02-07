import akka.actor.{Actor, ActorSystem, Props}

case object Ping
case object Pong


  class PingActor extends Actor {

    def receive = {
      case Pong =>
        println("Ping!")
        Thread.sleep(1000)
        sender() ! Ping
    }
  }

  class PongActor extends Actor {

    def receive = {
      case Ping =>
        println("Pong!")
        Thread.sleep(1000)
        sender() ! Pong
    }
  }

object ReplyActorApp extends App {

  val system = ActorSystem("ReplyActorApp")

  val ping = system.actorOf(Props[PingActor], "ping")
  val pong = system.actorOf(Props[PongActor], "pong")

  ping.tell(Pong, pong)

  Thread.currentThread().join()

}
