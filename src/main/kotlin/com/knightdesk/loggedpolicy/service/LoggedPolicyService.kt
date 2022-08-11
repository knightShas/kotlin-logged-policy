package com.knightdesk.loggedpolicy.service

import com.knightdesk.loggedpolicy.model.LoggedPolicy
import java.util.Optional


interface LoggedPolicyService {

    fun addLoggedPolicy(policy: LoggedPolicy)

    fun findAllByAgentEmail(email: String?): List<LoggedPolicy?>?

    fun getPolicyDetail(policyNo: String?): LoggedPolicy?

    fun findAllLoggedPolicy(): List<LoggedPolicy?>?

    fun checkPolicyNo(policyNo: String?): Optional<Boolean>
}