package lv.tti.airdock.core.services

import lv.tti.airdock.core.database.RequestRepository
import lv.tti.airdock.core.domain.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class RequestService {

    @Autowired lateinit var sessionService: SessionService
    @Autowired lateinit var repository: RequestRepository

    fun createDraftRequest() : Request {
        val request = Request(
                author = sessionService.getActiveUser().get(),
                status = Request.Status.DRAFT,
                title = "Place your title here",
                description = "Place the description here",
                creationDate = Date()
        )
        return repository.save(request)
    }

    fun getRequestsBy(status: String?): List<Request> =
            if (status == null) repository.findAll()
            else                repository.findAllByStatus(Request.Status.valueOf(status))


    fun updateRequestStatus(id: Long, status: Request.Status) : Request{
        repository.updateStatus(id, status)
        return repository.findById(id).get()
    }

    fun getRequestsByUserId(id: Long): List<Request> = repository.findByAuthor(id)

    fun deleteRequest(id: Long) : Long {
        repository.deleteById(id)
        return id;
    }

    fun updateRequestField(request: Request) : Request{
        repository.updateFieldsById(
                id          = request.id!!,
                description = request.description,
                title       = request.title
        )
        return repository.findById(request.id!!).get()
    }
}