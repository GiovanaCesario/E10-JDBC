package org.example.testes;

import org.example.agencia.Agencia;
import org.example.agencia.agenciaDAO.*;
import org.example.banco.Banco;
import org.example.banco.bancoDAO.*;
import org.example.cliente.Cliente;
import org.example.cliente.clienteDAO.*;
import org.example.conta.Conta;
import org.example.conta.contaDAO.*;
import org.example.contaCliente.*;
import org.example.conexao.FalhaConexaoException;

import java.util.List;

/**
 * Testes feitos para ver se os metodos do DAO estavam funcionando
 */

public class ContaClienteDAOTest {

    // Testa a inserção de um relacionamento entre conta e cliente
    public static void testaInsere() {
        try {
            Banco banco1 = new Banco("11.111.111/0001-11", "Banco 1");
            
            Agencia agencia1 = new Agencia(1111, "Rua A 123", "1111@gmail.com", banco1);
            
            Conta conta1 = new Conta(11111111, 1000, agencia1);
            
            Cliente cliente1 = new Cliente("111.111.111-11", "Giovana", "gio@gmail.com");

            BancoDAO.insere(banco1);
            AgenciaDAO.insere(agencia1);
            ContaDAO.insere(conta1);
            ClienteDAO.insere(cliente1);

            // Cria o relacionamento ContaCliente
            ContaClienteDAO.insereContaCliente(11111111, "111.111.111-11");

            // Verifica o relacionamento
            List<Cliente> clientes = ContaClienteDAO.obtemClientesDeConta(11111111);
            System.out.println(clientes);

        } catch (ContaClienteExistenteException | FalhaConexaoException | ClienteNaoEncontradoException | 
                 BancoExistenteException | AgenciaExistenteException |
                 ClienteExistenteException | ContaExistenteException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    // Testa a remoção de um relacionamento Conta-Cliente
    public static void testaRemove() {
        try {
            Banco banco2 = new Banco("22.222.222/0002-22", "Banco 2");

            Agencia agencia2 = new Agencia(2222, "Rua A 223", "2222@gmail.com", banco2);

            Conta conta2 = new Conta(22222222, 2000, agencia2);

            Cliente cliente2 = new Cliente("222.222.222-22", "Giovana", "gio@gmail.com");

            BancoDAO.insere(banco2);
            AgenciaDAO.insere(agencia2);
            ContaDAO.insere(conta2);
            ClienteDAO.insere(cliente2);

            // Cria o relacionamento Conta-Cliente
            ContaClienteDAO.insereContaCliente(22222222, "222.222.222-22");

            // Remover o relacionamento
            ContaClienteDAO.removeContaCliente(22222222, "222.222.222-22");

            // Verifica se o relacionamento foi removido
            List<Cliente> clientes = ContaClienteDAO.obtemClientesDeConta(22222222);
            System.out.println(clientes);

        } catch (ContaClienteNaoEncontradoException | FalhaConexaoException | ClienteNaoEncontradoException | 
                 BancoExistenteException | AgenciaExistenteException | ContaExistenteException | 
                 ClienteExistenteException | ContaClienteExistenteException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    // Testa a obtenção de todas as contas de um cliente
    public static void testaObtemContasDeCliente() {
        try {
            Banco banco3 = new Banco("33.333.333/0003-33", "Banco 3");

            Agencia agencia3 = new Agencia(3333, "Rua A 333", "3333@gmail.com", banco3);

            Conta conta3 = new Conta(33333333, 3000, agencia3);
            Conta conta13 = new Conta(55555555, 13000, agencia3);
            Conta conta33 = new Conta(31313131, 33000, agencia3);

            Cliente cliente3 = new Cliente("333.333.333-33", "Giovana", "gio@gmail.com");

            BancoDAO.insere(banco3);
            AgenciaDAO.insere(agencia3);
            ClienteDAO.insere(cliente3);
            
            ContaDAO.insere(conta3);
            ContaDAO.insere(conta13);
            ContaDAO.insere(conta33);
            
            // Cria o relacionamento ContaCliente
            ContaClienteDAO.insereContaCliente(33333333, "333.333.333-33");
            ContaClienteDAO.insereContaCliente(55555555, "333.333.333-33");
            ContaClienteDAO.insereContaCliente(31313131, "333.333.333-33");


            // Obtém as contas associadas ao cliente
            List<Conta> contas = ContaClienteDAO.obtemContasDeCliente("333.333.333-33");
            System.out.println(contas);

        } catch (FalhaConexaoException | ContaNaoEncontradaException | BancoExistenteException |
                 AgenciaExistenteException | ClienteExistenteException | ContaExistenteException |
                 ContaClienteExistenteException | AgenciaNaoEncontradaException | BancoNaoEncontradoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    // Testa a obtenção de todos os clientes de uma conta
    public static void testaObtemClientesDeConta() {
        try {
            // Cria Conta
            Banco banco8 = new Banco("88.888.888/0008-88", "Banco 8");

            Agencia agencia8 = new Agencia(8888, "Rua A 883", "8888@gmail.com", banco8);

            Conta conta8 = new Conta(88888888, 8000, agencia8);

            Cliente cliente8 = new Cliente("888.888.888-88", "Giovana", "gio@gmail.com");
            Cliente cliente9 = new Cliente("999.999.999-99", "Fernanda", "fe@gmail.com");
            Cliente cliente7 = new Cliente("777.777.777-77", "Graziela", "gra@gmail.com");

            BancoDAO.insere(banco8);
            AgenciaDAO.insere(agencia8);
            ContaDAO.insere(conta8);
            
            ClienteDAO.insere(cliente8);
            ClienteDAO.insere(cliente9);
            ClienteDAO.insere(cliente7);

            // Cria o relacionamento Conta-Cliente
            ContaClienteDAO.insereContaCliente(88888888, "888.888.888-88");
            ContaClienteDAO.insereContaCliente(88888888, "999.999.999-99");
            ContaClienteDAO.insereContaCliente(88888888, "777.777.777-77");

            // Obtém os clientes associados à conta
            List<Cliente> clientes = ContaClienteDAO.obtemClientesDeConta(88888888);
            System.out.println(clientes);

        } catch (FalhaConexaoException | BancoExistenteException | AgenciaExistenteException |
                 ClienteExistenteException | ContaExistenteException | ContaClienteExistenteException |
                 ClienteNaoEncontradoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    // Testa a exceção ContaClienteNaoEncontradoException
    public static void testaContaClienteNaoEncontradoException() {
        try {
            ContaClienteDAO.removeContaCliente(00000000, "000.000.000-00");
        } catch (FalhaConexaoException | ContaClienteNaoEncontradoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }
}

