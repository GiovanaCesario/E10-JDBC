package org.example.testes;

import org.example.conexao.FalhaConexaoException;
import org.example.cliente.Cliente;
import org.example.cliente.clienteDAO.*;

import java.util.List;

/**
 * Testes feitos para ver se os metodos do DAO estavam funcionando
 */

public class ClienteDAOTest {

    //Testa a inserção de um cliente.
    public static void testaInsere() {
        try {
            // Cria um cliente e persiste
            Cliente cliente = new Cliente("111.111.111-11", "Giovana", "gio@gmail.com");
            ClienteDAO.insere(cliente);

            // Tenta recuperar o cliente recém criado
            Cliente clienteRecuperado = ClienteDAO.obtemClientePorCpf("111.111.111-11");

            // Mostra as informações dos dois para serem comparadas
            System.out.println(cliente);
            System.out.println(clienteRecuperado);
        } catch (ClienteExistenteException | FalhaConexaoException | ClienteNaoEncontradoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a atualização de um cliente.
    public static void testaAtualiza() {
        try {
            // Cria um Cliente e persiste
            Cliente cliente = new Cliente("222.222.222-22", "Fernanda", "fe@hotmail.com");
            ClienteDAO.insere(cliente);

            //Montra a cliente antes da alteracao
            System.out.println(cliente);

            // Altera dados e persiste
            cliente.setEmail("fefefe@gmail.com");
            ClienteDAO.atualiza(cliente);

            // Tenta recuperar o Cliente recém atualizado
            Cliente clienteRecuperado = ClienteDAO.obtemClientePorCpf("222.222.222-22");

            // Mostra as informações dos dois para serem comparadas
            System.out.println(cliente);
            System.out.println(clienteRecuperado);
        } catch (ClienteExistenteException | FalhaConexaoException | ClienteNaoEncontradoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a remoção de uma cliente.
    public static void testaRemove() {
        try {
            // Cria uma cliente e persiste
            Cliente cliente = new Cliente("333.333.333-33", "Graziela", "gra@gmail.com");
            ClienteDAO.insere(cliente);

            //Mostra a cliente antes de remover
            System.out.println(ClienteDAO.obtemClientePorCpf("333.333.333-33"));

            //Tenta remover
            ClienteDAO.remove(cliente);

            //Tenta mostrar a cliente removida
            System.out.println(ClienteDAO.obtemClientePorCpf("333.333.333-33"));
        } catch (ClienteExistenteException | ClienteNaoEncontradoException | FalhaConexaoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a obtenção de um Cliente por numero.
    public static void testaObtemClientePorCpf() {
        try {
            // Cria um Cliente e persiste
            Cliente cliente = new Cliente("444.444.444-44", "Neuzelia", "neu@gmail.com");
            ClienteDAO.insere(cliente);

            // Tenta recuperar o Cliente recém atualizado
            Cliente clienteRecuperado = ClienteDAO.obtemClientePorCpf("444.444.444-44");

            // Mostra as informações dos dois para serem comparadas
            System.out.println(cliente);
            System.out.println(clienteRecuperado);
        } catch (ClienteExistenteException | FalhaConexaoException | ClienteNaoEncontradoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a obtenção da lista de Clientes.
    public static void testaObtemListaClientes() {
        try {
            // Cria Clientes e persiste
            Cliente cliente1 = new Cliente("555.555.555-55", "Wanderley", "www@gmail.com");
            Cliente cliente2 = new Cliente("666.666.666.66", "Solange", "sol@outlook.com");
            ClienteDAO.insere(cliente1);
            ClienteDAO.insere(cliente2);

            // Obtem a lista de Clientes
            List<Cliente> listaClientes = ClienteDAO.obtemListaClientes();

            // Exibe a lista de Clientes
            System.out.println("Lista de Clientes: ");
            for(Cliente cliente : listaClientes) {
                System.out.println(cliente);
            }

        } catch (ClienteExistenteException | FalhaConexaoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a exceção ClienteNaoEncontradoException.
    public static void testaClienteNaoEncontradoException() {
        try {
            ClienteDAO.obtemClientePorCpf("000.000.000-00");
        } catch (FalhaConexaoException | ClienteNaoEncontradoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }
}
