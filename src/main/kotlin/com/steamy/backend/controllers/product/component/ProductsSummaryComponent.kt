package com.steamy.backend.controllers.product.component

import com.steamy.backend.controllers.product.model.ProductsSummary
import com.steamy.backend.controllers.product.repository.ProductsSummaryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component
import java.beans.Expression

@Component
class ProductsSummaryComponent( val productsSummaryRepository: ProductsSummaryRepository ) {

    fun findAllProducts( name: String?, category: String?, price: Double?, pageable: Pageable): Page<ProductsSummary> {
        var specification: Specification<ProductsSummary> = Specification.where( null );
        name?.let { specification = specification.and( byName( name ) ) };
        category?.let { specification = specification.and( byCategory( category ) ) };
        price?.let { specification = specification.and( byPrice( price ) ) };

        return this.productsSummaryRepository.findAll( specification, pageable );
    };

    private fun byName( name: String ): Specification<ProductsSummary> {
        return Specification<ProductsSummary> { root, _, builder ->
            builder.like( root.get( "prodName" ), "%$name%" );
        }
    }

    private fun byCategory( category: String ): Specification<ProductsSummary> {
        return Specification<ProductsSummary> { root, _, builder ->
            builder.like( root.get( "prodCategory" ), category );
        }
    }

    private fun byPrice( price: Double ): Specification<ProductsSummary> {
        return Specification<ProductsSummary> { root, _, builder ->
            builder.lessThanOrEqualTo( root.get( "prodPrice" ), price );
        }
    }
}