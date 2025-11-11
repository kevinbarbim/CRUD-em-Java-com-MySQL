/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.ProdutoDTO;
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
public class ProdutoDAO {
     Connection conn;
    PreparedStatement pstm;
    ArrayList<ProdutoDTO> lista = new ArrayList<>();
    ResultSet rs;

    public void cadastrarProduto(ProdutoDTO objProdutoDTO) {
        String sql = "insert into tb_produto (nome_produto,preco_unitario) values(?,?)";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objProdutoDTO.getNome());
            pstm.setString(2, objProdutoDTO.getPrecounitario());
       

            
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ProdutoDAO cadastrarProduto" + erro);
        }
    }
    public void alterarProduto(ProdutoDTO objProdutoDTO) {
        String sql = "update tb_produto set nome_produto=?,preco_unitario=? where id_produto=?";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objProdutoDTO.getNome());
            pstm.setString(2, objProdutoDTO.getPrecounitario());
        pstm.setInt(3, objProdutoDTO.getId());

            
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ProdutoDAO alterarProduto" + erro);
        }
    }
    public void excluirProduto(ProdutoDTO objProdutoDTO) {
        String sql = "delete from tb_produto where id_produto=?";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objProdutoDTO.getId());
          
       

            
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ProdutoDAO excluirProduto" + erro);
        }
    }
    public ArrayList<ProdutoDTO> PesquisarProduto() throws SQLException {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select*from tb_produto";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ProdutoDTO objProdutoDTO = new ProdutoDTO();
                objProdutoDTO.setId(rs.getInt("id_Produto"));
                objProdutoDTO.setNome(rs.getString("nome_produto"));
               
                objProdutoDTO.setPrecounitario(rs.getString("preco_unitario"));
             
            
               
                lista.add(objProdutoDTO);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ProdutoDAO PesquisarProduto" + e);
        }
        return lista;
    }
}
