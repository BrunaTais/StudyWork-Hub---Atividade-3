package study_work_hub;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Tela de acesso ao StudyWork Hub.
 *
 * @author Bruna Tais
 */
public class TelaLogin extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtSenha;

    public TelaLogin() {
        configurarTela();
        montarComponentes();
    }

    private void configurarTela() {
        setTitle("StudyWork Hub - Login");
        setSize(760, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void montarComponentes() {
        JPanel fundo = new JPanel(new GridBagLayout());
        fundo.setBackground(new Color(248, 250, 252));

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 18, 10, 18);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;

        JLabel titulo = new JLabel("StudyWork Hub", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(30, 58, 138));
        formulario.add(titulo, c);

        c.gridy++;
        JLabel mensagem = new JLabel("Organize seus estudos em um só lugar.", JLabel.CENTER);
        mensagem.setFont(new Font("Arial", Font.PLAIN, 16));
        formulario.add(mensagem, c);

        c.gridy++;
        JLabel acesso = new JLabel(
                "Acesso para teste: bruna@email.com | Senha: 1234",
                JLabel.CENTER
        );
        acesso.setFont(new Font("Arial", Font.PLAIN, 14));
        acesso.setForeground(new Color(71, 85, 105));
        formulario.add(acesso, c);

        c.gridy++;
        c.gridwidth = 1;
        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        formulario.add(lblEmail, c);

        c.gridx = 1;
        txtEmail = new JTextField(22);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        txtEmail.setToolTipText("Informe seu e-mail");
        lblEmail.setLabelFor(txtEmail);
        formulario.add(txtEmail, c);

        c.gridx = 0;
        c.gridy++;
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 16));
        formulario.add(lblSenha, c);

        c.gridx = 1;
        txtSenha = new JPasswordField(22);
        txtSenha.setFont(new Font("Arial", Font.PLAIN, 16));
        txtSenha.setToolTipText("Informe sua senha");
        lblSenha.setLabelFor(txtSenha);
        formulario.add(txtSenha, c);

        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 2;
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEntrar.setBackground(new Color(37, 99, 235));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setMnemonic('E');
        btnEntrar.setToolTipText("Entrar no sistema");
        btnEntrar.addActionListener(e -> entrar());
        formulario.add(btnEntrar, c);

        getRootPane().setDefaultButton(btnEntrar);
        fundo.add(formulario);
        add(fundo, BorderLayout.CENTER);
    }

    private void entrar() {
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o e-mail e a senha.");
        } else {
            Usuario usuario = new Usuario(
                    1, "Bruna Tais", "bruna@email.com", "1234"
            );

            if (usuario.autenticar(email, senha)) {
                TelaPrincipal tela = new TelaPrincipal(usuario);
                tela.setLocationRelativeTo(this);
                tela.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "E-mail ou senha inválidos.");
            }
        }
    }
}
