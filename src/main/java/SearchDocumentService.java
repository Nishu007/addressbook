import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class SearchDocumentService {
    public long searchDocument(String id){
        long totalHits = 0;
        try{
            DataLayer layer = new DataLayer();

            RestHighLevelClient client = layer.getConnection();
            SearchRequest searchRequest = new SearchRequest("address_book3");
            searchRequest.types("user");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name", id);
            searchSourceBuilder.query(matchQueryBuilder);
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest);
            SearchHits hits = searchResponse.getHits();
            totalHits = hits.getTotalHits();
            client.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return totalHits;
    }

}
