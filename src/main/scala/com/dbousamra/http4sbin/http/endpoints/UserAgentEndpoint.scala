package com.dbousamra.http4sbin.http.endpoints

import cats.Monad
import com.dbousamra.http4sbin.http._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._
import org.http4s.headers.`User-Agent`

class UserAgentEndpoint[F[_]: Monad] extends Endpoint[F] {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/user-agent",
      method = None,
      description = "Returns the user-agent you sent"
    )

  val service: HttpService[F] = HttpService[F] {
    case req @ _ -> Root / "user-agent" =>
      Ok(Map("user-agent" -> req.headers.get(`User-Agent`).map(_.value)).asJson)
  }
}
