package com.dbousamra.http4sbin.http

import org.http4s.Method

case class EndpointDescriptor(path: String, method: Option[Method], description: String)
