package org.javaclub;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class TodoResource {

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Todo postTodo(String title) {
        Todo todo = new Todo();
        todo.title = title;
        todo.persist();
        return todo;
    }

    @GET
    public List<Todo> getAll(@QueryParam("query") String title) {
        if (null == title || title.isBlank()) {
            return Todo.findAll().list();
        }
        return Todo.find("title", title).list();
    }

    @PATCH
    public Todo patchTodo(Todo todo) {
        Todo byId = Todo.findById(todo.id);
        byId.title = todo.title;
        byId.isDone = todo.isDone;
        byId.persist();
        return byId;
    }

    @DELETE
    @Path("{id}")
    public Todo deleteTodo(@PathParam("id") Long id) {
        Todo todo = Todo.findById(id);
        todo.delete();
        return todo;
    }
}
