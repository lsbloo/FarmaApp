package com.farmawebservice.delivery.deliveryzone.controller

import com.farmawebservice.delivery.base.BaseDTO
import com.farmawebservice.delivery.base.validator.resource.ResourceMessage
import com.farmawebservice.delivery.deliveryzone.model.dto.DDeliveryZoneDTO
import com.farmawebservice.delivery.deliveryzone.model.dto.DeliveryZoneDTO
import com.farmawebservice.delivery.deliveryzone.service.DeliveryZoneService
import com.farmawebservice.delivery.util.messages.dtos.MessageResponseDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/deliveryzone")
class DeliveryZoneController {

    @Autowired
    private lateinit var deliveryZoneService: DeliveryZoneService

    @GetMapping("/shop")
    @ApiOperation(value = "recovery all deliveryzones for shopStore", notes = "")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successufully recovery deliveryzones"),
        ApiResponse(code = 404, message = "Not found"), ApiResponse(code = 500, message = "Error Internal")])
    fun getAllDeliveryZonesForShop(@RequestBody dto: BaseDTO): ResponseEntity<MessageResponseDTO> {

        val resourceMessage = this.deliveryZoneService.getAllDeliveryZoneForShop(dto)

        resourceMessage?.let { dataMessage ->
            return if (dataMessage.isError) {
                ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage.messageValidator, resourceMessage.messageValidator, HttpServletResponse.SC_BAD_REQUEST))
            } else {
                ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage.messageValidator, resourceMessage.messageValidator, HttpServletResponse.SC_ACCEPTED, resourceMessage.responseDTO))

            }
        }
        return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("DeliveryZone don't deleted", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST))
    }


    @DeleteMapping("/shop")
    @ApiOperation(value = "delete a deliveryzone for shopStore", notes = "")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successufully at deleted deliveryzone"),
        ApiResponse(code = 404, message = "Not found"), ApiResponse(code = 500, message = "Error Internal")])
    fun deleteDeliveryZoneForShop(@RequestBody dto: DDeliveryZoneDTO): ResponseEntity<MessageResponseDTO> {
        val resourceMessage = this.deliveryZoneService.deleteDeliveryZoneForShop(dto)

        resourceMessage?.let { dataMessage ->
            return if (dataMessage.isError) {
                ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage.messageValidator, resourceMessage.messageValidator, HttpServletResponse.SC_BAD_REQUEST))
            } else {
                ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage.messageValidator, resourceMessage.messageValidator, HttpServletResponse.SC_ACCEPTED, resourceMessage.responseDTO))

            }
        }
        return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("DeliveryZone don't deleted", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST))
    }


    @PostMapping("/shop")
    @ApiOperation(value = "generate a deliveryzone for shopStore", notes = "")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successfully generate deliveryzone"),
        ApiResponse(code = 404, message = "Not found"), ApiResponse(code = 500, message = "Error Internal")])
    fun createDeliveryZoneForShop(@RequestBody dto: DeliveryZoneDTO): ResponseEntity<MessageResponseDTO>? {

        val resourceMessage = this.deliveryZoneService.createDeliveryZoneForShop(dto)
        resourceMessage?.let { dataMessage ->
            return if (dataMessage.isError) {
                ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage.messageValidator, resourceMessage.messageValidator, HttpServletResponse.SC_BAD_REQUEST))
            } else {
                ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage.messageValidator, resourceMessage.messageValidator, HttpServletResponse.SC_ACCEPTED, resourceMessage.responseDTO))
            }
        }
        return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("DeliveryZone don't generate", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST))
    }

}