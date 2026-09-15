package study_work_hub;

/**
 * Classe principal do projeto StudyWork Hub.
 *
 * @author Bruna Tais
 */
public class StudyWorkHub {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            TelaLogin tela = new TelaLogin();
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
        });
    }
}
