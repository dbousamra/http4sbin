package com.dbousamra.http4sbin.http.endpoints

import argonaut._
import Argonaut._
import com.dbousamra.http4sbin.http._
import org.http4s.HttpService
import org.http4s.dsl._
import org.http4s.argonaut._
import org.http4s.headers.`X-Forwarded-For`

object OriginEndpoint extends Endpoint {

  val description: EndpointDescriptor =
    EndpointDescriptor("/ip", None, "Returns Origin IP")

  val service: HttpService = HttpService {
    case req @ _ -> Root / "ip" => {
      val origin = req.headers.get(`X-Forwarded-For`).map(_.value).orElse(req.remoteAddr)
      Ok(Json("origin" := origin))
    }
  }
}
