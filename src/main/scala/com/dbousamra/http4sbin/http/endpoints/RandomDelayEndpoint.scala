package com.dbousamra.http4sbin.http.endpoints

import com.dbousamra.http4sbin.http._
import com.dbousamra.http4sbin.http.endpoints.Helpers._
import org.http4s.HttpService
import org.http4s.dsl._

import scala.util.Random
import scalaz.concurrent.Task

object RandomDelayEndpoint extends Endpoint {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/delay/random/:n",
      method = None,
      description = "Delays responding for a random amount of time, between 0 and n seconds. (max 60 seconds)"
    )

  val service: HttpService = HttpService {
    case req @ _ -> Root / "delay" / "random" / DelayTimeParamMatcher(n) => {
      Task.delay { Thread.sleep(Random.nextInt(n) * 1000) }.flatMap(_ => Ok())
    }
  }
}
