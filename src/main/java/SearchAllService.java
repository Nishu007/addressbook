import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;

public class SearchAllService {
    public ArrayList<String> searchAll(String page, String size, String query){
        ArrayList<String> result  = new ArrayList<String>();
        String[] quer = query.split(",");
        try{
            int sizes = 10;
            DataLayer layer = new DataLayer();
            RestHighLevelClient client = layer.getConnection();
            SearchRequest searchRequest = new SearchRequest("address_book3");
            searchRequest.types("user");
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            if(query != null)
                sourceBuilder.query(QueryBuilders.termQuery(quer[0], quer[1]));
            if(size != null) {
                sizes = Integer.parseInt(size);
                sourceBuilder.size(sizes);
            }
            if(page != null) {
                int pages = (Integer.parseInt(page) * sizes) - sizes;
                sourceBuilder.from(pages);
            }

            searchRequest.source(sourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest);
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit : searchHits) {
                result.add(hit.getSourceAsString());
            }
            client.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }
}
