import akka.actor.{Actor, ActorSystem, Props}

case object Greet

class Greeter1 extends Actor {
  def receive = {
    case Greet => {
      println("おい！")
      sender ! Greet
    }
  }
}

class Greeter2 extends Actor {
  def receive = {
    case Greet => {
      println("こら！")
      sender ! Greet
    }
  }
}

object ReplyActorApp extends App {
  val system = ActorSystem("replyActor")

  val greeter1 = system.actorOf(Props[Greeter1], "greeter1")
  val greeter2 = system.actorOf(Props[Greeter2], "greeter2")

  greeter1.tell(Greet, greeter2)  // greeter1 に Greet を送信
}
