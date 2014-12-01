package sample.telas;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.bancoDados.BancoDeDados;
import sample.logica.ClienteBusinnes;
import sample.modelo.Cliente;

import static javafx.scene.layout.Priority.ALWAYS;


/**
 * Created by juliosossai on 20/11/2014.
 */
public class TelaPesquisa extends Application {

    private ObservableList<Cliente> dadosGrid = FXCollections.observableArrayList();

    private BorderPane borderPane = new BorderPane();

    private TableView<Cliente> gridClientes;

    private Stage stage;

    @Override
    public void start(Stage palco) throws Exception {
        dadosGrid.addAll(BancoDeDados.getInstance().tbCliente);

        palco.setTitle("Pesquisa");

        borderPane.paddingProperty().setValue(new Insets(10));
        borderPane.setPrefSize(780, 590);

        HBox boxCampoDePesquisa = montaPainelDePesquisa();

        borderPane.setTop(boxCampoDePesquisa);

        gridClientes = montaGridClientes();

        borderPane.setCenter(gridClientes);


        ToolBar toolBar = montaBarraDeFerramentas();

        borderPane.setBottom(toolBar);

        palco.setScene(new Scene(borderPane, 800, 600));
        palco.show();

        this.stage = palco;
    }

    private ToolBar montaBarraDeFerramentas() {

        ToolBar toolBar = new ToolBar();

        Button btnNovo = new Button("Novo");
        Button btnAlterar = new Button("Alterar");
        Button btnExcluir = new Button("Excluir");

        toolBar.getItems().addAll(btnNovo, btnAlterar, btnExcluir);


        btnNovo.setOnAction(click -> {

            invocarTelaDeCadastro(null);

        });

        btnExcluir.setOnAction(click -> {


            if (gridClientes.getSelectionModel().getSelectedItem() != null) {
                Cliente cliente = gridClientes.getSelectionModel().getSelectedItems().get(0);
                ClienteBusinnes businnes = new ClienteBusinnes();
                businnes.excluirCliente(cliente);
                //atualizar a tela
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dadosGrid = FXCollections.observableArrayList();
                        dadosGrid.addAll(BancoDeDados.getInstance().tbCliente);
                        gridClientes.setItems(null);
                        gridClientes.setItems(dadosGrid);
                    }
                });


            }
        });


        btnAlterar.setOnAction(click -> {
            if (gridClientes.getSelectionModel().getSelectedItem() != null) {
                Cliente cliente = gridClientes.getSelectionModel().getSelectedItems().get(0);

                //chamar a tela de cadastro

                invocarTelaDeCadastro(cliente);


                ClienteBusinnes businnes = new ClienteBusinnes();
                businnes.alterarCliente(cliente);


            }
        });


        return toolBar;
    }

    private void invocarTelaDeCadastro(Cliente cliente) {
        TelaCadastro cadastro = new TelaCadastro(stage);
        stage.setScene(new Scene(cadastro.getTela(), 800, 600));

        if (cliente == null) {
            cadastro.iniciaInsercao();
        } else {
            cadastro.iniciaAlteracao(cliente);
        }

    }

    private HBox montaPainelDePesquisa() {

        HBox boxCampoDePesquisa = new HBox();
        boxCampoDePesquisa.paddingProperty().setValue(new Insets(10));
        boxCampoDePesquisa.setHgrow(borderPane, ALWAYS);
        //campo de texto
        TextField ct = new TextField();
        ct.setPrefColumnCount(60);

        ct.promptTextProperty().setValue("digite aqui para pesquisar");

        //botão de pesquisa
        Button btnPesquisa = new Button("Busca");
        boxCampoDePesquisa.getChildren().add(ct);
        boxCampoDePesquisa.getChildren().add(btnPesquisa);

        btnPesquisa.setOnAction(action -> {
            String chave = ct.textProperty().get();
            ClienteBusinnes businnes = new ClienteBusinnes();
            try {
                Cliente cliente = businnes.pesquisaPorCodigo(chave);
                if (cliente != null) {
                    dadosGrid.clear();
                    dadosGrid.add(cliente);
                } else {
                    System.out.println(" Pessoa não encontrada");
                }
            } catch (Exception e) {
                e.printStackTrace();
                /*Popup erro = new Popup();
                erro.centerOnScreen();
                erro.getContent().addAll( new Label(e.getMessage()));
                erro.show(palco);*/

            }

        });

        // final TextField edtCodigo = new TextField();
        return boxCampoDePesquisa;
    }

    private TableView<Cliente> montaGridClientes() {

        //Grid de pesquisa com Table view
        TableColumn colunaCodigo = new TableColumn();
        colunaCodigo.minWidthProperty().setValue(100);
        //cabecalho da coluna
        colunaCodigo.setText("Código");
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("idCli"));

        TableColumn colunaNome = new TableColumn();
        colunaNome.minWidthProperty().setValue(100);
        colunaNome.setText("Nome");
        colunaNome.setCellValueFactory(new PropertyValueFactory("nomeCli"));

        TableColumn colunaCNPJ = new TableColumn();
        colunaCNPJ.minWidthProperty().setValue(100);
        colunaCNPJ.setText("CNPJ");
        colunaCNPJ.setCellValueFactory(new PropertyValueFactory("cnpjCli"));

        TableColumn colunaEmail = new TableColumn();
        colunaEmail.minWidthProperty().setValue(100);
        colunaEmail.setText("Email");
        colunaEmail.setMinWidth(200);
        colunaEmail.setCellValueFactory(new PropertyValueFactory("emailCli"));

        TableView<Cliente> gridClientes = new TableView();

        gridClientes.getColumns().addAll(colunaCodigo, colunaNome, colunaCNPJ, colunaEmail);
        gridClientes.setItems(dadosGrid);


        return gridClientes;
    }


    public void atualizarGrid() {
        //atualizar a tela
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dadosGrid = FXCollections.observableArrayList();
                dadosGrid.addAll(BancoDeDados.getInstance().tbCliente);
                gridClientes.setItems(null);
                gridClientes.setItems(dadosGrid);
            }
        });
    }


}
