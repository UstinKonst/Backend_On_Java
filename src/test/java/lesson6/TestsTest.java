package lesson6;

import lesson5.dto.Product;
import lesson6.db.dao.ProductsMapper;
import lesson6.db.model.Products;
import lesson6.db.model.ProductsExample;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestsTest extends AbstractTest {

    @SneakyThrows
    @Test
    void create() {
        product = new Product()
                .withTitle("mobile")
                .withCategoryTitle("Electronic")
                .withPrice(300);
        Response<Product> response = productService.createProduct(product).execute();

        ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);
        ProductsExample example = new ProductsExample();
        example.createCriteria().andPriceEqualTo(300);
        List<Products> list = productsMapper.selectByExample(example);
        id = response.body().getId();
        Assertions.assertEquals("mobile", list.get(0).getTitle());
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

    }

    @SneakyThrows
    @Test
    void update() {
        product = new Product()
                .withId(id)
                .withTitle("radio")
                .withCategoryTitle("Electronic")
                .withPrice(1000);
        Response<Product> response = productService.modifyProduct(product).execute();

        ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);
        ProductsExample example = new ProductsExample();
        example.createCriteria().andTitleLike("%radio%");
        List<Products> list = productsMapper.selectByExample(example);

        Assertions.assertEquals(1000, list.get(0).getPrice());
        Assertions.assertEquals(2, list.get(0).getCategory_id());
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @AfterAll
    @Test
    static void delete() {
        Response<ResponseBody> response = productService.deleteProduct(Math.toIntExact(id)).execute();

        ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);
        ProductsExample example = new ProductsExample();
        example.createCriteria().andIdEqualTo(id);
        List<Products> list = productsMapper.selectByExample(example);

        Assertions.assertEquals(0, list.toArray().length);
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

//    @Test
//    public void one() {
//        try {
//            CategoriesMapper categoriesMapper = session.getMapper(CategoriesMapper.class);
//
//            CategoriesExample example = new CategoriesExample();
//            example.createCriteria().andIdEqualTo(1);
//            List<Categories> list = categoriesMapper.selectByExample(example);
//            System.out.println(list);
//
//            Categories categories = new Categories();
//            categories.setTitle("test");
//            categoriesMapper.insert(categories);
//            session.commit();
//
//            CategoriesExample example2 = new CategoriesExample();
//            example2.createCriteria().andTitleLike("%test%");
//            List<Categories> list2 = categoriesMapper.selectByExample(example2);
//            Categories categories2 = list2.get(0);
//            categories2.setTitle("test100");
//            categoriesMapper.updateByPrimaryKey(categories2);
//            session.commit();
//
//            categoriesMapper.deleteByPrimaryKey(categories2.getId());
//            session.commit();
//
//        }  finally {
//            session.close();
//        }
//    }
}
