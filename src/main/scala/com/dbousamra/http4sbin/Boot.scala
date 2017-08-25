package com.dbousamra.http4sbin

import cats.effect.IO
import com.dbousamra.http4sbin.http.Endpoint
import com.dbousamra.http4sbin.http.endpoints._
import com.dbousamra.http4sbin.http.middleware.LoggingMiddleware
import fs2._
import org.http4s._
import org.http4s.server._
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.server.syntax._
import org.http4s.util.StreamApp
import org.log4s._

import scala.concurrent.ExecutionContext
import scala.util.Try

object Boot extends StreamApp[IO] {

  val logger: Logger = getLogger

  implicit val executionContext: ExecutionContext = ExecutionContext.global

  val port: Int = Try(System.getenv("PORT").toInt).toOption.getOrElse(8080)

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, Nothing] =
    Scheduler[IO](corePoolSize = 4, threadPrefix = "http4sbin-scheduler").flatMap {
      implicit scheduler =>
        val endpoints: List[Endpoint[IO]] =
          List(
            new OriginEndpoint,
            new EchoEndpoint,
            new HeadersEndpoint,
            new UserAgentEndpoint,
            new RandomBytesEndpoint,
            new DelayEndpoint,
            new RandomDelayEndpoint
          )

        val rootEndpoint: Endpoint[IO] = RootEndpoint(endpoints.map(_.description))

        val middleware: HttpMiddleware[IO] = LoggingMiddleware(_)(logger.info(_))

        val composedService: HttpService[IO] =
          endpoints.foldLeft(rootEndpoint.service)((i, e) => i.orElse(e.service))

        BlazeBuilder[IO]
          .bindHttp(port)
          .mountService(middleware(composedService), "/")
          .serve
    }

}
