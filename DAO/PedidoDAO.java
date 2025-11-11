/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.PedidoDTO;
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
public class PedidoDAO {
    Connection conn;
    PreparedStatement pstm;
    ArrayList<PedidoDTO> lista = new ArrayList<>();
    ResultSet rs;

    public void cadastrarPedido(PedidoDTO objPedidoDTO) {
        String sql = "insert into tb_pedido (valor_final,hora,quantidade,data) values(?,?,?,?)";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objPedidoDTO.getValorfinal());
            pstm.setString(2, objPedidoDTO.getHora());
            pstm.setString(3, objPedidoDTO.getQuantidade());          
            pstm.setString(4, objPedidoDTO.getData());

            
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "PedidoDAO cadastrarPedido" + erro);
        }
    }
     public void alterarPedido(PedidoDTO objPedidoDTO) {
        String sql = "update tb_pedido set valor_final=?,hora=?,quantidade=?,data=? where id_pedido=?";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objPedidoDTO.getValorfinal());
            pstm.setString(2, objPedidoDTO.getHora());
            pstm.setString(3, objPedidoDTO.getQuantidade());          
            pstm.setString(4, objPedidoDTO.getData());
            pstm.setInt(5, objPedidoDTO.getId());
            
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "PedidoDAO alterarPedido" + erro);
        }
    }
      public void excluirPedido(PedidoDTO objPedidoDTO) {
        String sql = "delete from tb_pedido where id_pedido=?";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
           
            pstm.setInt(1, objPedidoDTO.getId());
            
            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "PedidoDAO excluirPedido" + erro);
        }
    }
      public ArrayList<PedidoDTO> PesquisarPedido() throws SQLException {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select*from tb_pedido";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                PedidoDTO objPedidoDTO = new PedidoDTO();
                objPedidoDTO.setId(rs.getInt("id_Pedido"));
                objPedidoDTO.setValorfinal(rs.getString("valor_final"));
               
                objPedidoDTO.setHora(rs.getString("hora"));
             
                objPedidoDTO.setQuantidade(rs.getString("quantidade"));
                 objPedidoDTO.setData(rs.getString("data"));
               
                lista.add(objPedidoDTO);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "PedidoDAO PesquisarPedido" + e);
        }
        return lista;
    }
}
