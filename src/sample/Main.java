package sample;


import javafx.application.Application;
import javafx.stage.Stage;
import sample.bancoDados.BancoDeDados;
import sample.telas.TelaPesquisa;

public class Main extends Application {

    @Override
    public void start(Stage palco) throws Exception {
        try {

            TelaPesquisa pesquisa = new TelaPesquisa();
            pesquisa.start(palco);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);

        BancoDeDados b = BancoDeDados.getInstance();


    }

}
