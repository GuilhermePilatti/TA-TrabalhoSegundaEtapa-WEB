/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClienteFisicoDAO;
import br.edu.ifsul.modelo.ClienteFisico;
import br.edu.ifsul.utils.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ws
 */
@ManagedBean(name = "controleClienteFisico")
@SessionScoped
public class ControleClienteFisico implements Serializable{
    @EJB
    private ClienteFisicoDAO dao;
    private ClienteFisico objeto;

    public ControleClienteFisico() {
    }
    
    public String listar() {
        return "/privado/clientefisico/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new ClienteFisico();
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

    public ClienteFisicoDAO getDao() {
        return dao;
    }

    public void setDao(ClienteFisicoDAO dao) {
        this.dao = dao;
    }

    public ClienteFisico getObjeto() {
        return objeto;
    }

    public void setObjeto(ClienteFisico objeto) {
        this.objeto = objeto;
    }
}
