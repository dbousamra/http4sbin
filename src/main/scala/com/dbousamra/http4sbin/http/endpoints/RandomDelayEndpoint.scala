package com.dbousamra.http4sbin.http.endpoints

import cats.effect.Async
import com.dbousamra.http4sbin.http._
import com.dbousamra.http4sbin.http.endpoints.Helpers._
import fs2.Scheduler
import org.http4s.HttpService
import org.http4s.dsl._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.util.Random

class RandomDelayEndpoint[F[_]: Async](
    implicit executionContext: ExecutionContext,
    scheduler: Scheduler
) extends Endpoint[F] {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/delay/random/:n",
      method = None,
      description =
        "Delays responding for a random amount of time, between 0 and n seconds. (max 30 seconds)"
    )

  val service: HttpService[F] = HttpService[F] {
    case _ -> Root / "delay" / "random" / DelayTimeParamMatcher(n) =>
      scheduler.effect.delay(Ok(), Random.nextInt(n + 1).seconds)
  }
}
