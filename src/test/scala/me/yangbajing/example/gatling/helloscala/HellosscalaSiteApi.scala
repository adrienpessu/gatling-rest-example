package me.yangbajing.example.gatling.helloscala

import io.gatling.core.Predef._
import io.gatling.core.session.SessionAttribute
import io.gatling.http.Predef._
import me.yangbajing.example.gatling.utils.Utils

/**
 * External SDK API Test Suite
 * Created by jingyang on 15/8/4.
 */
object HellosscalaSiteApi {
  val externalIds = (1 to 500).map(_ => Map("externalId" -> org.bson.types.ObjectId.get.toString))

  val createQuickies = resetTimestamp
    .exec(
      http("createQuickie")
        .post("/quickies")
        .body(StringBody(
        """{
          |  "name":"gatling for warriors",
          |  "duration":15
          |}""".stripMargin)).asJSON
        .check(status.is(201))
    )

  val getQuickies = resetTimestamp
    .exec(
      http("getQuickies")
        .get("/quickies")
        .check(status.is(200))
    )

  private def resetTimestamp = exec(session => session.map(_.set("timestamp", System.currentTimeMillis().toString)))

}
