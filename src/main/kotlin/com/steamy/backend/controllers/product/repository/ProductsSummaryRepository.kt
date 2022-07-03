package com.steamy.backend.controllers.product.repository

import com.steamy.backend.controllers.product.model.ProductsSummary
import org.springframework.data.jpa.repository.JpaRepository

interface ProductsSummaryRepository : JpaRepository<ProductsSummary, Int> {
}