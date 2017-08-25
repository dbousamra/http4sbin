package com.dbousamra.http4sbin.http.endpoints

import cats.Monad
import com.dbousamra.http4sbin.http._
import org.http4s.dsl._
import org.http4s.{HttpService, MediaType}

import scalatags.Text.all._

case class RootEndpoint[F[+_]: Monad](endpoints: List[EndpointDescriptor]) extends Endpoint[F] {
  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/",
      method = None,
      description = "Returns this page"
    )

  val l = html(
    head(),
    body(
      div(
        body(
          h1(id := "title", "http4sbin - HTTP requests and responses"),
          p("http4sbin is a clone of httpbin I wrote to demonstrate using http4s"),
          h2("Endpoints"),
          ul(
            endpoints.map { e =>
              li(
                s"${e.path} - ${e.method} - ${e.description}"
              )
            }
          )
        )
      )
    )
  )

  val service: HttpService[F] = HttpService[F] {
    case GET -> Root =>
      Ok(l.render).withType(MediaType.`text/html`)
  }

}
