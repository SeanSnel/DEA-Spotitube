package nl.sean.dea.exceptionmapper;

import nl.sean.dea.dto.ErrorDTO;
import nl.sean.dea.persistence.SpotitubePersistenceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<SpotitubePersistenceException> {
    @Override
    public Response toResponse(SpotitubePersistenceException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorDTO(e.getMessage())).build();
    }
}
