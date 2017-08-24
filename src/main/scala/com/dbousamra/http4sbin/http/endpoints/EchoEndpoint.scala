package com.dbousamra.http4sbin.http.endpoints

import cats.effect.IO
import com.dbousamra.http4sbin.http._
import org.http4s._
import org.http4s.dsl._

object EchoEndpoint extends Endpoint[IO] {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/echo",
      method = None,
      description = "Echos back your request, as a response"
    )

  val service: HttpService[IO] = HttpService[IO] {
    case req @ _ -> Root / "echo" =>
      IO.pure(Response(Status.Ok, req.httpVersion, req.headers, body = req.body, req.attributes))
  }
}
