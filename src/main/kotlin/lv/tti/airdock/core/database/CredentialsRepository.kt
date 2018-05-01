package lv.tti.airdock.core.database

import lv.tti.airdock.core.domain.Credentials
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CredentialsRepository : JpaRepository<Credentials, Long> {
    fun findByUserId(id : Long) : Optional<Credentials>
    fun findByLogin(login: String) : Optional<Credentials>
}