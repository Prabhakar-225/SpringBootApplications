package com.axon.wickland.units.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateUnitCommand(
    @TargetAggregateIdentifier
    var unitTypeId: String? =null,
    var unitTypeName: String? =null,
    var unitOccupation: String? =null
)

data class UnitUpdatedEvent(
    var unitTypeId: String? =null,
    var unitTypeName: String? =null,
    var unitOccupation: String? =null
)

data class UpdateUnitDTO(
    var unitTypeId: String? =null,
    var unitTypeName: String? =null,
    var unitOccupation: String? =null
)