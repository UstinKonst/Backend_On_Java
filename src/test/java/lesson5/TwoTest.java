package lesson5;

import com.github.javafaker.Code;
import com.github.javafaker.Faker;
import lesson5.api.CategoryService;
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

public class TwoTest {

    static ProductService productService;
    
    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @SneakyThrows
    @Test
    void tearDown() {
        for (int i = 1; i < 6; i++) {
            Response<ResponseBody> response = productService.deleteProduct(i).execute();
            assertThat(response.isSuccessful(), CoreMatchers.is(true));
        }
    }

    @SneakyThrows
    @Test
    void getProductById() {
        for (int i = 1; i < 6; i++) {
            Response<Product> response = productService.getProductById(i).execute();
            assertThat(response.isSuccessful(), CoreMatchers.is(false));
        }

    }
}
