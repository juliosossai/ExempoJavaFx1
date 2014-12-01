package sample.bancoDados;

import sample.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juliosossai on 20/11/2014.
 */
public class BancoDeDados {

    public List<Cliente> tbCliente = new ArrayList<Cliente>();

    private static  BancoDeDados bancoDeDados;

    private BancoDeDados(){
        tbCliente.add(new Cliente("1","Julio","02612559914","julio.sossai@hitechti.com.br"));
        tbCliente.add(new Cliente("2","Cesar","02813241600","cesar@gmail.com"));
        tbCliente.add(new Cliente("3","Lucas","12641278900","lucas@hitechti.com.br"));
    }

    public static BancoDeDados getInstance(){
        if (bancoDeDados == null){
            bancoDeDados = new BancoDeDados();
        }

        return bancoDeDados;
    }
}
