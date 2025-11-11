/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ClienteDTO;
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
public class ClienteDAO {
     Connection conn;
    PreparedStatement pstm;
    ArrayList<ClienteDTO> lista = new ArrayList<>();
    ResultSet rs;

    public void cadastrarCliente(ClienteDTO objClienteDTO) {
        String sql = "insert into tb_cliente (nome,cpf,endereco,cidade) values(?,?,?,?)";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objClienteDTO.getNome());
           pstm.setString(2, objClienteDTO.getCpf());
            pstm.setString(3, objClienteDTO.getEndereco());
            pstm.setString(4, objClienteDTO.getCidade());
 

            
            pstm.execute();
            pstm.close();
          } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ClienteDAO cadastrarCliente" + erro);
        }
    }
      public ArrayList<ClienteDTO> PesquisarCliente() throws SQLException {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select*from tb_cliente";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ClienteDTO objClienteDTO = new ClienteDTO();
                objClienteDTO.setId(rs.getInt("id_cliente"));
                objClienteDTO.setNome(rs.getString("nome"));
               
                objClienteDTO.setEndereco(rs.getString("endereco"));
             
                objClienteDTO.setCidade(rs.getString("cidade"));
                 objClienteDTO.setCpf(rs.getString("cpf"));
               
                lista.add(objClienteDTO);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ClienteDAO PesquisarCliente" + e);
        }
        return lista;
    }
       public void alterarCliente(ClienteDTO objClienteDTO) {
        String sql = "update tb_cliente set nome=?,endereco=?,cidade=?,cpf=? where id_cliente=?";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
           
             
            pstm.setString(1, objClienteDTO.getNome());
          
            pstm.setString(2, objClienteDTO.getEndereco());
            pstm.setString(3, objClienteDTO.getCidade());
             pstm.setString(4, objClienteDTO.getCpf());
 pstm.setInt(5, objClienteDTO.getId());
            pstm.execute();
            pstm.close();
          } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ClienteDAO alterarCliente" + erro);
        }
    }
        public void excluirCliente(ClienteDTO objClienteDTO) {
        String sql = "delete from tb_cliente where id_cliente=?";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
        
             pstm.setInt(1, objClienteDTO.getId());
            pstm.execute();
            pstm.close();
          } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ClienteDAO excluirCliente" + erro);
        }
    }
}
