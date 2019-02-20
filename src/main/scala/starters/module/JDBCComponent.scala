package com.tdi.starters.module

import com.typesafe.scalalogging.LazyLogging
import java.sql._

trait JDBCComponent extends LazyLogging {

  /* Register JDBC Driver */
  DriverManager.registerDriver(new com.ibm.as400.access.AS400JDBCDriver())

  lazy val con = DriverManager.getConnection("jdbc:as400://tdimain")

  /* Create Prepared Statment for a specific County */
  private def pre2008PS(con: Connection, dbName: String): PreparedStatement = {
    val countString = "SELECT COUNT(*) FROM T2TDI" + dbName + ".TMDFMSTL WHERE DMCOMPANY =? AND DMDOCSRC='9' AND DMSTATUS = 'P' AND DMIMGIDX != 0 AND DMFILEYY < 2008"
    con.prepareStatement(countString, ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_UPDATABLE)
  }

  /* Create Prepared Statment for a specific County */
  private def yearlyPS(con: Connection, dbName: String): PreparedStatement = {
    val countString = "SELECT COUNT(*) FROM T2TDI" + dbName + ".TMDFMSTL WHERE DMCOMPANY =? AND DMDOCSRC='9' AND DMSTATUS = 'P' AND DMIMGIDX != 0 AND DMFILEYY = ?"
    con.prepareStatement(countString, ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_UPDATABLE)
  }

  /* Perform Count */
  def pre2008Count(countyCode: String, companyCode: String): Int = {
    /* Create statment */
    val stmt = pre2008PS(con, countyCode)
    stmt.setString(1, companyCode)

    /* Run query and retrieve result */
    val rs = stmt.executeQuery()
    rs.next
    val count = rs.getInt(1)

    /* Close stmt and rs */
    rs.close()
    stmt.close()

    count
  }

  /* Perform Count */
  def yearlyCount(countyCode: String, companyCode: String, year: Int): Int = {
    /* Create statment */
    val stmt = yearlyPS(con, countyCode)
    stmt.setString(1, companyCode)
    stmt.setInt(2, year)

    /* Run query and retrieve result */
    val rs = stmt.executeQuery()
    rs.next
    val count = rs.getInt(1)

    /* Close stmt and rs */
    rs.close()
    stmt.close()

    count
  }
}