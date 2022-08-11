package com.knightdesk.loggedpolicy.service

import com.knightdesk.loggedpolicy.model.LoggedPolicy
import com.knightdesk.loggedpolicy.repository.LoggedPolicyRepository
import org.springframework.stereotype.Service
import java.util.Optional
import javax.swing.text.html.Option

@Service
class LoggedPolicyServiceImpl(val loggedPolicyRepository: LoggedPolicyRepository): LoggedPolicyService {

    override fun addLoggedPolicy(loggedPolicy: LoggedPolicy) {
        loggedPolicyRepository.save(loggedPolicy)
    }

    override fun findAllByAgentEmail(email: String?): List<LoggedPolicy?>? {
        return loggedPolicyRepository.findByAgentEmail(email)
    }

    override fun getPolicyDetail(policyNo: String?): LoggedPolicy? {
        return loggedPolicyRepository.findByPolicyNo(policyNo)
    }

    override fun findAllLoggedPolicy(): List<LoggedPolicy?>? {
        return loggedPolicyRepository.findAll()
    }

    override fun checkPolicyNo(policyNo: String?): Optional<Boolean> {
        return loggedPolicyRepository.findOneByPolicyNo(policyNo)
    }
}

