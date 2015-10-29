/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Fornecedor;
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
public class FornecedorDAO implements Serializable{
    @PersistenceContext(unitName = "TA-Trabalho-WebPU")
    private EntityManager em;
    private List<Fornecedor> listarTodos;

    public FornecedorDAO() {
    }
    
    public void persist(Fornecedor objeto) throws Exception {
        em.persist(objeto);
    }
    
    public void merge(Fornecedor objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(Fornecedor objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Fornecedor getObjectById(Integer id) throws Exception {
        return em.find(Fornecedor.class, id);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Fornecedor> getListarTodos() {
        return em.createQuery("FROM Fornecedor ORDER BY nome").getResultList();
    }

    public void setListarTodos(List<Fornecedor> listarTodos) {
        this.listarTodos = listarTodos;
    }
}
