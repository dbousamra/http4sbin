package com.dbousamra.http4sbin.http.middleware

import org.http4s.HttpService

object LoggingMiddleware {
  def apply(service: HttpService)(log: String => Unit) = HttpService.lift { req =>
    service.run(req).map { resp =>
      log(s"${req.remoteAddr.getOrElse("Unknown remote")} ${req.method} ${req.uri.toString} ${resp.status.code}")
      resp
    }
  }
}
