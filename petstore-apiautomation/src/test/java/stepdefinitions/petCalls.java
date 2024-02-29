package stepdefinitions;

import Payloads.Category;
import Payloads.Pet;
import Payloads.Tag;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Collections;

import static endpoints.Routes.pet_url;
import static io.restassured.RestAssured.given;

public class petCalls {

    private Response response;
    Pet petPayload;
    Category category;
    Tag tag;

    @Given("^I want to create a pet with the following data: (\\d+), \"([^\"]*)\", (\\d+), \"([^\"]*)\", \"([^\"]*)\", (\\d+), \"([^\"]*)\", \"([^\"]*)\"$")
    public void iWantToCreateAPetWithTheFollowingDataIdCategory_idTag_id (int id, String name, int category_id, String category_name, String photoUrl, int tag_id, String tag_name,String status) {

        setupPetData(id, name, category_id, category_name, photoUrl, tag_id, tag_name, status);
        response = createPet(petPayload);

    }

    @Given("^I want to update a pet with the following data: (\\d+), \"([^\"]*)\", (\\d+), \"([^\"]*)\", \"([^\"]*)\", (\\d+), \"([^\"]*)\", \"([^\"]*)\"$")
    public void iWantToUpdateAPetWithTheFollowingDataIdCategory_idTag_id (int id, String name, int category_id, String category_name, String photoUrl, int tag_id, String tag_name,String status) {

        setupPetData(id, name, category_id, category_name, photoUrl, tag_id, tag_name, status);
        response = updatePet(petPayload);

    }

    @Then("Response should be {string}")
    public void response_should_be(String statusCode) {

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println("Status Code: " + statusCode);

     }

    @Given("I want to search a pet by (\\d+)$")
    public void iWantToSearchAPetById (int id) {

        response = findPetById(id);
    }

    @And("The pet name should be {string}")
    public void thePetNameShouldBe (String name) {

        response.then().log().all();
        String petName = response.jsonPath().getString("name");
        Assert.assertEquals(petName, name);
    }

    @Given("I want to delete a pet by (\\d+)$")
    public void iWantToDeleteAPetById (int id) {

        response = deletePetById(id);
    }

     public static Response createPet(Pet payload) {
         return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(pet_url);
     }

    public static Response findPetById(int petId) {
        return given()
                .pathParam("petId",petId)
                .when()
                .get(pet_url+"/{petId}");
    }

    public static Response updatePet(Pet payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(pet_url);
    }

    public static Response deletePetById(int petId) {
        return given()
                .pathParam("petId",petId)
                .when()
                .delete(pet_url+"/{petId}");
    }

    public void setupPetData(int id, String name, int category_id, String category_name, String photoUrl, int tag_id, String tag_name,String status){

        petPayload = new Pet();
        category = new Category();
        tag = new Tag();

        petPayload.setId(id);
        petPayload.setName(name);
        category.setId(category_id);
        category.setName(category_name);
        petPayload.setCategory(category);
        petPayload.setPhotoUrls(Collections.singletonList(photoUrl));
        tag.setId(tag_id);
        tag.setName(tag_name);
        petPayload.setTags(Collections.singletonList(tag));
        petPayload.setStatus(status);
    }



}
