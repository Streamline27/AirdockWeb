package lv.tti.airdock.core.services

import lv.tti.airdock.core.database.CredentialsRepository
import lv.tti.airdock.core.database.UserRepository
import lv.tti.airdock.core.domain.Credentials
import lv.tti.airdock.core.domain.User
import lv.tti.airdock.core.utilities.UserException
import lv.tti.airdock.rest.dto.RegistrationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RegistrationService {

    @Autowired lateinit var credentialsRepository: CredentialsRepository
    @Autowired lateinit var userRepository: UserRepository

    fun registerWorker(registration : RegistrationDto) = with(registration){

        val credentialsWithSuppliedLogin = credentialsRepository.findByLogin(login)

        if (credentialsWithSuppliedLogin.isPresent) throw UserException("User with supplied username already exists")

        val user = User(name = name, role = User.Role.WORKER)

        val credentials = Credentials(
                login = login,
                password = password,
                user = user
        )

        userRepository.save(user)
        credentialsRepository.save(credentials)

        return@with user
    }

}