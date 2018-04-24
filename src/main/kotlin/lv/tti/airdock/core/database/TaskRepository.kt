package lv.tti.airdock.core.database

import lv.tti.airdock.core.domain.Task
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Transactional(Transactional.TxType.MANDATORY)
interface TaskRepository : JpaRepository<Task, Long>