package victorops.example

import akka.actor.{ ActorSelection, ActorSystem }
import akka.pattern._
import akka.util.Timeout
import victorops.example.Console.Imports
import victorops.example.CountingActor.{ GetCount, IncrementCount, ResetCount }

import scala.concurrent.duration._
import scala.concurrent.{ Await, Future }

trait CountingTasks {

  implicit def timeout: Timeout

  def system: ActorSystem

  def countingActor: ActorSelection

  def getCount(): Int = awaitResult {
    (countingActor ? GetCount).mapTo[Int]
  }

  def incrementCount(): Int = awaitResult {
    (countingActor ? IncrementCount).mapTo[Int]
  }

  def resetCount(): Int = awaitResult {
    (countingActor ? ResetCount).mapTo[Int]
  }

  private def awaitResult[T](f: Future[T]) = Await.result(f, Duration.Inf)
}

object Console {
  class Imports(val system: ActorSystem)
}

class Console(system: ActorSystem) {
  object imports extends Imports(system) with CountingTasks {
    implicit val timeout = Timeout(1 second)

    lazy val countingActor: ActorSelection = {
      system.actorSelection("akka.tcp://CounterActorSystem@127.0.0.1:2552/user/counting-actor")
    }
  }
}
