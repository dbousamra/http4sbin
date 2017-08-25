package com.dbousamra.http4sbin.http.endpoints

import scala.util.Try

object Helpers {

  object DelayTimeParamMatcher {
    def unapply(str: String): Option[Int] =
      Try(str.toInt).toOption.filter(_ <= 30)
  }

}
