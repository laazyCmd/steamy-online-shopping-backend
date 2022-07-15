package com.steamy.backend.controllers.product.model

import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table( name = "products" )
class ProductsSummary {

    @Id
    @Column
    val id: Int = 0;

    @Column
    val name: String = "";

    @Column
    val image: ByteArray? = null;

    @Column
    val price: Double = 0.0;

    @Column
    val sales: Int = 0;

    @Column
    val category: String = "";

    @Column( name = "date_created" )
    val dateCreated: String = "";

    @Column( name = "shipping_fee" )
    val shippingFee: Double = 0.0;

    @Column( name = "is_popular" )
    val isPopular: Boolean = false;
}