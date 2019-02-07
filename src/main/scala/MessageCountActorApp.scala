import akka.actor.{Actor, ActorSystem, Inbox, Props}
import scala.concurrent.duration._

case object Count
case object Get

class CountUpActor(countEnd: Int) extends Actor {
  var count = 0

  def receive =  {
    case Count =>
      count += 1
      println(s"count: $count")
    case Get   => sender ! ""
  }
}

object MessageCountActorApp extends App {
  val system = ActorSystem("MessageCountActorApp")

  val countTimes = 10000
  val counter = system.actorOf(Props(classOf[CountUpActor], countTimes))
  val inbox = Inbox.create(system)

  for (_ <- 1 to countTimes) { counter ! Count }
  inbox.send(counter, Get)
  inbox.receive(5.seconds)
  println("count end")

  system.terminate()

}
