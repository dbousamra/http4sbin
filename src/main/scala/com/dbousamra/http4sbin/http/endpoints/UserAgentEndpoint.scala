package com.dbousamra.http4sbin.http.endpoints

import argonaut._
import argonaut.Argonaut._
import com.dbousamra.http4sbin.http._
import org.http4s._
import org.http4s.dsl._
import org.http4s.argonaut._
import org.http4s.headers.`User-Agent`

object UserAgentEndpoint extends Endpoint {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/user-agent",
      method = None,
      description = "Returns the user-agent you sent"
    )

  val service: HttpService = HttpService {
    case req @ _ -> Root / "user-agent" =>{
      Ok(Json(
        "user-agent" := req.headers.get(`User-Agent`).map(_.value)
      ))
    }
  }
}
