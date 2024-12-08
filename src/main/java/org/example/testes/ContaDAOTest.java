package org.example.testes;

import org.example.agencia.Agencia;
import org.example.agencia.agenciaDAO.*;
import org.example.banco.Banco;
import org.example.banco.bancoDAO.*;
import org.example.conexao.FalhaConexaoException;
import org.example.conta.Conta;
import org.example.conta.contaDAO.*;

import java.util.List;

/**
 * Testes feitos para ver se os metodos do DAO estavam funcionando
 */

public class ContaDAOTest {

    //Testa a inserção de um Conta.
    public static void testaInsere() {
        try {
            // Cria Banco
            Banco banco1 = new Banco("11.111.111/0001-11", "Banco 1");

            //Cria Agencia
            Agencia agencia1 = new Agencia(1111, "Rua A 123", "1111@gmail.com", banco1);

            // Cria um Conta e persiste
            Conta conta1 = new Conta(11111111, 1000, agencia1);

            BancoDAO.insere(banco1);
            AgenciaDAO.insere(agencia1);
            ContaDAO.insere(conta1);

            // Tenta recuperar o Conta recém criado
            Conta contaRecuperada = ContaDAO.obtemContaPorNumero(11111111);

            // Mostra as informações dos dois para serem comparadas
            System.out.println(conta1);
            System.out.println(contaRecuperada);
        } catch (ContaExistenteException | FalhaConexaoException | ContaNaoEncontradaException |
                 AgenciaNaoEncontradaException | BancoNaoEncontradoException | BancoExistenteException |
                 AgenciaExistenteException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a atualização de um Conta.
    public static void testaAtualiza() {
        try {
            // Cria Banco
            Banco banco2 = new Banco("22.222.222/0002-22", "Banco 2");

            //Cria Agencia
            Agencia agencia2 = new Agencia(2222, "Rua A 223", "2222@gmail.com", banco2);

            // Cria um Conta e persiste
            Conta conta2 = new Conta(22222222, 2000, agencia2);

            BancoDAO.insere(banco2);
            AgenciaDAO.insere(agencia2);
            ContaDAO.insere(conta2);

            //Montra a conta antes da alteracao
            System.out.println(conta2);

            // Altera dados e persiste
            conta2.setSaldo(2222);
            ContaDAO.atualiza(conta2);

            // Tenta recuperar o Conta recém atualizado
            Conta contaRecuperada = ContaDAO.obtemContaPorNumero(22222222);

            // Mostra as informações dos dois para serem comparadas
            System.out.println(conta2);
            System.out.println(contaRecuperada);
        } catch (ContaExistenteException | FalhaConexaoException | ContaNaoEncontradaException |
                AgenciaNaoEncontradaException | BancoNaoEncontradoException | AgenciaExistenteException | 
                BancoExistenteException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a remoção de uma conta.
    public static void testaRemove() {
        try {
            // Cria Banco
            Banco banco3 = new Banco("33.333.333/0003-33", "Banco 3");

            //Cria Agencia
            Agencia agencia3 = new Agencia(3333, "Rua A 333", "3333@gmail.com", banco3);

            // Cria um Conta e persiste
            Conta conta3 = new Conta(33333333, 3000, agencia3);

            BancoDAO.insere(banco3);
            AgenciaDAO.insere(agencia3);
            ContaDAO.insere(conta3);

            //Mostra a conta antes de remover
            System.out.println(ContaDAO.obtemContaPorNumero(33333333));

            //Tenta remover
            ContaDAO.remove(conta3);

            //Tenta mostrar a conta removida
            System.out.println(ContaDAO.obtemContaPorNumero(33333333));
        } catch (ContaExistenteException | ContaNaoEncontradaException | FalhaConexaoException |
                AgenciaNaoEncontradaException | BancoNaoEncontradoException | AgenciaExistenteException |
                 BancoExistenteException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a obtenção de uma conta por numero.
    public static void testaObtemContaPorNumero() {
        try {
            // Cria Banco
            Banco banco4 = new Banco("44.444.444/0004-44", "Banco 4");

            //Cria Agencia
            Agencia agencia4 = new Agencia(4444, "Rua A 443", "4444@gmail.com", banco4);

            // Cria um Conta e persiste
            Conta conta4 = new Conta(44444444, 4000, agencia4);

            BancoDAO.insere(banco4);
            AgenciaDAO.insere(agencia4);
            ContaDAO.insere(conta4);

            // Tenta recuperar a conta recém atualizado
            Conta contaRecuperada = ContaDAO.obtemContaPorNumero(44444444);

            // Mostra as informações dos dois para serem comparadas
            System.out.println(conta4);
            System.out.println(contaRecuperada);
        } catch (ContaExistenteException | FalhaConexaoException | ContaNaoEncontradaException |
                AgenciaNaoEncontradaException | BancoNaoEncontradoException | AgenciaExistenteException | 
                BancoExistenteException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a obtenção da lista de contas.
    public static void testaObtemListaContas() {
        try {
            // Cria Banco
            Banco banco5 = new Banco("55.555.555/0005-55", "Banco 5");

            //Cria Agencia
            Agencia agencia5 = new Agencia(5555, "Rua A 553", "5555@gmail.com", banco5);

            // Cria um Conta e persiste
            Conta conta5 = new Conta(55555555, 5000, agencia5);
            Conta conta6 = new Conta(66666666, 6000, agencia5);
            
            BancoDAO.insere(banco5);
            AgenciaDAO.insere(agencia5);
            ContaDAO.insere(conta5);
            ContaDAO.insere(conta6);

            // Obtem a lista de contas
            List<Conta> listaContas = ContaDAO.obtemListaContas();

            // Exibe a lista de contas
            System.out.println("Lista de Contas: ");
            for(Conta conta : listaContas) {
                System.out.println(conta);
            }

        } catch (ContaExistenteException | FalhaConexaoException | AgenciaNaoEncontradaException |
                BancoNaoEncontradoException | AgenciaExistenteException | BancoExistenteException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a obtenção da lista de contas.
    public static void testaObtemListaContasDeUmaAgencia() {
        try {
            // Cria Banco
            Banco banco6 = new Banco("66.666.666/0006-66", "Banco 6");

            //Cria Agencia
            Agencia agencia6 = new Agencia(6666, "Rua A 663", "6666@gmail.com", banco6);

            // Cria um Conta e persiste
            Conta conta6 = new Conta(66666666, 6000, agencia6);
            Conta conta7 = new Conta(77777777, 7000, agencia6);

            BancoDAO.insere(banco6);
            AgenciaDAO.insere(agencia6);
            ContaDAO.insere(conta6);
            ContaDAO.insere(conta7);

            // Obtem a lista de contas
            List<Conta> listaContas = ContaDAO.obtemListaContasDeUmaAgencia(agencia6.getNumero());

            // Exibe a lista de contas
            System.out.println("Lista de Contas da Agencia " +agencia6.getNumero() + ":");
            for(Conta conta : listaContas) {
                System.out.println(conta);
            }

        } catch (ContaExistenteException | FalhaConexaoException | AgenciaNaoEncontradaException |
                 BancoNaoEncontradoException | AgenciaExistenteException | BancoExistenteException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }
    
    //Testa a exceção ContaNaoEncontradaException.
    public static void testaContaNaoEncontradaException() {
        try {
            ContaDAO.obtemContaPorNumero(00000000);
        } catch (FalhaConexaoException | ContaNaoEncontradaException | AgenciaNaoEncontradaException | 
                BancoNaoEncontradoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }
}
