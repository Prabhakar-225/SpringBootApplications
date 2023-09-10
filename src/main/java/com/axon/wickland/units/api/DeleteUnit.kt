package com.axon.wickland.units.api

import org.axonframework.modelling.command.TargetAggregateIdentifier


data class  DeleteUnitTypeCommand(
    @TargetAggregateIdentifier
    val unitTypeId: String? =null
)

data class UnitTypeDeletedEvent(
    val unitTypeId: String? =null
)

data class DeleteUnitTypeDTO(
    val unitTypeId: String? =null
)

