package study_work_hub;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Janela principal com as funcionalidades do StudyWork Hub.
 *
 * @author Bruna Tais
 */
public class TelaPrincipal extends JFrame {

    private final CardLayout telas = new CardLayout();
    private final JPanel painelConteudo = new JPanel(telas);
    private JLabel lblTotalTarefas;
    private JLabel lblTotalMateriais;
    private JLabel lblTotalPlanos;

    private JTextField txtTituloTarefa;
    private JTextField txtDescricaoTarefa;
    private JTextField txtDataTarefa;
    private JComboBox<String> cmbStatusTarefa;
    private JTable tblTarefas;

    private JTextField txtNomeMaterial;
    private JTextField txtTipoMaterial;
    private JTextField txtCaminhoMaterial;
    private JTable tblMateriais;

    private JTextField txtDisciplina;
    private JTextField txtDataPlano;
    private JTextField txtHorario;
    private JTextField txtObjetivo;
    private JTable tblPlanos;

    public TelaPrincipal(Usuario usuario) {
        setTitle("StudyWork Hub");
        setSize(1180, 720);
        setMinimumSize(new Dimension(1050, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(criarMenu(), BorderLayout.WEST);

        painelConteudo.add(criarDashboard(usuario), "Dashboard");
        painelConteudo.add(criarTelaTarefas(), "Tarefas");
        painelConteudo.add(criarTelaMateriais(), "Materiais");
        painelConteudo.add(criarTelaPlanos(), "Planos");
        add(painelConteudo, BorderLayout.CENTER);

        mostrarTela("Dashboard");
    }

    private JPanel criarMenu() {
        JPanel menu = new JPanel(new GridBagLayout());
        menu.setBackground(new Color(30, 58, 138));
        menu.setPreferredSize(new Dimension(220, 0));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(8, 14, 8, 14);

        JLabel nome = new JLabel("StudyWork Hub", JLabel.CENTER);
        nome.setForeground(Color.WHITE);
        nome.setFont(new Font("Arial", Font.BOLD, 21));
        c.gridy = 0;
        c.insets = new Insets(24, 14, 28, 14);
        menu.add(nome, c);

        c.insets = new Insets(7, 14, 7, 14);
        c.gridy++;
        menu.add(criarBotaoMenu("Início", "Dashboard", 'I'), c);
        c.gridy++;
        menu.add(criarBotaoMenu("Tarefas", "Tarefas", 'T'), c);
        c.gridy++;
        menu.add(criarBotaoMenu("Materiais", "Materiais", 'M'), c);
        c.gridy++;
        menu.add(criarBotaoMenu("Plano de estudos", "Planos", 'P'), c);

        c.gridy++;
        c.weighty = 1;
        menu.add(new JLabel(), c);

        JButton sair = criarBotao("Sair");
        sair.setToolTipText("Encerrar o sistema");
        sair.addActionListener(e -> System.exit(0));
        c.gridy++;
        c.weighty = 0;
        c.insets = new Insets(7, 14, 24, 14);
        menu.add(sair, c);
        return menu;
    }

    private JButton criarBotaoMenu(String texto, String destino, char atalho) {
        JButton botao = criarBotao(texto);
        botao.setMnemonic(atalho);
        botao.setToolTipText("Abrir " + texto);
        botao.addActionListener(e -> mostrarTela(destino));
        return botao;
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 15));
        botao.setFocusPainted(false);
        return botao;
    }

    private JPanel criarPainelBase(String titulo) {
        JPanel painel = new JPanel(new BorderLayout(15, 15));
        painel.setBackground(new Color(248, 250, 252));
        painel.setBorder(BorderFactory.createEmptyBorder(24, 28, 24, 28));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(31, 41, 55));
        painel.add(lblTitulo, BorderLayout.NORTH);
        return painel;
    }

    private JPanel criarDashboard(Usuario usuario) {
        JPanel painel = criarPainelBase("Olá, " + usuario.getNome() + "!");

        JPanel centro = new JPanel(new BorderLayout(15, 22));
        centro.setOpaque(false);

        JLabel mensagem = new JLabel(
                "Organize suas tarefas, materiais e horários de estudo em um só lugar."
        );
        mensagem.setFont(new Font("Arial", Font.PLAIN, 17));
        centro.add(mensagem, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(1, 3, 18, 18));
        cards.setOpaque(false);
        lblTotalTarefas = criarCard(cards, "Tarefas cadastradas");
        lblTotalMateriais = criarCard(cards, "Materiais cadastrados");
        lblTotalPlanos = criarCard(cards, "Próximos estudos");
        centro.add(cards, BorderLayout.CENTER);
        painel.add(centro, BorderLayout.CENTER);
        return painel;
    }

    private JLabel criarCard(JPanel cards, String titulo) {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225)),
                BorderFactory.createEmptyBorder(25, 20, 25, 20)
        ));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        JLabel rotulo = new JLabel(titulo);
        rotulo.setFont(new Font("Arial", Font.BOLD, 17));
        card.add(rotulo, c);

        c.gridy = 1;
        c.insets = new Insets(25, 0, 0, 0);
        JLabel numero = new JLabel("0");
        numero.setFont(new Font("Arial", Font.BOLD, 42));
        numero.setForeground(new Color(37, 99, 235));
        card.add(numero, c);
        cards.add(card);
        return numero;
    }

    private JPanel criarTelaTarefas() {
        JPanel painel = criarPainelBase("Tarefas");
        JPanel centro = new JPanel(new BorderLayout(12, 12));
        centro.setOpaque(false);

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Color.WHITE);
        GridBagConstraints c = configurarRestricoes();

        txtTituloTarefa = adicionarCampo(formulario, c, 0, "Título:");
        txtDescricaoTarefa = adicionarCampo(formulario, c, 1, "Descrição:");
        txtDataTarefa = adicionarCampo(formulario, c, 2, "Data:");

        c.gridx = 0;
        c.gridy = 3;
        formulario.add(criarLabel("Status:"), c);
        c.gridx = 1;
        cmbStatusTarefa = new JComboBox<>(new String[]{"Pendente", "Em andamento", "Concluída"});
        cmbStatusTarefa.setFont(new Font("Arial", Font.PLAIN, 16));
        formulario.add(cmbStatusTarefa, c);

        JPanel botoes = new JPanel();
        botoes.setBackground(Color.WHITE);
        JButton cadastrar = criarBotao("Cadastrar");
        cadastrar.addActionListener(e -> cadastrarTarefa());
        JButton alterar = criarBotao("Alterar status");
        alterar.addActionListener(e -> alterarStatusTarefa());
        botoes.add(cadastrar);
        botoes.add(alterar);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        formulario.add(botoes, c);

        tblTarefas = criarTabela(new String[]{"Título", "Descrição", "Status", "Data"});
        centro.add(formulario, BorderLayout.NORTH);
        centro.add(new JScrollPane(tblTarefas), BorderLayout.CENTER);
        painel.add(centro, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarTelaMateriais() {
        JPanel painel = criarPainelBase("Materiais");
        JPanel centro = new JPanel(new BorderLayout(12, 12));
        centro.setOpaque(false);

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Color.WHITE);
        GridBagConstraints c = configurarRestricoes();
        txtNomeMaterial = adicionarCampo(formulario, c, 0, "Nome do arquivo:");
        txtTipoMaterial = adicionarCampo(formulario, c, 1, "Tipo:");
        txtCaminhoMaterial = adicionarCampo(formulario, c, 2, "Caminho:");

        JPanel botoes = new JPanel();
        botoes.setBackground(Color.WHITE);
        JButton cadastrar = criarBotao("Cadastrar");
        cadastrar.addActionListener(e -> cadastrarMaterial());
        JButton visualizar = criarBotao("Visualizar");
        visualizar.addActionListener(e -> visualizarMaterial());
        botoes.add(cadastrar);
        botoes.add(visualizar);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        formulario.add(botoes, c);

        tblMateriais = criarTabela(new String[]{"Nome do arquivo", "Tipo", "Caminho"});
        centro.add(formulario, BorderLayout.NORTH);
        centro.add(new JScrollPane(tblMateriais), BorderLayout.CENTER);
        painel.add(centro, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarTelaPlanos() {
        JPanel painel = criarPainelBase("Plano de estudos");
        JPanel centro = new JPanel(new BorderLayout(12, 12));
        centro.setOpaque(false);

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Color.WHITE);
        GridBagConstraints c = configurarRestricoes();
        txtDisciplina = adicionarCampo(formulario, c, 0, "Disciplina:");
        txtDataPlano = adicionarCampo(formulario, c, 1, "Data:");
        txtHorario = adicionarCampo(formulario, c, 2, "Horário:");
        txtObjetivo = adicionarCampo(formulario, c, 3, "Objetivo:");

        JButton salvar = criarBotao("Salvar");
        salvar.addActionListener(e -> cadastrarPlano());
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        formulario.add(salvar, c);

        tblPlanos = criarTabela(new String[]{"Disciplina", "Data", "Horário", "Objetivo"});
        centro.add(formulario, BorderLayout.NORTH);
        centro.add(new JScrollPane(tblPlanos), BorderLayout.CENTER);
        painel.add(centro, BorderLayout.CENTER);
        return painel;
    }

    private GridBagConstraints configurarRestricoes() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 12, 8, 12);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        return c;
    }

    private JTextField adicionarCampo(JPanel painel, GridBagConstraints c,
            int linha, String texto) {
        c.gridx = 0;
        c.gridy = linha;
        c.gridwidth = 1;
        c.weightx = 0;
        JLabel label = criarLabel(texto);
        painel.add(label, c);

        c.gridx = 1;
        c.weightx = 1;
        JTextField campo = new JTextField(30);
        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        campo.setToolTipText("Informe " + texto.replace(":", "").toLowerCase());
        label.setLabelFor(campo);
        painel.add(campo, c);
        return campo;
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        return label;
    }

    private JTable criarTabela(String[] colunas) {
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };
        JTable tabela = new JTable(modelo);
        tabela.setFont(new Font("Arial", Font.PLAIN, 15));
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        tabela.setRowHeight(26);
        tabela.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        return tabela;
    }

    private void cadastrarTarefa() {
        String titulo = txtTituloTarefa.getText();
        String descricao = txtDescricaoTarefa.getText();
        String data = txtDataTarefa.getText();
        String status = cmbStatusTarefa.getSelectedItem().toString();

        if (titulo.isEmpty() || descricao.isEmpty() || data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos da tarefa.");
        } else if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Informe a data no formato dd/mm/aaaa.");
        } else {
            Tarefa tarefa = new Tarefa(
                    Dados.listarTarefas().size() + 1, titulo, descricao, status, data
            );
            Dados.listarTarefas().add(tarefa);
            atualizarTarefas();
            limparTarefa();
            JOptionPane.showMessageDialog(this, "Tarefa cadastrada com sucesso.");
        }
    }

    private void alterarStatusTarefa() {
        int linha = tblTarefas.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa na tabela.");
        } else {
            String[] opcoes = {"Pendente", "Em andamento", "Concluída"};
            String status = (String) JOptionPane.showInputDialog(
                    this, "Selecione o novo status:", "Alterar status",
                    JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]
            );
            if (status != null) {
                Dados.listarTarefas().get(linha).alterarStatus(status);
                atualizarTarefas();
                JOptionPane.showMessageDialog(this, "Status alterado com sucesso.");
            }
        }
    }

    private void cadastrarMaterial() {
        String nome = txtNomeMaterial.getText();
        String tipo = txtTipoMaterial.getText();
        String caminho = txtCaminhoMaterial.getText();
        if (nome.isEmpty() || tipo.isEmpty() || caminho.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos do material.");
        } else {
            Material material = new Material(
                    Dados.listarMateriais().size() + 1, nome, tipo, caminho
            );
            Dados.listarMateriais().add(material);
            atualizarMateriais();
            txtNomeMaterial.setText("");
            txtTipoMaterial.setText("");
            txtCaminhoMaterial.setText("");
            JOptionPane.showMessageDialog(this, "Material cadastrado com sucesso.");
        }
    }

    private void visualizarMaterial() {
        int linha = tblMateriais.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um material na tabela.");
        } else {
            Dados.listarMateriais().get(linha).visualizar();
        }
    }

    private void cadastrarPlano() {
        String disciplina = txtDisciplina.getText();
        String data = txtDataPlano.getText();
        String horario = txtHorario.getText();
        String objetivo = txtObjetivo.getText();

        if (disciplina.isEmpty() || data.isEmpty()
                || horario.isEmpty() || objetivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos do plano.");
        } else if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Informe a data no formato dd/mm/aaaa.");
        } else if (!horario.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Informe o horário no formato hh:mm.");
        } else {
            PlanoEstudo plano = new PlanoEstudo(
                    Dados.listarPlanos().size() + 1,
                    disciplina, data, horario, objetivo
            );
            Dados.listarPlanos().add(plano);
            atualizarPlanos();
            txtDisciplina.setText("");
            txtDataPlano.setText("");
            txtHorario.setText("");
            txtObjetivo.setText("");
            JOptionPane.showMessageDialog(this, "Plano de estudos salvo com sucesso.");
        }
    }

    private void atualizarTarefas() {
        DefaultTableModel modelo = (DefaultTableModel) tblTarefas.getModel();
        modelo.setRowCount(0);
        for (Tarefa tarefa : Dados.listarTarefas()) {
            modelo.addRow(new Object[]{
                tarefa.getTitulo(), tarefa.getDescricao(),
                tarefa.getStatus(), tarefa.getData()
            });
        }
        atualizarDashboard();
    }

    private void atualizarMateriais() {
        DefaultTableModel modelo = (DefaultTableModel) tblMateriais.getModel();
        modelo.setRowCount(0);
        for (Material material : Dados.listarMateriais()) {
            modelo.addRow(new Object[]{
                material.getNomeArquivo(), material.getTipo(), material.getCaminho()
            });
        }
        atualizarDashboard();
    }

    private void atualizarPlanos() {
        DefaultTableModel modelo = (DefaultTableModel) tblPlanos.getModel();
        modelo.setRowCount(0);
        for (PlanoEstudo plano : Dados.listarPlanos()) {
            modelo.addRow(new Object[]{
                plano.getDisciplina(), plano.getData(),
                plano.getHorario(), plano.getObjetivo()
            });
        }
        atualizarDashboard();
    }

    private void atualizarDashboard() {
        lblTotalTarefas.setText(String.valueOf(Dados.listarTarefas().size()));
        lblTotalMateriais.setText(String.valueOf(Dados.listarMateriais().size()));
        lblTotalPlanos.setText(String.valueOf(Dados.listarPlanos().size()));
    }

    private void limparTarefa() {
        txtTituloTarefa.setText("");
        txtDescricaoTarefa.setText("");
        txtDataTarefa.setText("");
        cmbStatusTarefa.setSelectedIndex(0);
    }

    private void mostrarTela(String nome) {
        atualizarDashboard();
        telas.show(painelConteudo, nome);
    }
}
