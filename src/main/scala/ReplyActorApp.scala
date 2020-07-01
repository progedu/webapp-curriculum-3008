import akka.actor.{Actor, ActorSystem, Props}
import akka.event.{Logging, LoggingAdapter}

case object Ping
case object Pong

class PingActor extends Actor {
  val log: LoggingAdapter = Logging(context.system, this)

  override def receive: Receive = {
    case Ping =>
      log.info("ping")
      sender() ! Pong
    case _ =>
  }
}

class PongActor extends Actor {
  val log: LoggingAdapter = Logging(context.system, this)

  override def receive: Receive = {
    case Pong =>
      log.info("pong")
      sender() ! Ping
    case _ =>
  }
}

object ReplyActorApp extends App {

  val system = ActorSystem("replyActorApp")

  val pingActor = system.actorOf(Props[PingActor], "pingActor")

  val pongActor = system.actorOf(Props[PongActor], "pongActor")

  pingActor.tell(Ping, pongActor)

}
