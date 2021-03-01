package br.edu.ifsul.servicos;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorgebavaresco@ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateless
@Path("pessoa")
public class ServicoPessoa implements Serializable {

    @EJB
    private PessoaDAO dao;

    public ServicoPessoa() {
    }

    @GET
    @Produces("application/json; charset=ISO-8859-1")
    @Path("lista")
    public Response listaPessoas() {
        try {
            List<Pessoa> lista = dao.getLista();
            return Response.ok().entity(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces("application/json; charset=ISO-8859-1")
    public Response findById(@PathParam("id") Integer id) {
        try {
            return Response.ok().entity(dao.findById(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces("application/json; charset=ISO-8859-1")
    @Consumes("application/json; charset=ISO-8859-1")
    public Response adicionar(Pessoa objeto) {
        try {
            return Response.ok().entity(dao.persist(objeto)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Produces("application/json; charset=ISO-8859-1")
    @Consumes("application/json; charset=ISO-8859-1")
    public Response alterar(Pessoa objeto) {
        try {
            return Response.ok().entity(dao.merge(objeto)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/remove/{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            dao.remove(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Erro ao remover: " + 
                    e.getMessage()).build();
        }
    }

    public PessoaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaDAO dao) {
        this.dao = dao;
    }

}