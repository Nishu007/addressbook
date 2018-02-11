import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

public class UpdateService {
    public String updateData(DataObject obj){
        String result = "Successfully Updated";
        try{
            DataLayer layer = new DataLayer();
            SearchDocumentService search = new SearchDocumentService();
            RestHighLevelClient client = layer.getConnection();
            long status = search.searchDocument(obj.getName());
            if(status == 0)
                return "Document not found";

            UpdateRequest request = new UpdateRequest("address_book3", "user", obj.getName());
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                if(obj.getHouseNumber() != null)
                    builder.field("house_number", obj.getHouseNumber());
                if(obj.getStreetName() != null)
                    builder.field("street_name", obj.getStreetName());
                if(obj.getCity() != null)
                    builder.field("city", obj.getCity());
                if(obj.getState() != null)
                    builder.field("state", obj.getState());
                if(obj.getZipcode() != null)
                    builder.field("zipcode", obj.getZipcode());
                if(obj.getPhone() != null)
                    builder.field("phone", obj.getPhone());
            }
            builder.endObject();
            request.doc(builder);
            UpdateResponse updateResponse = client.update(request);
            if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                System.out.println("Success in update");
            } else{
                System.out.println("Unexpected action");
            }

            client.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }
}
