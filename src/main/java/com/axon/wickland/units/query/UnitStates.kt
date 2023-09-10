package com.axon.wickland.units.query

import org.springframework.data.annotation.Id
import javax.persistence.Entity


@Entity
data class UnitStates(

    @Id
    var unitTypeId: String? =null,
    var unitTypeName: String? =null,
    var unitOccupation: String? =null
)