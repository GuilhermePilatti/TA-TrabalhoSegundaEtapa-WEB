/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.MateriaPrima;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ws
 */
@Stateless
public class MateriaPrimaDAO implements Serializable{
    @PersistenceContext(unitName = "TA-Trabalho-WebPU")
    private EntityManager em;
    private List<MateriaPrima> listarTodos;

    public MateriaPrimaDAO() {
    }
    
    public void persist(MateriaPrima objeto) throws Exception {
        em.persist(objeto);
    }
    
    public void merge(MateriaPrima objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(MateriaPrima objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public MateriaPrima getObjectById(Integer id) throws Exception {
        return em.find(MateriaPrima.class, id);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<MateriaPrima> getListarTodos() {
        return em.createQuery("FROM MateriaPrima ORDER BY nome").getResultList();
    }

    public void setListarTodos(List<MateriaPrima> listarTodos) {
        this.listarTodos = listarTodos;
    }
}
