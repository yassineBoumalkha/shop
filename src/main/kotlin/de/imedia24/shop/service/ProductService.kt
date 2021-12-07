package de.imedia24.shop.service

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.domain.product.ProductRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductResponse.Companion.toProductResponse
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.ZonedDateTime

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findProductBySku(sku: String): ProductResponse? {
        val productEntity = productRepository.findBySku(sku);
        return productEntity?.toProductResponse()
    }

    fun findListProductBySku(skus: List<String>): List<ProductResponse> {
        val skusIterator : Iterator<String> = skus.iterator();
        val listProductResponse : ArrayList<ProductResponse> = ArrayList();
        while (skusIterator.hasNext()){
            findProductBySku(skusIterator.next())?.let { listProductResponse.add(it) };
        }
        return listProductResponse;
    }

    fun addProduct(product: ProductRequest) {
        val productEntity : ProductEntity = ProductEntity(
                product.sku,
                product.name,
                product.description,
                product.price,
                ZonedDateTime.now(),
                ZonedDateTime.now(),
                product.stock
        );
        val productEntityDb : ProductEntity? = productRepository.findBySku(product.sku);
        if(productEntityDb != null){
            throw RuntimeException("Product already exists.")
        }
        productRepository.save(productEntity);

    }

    fun updateProduct(product: ProductRequest) {
        val productEntity : ProductEntity = ProductEntity(
                product.sku,
                product.name,
                product.description,
                product.price,
                ZonedDateTime.now(),
                ZonedDateTime.now(),
                product.stock
        );
        val productEntityDb : ProductEntity = productRepository.findBySku(product.sku)
                ?: throw RuntimeException("Product does not exists.");
        productRepository.save(productEntity);

    }
}
