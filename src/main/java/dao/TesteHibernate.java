/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author tiago
 */
public class TesteHibernate {
    public static void main(String[] args) {
        
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction trans  = em.getTransaction();
        trans.begin();
            
        UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate(em);
        Usuario user = new Usuario(1);
        user = usuarioDAO.selectPorId(user);
        System.out.println("Id: " + user.getId() + " Nome: " +user.getUsuario() + " Senha: " + user.getSenha());
        
        trans.commit();
        em.close();
        
    }
}
