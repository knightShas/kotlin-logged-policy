package com.knightdesk.loggedpolicy.controller

import com.knightdesk.loggedpolicy.exception.EmptyListException
import com.knightdesk.loggedpolicy.model.LoggedPolicy
import com.knightdesk.loggedpolicy.service.LoggedPolicyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Optional


@RestController
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RequestMapping("/jdbank")
class LoggedPolicyController(val loggedPolicyService: LoggedPolicyService) {

    @PostMapping("/policy")
    fun addNewLoggedPolicy(@RequestBody policy: LoggedPolicy): ResponseEntity<String> {
            val otp: Optional<Boolean> = loggedPolicyService.checkPolicyNo(policy.policyNo)
        return if (otp.isPresent) {
            ResponseEntity("Error", HttpStatus.OK)
            }
        else {
                if (policy.insuranceAmount.toInt() >= 100000 && policy.insuranceAmount.toInt() < 300000) {
                    policy.rewardPoint = 10
                } else if (policy.insuranceAmount.toInt() >= 300000 && policy.insuranceAmount.toInt() < 800000) {
                    policy.rewardPoint = 20
                } else if (policy.insuranceAmount.toInt() >= 800000 && policy.insuranceAmount.toInt() < 1000000) {
                    policy.rewardPoint = 50
                } else if (policy.insuranceAmount.toInt() >= 1000000) {
                    policy.rewardPoint = 90
                } else {
                    policy.rewardPoint = 0
                }
                loggedPolicyService.addLoggedPolicy(policy)
                ResponseEntity("Logged Policy Detail Inserted! Success", HttpStatus.OK)
            }
    }

    @GetMapping("/policy_detail/{email}")
    fun getAllLoggedPolicy(@PathVariable email: String?): List<LoggedPolicy?>? {
        val loggedPolicy = loggedPolicyService.findAllByAgentEmail(email)
        val newList = ArrayList<LoggedPolicy?>(loggedPolicy!!.size)
        return if (loggedPolicy.isEmpty()) {
            throw EmptyListException()
        } else {
            for (i in loggedPolicy.indices.reversed()) {
                newList.add(loggedPolicy[i])
            }
            newList
        }
    }

    @GetMapping("/policy/detail/{policyNo}")
    fun getPolicyDetail(@PathVariable policyNo: String?): LoggedPolicy? {
        return loggedPolicyService.getPolicyDetail(policyNo)
    }

    @GetMapping("/reward/{email}")
    fun getReward(@PathVariable email: String?): Int {
        val loggedPolicy = loggedPolicyService.findAllByAgentEmail(email)
        return if (loggedPolicy!!.isEmpty()) {
            0
        } else {
            var total = 0
            for (log in loggedPolicy) {
                total += log!!.rewardPoint
            }
            total
        }
    }

    @GetMapping("/top")
    fun getTopReward(): String? {
        val loggedPolicyList = loggedPolicyService.findAllLoggedPolicy()
        var largeRewardPoint = 0
        var email = ""
        if (loggedPolicyList != null) {
            for (i in loggedPolicyList.indices.reversed()) {
                val loggedPolicy = loggedPolicyList?.get(i)
                val rewardPoint = loggedPolicy?.rewardPoint
                if (largeRewardPoint < rewardPoint!!) {
                    largeRewardPoint = rewardPoint
                    if (loggedPolicy != null) {
                        email = loggedPolicy.agentEmail.toString()
                    }
                }
            }
        }
        return email
    }
}