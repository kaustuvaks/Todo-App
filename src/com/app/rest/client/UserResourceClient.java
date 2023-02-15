package com.app.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app.rest.model.UserModel;

public class UserResourceClient {
	
    public static void main(String[] args) {
        //getUsers();
        getUsers();
       // createUser();
       // updateUser();
       //deleteUser();
    }


    public static void getUsers() {
        Client client = ClientBuilder.newClient();
        
        System.out.println("ÏN GEt");
        String entity = client.target("http://localhost:9495/BlogApp/api/users")
        .request(MediaType.APPLICATION_JSON).header("method","GET").get(String.class);

        System.out.println(entity);
    }
//
//    private static void getUser() {
//        Client client = ClientBuilder.newClient();
//
//        String entity = client.target("http://localhost:9495/BlogApp/api").path("users").path("user/100")
//       .request(MediaType.APPLICATION_JSON).header("some-header", "true").get(String.class);
//
//        System.out.println(entity);
//    }
// 
//    private static void createUser() {
//        Client client = ClientBuilder.newClient();
//        WebTarget webTarget = client.target("http://localhost:8080/jersey-crud-example/api").path("users");
//
//        UserModel user = new UserModel();
//        user.setId(1);
//        user.setName("Ramesh");
//
//        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//        Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));
//
//        System.out.println(response.getStatus());
//        System.out.println(response.readEntity(String.class));
//    }
//
//    private static void updateUser() {
//        Client client = ClientBuilder.newClient();
//        WebTarget webTarget = client.target("http://localhost:8080/jersey-crud-example/api").path("users")
//        .path("user/1");
//
//        UserModel user = new UserModel();
//        user.setId(1);
//        user.setName("Ramesh");
//
//        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//        Response response = invocationBuilder.put(Entity.entity(user, MediaType.APPLICATION_JSON));
//
//        String userJson = response.readEntity(String.class);
//
//        System.out.println(response.getStatus());
//        System.out.println(userJson);
//    }
//
//    private static void deleteUser() {
//
//         Client client = ClientBuilder.newClient();
//         WebTarget webTarget = client.target("http://localhost:8080/jersey-crud-example/api").path("users")
//        .path("user/100");
//
//         UserModel user = new UserModel();
//         user.setId(1);
//         user.setName("Ramesh");
//
//         Invocation.Builder invocationBuilder = webTarget.request();
//         Response response = invocationBuilder.delete();
//
//         System.out.println(response.getStatus());
//         System.out.println(response.readEntity(String.class));
//    }
}
