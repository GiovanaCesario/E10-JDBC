package org.example.testes;

import org.example.banco.Banco;
import org.example.conexao.FalhaConexaoException;
import org.example.banco.bancoDAO.*;

import java.util.List;

/**
 * Testes feitos para ver se os metodos do DAO estavam funcionando
 */

public class BancoDAOTest {

    //Testa a inserção de um banco.
    public static void testaInsere() {
        try {
            // Cria um banco e persiste
            Banco banco = new Banco("11.111.111/0001-11", "Inter");
            BancoDAO.insere(banco);

            // Tenta recuperar o banco recém criado
            Banco bancoRecuperado = BancoDAO.obtemBancoPorCnpj("11.111.111/0001-11");

            // Mostra as informações dos dois para serem comparadas
            System.out.println(banco);
            System.out.println(bancoRecuperado);
        } catch (BancoExistenteException | FalhaConexaoException | BancoNaoEncontradoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a atualização de um banco.
    public static void testaAtualiza() {
        try {
            // Cria um banco e persiste
            Banco banco = new Banco("22.222.222/0001-22", "Nubank");
            BancoDAO.insere(banco);

            // Mostra o banco antes da alteracao
            System.out.println(banco);

            // Altera dados e persiste
            banco.setNome("Bradesco");
            BancoDAO.atualiza(banco);

            // Tenta recuperar o banco recém atualizado
            Banco bancoRecuperado = BancoDAO.obtemBancoPorCnpj("22.222.222/0001-22");

            // Mostra as informações dos dois para serem comparadas
            System.out.println(banco);
            System.out.println(bancoRecuperado);
        } catch (BancoExistenteException | FalhaConexaoException | BancoNaoEncontradoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a remoção de um banco.
    public static void testaRemove() {
        try {
            // Cria um banco e persiste
            Banco banco = new Banco("33.333.333/0001-33", "Banco do Brasil");
            BancoDAO.insere(banco);

            //Mostra o banco antes de remover
            System.out.println(BancoDAO.obtemBancoPorCnpj("33.333.333/0001-33"));

            // Tenta remover
            BancoDAO.remove(banco);

            //Tenta mostrar o banco removido
            System.out.println(BancoDAO.obtemBancoPorCnpj("33.333.333/0001-33"));
        } catch (BancoExistenteException | BancoNaoEncontradoException | FalhaConexaoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a obtenção de um banco por matrícula.
    public static void testaObtemBancoPorCnpj() {
        try {
            // Cria um banco e persiste
            Banco banco = new Banco("44.444.444/0001-46", "Santander");
            BancoDAO.insere(banco);

            // Tenta recuperar o banco recém atualizado
            Banco bancoRecuperado = BancoDAO.obtemBancoPorCnpj("44.444.444/0001-46");

            // Mostra as informações dos dois para serem comparadas
            System.out.println(banco);
            System.out.println(bancoRecuperado);
        } catch (BancoExistenteException | FalhaConexaoException | BancoNaoEncontradoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a obtenção da lista de bancos.
    public static void testaObtemListaBancos() {
        try {
            // Cria bancos e persiste
            Banco banco1 = new Banco("55.555.555/0001-55", "C6");
            Banco banco2 = new Banco("66.666.666/0001-66", "Itau");
            BancoDAO.insere(banco1);
            BancoDAO.insere(banco2);

            // Obtem a lista de bancos
            List<Banco> listabancos = BancoDAO.obtemListaBancos();

            // Exibe a lista de bancos
            System.out.println("Lista de bancos: ");
            for(Banco banco : listabancos) {
                System.out.println(banco);
            }

        } catch (BancoExistenteException | FalhaConexaoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a exceção BancoNaoEncontradoException.
    public static void testaBancoNaoEncontradoException() {
        try {
            BancoDAO.obtemBancoPorCnpj("00.000.000/0001-00");
        } catch (FalhaConexaoException | BancoNaoEncontradoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }
}

