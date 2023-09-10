package com.axon.wickland.units.contrller

import com.axon.wickland.config.ResponseWithError
import com.axon.wickland.units.api.CreateUnitDTO
import com.axon.wickland.units.service.UnitService
import com.axon.wickland.units.util.CreateUnitConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import javax.validation.Valid

@RestController
@RequestMapping("/units")
class UnitController {

    @Autowired
    private val unitService: UnitService? =null

    @PostMapping("/createUnit")
    fun createUnit(@RequestBody @Valid createUnitDTO: CreateUnitDTO): CompletableFuture<ResponseWithError<String>> {
        val result = CreateUnitConverter.convertDtoToCommand(createUnitDTO)
        return unitService!!.createUnit(result)
    }


}