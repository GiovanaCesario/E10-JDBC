package org.example;

import org.example.agencia.Agencia;
import org.example.agencia.agenciaDAO.*;
import org.example.banco.Banco;
import org.example.banco.bancoDAO.*;
import org.example.cliente.Cliente;
import org.example.cliente.clienteDAO.*;
import org.example.conexao.FalhaConexaoException;
import org.example.conta.Conta;
import org.example.conta.contaDAO.*;
import org.example.contaCliente.ContaClienteDAO;
import org.example.contaCliente.ContaClienteExistenteException;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Cria as tabelas se nao existirem
        try {
            BancoDAO.criaTabelaBanco();
            AgenciaDAO.criaTabelaAgencia();
            ContaDAO.criaTabelaConta();
            ClienteDAO.criaTabelaCliente();
            ContaClienteDAO.criaTabelaContaCliente();

        } catch (FalhaConexaoException e) {
            System.out.println("Falha na conexao com o banco de dados");
        }

       //Cria objetos

        Banco banco = new Banco("11.111.111/0001-11", "Inter");

        Agencia agencia1 = new Agencia(1111, "Rua A 123", "1111@gnail.com", banco);
        Agencia agencia2 = new Agencia(2222, "Rua B 321", "2222@gnail.com", banco);

        banco.associaAgencia(agencia1);
        banco.associaAgencia(agencia2);

        Conta conta1 = new Conta(123456, 300.50f, agencia1);
        Conta conta2 = new Conta(654321, 500, agencia2);

        agencia1.associaConta(conta1);
        agencia2.associaConta(conta2);

        Cliente cliente1 = new Cliente("111.111.111-11", "Giovana", "gio@gmail.com");
        Cliente cliente2 = new Cliente("222.222.222-22", "Fernanda", "fe@gmail.com");

          //Cliente2 possui duas conta e Conta1 possui dois clientes
        conta1.associaCliente(cliente1);
        conta1.associaCliente(cliente2);
        conta2.associaCliente(cliente2);

        //Carrega os objetos no banco de dados

        try {
            BancoDAO.insere(banco);

            AgenciaDAO.insere(agencia1);
            AgenciaDAO.insere(agencia2);

            ContaDAO.insere(conta1);
            ContaDAO.insere(conta2);

            ClienteDAO.insere(cliente1);
            ClienteDAO.insere(cliente2);

            ContaClienteDAO.insereContaCliente(conta1.getNumero(), cliente1.getCpf());
            ContaClienteDAO.insereContaCliente(conta2.getNumero(), cliente2.getCpf());

        } catch (FalhaConexaoException | BancoExistenteException | AgenciaExistenteException |
                 ContaExistenteException | ClienteExistenteException | ContaClienteExistenteException e) {

            System.err.println("Excecao: " + e.getMessage());
        }

        //Altera alguns objetos e relacoes

        cliente1.setEmail("gg@gmail.com"); //Muda o email da Giovana
        conta2.setSaldo(20000); //Muda o saldo da conta da Fernanda
        conta1.associaCliente(cliente2); //Adiciona a Fernanda da conta conjunta com a Giovana

        //Atualiza no banco de dados

        try {
            ClienteDAO.atualiza(cliente1);
            ContaDAO.atualiza(conta2);
            ContaClienteDAO.insereContaCliente(conta1.getNumero(), cliente2.getCpf());

        } catch (FalhaConexaoException | ClienteNaoEncontradoException | ContaNaoEncontradaException |
                 ContaClienteExistenteException e) {

            System.err.println("Excecao: " + e.getMessage());
        }

        //Pega do banco os dados alterados

        List<Cliente> clientesDaConta1 = null;

        try {
            cliente1 = ClienteDAO.obtemClientePorCpf(cliente1.getCpf());
            conta2 = ContaDAO.obtemContaPorNumero(conta2.getNumero());
            clientesDaConta1 = ContaClienteDAO.obtemClientesDeConta(conta1.getNumero());


            //Imprime os dados alterados

            System.out.println(cliente1);
            System.out.println(conta2);

            for(Cliente cliente : clientesDaConta1) {
                System.out.println(cliente);
            }

        } catch (FalhaConexaoException | ClienteNaoEncontradoException | ContaNaoEncontradaException |
                 AgenciaNaoEncontradaException | BancoNaoEncontradoException e) {

            System.err.println("Excecao: " + e.getMessage());
        }
    }
}

