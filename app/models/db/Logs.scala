package models.db

import models.LogEntry
import play.api.db.slick.Config.driver.simple._

/**
 * Created by carlostest on 11/19/14.
 */
class Logs (tag: Tag) extends Table[LogEntry](tag, "LOG") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def job_id = column[Int]("JOB_ID")
  def description = column[String]("DESCRIPTION")


  // Slick requires a `*` ‘projection’, to define a row (tuple of columns).
  // `id` is mapped to an `Option[Int]`, hence the use of Slick’s ? method.
  def * = (id.?, job_id, description) <> ((LogEntry.apply _).tupled, LogEntry.unapply)
}