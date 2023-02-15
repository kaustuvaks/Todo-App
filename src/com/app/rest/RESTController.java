package com.app.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("/api")
public class RESTController extends ResourceConfig{
	public RESTController() {
        packages("com.app.rest.resources");
    }
}
