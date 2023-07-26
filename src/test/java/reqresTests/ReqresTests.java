package reqresTests;

import common.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reqresPojos.*;

import java.util.List;
import java.util.stream.Collectors;

import static common.Specifications.installSpecification;
import static io.restassured.RestAssured.given;

public class ReqresTests {


    @Test
    public void avatarContainId() {

        installSpecification(Specifications.requestSpecification(), Specifications.responseSpecification(200));
        List<UserRes> list = given()
                .when()
                .get("api/users?page=2")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", UserRes.class);

        List<String> idList = list.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        List<String> avatarList = list.stream().map(UserRes::getAvatar).toList();

        Assertions.assertNotNull(idList);

        for (int i = 0; i < idList.size(); i++) {
            Assertions.assertTrue(avatarList.get(i).contains(idList.get(i)));
        }

    }

    @Test
    public void checkUserMail() {
        installSpecification(Specifications.requestSpecification(), Specifications.responseSpecification(200));
        UserRes user = given()
                .when()
                .get("api/users/2")
                .then()
                .log().all()
                .extract().jsonPath().getObject("data", UserRes.class);


        Assertions.assertTrue(user.getEmail().endsWith("@reqres.in"));

    }

    @Test

    public void userNotFound404() {
        installSpecification(Specifications.requestSpecification(), Specifications.responseSpecification(404));
        given()
                .when()
                .get("api/users/23")
                .then()
                .log().all();
    }

    @Test
    public void usersSortedByYears() {
        installSpecification(Specifications.requestSpecification(), Specifications.responseSpecification(200));

        List<InfoUserRes> list = given()
                .when()
                .get("api/unknown")
                .then()
                .log().all().extract().jsonPath().getList("data", InfoUserRes.class);

        Assertions.assertNotNull(list);

        List<Integer> sorted = list.stream().map(InfoUserRes::getYear).sorted().toList();
        List<Integer> notSorted = list.stream().map(InfoUserRes::getYear).toList();

        for (int i = 0; i < sorted.size(); i++) {
            Assertions.assertEquals(sorted.get(i), notSorted.get(i));
        }
    }

    @Test
    public void createNewUser() {
        installSpecification(Specifications.requestSpecification(), Specifications.responseSpecification(201));
        CreateUserReq newUser = new CreateUserReq("Ivan", "Bodrov");

        CreateUserRes userResp = given()
                .when()
                .body(newUser)
                .post("api/users")
                .then().log().all()
                .extract()
                .as(CreateUserRes.class);

        Assertions.assertNotNull(userResp.getId());

    }

    @Test
    public void deleteUser() {
        installSpecification(Specifications.requestSpecification(), Specifications.responseSpecification(204));

        given()
                .delete("api/users/2")
                .then().log().all();

    }

    @Test
    public void userRegister() {
        installSpecification(Specifications.requestSpecification(), Specifications.responseSpecification(200));

        RegisterUserReq user = new RegisterUserReq("eve.holt@reqres.in", "pistol");

        RegisterUserRes resp = given()
                .body(user)
                .post("api/register")
                .then()
                .log().all()
                .extract().as(RegisterUserRes.class);
        Assertions.assertEquals(4, (int) resp.getId());
        Assertions.assertEquals("QpwL5tke4Pnpja7X4", resp.getToken());
    }


}
