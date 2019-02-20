# Starter Counts

The purpose of this project is to determine how many Digital Starters reside in our Image Library based on the Company and County Code.  This is implemented with Scala using the Config library to maintain a list of Company and County codes.  Finally the counters are determined with SQL.

## REPL

```
import com.tdi.starters._

Starter.reportingService.reportResults
Starter.reportingService.con.close


val rec1 = ResultRecord("HA", "UA", 100, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18)
val rec2 = ResultRecord("FB", "UA", 75, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18)
val rec3 = ResultRecord("MO", "UA", 50, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18)
val rec4 = ResultRecord("GV", "UA", 25, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18)
val rec5 = ResultRecord("BR", "UA", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

val list = List(rec1, rec2, rec3, rec4, rec5)

list filter (_.total > 0)

```         




 