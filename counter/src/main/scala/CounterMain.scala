import akka.actor.{ ActorSystem, Props }
import victorops.example.CountingActor

object CounterMain extends App {

  val system = ActorSystem("CounterActorSystem")

  val countingActor = system.actorOf(Props(classOf[CountingActor]), "counting-actor")

  system.awaitTermination()

}
