package study_work_hub;

import java.util.ArrayList;

/**
 * Mantém os registros do sistema durante a execução do programa.
 *
 * @author Bruna Tais
 */
public class Dados {

    private static final ArrayList<Tarefa> tarefas = new ArrayList<>();
    private static final ArrayList<Material> materiais = new ArrayList<>();
    private static final ArrayList<PlanoEstudo> planos = new ArrayList<>();

    public static ArrayList<Tarefa> listarTarefas() {
        return tarefas;
    }

    public static ArrayList<Material> listarMateriais() {
        return materiais;
    }

    public static ArrayList<PlanoEstudo> listarPlanos() {
        return planos;
    }
}
