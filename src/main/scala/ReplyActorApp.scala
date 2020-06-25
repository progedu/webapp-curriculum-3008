import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

case object Ping
case object Pong

class PingActor extends Actor {
  val log = Logging(context.system,this)

  def receive ={
    case Ping => {
      log.info("ping")
      sender ! Pong
    }
    case _ =>
  }
}

class PongActor extends Actor {
  val log = Logging(context.system,this)

  def resive ={
    case Pong => {
      log.info("pong")
      sender ! Ping
    }
    case _ =>
  }
}

object ReplyActorApp {
  val system = ActorSystem("ReplyActorApp")
  val pingActor = system.actorOf(Props[PingActor], "pingActor")
  val pongActor = system.actorOf(Props[PongActor], "pongActor")
  
  pingActor.tell(Ping,pongActor)
}
