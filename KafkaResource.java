package com.resource;

import com.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("kafka")
public class KafkaResource {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaResource.class);

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("send")
    public Response sendMessage(@QueryParam("msg") String message) {
        LOGGER.info("Message to be sent to Kafka. message::" + message);
        boolean result = KafkaSender.send(message);
        return Response.ok().entity(result ? "message has been sent" : "message has been failed").build();
    }
}
