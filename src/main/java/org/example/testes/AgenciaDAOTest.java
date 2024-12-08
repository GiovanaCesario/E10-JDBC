package org.example.testes;

import org.example.banco.Banco;
import org.example.banco.bancoDAO.BancoDAO;
import org.example.banco.bancoDAO.BancoExistenteException;
import org.example.banco.bancoDAO.BancoNaoEncontradoException;
import org.example.conexao.FalhaConexaoException;
import org.example.agencia.Agencia;
import org.example.agencia.agenciaDAO.*;

import java.util.List;

/**
 * Testes feitos para ver se os metodos do DAO estavam funcionando
 */

public class AgenciaDAOTest {

    //Testa a inserção de um Agencia.
    public static void testaInsere() {
        try {
            //Cria um banco
            Banco banco12 = new Banco("12.123.123/001-12", "Caixa");

            // Cria um Agencia e persiste
            Agencia agencia = new Agencia(0001, "Rua A 123",
                                            "0001@gmail.com", banco12);

            BancoDAO.insere(banco12);
            AgenciaDAO.insere(agencia);

            // Tenta recuperar o Agencia recém criado
            Agencia agenciaRecuperada = AgenciaDAO.obtemAgenciaPorNumero(0001);

            // Mostra as informações dos dois para serem comparadas
            System.out.println(agencia);
            System.out.println(agenciaRecuperada);

        } catch (AgenciaExistenteException | FalhaConexaoException | AgenciaNaoEncontradaException |
                 BancoNaoEncontradoException | BancoExistenteException e) {

            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a atualização de um Agencia.
    public static void testaAtualiza() {
        try {
            //Cria um banco
            Banco banco78 = new Banco("78.789.789/0001-78", "Sicoob");

            // Cria um Agencia e persiste
            Agencia agencia = new Agencia(2992, "Rua B 234",
                                            "2992@gmail.com",banco78);

            BancoDAO.insere(banco78);
            AgenciaDAO.insere(agencia);

            //Montra a agencia antes da alteracao
            System.out.println(agencia);

            // Altera dados e persiste
            agencia.setEndereco("Rua NovaB 432");
            AgenciaDAO.atualiza(agencia);

            // Tenta recuperar o Agencia recém atualizado
            Agencia agenciaRecuperada = AgenciaDAO.obtemAgenciaPorNumero(2992);

            // Mostra as informações dos dois para serem comparadas
            System.out.println(agencia);
            System.out.println(agenciaRecuperada);

        } catch (AgenciaExistenteException | FalhaConexaoException | AgenciaNaoEncontradaException |
                 BancoNaoEncontradoException | BancoExistenteException e) {

            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a remoção de uma agencia.
    public static void testaRemove() {
        try {
            //Cria um banco
            Banco banco56 = new Banco("56.567.567/0001-56", "Banquinho");

            // Cria uma agencia e persiste
            Agencia agencia = new Agencia(7777, "Rua Perfeita 666",
                                            "7777@gmail.com", banco56);

            BancoDAO.insere(banco56);
            AgenciaDAO.insere(agencia);

            //Mostra a agencia antes de remover
            System.out.println(AgenciaDAO.obtemAgenciaPorNumero(7777));

            //Tenta remover
            AgenciaDAO.remove(agencia);

            //Tenta mostrar a agencia removida
            System.out.println(AgenciaDAO.obtemAgenciaPorNumero(7777));

        } catch (AgenciaExistenteException | AgenciaNaoEncontradaException | FalhaConexaoException |
                 BancoNaoEncontradoException | BancoExistenteException e) {

            System.err.println("Excecao: " + e.getMessage());
        }
    }


    //Testa a obtenção de uma agencia por numero.
    public static void testaObtemAgenciaPorNumero() {
        try {
            //Cria um banco
            Banco banco23 = new Banco("23.234.234/0001-234", "Banco1");

            // Cria um agencia e persiste
            Agencia agencia = new Agencia(4545, "Rua da Lua 555",
                                            "4545@gmail.com", banco23);

            BancoDAO.insere(banco23);
            AgenciaDAO.insere(agencia);

            // Tenta recuperar a agencia recém atualizado
            Agencia agenciaRecuperada = AgenciaDAO.obtemAgenciaPorNumero(4545);

            // Mostra as informações dos dois para serem comparadas
            System.out.println(agencia);
            System.out.println(agenciaRecuperada);

        } catch (AgenciaExistenteException | FalhaConexaoException | AgenciaNaoEncontradaException |
                 BancoNaoEncontradoException | BancoExistenteException e) {

            System.err.println("Excecao: " + e.getMessage());
        }
    }

    //Testa a obtenção da lista de agencias.
    public static void testaObtemListaAgencias() {
        try {
            //Cria bancos
            Banco banco45 = new Banco("45.456.456/0001-45", "Banco 2");
            Banco banco34 = new Banco ("34.345.345/0001/34", "Banco 3");

            // Cria agencias e persiste
            Agencia agencia1 = new Agencia(1111, "Av. Lobo 888",
                                            "1111@gmail.com", banco45);
            Agencia agencia2 = new Agencia(2222,"Rua da Silva 098",
                                            "2222@gmail.com", banco34);

            BancoDAO.insere(banco34);
            BancoDAO.insere(banco45);
            AgenciaDAO.insere(agencia1);
            AgenciaDAO.insere(agencia2);

            // Obtem a lista de agencias
            List<Agencia> listaAgencias = AgenciaDAO.obtemListaAgencias();

            // Exibe a lista de agencias
            System.out.println("Lista de Agencias: ");
            for(Agencia agencia : listaAgencias) {
                System.out.println(agencia);
            }

        } catch (AgenciaExistenteException | FalhaConexaoException |
                 BancoNaoEncontradoException | BancoExistenteException e) {

            System.err.println("Excecao: " + e.getMessage());
        }
    }


    //Testa a obtenção da lista de agencias de determinado banco.
    public static void testaObtemListaAgenciasDeUmBanco() {
        try {
            //Cria bancos
            Banco banco1 = new Banco("11.222.333/0001-44", "Banco Luz");
            Banco banco2 = new Banco("44.333.222/0001-11", "Banco Sombra");

            // Cria agencias e persiste
            Agencia agencia1 = new Agencia(9191, "Av. Cruz 1818",
                    "9191@gmail.com", banco1);
            Agencia agencia2 = new Agencia(2322,"Rua da Silva 098",
                    "2322@gmail.com", banco1);
            Agencia agencia3 = new Agencia(3838,"Rua da Vera 999",
                    "3838@gmail.com", banco2);

            BancoDAO.insere(banco1);
            BancoDAO.insere(banco2);

            AgenciaDAO.insere(agencia1);
            AgenciaDAO.insere(agencia2);
            AgenciaDAO.insere(agencia3);

            // Obtem a lista de agencias
            List<Agencia> listaAgencias = AgenciaDAO.obtemListaAgenciasDeUmBanco(banco1.getCnpj());

            // Exibe a lista de agencias
            System.out.println("Lista de Agencias do " + banco1.getNome() + ":");
            for(Agencia agencia : listaAgencias) {
                System.out.println(agencia);
            }
        } catch (AgenciaExistenteException | FalhaConexaoException |
                 BancoNaoEncontradoException | BancoExistenteException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }


    //Testa a exceção AgenciaNaoEncontradaException.
    public static void testaAgenciaNaoEncontradaException() {
        try {
            AgenciaDAO.obtemAgenciaPorNumero(0000);
        } catch (FalhaConexaoException | AgenciaNaoEncontradaException | BancoNaoEncontradoException e) {
            System.err.println("Excecao: " + e.getMessage());
        }
    }
}
