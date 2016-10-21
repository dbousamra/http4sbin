package com.dbousamra.http4sbin.http.endpoints

import argonaut._, Argonaut._
import com.dbousamra.http4sbin.http._
import org.http4s._
import org.http4s.dsl._
import org.http4s.argonaut._

object HeadersEndpoint extends Endpoint {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/headers",
      method = None,
      description = "Returns the headers you sent"
    )

  val service: HttpService = HttpService {
    case req @ _ -> Root / "headers" =>{
      val json = req.headers.foldLeft(jEmptyObject)((i, h) => i.->:(h.name.value := h.value))
      Ok(json)
    }
  }
}
