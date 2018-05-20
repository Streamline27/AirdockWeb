package lv.tti.airdock.core.services

import lv.tti.airdock.core.database.RequestRepository
import lv.tti.airdock.core.domain.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RequestService {

    @Autowired lateinit var repository: RequestRepository

    fun saveRequest(request: Request) = repository.save(request)

    fun deleteRequest(id: Long) = repository.deleteById(id)

    fun getRequestsBy(status: String?): List<Request> =
            if (status == null) repository.findAll()
            else                repository.findAllByStatus(Request.Status.valueOf(status))


    fun updateRequestStatus(id: Long, status: Request.Status) : Request{
        repository.updateStatus(id, status)
        return repository.findById(id).get()
    }

    fun getRequestsByUserId(id: Long): List<Request> = repository.findByAuthor(id)
}