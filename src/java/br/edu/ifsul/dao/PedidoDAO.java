/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pedido;
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
public class PedidoDAO implements Serializable{
    @PersistenceContext(unitName = "TA-Trabalho-WebPU")
    private EntityManager em;
    private List<Pedido> listarTodos;

    public PedidoDAO() {
    }
    
    public void persist(Pedido objeto) throws Exception {
        em.persist(objeto);
    }
    
    public void merge(Pedido objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(Pedido objeto) throws Exception {
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public Pedido getObjectById(Integer id) throws Exception {
        Pedido obj = em.find(Pedido.class, id);
        obj.getItens().size();
        return obj;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Pedido> getListarTodos() {
        return em.createQuery("FROM Pedido ORDER BY dataPedido").getResultList();
    }

    public void setListarTodos(List<Pedido> listarTodos) {
        this.listarTodos = listarTodos;
    }
}
