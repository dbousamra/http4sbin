package com.dbousamra.http4sbin.http.endpoints

import cats.Monad
import cats.effect._
import com.dbousamra.http4sbin.http._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._
import org.http4s.headers.`X-Forwarded-For`

class OriginEndpoint[F[_]: Monad] extends Endpoint[F] {

  val description: EndpointDescriptor =
    EndpointDescriptor("/ip", None, "Returns Origin IP")

  val service: HttpService[F] = HttpService[F] {
    case req @ _ -> Root / "ip" =>
      val origin: Option[String] =
        req.headers
          .get(`X-Forwarded-For`)
          .map(_.value)
          .orElse(req.remoteAddr)
      Ok(Map("origin" -> origin).asJson)
  }
}
