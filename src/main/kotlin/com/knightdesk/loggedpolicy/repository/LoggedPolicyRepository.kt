package com.knightdesk.loggedpolicy.repository

import com.knightdesk.loggedpolicy.model.LoggedPolicy
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LoggedPolicyRepository: MongoRepository<LoggedPolicy, String> {

    fun findByAgentEmail(email: String?): List<LoggedPolicy?>?

    fun findByPolicyNo(policyNo: String?): LoggedPolicy?

    override fun findAll(): MutableList<LoggedPolicy>

    fun findOneByPolicyNo(policyNo: String?): Optional<Boolean>
}