import java.io.IOException;
import java.util.ArrayList;


import org.apache.http.HttpHost;
import org.elasticsearch.ResourceAlreadyExistsException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;

import org.elasticsearch.common.xcontent.XContentType;


public class DataLayer {


    public RestHighLevelClient  getConnection(){
        RestHighLevelClient  client = null;

        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));

        return client;
    }

    public void createIndex(){
        RestHighLevelClient  client = getConnection();
        try {
            CreateIndexRequest request = new CreateIndexRequest("address_book3");
            String entry_mapping = "{\n" +
                        "    \"user\": {\n" +
                        "      \"properties\": {\n" +
                        "        \"name\": {" +
                        "          \"type\": \"text\"\n" +
                        "        },\n" +
                        "        \"house_number\": {" +
                        "          \"type\": \"text\"\n" +
                        "        },\n" +
                        "        \"street_name\": {" +
                        "          \"type\": \"text\"\n" +
                        "        },\n" +
                        "        \"city\": {" +
                        "          \"type\": \"text\"\n" +
                        "        },\n" +
                        "        \"state\": {" +
                        "          \"type\": \"text\"\n" +
                        "        },\n" +
                        "        \"zipcode\": {" +
                        "          \"type\": \"text\"\n" +
                        "        },\n" +
                        "        \"phone\": {" +
                        "          \"type\": \"text\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }";
            request.settings(Settings.builder()
                    .put("index.number_of_shards", 1)
                    .put("index.number_of_replicas", 0)
            );
            request.mapping("user", entry_mapping, XContentType.JSON);
            try {
                System.out.println("Index created");
                CreateIndexResponse createIndexResponse = client.indices().create(request);
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(ResourceAlreadyExistsException e){
            e.printStackTrace();
        }
    }

    public String verify(DataObject obj){
        if(obj.getPhone().length() >= 13)
            return "Verify Phone Number";
        if(obj.getZipcode().length() >= 10)
            return "Verify Zipcode";
        if(obj.getStreetName().length() >= 100)
            return "Verify Street Name";
        if(obj.getHouseNumber().length() >= 10)
            return "Verify HouseNumber";
        if(obj.getState().length() >= 25)
            return "verify State Name";
        if(obj.getCity().length() >= 30)
            return "Verify City Name";
        return "";
    }


}
