package Api;

import com.furrycatxd.RocketNews.News;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("News")
    Call<News> getNews(String country,String apiKey);
}
