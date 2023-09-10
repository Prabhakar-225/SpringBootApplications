package com.axon.wickland.units.service

import com.axon.wickland.config.ResponseWithError
import com.axon.wickland.units.api.CreateUnitCommand
import com.axon.wickland.units.api.DeleteUnitTypeCommand
import com.axon.wickland.units.api.UnitDto
import com.axon.wickland.units.api.UpdateUnitCommand
import com.axon.wickland.units.query.UnitStatesRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class UnitService(private  val commandGateway: CommandGateway,
                  private val queryGateway: QueryGateway,
                  private val unitStatesRepository: UnitStatesRepository) {

        private  val logger = LoggerFactory.getLogger(UnitService::class.java)

    //create unit
    fun createUnit(command: CreateUnitCommand): CompletableFuture<ResponseWithError<String>> {
        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of("Unit Created SuccessFully ->" + command.unitTypeId)
        }.exceptionally { x ->
            logger.error(x.message)
            ResponseWithError.ofError(x.message)
        }
        return result
    }

    //update unit
    fun updateUnit(command: UpdateUnitCommand): CompletableFuture<ResponseWithError<String>>{

        val result= commandGateway.send<Any>(command).thenApply { r ->
         ResponseWithError.of("Updated unit Id successfully..@ "+command.unitTypeId)
        }.exceptionally { x ->
            logger.error(x.message)
            ResponseWithError.ofError(x.message)
        }
        return result;
    }

    //delete unit
    fun deleteUnit(command: DeleteUnitTypeCommand): CompletableFuture<ResponseWithError<String>>{
        val  units= this.unitStatesRepository.findByUnitTypeId(command.unitTypeId)
        if(units.isNotEmpty()){
            return CompletableFuture.completedFuture(ResponseWithError.ofError("UnitTypeId is already exist in unitStates"))
        }
        val result = commandGateway.send<Any>(command).thenApply { r -> ResponseWithError.of("Unit Delete with ID -> " + command.unitTypeId) }
            .exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
        return result;
    }

    //get All units
    fun findAllUnits(): CompletableFuture<ResponseWithError<List<UnitDto>>>{
        val result = queryGateway.query("findAllUnits",
            ResponseTypes.multipleInstancesOf(UnitDto::class.java))

        return result.thenApply { x ->
            if (x.isEmpty()) ResponseWithError.ofError("Unit Not found")
            else ResponseWithError.of(x)
        }
            .exceptionally { e -> ResponseWithError.ofError(e.message) }
    }

    //findByUnitTypeId
    fun findByUnitTypeId(unitTypeId: String): CompletableFuture<ResponseWithError<UnitDto>>{
        val result=queryGateway.query("findByUnitTypeId",unitTypeId,ResponseTypes.instanceOf(UnitDto::class.java))

        return result.thenApply { r ->
            if(r.unitTypeId==null)
                ResponseWithError.ofError("Unit Type Id Not Found")
            else
                ResponseWithError.of(r)
        }
            .exceptionally { e -> ResponseWithError.ofError(e.message) }
    }



}