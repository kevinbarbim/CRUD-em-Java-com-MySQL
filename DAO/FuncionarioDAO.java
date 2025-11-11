/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.FuncionarioDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author LABINFO
 */
public class FuncionarioDAO {
      Connection conn;
    PreparedStatement pstm;
    ArrayList<FuncionarioDTO> lista = new ArrayList<>();
    ResultSet rs;

    public void cadastrarFuncionario(FuncionarioDTO objFuncionarioDTO) {
        String sql = "insert into tb_funcionario (nome,cpf,endereco,cidade) values(?,?,?,?)";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objFuncionarioDTO.getNome());
            pstm.setString(2, objFuncionarioDTO.getCpf());
            pstm.setString(3, objFuncionarioDTO.getEndereco());
            
            pstm.setString(4, objFuncionarioDTO.getCidade());

            
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO cadastrarFuncionario" + erro);
        }
    }
       public ArrayList<FuncionarioDTO> PesquisarFuncionario() throws SQLException {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select*from tb_funcionario";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                FuncionarioDTO objFuncionarioDTO = new FuncionarioDTO();
                objFuncionarioDTO.setId(rs.getInt("id_funcionario"));
                objFuncionarioDTO.setNome(rs.getString("nome"));
               
                objFuncionarioDTO.setEndereco(rs.getString("endereco"));
             
                objFuncionarioDTO.setCidade(rs.getString("cidade"));
                 objFuncionarioDTO.setCpf(rs.getString("cpf"));
               
                lista.add(objFuncionarioDTO);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO PesquisarFuncionario" + e);
        }
        return lista;
    }public void alterarFuncionario(FuncionarioDTO objFuncionarioDTO) {
        String sql = "update tb_funcionario set nome=?,endereco=?,cidade=?,cpf=? where id_Funcionario=?";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
           
             
            pstm.setString(1, objFuncionarioDTO.getNome());
          
            pstm.setString(2, objFuncionarioDTO.getEndereco());
            pstm.setString(3, objFuncionarioDTO.getCidade());
             pstm.setString(4, objFuncionarioDTO.getCpf());
 pstm.setInt(5, objFuncionarioDTO.getId());
            pstm.execute();
            pstm.close();
          } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO alterarFuncionario" + erro);
        }
    }
        public void excluirFuncionario(FuncionarioDTO objFuncionarioDTO) {
        String sql = "delete from tb_funcionario where id_Funcionario=?";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
        
             pstm.setInt(1, objFuncionarioDTO.getId());
            pstm.execute();
            pstm.close();
          } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO excluirFuncionario" + erro);
        }
}
}
