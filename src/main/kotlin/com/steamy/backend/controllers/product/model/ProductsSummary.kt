package com.steamy.backend.controllers.product.model

import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table( name = "products" )
class ProductsSummary {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    val prod_id: Int = 0;

    @Column
    val prod_name: String = "";

    @Column
    var prod_image: String? = null;

    @Column
    val prod_price: Double = 0.0;

    @Column
    val prod_sales: Int = 0;

    @Column
    val is_popular: Boolean = false;
}