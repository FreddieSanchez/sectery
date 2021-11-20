package sectery.producers

import sectery._
import zio.Inject._
import zio._
import zio.test.Assertion.equalTo
import zio.test.TestAspect._
import zio.test._
import zio.test.environment.TestClock

object BlinkSpec extends DefaultRunnableSpec:
  override def spec =
    suite(getClass().getName())(
      test("@blink foo produces blinking foo") {
        for
          (inbox, outbox, _) <- MessageQueues.loop
            .inject_(TestDb(), TestHttp())
          _ <- inbox.offer(Rx("#foo", "bar", "@blink foo"))
          _ <- TestClock.adjust(1.seconds)
          ms <- outbox.takeAll
        yield assert(ms)(
          equalTo(
            List(Tx("#foo", "\u0006foo\u0006"))
          )
        )
      } @@ timeout(2.seconds)
    )