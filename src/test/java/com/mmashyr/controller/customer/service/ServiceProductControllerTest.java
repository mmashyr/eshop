package com.mmashyr.controller.customer.service;

import com.mmashyr.entity.Product;
import com.mmashyr.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Mark on 27.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceProductControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ServiceProductController serviceProductController;
    private MockMvc mockMvc;
    private Product product1, product2;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(serviceProductController)
                .build();
        product1 = new Product();
        product1.setId(1L);
        product1.setName("product1");
        product1.setPrice(new BigDecimal(1));
        product1.setImageURL("image1URL");
        product2 = new Product();
        product2.setId(2L);
        product2.setName("product2");
        product2.setPrice(new BigDecimal(2));
        product2.setImageURL("image2URL");
    }

    @Test
    public void whenAllProductsRetrieved_then302Found() throws Exception {
        List<Product> products = Arrays.asList(
                product1,
                product2);
        when(productService.getAll()).thenReturn(products);
        mockMvc.perform(get("/service/product/"))
                .andExpect(status().is(302))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("product1")))
                .andExpect(jsonPath("$[0].price", is(1)))
                .andExpect(jsonPath("$[0].imageURL", is("image1URL")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("product2")))
                .andExpect(jsonPath("$[1].price", is(2)))
                .andExpect(jsonPath("$[1].imageURL", is("image2URL")));

        verify(productService, times(1)).getAll();
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void whenNoProductsRetrieved_then204NoContent() throws Exception {

        when(productService.getAll()).thenReturn(null);
        mockMvc.perform(get("/service/product/"))
                .andExpect(status().is(204));

        verify(productService, times(1)).getAll();
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void whenProductIsFound_then200Ok() throws Exception {
        when(productService.findOne(1L)).thenReturn(product1);
        mockMvc.perform(get("/service/product/1"))
                .andExpect(status().is(302))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("product1")))
                .andExpect(jsonPath("$.price", is(1)))
                .andExpect(jsonPath("$.imageURL", is("image1URL")));

        verify(productService, times(1)).findOne(1L);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void whenProductNotFound_then204NoContent() throws Exception {
        when(productService.findOne(1L)).thenReturn(null);
        mockMvc.perform(get("/service/product/1"))
                .andExpect(status().is(204));

        verify(productService, times(1)).findOne(1L);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void whenIdCannotBeParsed_then400BadRequest() throws Exception {
        mockMvc.perform(get("/service/product/notParsable"))
                .andExpect(status().is(400));
        verifyZeroInteractions(productService);
    }

    @Test
    public void whenPagesFound_thenReturnNumberOfPages_200OK() throws Exception {
        PageRequest request = new PageRequest(0, 3);
        Page page = mock(PageImpl.class);
        when(page.getTotalPages()).thenReturn(1);
        when(productService.findAllByPage(request)).thenReturn(page);

        mockMvc.perform(get("/service/product/maximumpages"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", is(1)));
    }

    @Test
    public void whenPagesNotFound_then204NoContent() throws Exception {
        PageRequest request = new PageRequest(0, 3);
        Page page = mock(PageImpl.class);
        when(page.getTotalPages()).thenReturn(0);
        when(productService.findAllByPage(request)).thenReturn(page);

        mockMvc.perform(get("/service/product/maximumpages"))
                .andExpect(status().is(204));
    }

    @Test
    public void whenPageFound_ReturnIt_200Ok() throws Exception {
        PageRequest request = new PageRequest(0, 3);
        Page<Product> page = new PageImpl(Arrays.asList(product1, product2));
        when(productService.findAllByPage(request)).thenReturn(page);

        mockMvc.perform(get("/service/product/page/1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("product1")))
                .andExpect(jsonPath("$[0].price", is(1)))
                .andExpect(jsonPath("$[0].imageURL", is("image1URL")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("product2")))
                .andExpect(jsonPath("$[1].price", is(2)))
                .andExpect(jsonPath("$[1].imageURL", is("image2URL")));
        verify(productService, times(1)).findAllByPage(request);
    }

    @Test
    public void whenPageNotFound_Return204NoContent() throws Exception {
        PageRequest request = new PageRequest(0, 3);
        Page<Product> page = new PageImpl(Collections.emptyList());
        when(productService.findAllByPage(request)).thenReturn(page);

        mockMvc.perform(get("/service/product/page/1"))
                .andExpect(status().is(204));
    }

    @Test
    public void whenPageNumberIsLessThanOne_then400BadRequest() throws Exception {
        mockMvc.perform(get("/service/product/page/0"))
                .andExpect(status().is(400));
        verifyZeroInteractions(productService);
    }

    @Test
    public void whenPageNumberCannotBeParsed_then400BadRequest() throws Exception {
        mockMvc.perform(get("/service/product/page/notParsable"))
                .andExpect(status().is(400));
        verifyZeroInteractions(productService);
    }

    @Test
    public void whenNameInSearchRequestIsNull_then400BadRequest() throws Exception {
        mockMvc.perform(get("/service/product/search/result"))
                .andExpect(status().is(404));
    }

    @Test
    public void whenNameInSearchRequestIsEmpty_then400BadRequest() throws Exception {
        mockMvc.perform(get("/service/product/search/result")
                .param("name", ""))
                .andExpect(status().is(404));
    }

    @Test
    public void whenNumberOfResultsIsZero_then204NoContent() throws Exception {
        String searchName = "someSearchName";
        PageRequest request = new PageRequest(0, 3);
        Page page = mock(PageImpl.class);
        when(page.getTotalPages()).thenReturn(0);
        when(productService.findByName(searchName, request)).thenReturn(page);

        mockMvc.perform(get("/service/product/search/result/")
                .param("name", searchName))
                .andExpect(status().is(204));
    }

    @Test
    public void whenResultsFound_thenReturnItsQuantity_200Ok() throws Exception {
        String searchName = "someSearchName";
        PageRequest request = new PageRequest(0, 3);
        Page page = mock(PageImpl.class);
        when(page.getTotalPages()).thenReturn(1);
        when(productService.findByName(searchName, request)).thenReturn(page);

        mockMvc.perform(get("/service/product/search/result/")
                .param("name", searchName))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", is(1)));

    }

    @Test
    public void whenPageNumberRequestedFromSearchIsLessThanOne_then400BadRequest() throws Exception {
        mockMvc.perform(get("/service/product/search/result/page/0"))
                .andExpect(status().is(400));
        verifyZeroInteractions(productService);
    }

    @Test
    public void whenPageNumberRequestedFromSearchCannotBeParsed_then400BadRequest() throws Exception {
        mockMvc.perform(get("/service/product/page/notParsable"))
                .andExpect(status().is(400));
        verifyZeroInteractions(productService);
    }

    @Test
    public void whenNameInPageRequestFromSearchIsNull_then400BadRequest() throws Exception {
        mockMvc.perform(get("/service/product/search/result/page/1"))
                .andExpect(status().is(400));
    }

    @Test
    public void whenNameInPageRequestFromSearchIsEmpty_then400BadRequest() throws Exception {
        mockMvc.perform(get("/service/product/search/result/page/1")
                .param("name", ""))
                .andExpect(status().is(400));
    }

    @Test
    public void whenPageIsRequestedFromSearch_thenReturnIt_200OK() throws Exception {
        PageRequest request = new PageRequest(0, 3);
        Page<Product> page = new PageImpl(Arrays.asList(product1, product2));
        String name = "some name";
        when(productService.findByName(name, request)).thenReturn(page);

        mockMvc.perform(get("/service/product/search/result/page/1")
                .param("name", name))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("product1")))
                .andExpect(jsonPath("$[0].price", is(1)))
                .andExpect(jsonPath("$[0].imageURL", is("image1URL")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("product2")))
                .andExpect(jsonPath("$[1].price", is(2)))
                .andExpect(jsonPath("$[1].imageURL", is("image2URL")));
        verify(productService, times(1)).findByName(name, request);
    }

    @Test
    public void whenResultOfSearchIsEmpty_then204NoContent() throws Exception {
        PageRequest request = new PageRequest(0, 3);
        Page<Product> page = new PageImpl(Collections.emptyList());
        String nameWithEmptySearchResult = "some name";
        when(productService.findByName(nameWithEmptySearchResult, request)).thenReturn(page);

        mockMvc.perform(get("/service/product/search/result/page/1")
                .param("name", nameWithEmptySearchResult))
                .andExpect(status().is(204));

    }

    @Test
    public void whenProducersAreNullInSearchByCategories_then400BadRequest() throws Exception {
        mockMvc.perform(get("/service/product/categories/result"))
                .andExpect(status().is(400));
    }

    @Test
    public void whenProducersAreEmptyInSearchByCategories_then400BadRequest() throws Exception {
        mockMvc.perform(get("/service/product/categories/result")
                .param("producer", ""))
                .andExpect(status().is(400));
    }

    @Test
    public void whenResultInSearchByCategoriesFound_thenReturnNumberOfPages_200OK() throws Exception {
        PageRequest request = new PageRequest(0, 3);
        Page page = mock(PageImpl.class);
        when(page.getTotalPages()).thenReturn(1);
        List<String> producer = new ArrayList<>();
        producer.add("category1");
        producer.add("category2");
        when(productService.findDistinctByCategoryNames(producer, request)).thenReturn(page);

        mockMvc.perform(get("/service/product/categories/result")
                .param("producer[]", "category1, category2"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", is(1)));
    }

    @Test
    public void whenResultInSearchByCategoriesIsEmpty_then204NoContent() throws Exception {
        PageRequest request = new PageRequest(0, 3);
        Page page = mock(PageImpl.class);
        when(page.getTotalPages()).thenReturn(0);
        List<String> producer = new ArrayList<>();
        producer.add("category1");
        producer.add("category2");
        when(productService.findDistinctByCategoryNames(producer, request)).thenReturn(page);

        mockMvc.perform(get("/service/product/categories/result")
                .param("producer[]", "category1, category2"))
                .andExpect(status().is(204));
    }

    @Test
    public void whenCategoriesAreNull_thenReturnAllProducts() throws Exception {
        PageRequest request = new PageRequest(0, 3);
        Page<Product> page = new PageImpl(Arrays.asList(product1, product2));
        when(productService.findAllByPage(request)).thenReturn(page);

        mockMvc.perform(get("/service/product/categories/result/page/1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("product1")))
                .andExpect(jsonPath("$[0].price", is(1)))
                .andExpect(jsonPath("$[0].imageURL", is("image1URL")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("product2")))
                .andExpect(jsonPath("$[1].price", is(2)))
                .andExpect(jsonPath("$[1].imageURL", is("image2URL")));
        verify(productService, times(1)).findAllByPage(request);
    }

    @Test
    public void whenCategoriesAreEmpty_thenReturnAllProducts() throws Exception {
        PageRequest request = new PageRequest(0, 3);
        Page<Product> page = new PageImpl(Arrays.asList(product1, product2));
        when(productService.findAllByPage(request)).thenReturn(page);

        mockMvc.perform(get("/service/product/categories/result/page/1")
                .param("producer[]", ""))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("product1")))
                .andExpect(jsonPath("$[0].price", is(1)))
                .andExpect(jsonPath("$[0].imageURL", is("image1URL")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("product2")))
                .andExpect(jsonPath("$[1].price", is(2)))
                .andExpect(jsonPath("$[1].imageURL", is("image2URL")));
        verify(productService, times(1)).findAllByPage(request);
    }

    @Test
    public void whenResultsInSearchByCategoriesFound_thenReturnPage_200OK() throws Exception {
        PageRequest request = new PageRequest(0, 3);
        Page<Product> page = new PageImpl(Arrays.asList(product1, product2));
        List<String> producer = new ArrayList<>();
        producer.add("category1");
        producer.add("category2");
        when(productService.findDistinctByCategoryNames(producer, request)).thenReturn(page);

        mockMvc.perform(get("/service/product/categories/result/page/1")
                .param("producer[]", "category1, category2"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("product1")))
                .andExpect(jsonPath("$[0].price", is(1)))
                .andExpect(jsonPath("$[0].imageURL", is("image1URL")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("product2")))
                .andExpect(jsonPath("$[1].price", is(2)))
                .andExpect(jsonPath("$[1].imageURL", is("image2URL")));
        verify(productService, times(1)).findDistinctByCategoryNames(producer, request);
    }

    @Test
    public void whenResultsInSearchByCategoriesAreEmpty_thenReturnPage_200OK() throws Exception {
        PageRequest request = new PageRequest(0, 3);
        Page<Product> page = new PageImpl(Collections.EMPTY_LIST);
        List<String> producer = new ArrayList<>();
        producer.add("category1");
        producer.add("category2");
        when(productService.findDistinctByCategoryNames(producer, request)).thenReturn(page);

        mockMvc.perform(get("/service/product/categories/result/page/1")
                .param("producer[]", "category1, category2"))
                .andExpect(status().is(204));
    }
}
