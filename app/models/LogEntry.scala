package models

import models.db.Logs
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._

/**
 * Domain model - a Log with a manually-set position that defines a total
 * ordering on the collection. The position is 1-based and strictly consecutive.
 */
case class LogEntry(id: Option[Int], job_id: Int, description: String)


/**
 * Data access functions.
 */
object LogEntry {
  // Base Slick database query to use for data access.
  val logs = TableQuery[Logs]

  /**
   * Returns a list of jobs, sorted by id.
   */
  def list = DB.withSession { implicit s: Session =>
    logs.sortBy(_.id).list
  }
}


