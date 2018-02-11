import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import static spark.Spark.awaitInitialization;
import static spark.Spark.stop;
import static junit.framework.TestCase.assertEquals;
public class TestJunit {
    @Before
    public void setUp() throws Exception {
        ContactService obj = new ContactService();
        awaitInitialization();

    }

    @After
    public void tearDown() throws Exception {
        stop();
    }

    @Test
    public void testModelObjectsGET() {

        String testUrl = "/contact/john21";
        ApiTestUtils.TestResponse res = ApiTestUtils.request("GET", testUrl, null);
        assertEquals(200, res.status);

    }
    @Test
    public void testModelObjectsPOST() {
        String reqbody = "{" +
                "\"name\":\"kimchy\"," +
                "\"house_number\":\"201\"," +
                "\"city\":\"Vienna\"" +
                "}";

        String testUrl = "/contact/john21";
        ApiTestUtils.TestResponse res = ApiTestUtils.request("POST", testUrl, reqbody);

        assertEquals(200, res.status);

    }

    @Test
    public void testModelObjectsGETActual() {

        String testUrl = "/contact/john21";
        ApiTestUtils.TestResponse res = ApiTestUtils.request("GET", testUrl, null);
        assertEquals(200, res.status);

    }

    @Test
    public void testModelObjectsPUT() {

        String testUrl = "/contact/john21";
        ApiTestUtils.TestResponse res = ApiTestUtils.request("PUT", testUrl, null);
        assertEquals(200, res.status);
    }

    @Test
    public void testModelObjectsDELETE() {

        String testUrl = "/contact/john21";
        ApiTestUtils.TestResponse res = ApiTestUtils.request("DELETE", testUrl, null);
        assertEquals(200, res.status);
    }

}
