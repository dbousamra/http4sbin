package com.dbousamra.http4sbin.http.endpoints

import cats.effect._
import com.dbousamra.http4sbin.http._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._
import org.http4s.headers.`X-Forwarded-For`

object OriginEndpoint extends Endpoint[IO] {

  val description: EndpointDescriptor =
    EndpointDescriptor("/ip", None, "Returns Origin IP")

  val service: HttpService[IO] = HttpService[IO] {
    case req @ _ -> Root / "ip" =>
      val origin: Option[String] =
        req.headers
          .get(`X-Forwarded-For`)
          .map(_.value)
          .orElse(req.remoteAddr)
      Ok(Map("origin" -> origin).asJson)
  }
}
