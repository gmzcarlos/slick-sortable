package controllers

import models.{LogEntry, Animal, Job}
import play.api.Logger
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}

import scala.util.{Success, Try, Failure}

object Application extends Controller {

  /**
   * Displays animals in position order.
   */
  def index = Action {
    Ok(views.html.index(Animal.list))
  }

  /**
   * Updates an animal’s position. Use a form so we can generate a URL without
   * parameters in the template, and do validation.
   */
  def reposition = Action { implicit request =>
    Logger.trace("setPosition")

    case class Position(from: Int, to: Int)

    // HTML form structure for the HTTP request.
    val form = Form(mapping(
      "from" -> number(min = 1),
      "to" -> number(min = 1)
    )(Position.apply)(Position.unapply))

    // Use the form to parse the data in the HTTP PUT request body.
    form.bindFromRequest.fold(
      formWithErrors => {
        Logger.warn("errors: " + formWithErrors.errorsAsJson)
        BadRequest(formWithErrors.errorsAsJson)
      },
      position => {
        Logger.trace(s"controller: from ${position.from} to ${position.to}")

        Try(Animal.reposition(position.from, position.to)) match {
          case Failure(error) => BadRequest(error.getMessage)
          case Success(_) => Ok
        }
      })
  }

  def jobqueuePage = Action {
    Ok(views.html.Jobqueue(Job.list))
  }

  def logPage = Action {
    Ok(views.html.log(LogEntry.list))
  }

}