package sample.telas;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.logica.ClienteBusinnes;
import sample.modelo.Cliente;

/**
 * Created by juliosossai on 20/11/2014.
 */
public class TelaCadastro {

    private Stage palco;
    private VBox janela;
    private EstadoTela estadoTela = EstadoTela.INSERINDO;
    private Cliente entidade;
    private TextField edtId = new TextField();
    private TextField edtNome = new TextField();
    private TextField edtCNPJ = new TextField();
    private TextField edtEmail = new TextField();

    public TelaCadastro(Stage palco) {
        this.palco = palco;

        janela = new VBox();
        //Comportamento de entrada de dados
        Label lblId = new Label("ID");

        edtId.promptTextProperty().setValue("Informe o código");
        janela.getChildren().addAll(lblId, edtId);

        //adicionando componentes a janela princiapl
        Label lblNome = new Label();
        lblNome.setText("Nome: ");

        //adicionando componentes ao janela principal
        janela.getChildren().add(lblNome);
        janela.getChildren().add(edtNome);

        //adicionando componentes a janela princiapl
        Label lblCNPJ = new Label();
        lblCNPJ.setText("CNPJ: ");

        //adicionando componentes ao janela principal
        janela.getChildren().add(lblCNPJ);
        janela.getChildren().add(edtCNPJ);

        //adicionando componentes ao janela principal
        Label lblEmail = new Label("Email:");

        edtEmail.promptTextProperty().setValue("Digite aqui o email");
        janela.getChildren().addAll(lblEmail, edtEmail);

        // controles
        Button btnConfirmar = new Button("Confirmar");


        btnConfirmar.setOnAction(event -> {
            ClienteBusinnes businnes = new ClienteBusinnes();

            if (estadoTela.equals(EstadoTela.INSERINDO)) {
                businnes.addNovaCliente(edtId.getText(), edtNome.getText(), edtCNPJ.getText(), edtEmail.getText());
                edtId.textProperty().setValue(null);
                edtNome.textProperty().setValue(null);
                edtCNPJ.textProperty().setValue(null);
                edtEmail.textProperty().setValue(null);
            } else if (estadoTela.equals(EstadoTela.EDITANDO)) {
                businnes.alterarCliente(this.entidade);
                try {
                    abrirTelaDePesquisa(palco);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnJanelaPesquisa = new Button("Pesquisa");

        ToolBar toolBar = new ToolBar();
        toolBar.getItems().addAll(btnConfirmar, btnJanelaPesquisa);

        janela.getChildren().add(toolBar);


        btnJanelaPesquisa.setOnAction(action -> {
            //comandos a executar quanto clicarmos no botão pesquisa
            try {
                abrirTelaDePesquisa(palco);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    private void abrirTelaDePesquisa(Stage palco) throws Exception {
        TelaPesquisa pesquisa = new TelaPesquisa();
        pesquisa.start(palco);
        pesquisa.atualizarGrid();
    }

    public VBox getTela() {
        return this.janela;
    }

    public void iniciaInsercao() {
        this.estadoTela = EstadoTela.INSERINDO;
    }

    public void iniciaAlteracao(Cliente cliente) {
        this.entidade = cliente;
        this.estadoTela = EstadoTela.EDITANDO;
        edtId.textProperty().setValue(cliente.getIdCli());
        edtNome.textProperty().setValue(cliente.getNomeCli());
        edtCNPJ.textProperty().setValue(cliente.getCnpjCli());
        edtEmail.textProperty().setValue(cliente.getEmailCli());

    }

}
