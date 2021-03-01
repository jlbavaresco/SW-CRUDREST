package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorgebavaresco@ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateless
public class PessoaDAO implements Serializable {

    @PersistenceContext(unitName = "SW-CRUDREST-PU")
    private EntityManager em;

    private List<Pessoa> lista;

    public PessoaDAO() {
    }
    
    public Pessoa persist(Pessoa objeto) throws Exception {
        objeto.setId(null);
        em.persist(objeto);
        return objeto;
    }
    
    public Pessoa merge(Pessoa objeto) throws Exception {
        em.merge(objeto);
        return objeto;
    }
    
    public void remove(Object id) throws Exception {
        Pessoa obj = em.find(Pessoa.class, id);
        em.remove(obj);
    }
    
    public Pessoa findById(Object id) throws Exception {
        return (Pessoa) em.find(Pessoa.class, id);
    }
    

    public List<Pessoa> getLista() throws Exception {
        return em.createQuery("from Pessoa order by id").getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void setLista(List<Pessoa> lista) {
        this.lista = lista;
    }
}
