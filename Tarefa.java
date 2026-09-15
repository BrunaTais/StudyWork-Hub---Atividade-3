package study_work_hub; 

/**
 * Representa uma tarefa cadastrada no sistema.
 *
 * @author Bruna Tais
 */
public class Tarefa {

    private int idTarefa;
    private String titulo;
    private String descricao;
    private String status;
    private String data;

    public Tarefa(int idTarefa, String titulo, String descricao, String status, String data) {
        this.idTarefa = idTarefa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.data = data;
    }

    public void alterarStatus(String novoStatus) {
        this.status = novoStatus;
        System.out.println("Status alterado para: " + novoStatus);
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getStatus() {
        return this.status;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData() {
        return data;
    }
}
