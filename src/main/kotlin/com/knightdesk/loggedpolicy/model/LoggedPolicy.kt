package com.knightdesk.loggedpolicy.model

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="logged_policy")
class LoggedPolicy {

    var policyName: String? = ""

    @Indexed(unique = true)
    var policyNo: String? = ""

    var agentName: String? = ""

    var agentEmail: String? = ""

    var agentPanNo: String? = ""

    var customerName: String? = ""

    var customerEmail: String? = ""

    var customerPanNo: String? = ""

    var customerPhoneNo: Long = 0

    var insuranceAmount = 0.0

    var noOfYear = 0

    var rewardPoint = 0
}