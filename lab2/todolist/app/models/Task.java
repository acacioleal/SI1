package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Task extends Model {

	private boolean done = false;
	
  @Id
  public Long id;
  
  @Required
  public String nome;
  
  @Required
  public String descricao;
  
  @Required
  public int prioridade;
  
 

public static Finder<Long,Task> find = new Finder(Long.class, Task.class);

  public static List<Task> all() {
          List<Task> bdList = find.all();
          Comparator<Task> comparador = new Comparator<Task>() {
                        @Override
                        public int compare(Task arg0, Task arg1) {
                                return arg0.getPrioridade() - arg1.getPrioridade();
                        }
          };
          Collections.sort(bdList, comparador);
          return bdList;
  }
	

	public static void create(Task task) {
	  task.save();
	}

	public static void delete(Long id) {
	  find.ref(id).delete();
	}
	
	public static void update(Long id) {
		Task task = (Task) find.ref(id);
        task.setDone(! task.isDone());
        task.update();
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
	
	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
