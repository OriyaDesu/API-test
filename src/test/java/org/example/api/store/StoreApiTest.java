package org.example.api.store;

import io.restassured.http.ContentType;
import org.example.model.Specifications;
import org.example.model.Store;
import org.example.model.StoreResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StoreApiTest {

    @Test
    public void placeOrderTest() {
        Specifications.installSpecification(Specifications.requestSpec(),Specifications.responseSpecOK2());
        // todo: офрмить заказ на питомца
        Integer id = 55;
        Store store = new Store(id,2,1, "2021-11-18T13:55:23.128Z", "placed", true);
        given()
                .when()
                .body(store)
                .post("order")
                .then()
                .log().all();

        // todo: найти оформленный заказ
        Store order = given()
                .when()
                .get("order/"+id)
                .then().log()
                .body().extract().as(Store.class);
        Assert.assertEquals(id,  order.getId());
    }

    @Test
    public void deleteOrderTest() {
        int id = 55;
        given()
               .when()
               .delete("https://petstore.swagger.io/v2/store/order/"+id)
               .then().log().all();

        // todo: проверить удаление заказа
        Specifications.installSpecification(Specifications.requestSpec(),Specifications.responseSpecError4());
        StoreResponse response = given()
                .when()
                .get("order/"+id)
                .then().log()
                .body().extract().as(StoreResponse.class);
        Assert.assertEquals(response.getMessage(),"Order not found");
    }
}
