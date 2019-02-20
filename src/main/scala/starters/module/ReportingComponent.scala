package com.tdi.starters.module

import com.tdi.starters._
import com.typesafe.scalalogging.LazyLogging
import com.github.tototoshi.csv._
import java.io.File

case class ResultRecord(countyCode: String, companyCode: String, pre2008Count: Int, count2008: Int, count2009: Int, count2010: Int, count2011: Int, count2012: Int, count2013: Int, count2014: Int, count2015: Int, count2016: Int, count2017: Int, count2018: Int) {
  /* Ability to filter on the total */
  def total = pre2008Count + count2008 + count2009 + count2010 + count2011 + count2012 + count2013 + count2014 + count2015 + count2016 + count2017 + count2018
}

trait ReportingComponent extends LazyLogging {
  this: JDBCComponent =>

  /* Create a ResultRecord object for each County and Company */
  private def resultList(countyCode: String, companyCode: String): ResultRecord = {
    ResultRecord(countyCode, companyCode,
      pre2008Count(countyCode, companyCode),
      yearlyCount(countyCode, companyCode, 2008),
      yearlyCount(countyCode, companyCode, 2009),
      yearlyCount(countyCode, companyCode, 2010),
      yearlyCount(countyCode, companyCode, 2011),
      yearlyCount(countyCode, companyCode, 2012),
      yearlyCount(countyCode, companyCode, 2013),
      yearlyCount(countyCode, companyCode, 2014),
      yearlyCount(countyCode, companyCode, 2015),
      yearlyCount(countyCode, companyCode, 2016),
      yearlyCount(countyCode, companyCode, 2017),
      yearlyCount(countyCode, companyCode, 2018)
    )
  }

  /* Build results for each county and company using a for comprehension */
  def getResults: List[ResultRecord] = {
    logger.info("Retrieving Results")
    for {
      county <- countyList
      company <- companyList
    } yield {
      resultList(county, company)
    }
  }

  /* Write results to CSV */
  def writeResults(results: List[ResultRecord]) {
    val f = new File(csvName)
    val writer = CSVWriter.open(f)

    /* Filter out entries that do not have any results */
    val res = results filter (_.total > 0)

    res map (x => {
      writer.writeRow(
        List(
          x.countyCode,
          x.companyCode,
          x.pre2008Count,
          x.count2008,
          x.count2009,
          x.count2010,
          x.count2011,
          x.count2012,
          x.count2013,
          x.count2014,
          x.count2015,
          x.count2016,
          x.count2017,
          x.count2018)
      )
    })
    logger.info("Completed writing CSV Results")
    writer.close
  }

  /* Retrieve and write results */
  def reportResults {
    writeResults(getResults)
  }
}