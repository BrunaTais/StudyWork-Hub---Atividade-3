package study_work_hub;

/**
 * Representa um material de estudo armazenado no sistema.
 *
 * @author Bruna Tais
 */
public class Material {
    private int idMaterial;
    private String nomeArquivo;
    private String tipo;
    private String caminho;

    public Material(int idMaterial, String nomeArquivo, String tipo, String caminho) {
        this.idMaterial = idMaterial;
        this.nomeArquivo = nomeArquivo;
        this.tipo = tipo;
        this.caminho = caminho;
    }

    public void visualizar() {
        javax.swing.JOptionPane.showMessageDialog(
                null,
                "Material: " + nomeArquivo + "\nTipo: " + tipo
                + "\nCaminho: " + caminho
        );
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCaminho() {
        return caminho;
    }
}
