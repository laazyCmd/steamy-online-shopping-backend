package com.steamy.backend.controllers.product

import com.steamy.backend.controllers.product.component.ProductsSummaryComponent
import com.steamy.backend.controllers.product.model.Products
import com.steamy.backend.controllers.product.model.ProductsSummary
import com.steamy.backend.controllers.product.repository.ProductsRepository
import com.steamy.backend.messages.NotFound
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping( "/product" )
class ProductsController(
    val productsRepository: ProductsRepository,
    val productsSummaryComponent: ProductsSummaryComponent ) {

    @GetMapping( value = ["/{id}"], produces = ["application/json"] )
    fun getProduct( @PathVariable( "id" ) product_id: Int ): ResponseEntity<Any> {
        val specific_product: Products? = this.productsRepository.findByIdOrNull( product_id )
        specific_product?.prodImage = Base64.getEncoder().encodeToString( this.productsRepository.getProductImage( product_id ) )

        if ( specific_product === null ) return ResponseEntity( "No product has been found with an ID of $product_id", HttpStatus.NOT_FOUND )
        return ResponseEntity( specific_product, HttpStatus.FOUND )
    }

    @GetMapping( value = ["/list"], produces = ["application/json"] )
    fun getProductList( @RequestParam( "name", required = false ) name: String?,
                        @RequestParam( "category", required = false ) category: String?,
                        @RequestParam( "price", required = false ) price: Double?,
                        @RequestParam( "sort", required = false ) sort: String?,
                        @RequestParam( "pageNo", required = false, defaultValue = "0" ) page_no: Int,
                        @RequestParam( "pageSize", required = false, defaultValue = "9" ) page_size: Int ): ResponseEntity<Any> {

        val product_list: Page<ProductsSummary> = this.productsSummaryComponent.findAllProducts( name, category, price, PageRequest.of( page_no, page_size ) );

        if ( product_list.isEmpty ) return ResponseEntity( "No products found", HttpStatus.NOT_FOUND );
        return ResponseEntity( product_list, HttpStatus.FOUND );
    }
}