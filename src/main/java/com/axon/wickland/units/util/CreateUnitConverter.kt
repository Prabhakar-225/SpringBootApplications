package com.axon.wickland.units.util

import com.axon.wickland.units.api.*
import com.axon.wickland.units.query.UnitStates
import java.util.*

object CreateUnitConverter {

    fun convertModelToDTO(State: UnitStates): UnitDto {
        return UnitDto(State.unitTypeId, State.unitTypeName)
    }

    fun convertDtoToCommand(createParkDTO: CreateUnitDTO): CreateUnitCommand {
        return CreateUnitCommand(UUID.randomUUID().toString(), createParkDTO.unitTypeName)

    }

    fun convertDtoToCommandToUpdtaeUnit(updateDto: UpdateUnitDTO): UpdateUnitCommand {
        return UpdateUnitCommand(updateDto.unitTypeId, updateDto.unitTypeName)
    }

    fun convertDtoToCommandToUnitDelete(deleteDTO: DeleteUnitTypeDTO): DeleteUnitTypeCommand {
        return DeleteUnitTypeCommand(deleteDTO.unitTypeId)
    }
}