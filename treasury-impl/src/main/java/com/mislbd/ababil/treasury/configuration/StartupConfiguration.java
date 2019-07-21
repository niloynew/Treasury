package com.mislbd.ababil.treasury.configuration;

import com.mislbd.ababil.approvalflow.annotation.EnableAbabilApprovalflow;
import com.mislbd.ababil.asset.annotation.EnableAbabilAsset;
import com.mislbd.ababil.contacts.annotation.EnableAbabilContacts;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("AbabilTreasuryStartupConfiguration")
@ComponentScan("com.mislbd")
@EnableAbabilAsset
@EnableAbabilContacts
@EnableAbabilApprovalflow
public class StartupConfiguration {}
