package com.dbousamra.http4sbin.http.endpoints

import cats.effect.IO
import com.dbousamra.http4sbin.http._
import fs2._
import org.http4s.HttpService
import org.http4s.dsl._

import scala.util.Random

object RandomBytesEndpoint extends Endpoint[IO] {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/bytes/:n",
      method = None,
      description = "Generates n random bytes of binary data."
    )

  val randomBytes: Stream[IO, Byte] = Stream.repeatEval(IO((Random.nextInt(256) - 128).toByte))

  val service: HttpService[IO] = HttpService[IO] {
    case _ -> Root / "bytes" / IntVar(n) =>
      Ok(randomBytes.take(n))
  }
}
