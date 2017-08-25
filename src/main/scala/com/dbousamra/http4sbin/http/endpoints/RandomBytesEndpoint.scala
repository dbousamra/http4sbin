package com.dbousamra.http4sbin.http.endpoints

import cats.effect.Sync
import com.dbousamra.http4sbin.http._
import fs2._
import org.http4s.HttpService
import org.http4s.dsl._

import scala.util.Random

class RandomBytesEndpoint[F[_]](implicit F: Sync[F]) extends Endpoint[F] {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/bytes/:n",
      method = None,
      description = "Generates n random bytes of binary data."
    )

  val randomBytes: Stream[F, Byte] = Stream.repeatEval(F.delay((Random.nextInt(256) - 128).toByte))

  val service: HttpService[F] = HttpService[F] {
    case _ -> Root / "bytes" / IntVar(n) =>
      Ok(randomBytes.take(n))
  }
}
