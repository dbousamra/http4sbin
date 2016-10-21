package com.dbousamra.http4sbin.http

import org.http4s.HttpService

trait Endpoint {

  def service: HttpService

  def description: EndpointDescriptor
}
