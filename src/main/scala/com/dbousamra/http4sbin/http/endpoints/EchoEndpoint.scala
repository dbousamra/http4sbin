package com.dbousamra.http4sbin.http.endpoints

import cats.Applicative
import com.dbousamra.http4sbin.http._
import org.http4s._
import org.http4s.dsl._

class EchoEndpoint[F[_]](implicit F: Applicative[F]) extends Endpoint[F] {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/echo",
      method = None,
      description = "Echos back your request, as a response"
    )

  val service: HttpService[F] = HttpService[F] {
    case req @ _ -> Root / "echo" =>
      F.pure(Response(Status.Ok, req.httpVersion, req.headers, body = req.body, req.attributes))
  }
}
