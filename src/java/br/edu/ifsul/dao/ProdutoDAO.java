/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Produto;
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
public class ProdutoDAO implements Serializable{
    @PersistenceContext(unitName = "TA-Trabalho-WebPU")
    private EntityManager em;
    private List<Produto> listarTodos;

    public ProdutoDAO() {
    }
    
    public void persist(Produto objeto) throws Exception {
        em.persist(objeto);
    }
    
    public void merge(Produto objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(Produto objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Produto getObjectById(Integer id) throws Exception {
        Produto obj = em.find(Produto.class, id);
        obj.getItensProduto().size();
        return obj;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Produto> getListarTodos() {
        return em.createQuery("FROM Produto ORDER BY nome").getResultList();
    }

    public void setListarTodos(List<Produto> listarTodos) {
        this.listarTodos = listarTodos;
    }
}
