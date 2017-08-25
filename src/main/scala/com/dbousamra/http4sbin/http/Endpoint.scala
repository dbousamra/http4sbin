package com.dbousamra.http4sbin.http

import org.http4s.HttpService

trait Endpoint[F[_]] {

  def service: HttpService[F]

  def description: EndpointDescriptor

}
