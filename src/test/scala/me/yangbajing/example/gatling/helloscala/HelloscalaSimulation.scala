package me.yangbajing.example.gatling.helloscala

import io.gatling.core.Predef._
import me.yangbajing.example.gatling.utils.Utils

class HelloscalaSimulation extends Simulation {
  val httpConf = Utils.gatlingHttp("http://localhost:9000")

  val helloscalaSDKApiScenario = scenario("HelloscalaSDKApi")
    .feed(HellosscalaSiteApi.externalIds)
    .exec(
      HellosscalaSiteApi.createQuickies
      //HellosscalaSiteApi.getQuickies
    )

  val userSize = HellosscalaSiteApi.externalIds.size

  setUp(
    helloscalaSDKApiScenario.inject(rampUsers(500) over(10))
  ).protocols(httpConf)


}
