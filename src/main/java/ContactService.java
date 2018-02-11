
import com.google.gson.*;

import java.util.ArrayList;

import static spark.Spark.*;

public class ContactService {

    public ContactService(){


        get("/contact/:name", (req, res) -> {
            res.type("application/json");
            String name = req.params(":name");
            GetService obj = new GetService();
            String result = obj.fetchData(name);
            return result;
        }, new Gson():: toJson);

        get("/contact", (req, res) -> {
            res.type("application/json");
            String page = req.queryParams("page");
            String size = req.queryParams("size");
            String query = req.queryParams("query");
            SearchAllService obj = new SearchAllService();
            ArrayList<String> result = obj.searchAll(page, size, query);

            return result;
        }, new Gson():: toJson);

        post("/contact/:name", (req, res) -> {
            res.type("application/text");
            DataObject contact = new Gson().fromJson(req.body(), DataObject.class);
            DataLayer layer = new DataLayer();
            String name = req.params(":name");
            contact.setName(name);
            if(layer.verify(contact) != "")
                return layer.verify(contact);
            PostService obj = new PostService();
            String result = obj.enterData(contact);
            return result;
        });

        delete("/contact/:name", (req, res) -> {
            res.type("application/text");
            String name = req.params(":name");
            DeleteService obj = new DeleteService();
            String result = obj.deleteData(name);
            return result;
        });

        put("/contact/:name", (req, res) -> {
            res.type("application/text");
            String name = req.params(":name");
            DataLayer layer = new DataLayer();
            DataObject contact = new Gson().fromJson(req.body(), DataObject.class);
            contact.setName(name);
            if(layer.verify(contact) != "")
                return layer.verify(contact);
            UpdateService obj = new UpdateService();
            String result = obj.updateData(contact);
            return result;
        });
    }


    public static void main(String[] args) {
        System.out.println("Test");
        ContactService contactService = new ContactService();
        DataLayer dataLayer = new DataLayer();
        dataLayer.createIndex();
    }
}