package com.tdi.starters

object RunReport extends App {
  Starter.reportingService.reportResults
  Starter.reportingService.con.close
}