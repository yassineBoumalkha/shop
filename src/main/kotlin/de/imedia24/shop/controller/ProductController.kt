package de.imedia24.shop.controller

import de.imedia24.shop.domain.product.ProductRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)!!

    @GetMapping("/products/{sku}", produces = ["application/json;charset=utf-8"])
    fun findProductsBySku(
        @PathVariable("sku") sku: String
    ): ResponseEntity<ProductResponse> {
        logger.info("Request for product $sku")

        val product = productService.findProductBySku(sku)
        return if(product == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }

    @GetMapping("/products", produces = ["application/json;charset=utf-8"])
    fun findListProductsBySku(
            @RequestParam("skus") skus: List<String>
    ): ResponseEntity<List<ProductResponse>> {
        logger.info("Request for product $skus")

        val product = productService.findListProductBySku(skus)
        return if(product == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }

    @PostMapping("/products", produces = ["application/json;charset=utf-8"])
    fun addProduct(
            @RequestBody product: ProductRequest
    ): ResponseEntity<Void> {
        logger.info("Request for product $product")

        productService.addProduct(product)
        return ResponseEntity.created(URI.create("/products")).build();

    }

    @PutMapping("/products", produces = ["application/json;charset=utf-8"])
    fun updateProduct(
            @RequestBody product: ProductRequest
    ): ResponseEntity<Void> {
        logger.info("Request for product $product")
        productService.updateProduct(product)
        return ResponseEntity.noContent().build();

    }
}
