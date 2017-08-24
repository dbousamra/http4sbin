package com.dbousamra.http4sbin.http.endpoints

import cats.effect.IO
import com.dbousamra.http4sbin.http._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._

object HeadersEndpoint extends Endpoint[IO] {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/headers",
      method = None,
      description = "Returns the headers you sent"
    )

  val service: HttpService[IO] = HttpService[IO] {
    case req @ _ -> Root / "headers" =>
      Ok(req.headers.toList.map(h => h.name.value -> h.value).toMap.asJson)
  }
}
