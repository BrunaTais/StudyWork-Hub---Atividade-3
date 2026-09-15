package study_work_hub; 

/**
 * Representa um usuário do sistema.
 *
 * @author Bruna Tais
 */
public class Usuario {
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;


    public Usuario(int idUsuario, String nome, String email, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public boolean autenticar(String emailTentativa, String senhaTentativa) {
        return this.email.equals(emailTentativa) && this.senha.equals(senhaTentativa);
    }

    public String getNome() {
        return this.nome;
    }
}
