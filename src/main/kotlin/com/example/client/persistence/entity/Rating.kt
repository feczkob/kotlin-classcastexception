package com.example.client.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "vehicle_id"])])
class Rating(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_seq")
    @SequenceGenerator(name = "rating_seq", sequenceName = "rating_seq", allocationSize = 1)
    @Column(nullable = false, name = "id")
    var id: Long? = null,
    @Column(nullable = false, name = "value")
    var value: Int = 0,
    @Column(name = "comment")
    var comment: String? = null,
    @Column(nullable = false, name = "user_id")
    var userId: Long = 0,
    // * https://stackoverflow.com/questions/53647672/how-to-save-parent-and-child-in-one-shot-jpa-hibernate
    @Column(nullable = false, name = "vehicle_id")
    var vehicleId: Long = 0,
)