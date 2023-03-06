package lesson5;

import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ThreeTest {
    static ProductService productService;
    Product product = null;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }



    @SneakyThrows
    @Test
    void modifyProduct(){
        Response<ResponseBody> response = productService.getProducts().execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));


        product = new Product()
                .withId(1)
                .withTitle("mobile")
                .withCategoryTitle("Electronic")
                .withPrice(300);
        Response<Product> response1 = productService.modifyProduct(product).execute();
        assertThat(response1.isSuccessful(), CoreMatchers.is(true));
        assertThat(response1.body().getId(), equalTo(1));
        assertThat(response1.body().getTitle(), equalTo("mobile"));
    }
}
