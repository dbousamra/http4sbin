package com.dbousamra.http4sbin.http.middleware

import cats._
import cats.implicits._
import org.http4s._

import scala.language.higherKinds

object LoggingMiddleware {
  def apply[F[_]: Monad](service: HttpService[F])(log: String => Unit): HttpService[F] =
    HttpService.lift { req =>
      service.run(req).map {
        case Pass() => Pass()
        case resp: Response[F] =>
          log(s"${req.remoteAddr.getOrElse("Unknown remote")} ${req.method} ${req.uri.toString} ${resp.status.code}")
          resp
      }
    }
}
