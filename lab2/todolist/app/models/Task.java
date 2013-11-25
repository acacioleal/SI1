package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Task extends Model {

	private boolean done;
  @Id
  public Long id;
  
  @Required
  public String nome;
  @Required
  public String descricao;
  @Required
  public int prioridade;
  
  public boolean Done() {
	 done = true;
  }
  
  public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getDescricao() {
	return descricao;
}

public void setDescricao(String descricao) {
	this.descricao = descricao;
}

public int getPrioridade() {
	return prioridade;
}

public void setPrioridade(int prioridade) {
	this.prioridade = prioridade;
}

public static Finder<Long,Task> find = new Finder(Long.class, Task.class);

  public static List<Task> all() {
	  return find.all();
	}

	public static void create(Task task) {
	  task.save();
	}

	public static void delete(Long id) {
	  find.ref(id).delete();
	}
	
	public static void update(Long id) {
	  Task task = find.ref(id);
	  if (task.Done() == true) {
		  task.setDone(task.done);	  
	  }
	  else
		  task.setDone(task.done);
	  task.update();
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
