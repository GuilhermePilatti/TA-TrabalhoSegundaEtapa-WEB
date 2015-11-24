/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MateriaPrimaDAO;
import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.modelo.ItensProduto;
import br.edu.ifsul.modelo.MateriaPrima;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.utils.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ws
 */
@ManagedBean(name = "controleProduto")
@SessionScoped
public class ControleProduto implements Serializable{
    @EJB
    private ProdutoDAO dao;
    private Produto objeto;
    private ItensProduto item;
    private Boolean novoItem;
    
    @EJB
    private MateriaPrimaDAO daoMateriaPrima;

    public ControleProduto() {
    }
    
    public String listar() {
        return "/privado/produto/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Produto();
    }
    
    public void salvar() {
        try{
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
        item = new ItensProduto();
        novoItem = true;
    }
    
    public void alterarItem(int index){
        item = objeto.getItensProduto().get(index);
        novoItem = false;
    }
    
    public void salvarItem(){
        if(novoItem){
            objeto.adicionarItem(item);
        }
        Util.mensagemInformacao("Item adicionado com sucesso");
    }
    
    public void removerItem(int index){
        objeto.removeritem(index);
        Util.mensagemInformacao("Item removido com sucesso");
    }

    public ProdutoDAO getDao() {
        return dao;
    }

    public void setDao(ProdutoDAO dao) {
        this.dao = dao;
    }

    public Produto getObjeto() {
        return objeto;
    }

    public void setObjeto(Produto objeto) {
        this.objeto = objeto;
    }

    public ItensProduto getItem() {
        return item;
    }

    public void setItem(ItensProduto item) {
        this.item = item;
    }

    public Boolean getNovoItem() {
        return novoItem;
    }

    public void setNovoItem(Boolean novoItem) {
        this.novoItem = novoItem;
    }

    public MateriaPrimaDAO getDaoMateriaPrima() {
        return daoMateriaPrima;
    }

    public void setDaoMateriaPrima(MateriaPrimaDAO daoMateriaPrima) {
        this.daoMateriaPrima = daoMateriaPrima;
    }
}
