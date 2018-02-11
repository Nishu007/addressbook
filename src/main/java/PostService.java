import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

public class PostService {
    public String enterData(DataObject obj) {
        try{
            DataLayer layer = new DataLayer();
            SearchDocumentService search = new SearchDocumentService();
            RestHighLevelClient client = layer.getConnection();
            IndexRequest request = new IndexRequest("address_book3", "user", obj.getName()); //change
            long status = search.searchDocument(obj.getName());
            if(status == 1)
                return "Document already exists";


            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                builder.field("name", obj.getName());
                builder.field("house_number", obj.getHouseNumber());
                builder.field("street_name", obj.getStreetName());
                builder.field("city", obj.getCity());
                builder.field("state", obj.getState());
                builder.field("zipcode", obj.getZipcode());
                builder.field("phone", obj.getPhone());
            }
            builder.endObject();

            request.source(builder);
            System.out.println("Data Entered" + obj.getName());
            IndexResponse indexResponse = client.index(request);
            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
                System.out.println("Data newly created");
            } else  {
                System.out.println("Data not created. Unexpected Action!!");
            }
            ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();

            if (shardInfo.getFailed() > 0) {
                System.out.println("Failed");
                for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                    String reason = failure.reason();
                    System.out.println(reason);
                }
            }

            client.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return "Document Successfully created";
    }

}
