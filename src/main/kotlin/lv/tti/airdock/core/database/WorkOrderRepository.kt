package lv.tti.airdock.core.database

import lv.tti.airdock.core.domain.WorkOrder
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Transactional
interface WorkOrderRepository : JpaRepository<WorkOrder, Long>