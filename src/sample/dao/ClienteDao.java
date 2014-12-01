package sample.dao;

import sample.bancoDados.BancoDeDados;
import sample.modelo.Cliente;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by juliosossai on 20/11/2014.
 */
public class ClienteDao {

    private BancoDeDados banco = BancoDeDados.getInstance();

    public void salvar(Cliente cliente) {

        banco.tbCliente.add(cliente);

        System.out.println("Salvando cliente: " + cliente);
    }

    public Cliente pesquisaPorCodigo(String chave) {

        List<Cliente> clientes = banco.tbCliente;

        for (Cliente cliente : clientes) {
            if (cliente.getIdCli().equals(chave)) {
                return cliente;
            }
        }

        return null;
    }

    public void remover(Cliente cliente) {
        banco.tbCliente.remove(cliente);
    }

    public void alterarCliente(final Cliente clienteSelecionada) {

        banco.tbCliente.stream().forEach(new Consumer<Cliente>() {
            @Override
            public void accept(Cliente cliente) {
                if (cliente.getIdCli().equals(clienteSelecionada.getIdCli())) {
                    cliente.setNomeCli(clienteSelecionada.getNomeCli());
                    cliente.setEmailCli(clienteSelecionada.getEmailCli());
                    cliente.setCnpjCli(clienteSelecionada.getCnpjCli());
                }
            }
        });

    }

}
