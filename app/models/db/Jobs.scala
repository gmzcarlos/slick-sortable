package models.db

import models.Job
import play.api.db.slick.Config.driver.simple._

/**
 * Created by carlostest on 11/19/14.
 */
class Jobs (tag: Tag) extends Table[Job](tag, "JOBQUEUE") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def description = column[String]("DESCRIPTION")
  def status = column[String]("STATUS")


  // Slick requires a `*` ‘projection’, to define a row (tuple of columns).
  // `id` is mapped to an `Option[Int]`, hence the use of Slick’s ? method.
  def * = (id.?, name, description, status) <> ((Job.apply _).tupled, Job.unapply)
}