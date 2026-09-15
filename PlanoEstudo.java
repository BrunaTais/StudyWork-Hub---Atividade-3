package study_work_hub;

/**
 * Representa um horário do plano de estudos.
 *
 * @author Bruna Tais
 */
public class PlanoEstudo {

    private int idPlano;
    private String disciplina;
    private String data;
    private String horario;
    private String objetivo;

    public PlanoEstudo(int idPlano, String disciplina, String data,
            String horario, String objetivo) {
        this.idPlano = idPlano;
        this.disciplina = disciplina;
        this.data = data;
        this.horario = horario;
        this.objetivo = objetivo;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public String getObjetivo() {
        return objetivo;
    }
}
