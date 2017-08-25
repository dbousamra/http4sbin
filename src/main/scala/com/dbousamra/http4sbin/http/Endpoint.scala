package com.dbousamra.http4sbin.http

import org.http4s.HttpService
import org.http4s.dsl.Http4sDsl

trait Endpoint[F[_]] extends Http4sDsl[F] {

  def service: HttpService[F]

  def description: EndpointDescriptor

}
