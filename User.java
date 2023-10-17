package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Classe que representa o usuário da aplicação
  */
public class User {
  /* *
   * Método que estabelece conexão com o banco de dados
   * 
   * @return resultado da conexão
   * @throws Exception retorna uma mensagem de erro na conexão
   */
  public Connection conectarBD() {
    /* *
     * Variável que guarda o resultado da conexão, inicia como nula
     */
    Connection conn = null;
    try {
      Class.forName("com.mysql.Driver.Manager").newInstance(); // Responsável por estabelecer a conexão com o MySQL
      /* 
       * Informações necessárias para conectar como banco de dados contidas na String:
       * Endereço de IP;
       * Nome do database;
       * Nome de usuário;
       * Senha;
       */
      String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123"; 
      conn = DriverManager.getConnection(url); // Estabelece a conexão
    } catch (Exception e) { 
      // Caso haja um erro durante a conexão, ele é tratado aqui
    }
    return conn; // Retorna o resultado da conexão
  }
  /* *
   * Variável que gaurda o nome do usuário
   */
  public String nome="";
  /* *
   * Variável que guarda o estado da conexão, usada para saber se ela existe ou não
   */
  public boolean result = false;
  /* *
   * Verifica se o usuário existe no banco de dados
   * 
   * @param login O acesso usado no sistema
   * @param senha A senha usada no sistema
   * @return boolean identificando se o usuário existe ou não na aplicação
   * @throws Exception mensagem de erro durante a execução
   */
  public boolean verificarUsuario(String login, String senha) {
      String sql = "";
      Connection conn = conectarBD(); // Inicia a conexão com o banco de dados através do método conectarBD
    //INSTRUÇÃO SQL
    sql += "select nome from usuarios ";
    sql +="where login = " + "'" + login + "'";
    sql += " and senha = " + "'" + senha + "';";
    try {
      Statement st = conn.createStatement(); // Declara o objeto para envio da instrução SQL
      ResultSet rs = st.executeQuery(sql); // Envia a instrução SQL
      if(rs.next()) { // Caso haja objetos dentro do retorno do envio
        result = true;
        nome = rs.getString("nome"); // A variável nome recebe o atributo nome do resultado
      }
    } catch (Exception e) { 
      // Caso haja um erro durante a execução, ele é tratado aqui
    }
    return result; // Retorna o resultado da verificação
  }
}