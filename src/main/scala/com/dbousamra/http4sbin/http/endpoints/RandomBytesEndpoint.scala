package com.dbousamra.http4sbin.http.endpoints

import com.dbousamra.http4sbin.http._
import org.http4s.HttpService
import org.http4s.dsl._
import scalaz.stream.Process
import scala.util.Random
import scalaz.concurrent.Task

object RandomBytesEndpoint extends Endpoint {

  val description: EndpointDescriptor =
    EndpointDescriptor(
      path = "/bytes/:n",
      method = None,
      description = "Generates n random bytes of binary data."
    )

  val service: HttpService = HttpService {
    case req @ _ -> Root / "bytes" / IntVar(n) => {
      val randomBytes: Process[Task, Byte] = Process.repeatEval(
        Task { (Random.nextInt(256) - 128).toByte }
      )
      Ok(randomBytes.take(n))
    }
  }
}
