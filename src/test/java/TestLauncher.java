import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import reqresTests.ReqresTests;


@Suite
@SelectClasses({ReqresTests.class})

public class TestLauncher {

    @Test
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());
    }
}