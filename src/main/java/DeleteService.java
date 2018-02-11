import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class DeleteService {
    public String deleteData(String id){
        DataLayer layer = new DataLayer();
        RestHighLevelClient client = layer.getConnection();
        String result = "Successfully Deleted";
        DeleteRequest request = new DeleteRequest("address_book3", "user", id);
        try{
            DeleteResponse deleteResponse = client.delete(request);
            if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
                result = "Document not found!!";
            }
            client.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }
}
