package lv.tti.airdock.dto

import javax.validation.constraints.NotBlank

data class RegistrationDto(
        @get:NotBlank var login : String = "",
        @get:NotBlank var password : String = "",
        @get:NotBlank var name : String = ""
)