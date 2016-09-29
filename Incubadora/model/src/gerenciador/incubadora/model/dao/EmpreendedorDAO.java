/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.Empreendedor;
import gerenciador.incubadora.model.entity.Endereco;
import gerenciador.incubadora.model.entity.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class EmpreendedorDAO implements BaseDAO<Empreendedor> {

   public static final String CRITERION_NOME_ILIKE = "0";
   public static final String CRITERION_EMAIL_ILIKE = "1";

   @Override
   public void create(Empreendedor e, Connection conn) throws Exception {
      String sqlUsuario = "INSERT INTO usuario(nome,email, senha,telefone,tipo_usuario, sobrenome) "
              + "values(?,?,?,?,?,?) RETURNING id";
      PreparedStatement psUsuario = conn.prepareStatement(sqlUsuario);
      int i = 0;
      psUsuario.setString(++i, e.getNome());
      psUsuario.setString(++i, e.getEmail());
      psUsuario.setString(++i, e.getSenha());
      psUsuario.setString(++i, e.getTelefone());
      psUsuario.setString(++i, Usuario.TIPO_USUARIO_EMPREENDEDOR);
      psUsuario.setString(++i, e.getSobrenome());
      ResultSet rsUsuario = psUsuario.executeQuery();
      if (rsUsuario.next()) {
         e.setId(rsUsuario.getLong("id"));
      }
      rsUsuario.close();
      psUsuario.close();

      String sqlEmpreendedor = "INSERT INTO empreendedor(usuario_fk, cpf, rg, data_nascimento, escolaridade, formacao_profissional, ocupacao, fax) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
      PreparedStatement psEmpreendedor = conn.prepareStatement(sqlEmpreendedor);
      i = 0;
      psEmpreendedor.setLong(++i, e.getId());
      psEmpreendedor.setString(++i, e.getCpf());
      psEmpreendedor.setString(++i, e.getRg());
      psEmpreendedor.setDate(++i, new Date(e.getDataNascimento().getTime()));
      psEmpreendedor.setString(++i, e.getEscolaridade());
      psEmpreendedor.setString(++i, e.getFormacaoProfissional());
      psEmpreendedor.setString(++i, e.getOcupacao());
      psEmpreendedor.setString(++i, e.getFax());
      psEmpreendedor.execute();
      psEmpreendedor.close();

      String sqlEnderecoEmpreendedor = "INSERT INTO endereco_empreendedor(empreendedor_fk, rua, bairro, numero, cidade, uf) VALUES (?, ?, ?, ?, ?, ?);";
      PreparedStatement psEnderecoEmpreendedor = conn.prepareStatement(sqlEnderecoEmpreendedor);
      i = 0;
      psEnderecoEmpreendedor.setLong(++i, e.getId());
      psEnderecoEmpreendedor.setString(++i, e.getEndereco().getRua());
      psEnderecoEmpreendedor.setString(++i, e.getEndereco().getBairro());
      psEnderecoEmpreendedor.setString(++i, e.getEndereco().getNumero());
      psEnderecoEmpreendedor.setString(++i, e.getEndereco().getCidade());
      psEnderecoEmpreendedor.setString(++i, e.getEndereco().getUf());

      psEnderecoEmpreendedor.execute();
      psEnderecoEmpreendedor.close();
   }

   @Override
   public Empreendedor readById(Long id, Connection conn) throws Exception {
      String sql = "SELECT e.*, u.*, ee.* from empreendedor e left join usuario u on u.id=e.usuario_fk left join endereco_empreendedor ee on e.usuario_fk=ee.empreendedor_fk where u.id=?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();

      Empreendedor empreendedor = null;
      if (rs.next()) {
         empreendedor = new Empreendedor();
         empreendedor.setId(rs.getLong("usuario_fk"));
         empreendedor.setNome(rs.getString("nome"));
         empreendedor.setEmail(rs.getString("email"));
         empreendedor.setTelefone(rs.getString("telefone"));
         empreendedor.setTipoUsuario(rs.getString("tipo_usuario"));
         empreendedor.setSobrenome(rs.getString("sobrenome"));
         empreendedor.setCpf(rs.getString("cpf"));

         empreendedor.setRg(rs.getString("rg"));
         //empreendedor.setDataNascimento(new java.util.Date(rs.getDate("data_nascimento").getTime()));
         empreendedor.setDataNascimento(rs.getDate("data_nascimento"));
         empreendedor.setEscolaridade(rs.getString("escolaridade"));
         empreendedor.setFormacaoProfissional(rs.getString("formacao_profissional"));
         empreendedor.setOcupacao(rs.getString("ocupacao"));
         empreendedor.setFax(rs.getString("fax"));
         empreendedor.setSenha(rs.getString("senha"));

         Endereco endereco = new Endereco();
         endereco.setRua(rs.getString("rua"));
         endereco.setBairro(rs.getString("bairro"));
         endereco.setNumero(rs.getString("numero"));
         endereco.setCidade(rs.getString("cidade"));
         endereco.setUf(rs.getString("uf"));

         empreendedor.setEndereco(endereco);
      }
      rs.close();
      ps.close();
      return empreendedor;
   }

   @Override
   public List<Empreendedor> readByCriteria(Map<String, Object> criteria, Connection conn) throws Exception {
      String sql = "SELECT u.*,e.*, ee.* FROM empreendedor e inner join usuario u on u.id=e.usuario_fk join endereco_empreendedor ee on ee.empreendedor_fk=e.usuario_fk";
      List<Empreendedor> empreendedorList = new ArrayList<>();
      //TODO criterios

      if (criteria != null) {
         String criterionNomeIlike = (String) criteria.get(CRITERION_NOME_ILIKE);
         if (criterionNomeIlike != null && !criterionNomeIlike.isEmpty()) {
            sql += " and u.nome ilike '" + criterionNomeIlike + "'";
         }

         String criteironEmailIlike = (String) criteria.get(CRITERION_EMAIL_ILIKE);
         if (criteironEmailIlike != null && !criteironEmailIlike.isEmpty()) {
            sql += " and u.email ilike '" + criteironEmailIlike + "'";
         }
      }

      PreparedStatement ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
         Empreendedor empreendedor = new Empreendedor();
         empreendedor.setId(rs.getLong("usuario_fk"));
         empreendedor.setNome(rs.getString("nome"));
         empreendedor.setSobrenome(rs.getString("sobrenome"));
         empreendedor.setEmail(rs.getString("email"));
         empreendedor.setSenha(rs.getString("senha"));
         empreendedor.setTelefone(rs.getString("telefone"));
         empreendedor.setTipoUsuario(rs.getString("tipo_usuario"));
         empreendedor.setCpf(rs.getString("cpf"));
         empreendedor.setRg(rs.getString("rg"));
         empreendedor.setFormacaoProfissional(rs.getString("formacao_profissional"));
         empreendedor.setOcupacao(rs.getString("ocupacao"));
         empreendedor.setEscolaridade(rs.getString("escolaridade"));
         empreendedor.setDataNascimento(rs.getDate("data_nascimento"));
         
         Endereco endereco = new Endereco();
         endereco.setRua(rs.getString("rua"));
         endereco.setBairro(rs.getString("bairro"));
         endereco.setNumero(rs.getString("numero"));
         endereco.setCidade(rs.getString("cidade"));
         endereco.setUf(rs.getString("uf"));
         empreendedor.setEndereco(endereco);
         
         empreendedorList.add(empreendedor);
      }
      rs.close();
      ps.close();
      return empreendedorList;
   }

   @Override
   public void update(Empreendedor e, Connection conn) throws Exception {
      String sqlUsuario = "UPDATE usuario SET nome=?,email=?, senha=?, telefone=? ,tipo_usuario=? , sobrenome=? WHERE id=?;";
      PreparedStatement psUsuario = conn.prepareStatement(sqlUsuario);
      int i = 0;
      psUsuario.setString(++i, e.getNome());
      psUsuario.setString(++i, e.getEmail());
      psUsuario.setString(++i, e.getSenha());
      psUsuario.setString(++i, e.getTelefone());
      psUsuario.setString(++i, Usuario.TIPO_USUARIO_EMPREENDEDOR);
      psUsuario.setString(++i, e.getSobrenome());
      psUsuario.setLong(++i, e.getId());
      psUsuario.execute();

      String sqlEmpreendedor = "UPDATE empreendedor SET cpf=?, rg=?, data_nascimento=?, escolaridade=?, formacao_profissional=?, ocupacao=?, fax=? WHERE usuario_fk=?;";
      PreparedStatement psEmpreendedor = conn.prepareStatement(sqlEmpreendedor);
      i = 0;

      psEmpreendedor.setString(++i, e.getCpf());
      psEmpreendedor.setString(++i, e.getRg());
      psEmpreendedor.setDate(++i, new Date(e.getDataNascimento().getTime()));
      psEmpreendedor.setString(++i, e.getEscolaridade());
      psEmpreendedor.setString(++i, e.getFormacaoProfissional());
      psEmpreendedor.setString(++i, e.getOcupacao());
      psEmpreendedor.setString(++i, e.getFax());
      psEmpreendedor.setLong(++i, e.getId());
      psEmpreendedor.execute();
      psEmpreendedor.close();

      String sqlEnderecoEmpreendedor = "UPDATE endereco_empreendedor SET rua=?, bairro=?, numero=?, cidade=?, uf=? WHERE empreendedor_fk=?;";
      PreparedStatement psEnderecoEmpreendedor = conn.prepareStatement(sqlEnderecoEmpreendedor);
      i = 0;

      psEnderecoEmpreendedor.setString(++i, e.getEndereco().getRua());
      psEnderecoEmpreendedor.setString(++i, e.getEndereco().getBairro());
      psEnderecoEmpreendedor.setString(++i, e.getEndereco().getNumero());
      psEnderecoEmpreendedor.setString(++i, e.getEndereco().getCidade());
      psEnderecoEmpreendedor.setString(++i, e.getEndereco().getUf());
      psEnderecoEmpreendedor.setLong(++i, e.getId());

      psEnderecoEmpreendedor.execute();
      psEnderecoEmpreendedor.close();
   }

   @Override
   public void delete(Long id, Connection conn) throws Exception {
      String sql = "DELETE FROM usuario WHERE id=?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setLong(1, id);
      ps.execute();
      ps.close();
   }

}
