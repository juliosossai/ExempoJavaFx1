package sample.logica;

import sample.dao.ClienteDao;
import sample.modelo.Cliente;

/**
 * Created by juliosossai on 20/11/2014.
 */
public class ClienteBusinnes {

    private ClienteDao dao = new ClienteDao();

    public void addNovaCliente(String id, String nome, String CNPJ, String email) {

        //executar validações, testes de nulidade
        Cliente cliente = new Cliente();
        cliente.setIdCli(id);
        cliente.setNomeCli(nome);
        cliente.setCnpjCli(CNPJ);
        cliente.setEmailCli(email);

        dao.salvar(cliente);
    }

    public Cliente pesquisaPorCodigo(String chave) throws Exception {

        String nova = chave.trim();

        if (nova == null || nova.equals("")) {
            throw new Exception("Campo de pesquisa nulo, inválido");
        } else {
            return  dao.pesquisaPorCodigo(chave);
        }


    }

    public  void excluirCliente(Cliente cliente) {
        dao.remover(cliente);
    }

    public void alterarCliente(Cliente cliente) {
        dao.alterarCliente(cliente);
    }

}
