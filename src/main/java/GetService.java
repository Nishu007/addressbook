import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class GetService {
    public String fetchData(String id) {
        String sourceAsString = "";
        try{
            DataLayer layer = new DataLayer();
            RestHighLevelClient client = layer.getConnection();
            GetRequest getRequest = new GetRequest("address_book3", "user", id);
            GetResponse getResponse = client.get(getRequest);
            System.out.println(getResponse);
            if (getResponse.isExists()) {
                sourceAsString = getResponse.getSourceAsString();
                System.out.println(getResponse.getSourceAsString());
            }
            else {
                System.out.println("No such data available");
            }
            client.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return sourceAsString;
    }
}
