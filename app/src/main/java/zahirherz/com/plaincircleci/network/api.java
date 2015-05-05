package zahirherz.com.plaincircleci.network;


import java.util.List;
import retrofit.http.GET;
import retrofit.Callback;
import zahirherz.com.plaincircleci.model.Flower;

/**
 * Created by zahirh on 5/4/15.
 */
public interface api {
    @GET("/feeds/flowers.json")
    public void getData(retrofit.Callback<List<Flower>> response);
}
