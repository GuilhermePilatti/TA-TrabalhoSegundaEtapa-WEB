/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClienteFisicoDAO;
import br.edu.ifsul.dao.ClienteJuridicoDAO;
import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.dao.PedidoDAO;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.modelo.ClienteFisico;
import br.edu.ifsul.modelo.ItensPedido;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Pedido;
import br.edu.ifsul.utils.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ws
 */
@ManagedBean(name = "controlePedido")
@SessionScoped
public class ControlePedido implements Serializable{
    @EJB
    private PedidoDAO dao;
    private Pedido objeto;
    private ItensPedido item;
    private Boolean novoItem;
    
    @EJB
    private ProdutoDAO daoProduto;
    
    @EJB
    private ClienteFisicoDAO daoClienteFisico;
    
    @EJB
    private ClienteJuridicoDAO daoClienteJuridico;

    public ControlePedido() {
    }
    
    public void selecionarClienteFisico(){
        daoClienteFisico.getListarTodos();
    }
    
    public void selecionarClienteJuridico(){
        daoClienteJuridico.getListarTodos();
    }
    
    public void salvarCliente(){
        
    }
    
    public String listar() {
        return "/privado/pedido/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Pedido();
    }
    
    public void salvar() {
        try{
            Double pesoPedido = 0.0;
            Integer quantidadePedido = 0;
            Double valorPedido = 0.0;
            
            for (ItensPedido itens : objeto.getItens()) {
                pesoPedido += itens.getPeso();
                quantidadePedido += itens.getQuantidade();
                valorPedido += itens.getProduto().getValorUnitario() * itens.getQuantidade();
            }
            objeto.setPesoTotal(pesoPedido);
            objeto.setQuantidadeSacas(quantidadePedido);
            objeto.setValorTotal(valorPedido);
            
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
    
    public void novoItem(){
        item = new ItensPedido();
        novoItem = true;
    }
    
    public void alterarItem(int index){
        item = objeto.getItens().get(index);
        novoItem = false;
    }
    
    public void salvarItem(){
        if(novoItem){
            objeto.adicionarItem(item);
        }
        Double pesoItem = 1.0 * item.getQuantidade() * item.getProduto().getPesoSaca();
        item.setPeso(pesoItem);
        Util.mensagemInformacao("Item adicionado com sucesso");
    }
    
    public void removerItem(int index){
        objeto.removerItem(index);
        Util.mensagemInformacao("Item removido com sucesso");
    }

    public PedidoDAO getDao() {
        return dao;
    }

    public void setDao(PedidoDAO dao) {
        this.dao = dao;
    }

    public Pedido getObjeto() {
        return objeto;
    }

    public void setObjeto(Pedido objeto) {
        this.objeto = objeto;
    }

    public ItensPedido getItem() {
        return item;
    }

    public void setItem(ItensPedido item) {
        this.item = item;
    }

    public Boolean getNovoItem() {
        return novoItem;
    }

    public void setNovoItem(Boolean novoItem) {
        this.novoItem = novoItem;
    }

    public ProdutoDAO getDaoProduto() {
        return daoProduto;
    }

    public void setDaoProduto(ProdutoDAO daoProduto) {
        this.daoProduto = daoProduto;
    }

    public ClienteFisicoDAO getDaoClienteFisico() {
        return daoClienteFisico;
    }

    public void setDaoClienteFisico(ClienteFisicoDAO daoClienteFisico) {
        this.daoClienteFisico = daoClienteFisico;
    }

    public ClienteJuridicoDAO getDaoClienteJuridico() {
        return daoClienteJuridico;
    }

    public void setDaoClienteJuridico(ClienteJuridicoDAO daoClienteJuridico) {
        this.daoClienteJuridico = daoClienteJuridico;
    }
}
