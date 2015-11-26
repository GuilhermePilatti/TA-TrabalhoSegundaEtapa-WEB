/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClienteFisicoDAO;
import br.edu.ifsul.dao.ClienteJuridicoDAO;
import br.edu.ifsul.dao.PedidoDAO;
import br.edu.ifsul.dao.VendaDAO;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.modelo.ClienteFisico;
import br.edu.ifsul.modelo.Pedido;
import br.edu.ifsul.modelo.Venda;
import br.edu.ifsul.utils.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ws
 */
@ManagedBean(name = "controleVenda")
@SessionScoped
public class ControleVenda implements Serializable{
    @EJB
    private VendaDAO dao;
    private Venda objeto;
    
    @EJB
    private PedidoDAO daoPedido;

    public ControleVenda() {
    }
    
    public void salvarCliente(){
        
    }
    
    public String listar() {
        return "/privado/venda/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Venda();
    }
    
    public void salvar() {
        try{
            objeto.setValorTotal(objeto.getPedido().getValorTotal());
            objeto.setPesoTotal(objeto.getPedido().getPesoTotal());
            objeto.setQuantidadeSacas(objeto.getPedido().getQuantidadeSacas());
            
            if(objeto.getCodigo()== null){
                dao.persist(objeto);
            } else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto "+e.getMessage());
        }
    }
    
    public void editar(Integer id) {
        try {
//            Util.mensagemErro(id.toString());
            objeto = dao.getObjectById(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto "+Util.getMessageErro(e));
        }
    }
    
    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto "+Util.getMessageErro(e));
        }
    }
    
    public VendaDAO getDao() {
        return dao;
    }

    public void setDao(VendaDAO dao) {
        this.dao = dao;
    }

    public Venda getObjeto() {
        return objeto;
    }

    public void setObjeto(Venda objeto) {
        this.objeto = objeto;
    }

    public PedidoDAO getDaoPedido() {
        return daoPedido;
    }

    public void setDaoPedido(PedidoDAO daoPedido) {
        this.daoPedido = daoPedido;
    }
}
