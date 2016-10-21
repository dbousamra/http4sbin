package com.dbousamra.http4sbin.http.endpoints

import argonaut._
import Argonaut._
import com.dbousamra.http4sbin.http._
import org.http4s._
import org.http4s.dsl._
import org.http4s.argonaut._

import scalaz.concurrent.Task

object EchoEndpoint extends Endpoint {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/echo",
      method = None,
      description = "Echos back your request, as a response"
    )

  val service: HttpService = HttpService {
    case req @ _ -> Root / "echo" =>
      Task.now(Response(Status.Ok, req.httpVersion, req.headers, body = req.body, req.attributes))
  }
}
