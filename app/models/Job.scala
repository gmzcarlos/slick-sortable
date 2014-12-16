package models

import models.db.Jobs
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._

/**
 * Domain model - a Job with a manually-set position that defines a total
 * ordering on the collection. The position is 1-based and strictly consecutive.
 */
case class Job(id: Option[Int], name: String, description: String, status: String)


/**
 * Data access functions.
 */
object Job {
  // Base Slick database query to use for data access.
  val jobs = TableQuery[Jobs]

  /**
   * Returns a list of jobs, sorted by id.
   */
  def list = DB.withSession { implicit s: Session =>
    jobs.sortBy(_.id).list
  }
}
