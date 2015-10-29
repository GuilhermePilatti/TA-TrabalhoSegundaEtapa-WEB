/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FornecedorDAO;
import br.edu.ifsul.modelo.Fornecedor;
import br.edu.ifsul.utils.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ws
 */
@ManagedBean(name = "controleFornecedor")
@SessionScoped
public class ControleFornecedor implements Serializable{
    @EJB
    private FornecedorDAO dao;
    private Fornecedor objeto;

    public ControleFornecedor() {
    }
    
    public String listar() {
        return "/privado/fornecedor/listar?faces-redirect=true";
    }
    
    public String novo() {
        objeto = new Fornecedor();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar() {
        try{
            if(objeto.getCodigo()== null){
                dao.persist(objeto);
            } else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            return "listar?faces-redirect=true";
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto "+e.getMessage());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar() {
        objeto = null;
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id) {
        try {
//            Util.mensagemErro(id.toString());
            objeto = dao.getObjectById(id);
            return "formulario?faces-redirect=true";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto "+Util.getMessageErro(e));
            return "listar?faces-redirect=true";
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

    public FornecedorDAO getDao() {
        return dao;
    }

    public void setDao(FornecedorDAO dao) {
        this.dao = dao;
    }

    public Fornecedor getObjeto() {
        return objeto;
    }

    public void setObjeto(Fornecedor objeto) {
        this.objeto = objeto;
    }
}
