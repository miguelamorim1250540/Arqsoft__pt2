// package com.isep.lendings;


// import au.com.dius.pact.core.model.V4Pact;
// import au.com.dius.pact.core.model.PactSpecVersion;
// import au.com.dius.pact.core.model.PactSpecVersion;
// import au.com.dius.pact.core.model.V4Pact;
// import au.com.dius.pact.core.model.annotations.Pact;
// import io.restassured.RestAssured;
// import io.restassured.response.Response;
// import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// @PactTestFor(providerName = "car-service")
// class CarConsumerPactTest {

//     @Pact(consumer = "lending-service")
//     public V4Pact createPact(au.com.dius.pact.consumer.dsl.LambdaDslWithProvider builder) {
//         return builder
//                 .given("Car with id 1 exists")
//                 .uponReceiving("Request for car 1")
//                 .path("/cars/1")
//                 .method("GET")
//                 .willRespondWith()
//                 .status(200)
//                 .body(LambdaDsl.newJsonBody(o -> {
//                     o.numberType("id", 1);
//                     o.stringType("brand", "BMW");
//                     o.numberType("price", 25000);
//                 }).build())
//                 .toPact(PactSpecVersion.V4);
//     }

//     @Test
//     @PactTestFor(providerName = "car-service", port = "8089")
//     void testConsumer(MockServer mockServer) {
//         Response response = RestAssured.get(mockServer.getUrl() + "/cars/1");
//         assertEquals(200, response.getStatusCode());
//     }
// }
