package mahesh;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.config.XmlConfig.xmlConfig;

public class SOAPXMLTest {
    @Test
    public void parse_xml_test(){
        Response response = given().
        when().
                get("https://1409c5f3-0098-43a8-850a-4749429e00ec.mock.pstmn.io/get").
        then().
                extract().response();

        System.out.println(response.xmlPath().getString("Envelope.Body.getAdUnitsByStatement.filterStatement.query.text()"));
    }
}
