package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

/**
 * 
 * Classe das tarefas.
 *
 */
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

/**
 * 
 * @return Lista das tarefas ordenadas.
 */

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
	
  	/**
  	 * 
  	 * @param task
  	 * Cria e salva uma tarefa.
  	 */
  
	public static void create(Task task) {
	  task.save();
	}
	
	/**
	 * 
	 * @param id
	 * Deleta uma tarefa criada.
	 */
	
	public static void delete(Long id) {
	  find.ref(id).delete();
	}
	
	/**
	 * 
	 * @param id
	 * Marca a tarefa como realizada.
	 */
	
	public static void update(Long id) {
		Task task = (Task) find.ref(id);
        task.setDone(! task.isDone());
        task.update();
	}

	/**
	 * @return Nome da variavel.
	 * 
	 */
	
	public String getNome() {
		 return nome;
	}

	/**
	 * @param nome
	 * Modifica o nome da variavel.
	 */
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return Descricao da variavel.
	 * 
	 */
	
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 * Modifica a descricao da variavel.
	 */
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return prioridade da variavel.
	 * 
	 */
	
	public int getPrioridade() {
		return prioridade;
	}
	/**
	 * @param prioridade
	 * Modifica a prioridade da variavel.
	 */
	
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	
	/**
	 * @return Se a variavel foi feita ou nao.
	 * 
	 */
	
	public boolean isDone() {
		return done;
	}

	/**
	 * 
	 * @param done
	 * Modifica a condicao da variavel.
	 */
	
	public void setDone(boolean done) {
		this.done = done;
	}

}
