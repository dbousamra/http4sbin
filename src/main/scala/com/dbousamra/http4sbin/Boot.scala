package com.dbousamra.http4sbin

import com.dbousamra.http4sbin.http.endpoints._
import com.dbousamra.http4sbin.http.middleware.LoggingMiddleware
import org.http4s.HttpService
import org.http4s.server.ServerApp
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.server.syntax._
import org.log4s._

object Boot extends ServerApp {

  val logger = getLogger

  val endpoints = List(
    OriginEndpoint,
    EchoEndpoint,
    HeadersEndpoint,
    UserAgentEndpoint,
    RandomBytesEndpoint,
    DelayEndpoint,
    RandomDelayEndpoint
  )

  val rootEndpoint = RootEndpoint(endpoints.map(_.description))

  val middleware: HttpService => HttpService = LoggingMiddleware(_)(logger.info(_))

  val composedService = endpoints
    .foldLeft(rootEndpoint.service)((i, e) => i orElse e.service)

  def server(args: List[String]) = BlazeBuilder
    .bindHttp(8080, "0.0.0.0")
    .mountService(composedService, "/")
    .start
}
