/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FornecedorDAO;
import br.edu.ifsul.dao.MateriaPrimaDAO;
import br.edu.ifsul.modelo.MateriaPrima;
import br.edu.ifsul.utils.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ws
 */
@ManagedBean(name = "controleMateriaPrima")
@SessionScoped
public class ControleMateriaPrima implements Serializable{
    @EJB
    private MateriaPrimaDAO dao;
    private MateriaPrima objeto;
    
    @EJB
    private FornecedorDAO daoFornecedor;

    public ControleMateriaPrima() {
    }
    
    public String listar() {
        return "/privado/materiaprima/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new MateriaPrima();
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

    public MateriaPrimaDAO getDao() {
        return dao;
    }

    public void setDao(MateriaPrimaDAO dao) {
        this.dao = dao;
    }

    public MateriaPrima getObjeto() {
        return objeto;
    }

    public void setObjeto(MateriaPrima objeto) {
        this.objeto = objeto;
    }

    public FornecedorDAO getDaoFornecedor() {
        return daoFornecedor;
    }

    public void setDaoFornecedor(FornecedorDAO daoFornecedor) {
        this.daoFornecedor = daoFornecedor;
    }
}
