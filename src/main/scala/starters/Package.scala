package com.tdi

import com.typesafe.config.ConfigFactory
import scala.collection.JavaConverters._

package object starters {

  private val config = ConfigFactory.load()

  /* List of counties */
  lazy val countyList = config.getStringList("com.tdi.countyList").asScala.toList

  /* List of companies */
  lazy val companyList = config.getStringList("com.tdi.companyList").asScala.toList

  /* CSV Name */
  lazy val csvName = config.getString("com.tdi.csvName")

}