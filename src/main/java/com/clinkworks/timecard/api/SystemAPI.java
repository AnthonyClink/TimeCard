package com.clinkworks.timecard.api;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.clinkworks.timecard.component.SystemTimeComponent;
import com.google.inject.Inject;

/**
 * This provides system values to whoever needs em.
 * @author AnthonyJCLink
 *
 */
@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Path("timecard/system")
public final class SystemAPI {
	
	private final SystemTimeComponent systemTimeComponent;
	
	@Inject
	public SystemAPI(SystemTimeComponent systemTimeComponent){
		this.systemTimeComponent = systemTimeComponent;
	}
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response getCurrentSystemTime(){
		return build200ServiceResponse(systemTimeComponent.getSystemTime());
	}
	
	private Response build200ServiceResponse(Object payload){
		return Response.status(200).entity(payload).build();
	}
}
