/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.CategoriaDTO;
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
public class CategoriaDAO {
    Connection conn;
    PreparedStatement pstm;
    ArrayList<CategoriaDTO> lista = new ArrayList<>();
    ResultSet rs;

    public void cadastrarcategoria(CategoriaDTO objCategoriaDTO) {
        String sql = "insert into tb_categoria (nome_categoria) values(?)";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objCategoriaDTO.getNomecategoria());
         
            
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CategoriaDAO cadastrarCategoria" + erro);
        }
    } 
    public void alterarCategoria(CategoriaDTO objCategoriaDTO) {
        String sql = "update tb_categoria set NOME_categoria=? where id_categoria=?";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objCategoriaDTO.getNomecategoria());
         pstm.setInt(2, objCategoriaDTO.getId());
            
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CategoriaDAO alterarCategoria" + erro);
        }
    }
     public void excluirCategoria(CategoriaDTO objCategoriaDTO) {
        String sql = "delete from tb_categoria where id_categoria=?";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
           
         pstm.setInt(1, objCategoriaDTO.getId());
            
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CategoriaDAO excluirCategoria" + erro);
        }
    }
     public ArrayList<CategoriaDTO> PesquisarCategoria() throws SQLException {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select*from tb_Categoria";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                CategoriaDTO objCategoriaDTO = new CategoriaDTO();
                objCategoriaDTO.setId(rs.getInt("id_categoria"));
                objCategoriaDTO.setNomecategoria(rs.getString("nome_categoria"));
               
                lista.add(objCategoriaDTO);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "CategoriaDAO PesquisarCategoria" + e);
        }
        return lista;
    }
}
