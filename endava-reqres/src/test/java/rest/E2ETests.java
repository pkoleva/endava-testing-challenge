package rest;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.pkoleva.rest.Endpoints;
import org.pkoleva.rest.RequestHandler;
import org.pkoleva.rest.objects.User;
import org.pkoleva.rest.objects.Users;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;


@TestMethodOrder(MethodOrderer.MethodName.class)
@Tag("rest")
public class E2ETests extends BaseTest{

    @Test
    @Tag("happypath")
    public void test1000_getAllUsers(){
        // 1. List available users on page 1.
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("page", "1");
        Response response= request.sendGetRequest(Endpoints.users, queryParams, 200);
        Users users = dataHelper.getObject(response,".", Users.class);

        // Verify users are listed and the page.
        Assertions.assertEquals(queryParams.get("page"), users.getPage());
        Assertions.assertTrue(users.getData().length>0);

        // Extract single user details.
        User expectedUser = User.builder()
                .id(users.getData()[0].getId())
                .email(users.getData()[0].getEmail())
                .first_name(users.getData()[0].getFirst_name())
                .last_name(users.getData()[0].getLast_name())
                .build();

        // Extract all users, sort them by First Name alphabetically. Print sorted collection.
        User[] usersToSort = users.getData();
        Arrays.sort(usersToSort, Comparator.comparing(User::getFirst_name));
        for(User u: usersToSort){
            System.out.println("____________________");
            System.out.println(u.getId());
            System.out.println(u.getFirst_name());
            System.out.println(u.getLast_name());
            System.out.println(u.getEmail());
        }

                // 2. Get extracted user details
        response= request.sendGetRequest(Endpoints.userById(expectedUser.getId()),new HashMap<>(), 200);
        User actualUser = dataHelper.getObject(response, "data", User.class);

        // Verify user details.
        Assertions.assertEquals(expectedUser.getId(), actualUser.getId());
        Assertions.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        Assertions.assertEquals(expectedUser.getFirst_name(), actualUser.getFirst_name());
        Assertions.assertEquals(expectedUser.getLast_name(), actualUser.getLast_name());
    }

    @Test
    @Tag("printingCollection")
    public void test1010_sortResponseAndPrintToConsole(){
        // 1. List available users on page 1.
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("page", "1");
        Response response= request.sendGetRequest(Endpoints.users, queryParams, 200);
        Users users = dataHelper.getObject(response,".", Users.class);

        // Extract all users.
        queryParams.put("per_page", users.getTotal());
        response= request.sendGetRequest(Endpoints.users, queryParams, 200);
        User[] usersToSort = dataHelper.getObject(response,"data", User[].class);

        // Sort users by First Name alphabetically. Print sorted collection.
        Arrays.sort(usersToSort, Comparator.comparing(User::getFirst_name));
        for(User u: usersToSort){
            RequestHandler.logger.info("____________________");
            RequestHandler.logger.info(u.getId());
            RequestHandler.logger.info(u.getFirst_name());
            RequestHandler.logger.info(u.getLast_name());
            RequestHandler.logger.info(u.getEmail());
        }
    }

    @Test
    @Tag("happypath")
    public void test2000_createAndDeleteNewUser(){
        // Create UNIQUE new user.
        User expectedUser = User.builder().job("dummy").name(dataHelper.generateUniqueNumber()).build();
        Response response = request.sendPostRequest(Endpoints.users,expectedUser, 201);
        User actualUser = dataHelper.getObject(response, ".", User.class);
        Assertions.assertEquals(expectedUser.getJob(), actualUser.getJob());
        Assertions.assertEquals(expectedUser.getName(), actualUser.getName());

        // Delete newly created user.
        request.sendDeleteRequest(Endpoints.userById(actualUser.getId()), 204);
    }


    @Test
    @Tag("negative")
    public void test9000_getNonExistingUser(){
        // Try to get details of user that doesn't exist.
        Response response = request.sendGetRequest(
                Endpoints.userById("Nonexistent"), new HashMap<>(), 404);
        Assertions.assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
    }

//    @Test
//    @Tag("negative")
//    public void test9010_deleteNonExistingUser(){
//        // Try to delete a user that doesn't exist.
//        Response response = request.sendDeleteRequest(Endpoints.userById("Nonexistent"), 404);
//        Assertions.assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
//    }
}
