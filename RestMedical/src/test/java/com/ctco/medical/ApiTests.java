package com.ctco.medical;

import com.ctco.medical.bracelet.Bracelet;
import com.ctco.medical.bracelet.BraceletPage;
import com.ctco.medical.card.MedicalCard;
import com.ctco.medical.card.MedicalCardPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class ApiTests extends BaseTests {

    private static Stream<Arguments> sourceData() {
        return Stream.of(
                Arguments.of(Arrays.asList(0,2))
        );
    }

    @ParameterizedTest
    @MethodSource("sourceData")
    public void getMedCard(List<Integer> braceletIndex) {
        String braceletRequest = getJsonFromFile("/json/bracelet-request.json");

        BraceletPage braceletPage = given()
                .spec(requestSpecification)
                .body(braceletRequest)
                .when()
                .post()
                .then()
                .extract().as(BraceletPage.class);

        Bracelet[] bracelets = braceletPage.getData().getBracelets();

        List<Integer> searchIndexes = braceletIndex.isEmpty() ? generateIndexes(bracelets.length) : braceletIndex;

        for (Integer index : searchIndexes) {
            Bracelet bracelet = null;
            try {
                bracelet = bracelets[index];
            } catch (ArrayIndexOutOfBoundsException e) {
                fail("Such bracelet doesn't exist:" + index);
            }
            String medicalCardRequest = getJsonFromFile("/json/medical-card-request.json");
            String formattedRequest = String.format(medicalCardRequest, bracelet.getGuid());

            MedicalCardPage medicalCardPage = given()
                    .spec(requestSpecification)
                    .body(formattedRequest)
                    .when()
                    .post()
                    .then()
                    .extract().as(MedicalCardPage.class);

            MedicalCard medicalCard = medicalCardPage.getData().getMedicalCard();
            assertNotNull(medicalCard, "Medical card is not found for bracelet #" + index);
            assertEquals(true, medicalCard.getActive(), "Medical card is inactive. Bracelet ID: " + bracelet.getGuid() + "Medical card ID: " + medicalCard.getGuid());
        }
    }

    private List<Integer> generateIndexes(int length) {
        List<Integer> searchIndexes = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            searchIndexes.add(i);
        }
        return searchIndexes;
    }

}