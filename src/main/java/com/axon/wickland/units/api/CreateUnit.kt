package com.axon.wickland.units.api

import org.axonframework.modelling.command.TargetAggregateIdentifier
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateUnitCommand(

    @TargetAggregateIdentifier
    var unitTypeId: String? =null,
    var unitTypeName: String? =null,
    var unitOccupation: String? =null
)

data class UnitCreatedEvent(

    var unitTypeId: String? =null,
    var unitTypeName: String? =null,
    var unitOccupation: String? =null
)

data class  CreateUnitDTO(
    @get:Size(min = 3, max = 80, message = "unitTypeName must be consist Requirement min-3 to max-80 alphabets")
    @get:NotEmpty(message = "unitType should not be Empty")
    @get:NotNull(message = "unitType should not Null")
    @get:NotBlank(message = "unitType should not Blank")
    var unitTypeName: String? =null,
    @get:NotEmpty(message = "Unit Occupation must be units should not be Empty")
    @get:NotNull(message = "Unit Occupation must be units should not Null")
    @get:NotBlank(message = "Unit Occupation must be units should not Blank")
    var unitOccupation: String? =null
)

data class UnitDto(
    var unitTypeId: String? =null,
    var unitTypeName: String? =null,
    var unitOccupation: String? =null
)