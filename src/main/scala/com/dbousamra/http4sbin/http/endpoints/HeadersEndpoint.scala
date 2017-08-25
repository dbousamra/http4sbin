package com.dbousamra.http4sbin.http.endpoints

import cats.Monad
import com.dbousamra.http4sbin.http._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._

class HeadersEndpoint[F[_]: Monad] extends Endpoint[F] {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/headers",
      method = None,
      description = "Returns the headers you sent"
    )

  val service: HttpService[F] = HttpService[F] {
    case req @ _ -> Root / "headers" =>
      Ok(req.headers.toList.map(h => h.name.value -> h.value).toMap.asJson)
  }
}
