package victorops.example

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{ Actor, ActorLogging }
import victorops.example.CountingActor.{ GetCount, IncrementCount, ResetCount }

object CountingActor {
  case object GetCount
  case object IncrementCount
  case object ResetCount
}

class CountingActor extends Actor with ActorLogging {
  private val count = new AtomicInteger(0)

  override def receive: Receive = {
    case GetCount =>
      sender() ! count.get()
      log.info(s"Got GetCount [count=$count]")

    case IncrementCount =>
      sender() ! count.incrementAndGet()
      log.info(s"Got IncrementCount [count=$count]")

    case ResetCount =>
      sender() ! count.getAndSet(0)
      log.info(s"Got ResetCount [count=$count]")

  }
}
