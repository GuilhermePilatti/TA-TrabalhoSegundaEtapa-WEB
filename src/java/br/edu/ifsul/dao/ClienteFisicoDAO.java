/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.ClienteFisico;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ws
 */
public class ClienteFisicoDAO implements Serializable{
    @PersistenceContext(unitName = "TA-Trabalho-WebPU")
    private EntityManager em;
    private List<ClienteFisico> listarTodos;

    public ClienteFisicoDAO() {
    }
    
    public void persist(ClienteFisico objeto) throws Exception {
        em.persist(objeto);
    }
    
    public void merge(ClienteFisico objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(ClienteFisico objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public ClienteFisico getObjectById(Integer id) throws Exception {
        return em.find(ClienteFisico.class, id);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<ClienteFisico> getListarTodos() {
        return listarTodos;
    }

    public void setListarTodos(List<ClienteFisico> listarTodos) {
        this.listarTodos = listarTodos;
    }
}
