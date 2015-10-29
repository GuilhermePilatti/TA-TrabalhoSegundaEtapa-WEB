/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.ClienteJuridico;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ws
 */
public class ClienteJuridicoDAO implements Serializable{
    @PersistenceContext(unitName = "TA-Trabalho-WebPU")
    private EntityManager em;
    private List<ClienteJuridico> listarTodos;

    public ClienteJuridicoDAO() {
    }
    
    public void persist(ClienteJuridico objeto) throws Exception {
        em.persist(objeto);
    }
    
    public void merge(ClienteJuridico objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(ClienteJuridico objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public ClienteJuridico getObjectById(Integer id) throws Exception {
        return em.find(ClienteJuridico.class, id);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<ClienteJuridico> getListarTodos() {
        return listarTodos;
    }

    public void setListarTodos(List<ClienteJuridico> listarTodos) {
        this.listarTodos = listarTodos;
    }
}
