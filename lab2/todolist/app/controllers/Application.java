package controllers;

import models.Task;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

/**
 * 
 * Classe que realiza a aplicacao.
 *
 */
public class Application extends Controller {
	
	static Form<Task> taskForm = Form.form(Task.class);
	
	public static Result index() {
	    return  redirect(routes.Application.tasks());
	  }
	  
	/**
	 * @return tasks
	 */
	  public static Result tasks() {
		  return ok(views.html.index.render(Task.all(), taskForm));
	  }
	  
	  /**
	   * @return uma nova task criada.
	   */
	  public static Result newTask() {
		  Form<Task> filledForm = taskForm.bindFromRequest();
		  if(filledForm.hasErrors()) {
		    return badRequest(views.html.index.render(Task.all(), filledForm));
		  } else {
		    Task.create(filledForm.get());
		    return redirect(routes.Application.tasks());  
		  }
	  }
	  /**
	   * @param id
	   * @return deleta a task
	   */
	  public static Result deleteTask(Long id) {
		  Task.delete(id);
		  return redirect(routes.Application.tasks());
	  }
	  
	  /**
	   * @param id
	   * @return faz um update na tarefa. (marca como feita)
	   */
	  public static Result update(Long id) {
		  Task.update(id);
		  return redirect(routes.Application.tasks());
	  }
	  
}
