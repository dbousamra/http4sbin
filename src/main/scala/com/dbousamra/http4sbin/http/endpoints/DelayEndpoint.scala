package com.dbousamra.http4sbin.http.endpoints

import com.dbousamra.http4sbin.http._
import com.dbousamra.http4sbin.http.endpoints.Helpers._
import org.http4s.HttpService
import org.http4s.dsl._

import scalaz.concurrent.Task

object DelayEndpoint extends Endpoint {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "delay/:n",
      method = None,
      description = "Delays responding for n seconds. (max 30 seconds)"
    )

  val service: HttpService = HttpService {
    case req @ _ -> Root / "delay" / DelayTimeParamMatcher(n) => {
      Task.delay { Thread.sleep(n * 1000) }.flatMap(_ => Ok())
    }
  }
}


