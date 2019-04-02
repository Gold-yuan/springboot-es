package com.tsdp.demo.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "drugbusinessenterprise", type = "DrugBusinessEnterprise")
public class DrugBusinessEnterprise {
    @Id
    private Long id;
    @Field(type=FieldType.Keyword)
    private String licenseNumber;
    @Field(type=FieldType.Text,searchAnalyzer="ik_smart",analyzer="ik_max_word")
    private String enterpriseName;
    @Field(type=FieldType.Text,searchAnalyzer="ik_smart",analyzer="ik_max_word")
    private String enterpriseNameEN;
    @Field(type=FieldType.Keyword)
    private String socialCreditCode;
    @Field(type=FieldType.Text,searchAnalyzer="ik_smart",analyzer="ik_max_word")
    private String legalRepresentative;
    @Field(type=FieldType.Text,searchAnalyzer="ik_smart",analyzer="ik_max_word")
    private String enterprisePersonCharge;
    @Field(type=FieldType.Text,searchAnalyzer="ik_smart",analyzer="ik_max_word")
    private String qualityPersonCharge;
    @Field(type=FieldType.Text,searchAnalyzer="ik_smart",analyzer="ik_max_word")
    private String registeredAddress;
    @Field(type=FieldType.Text,searchAnalyzer="ik_smart",analyzer="ik_max_word")
    private String productionAddress;
    @Field(type=FieldType.Text,searchAnalyzer="ik_smart",analyzer="ik_max_word")
    private String productionScope;
    @Field(type=FieldType.Keyword)
    private String issuingDate;
    @Field(type=FieldType.Keyword)
    private String expirationDate;
    @Field(type=FieldType.Text,searchAnalyzer="ik_smart",analyzer="ik_max_word")
    private String issuingAuthority;
    @Field(type=FieldType.Text,searchAnalyzer="ik_smart",analyzer="ik_max_word")
    private String issuingPersone;
    @Field(type=FieldType.Text,searchAnalyzer="ik_smart",analyzer="ik_max_word")
    private String dailySupervisoryAuthority;
    @Field(type=FieldType.Text,searchAnalyzer="ik_smart",analyzer="ik_max_word")
    private String dailySupervisore;
    @Field(type=FieldType.Keyword)
    private String classificationCode;
    @Field(type=FieldType.Keyword)
    private String province;
    @Field(type=FieldType.Keyword)
    private String status;
    @Field(type=FieldType.Keyword)
    private String reportingTelephone;
    @Field(type=FieldType.Text,searchAnalyzer="ik_smart",analyzer="ik_max_word")
    private String memo;

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseNameEN() {
        return enterpriseNameEN;
    }

    public void setEnterpriseNameEN(String enterpriseNameEN) {
        this.enterpriseNameEN = enterpriseNameEN;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getEnterprisePersonCharge() {
        return enterprisePersonCharge;
    }

    public void setEnterprisePersonCharge(String enterprisePersonCharge) {
        this.enterprisePersonCharge = enterprisePersonCharge;
    }

    public String getQualityPersonCharge() {
        return qualityPersonCharge;
    }

    public void setQualityPersonCharge(String qualityPersonCharge) {
        this.qualityPersonCharge = qualityPersonCharge;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getProductionAddress() {
        return productionAddress;
    }

    public void setProductionAddress(String productionAddress) {
        this.productionAddress = productionAddress;
    }

    public String getProductionScope() {
        return productionScope;
    }

    public void setProductionScope(String productionScope) {
        this.productionScope = productionScope;
    }

    public String getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(String issuingDate) {
        this.issuingDate = issuingDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public String getIssuingPersone() {
        return issuingPersone;
    }

    public void setIssuingPersone(String issuingPersone) {
        this.issuingPersone = issuingPersone;
    }

    public String getDailySupervisoryAuthority() {
        return dailySupervisoryAuthority;
    }

    public void setDailySupervisoryAuthority(String dailySupervisoryAuthority) {
        this.dailySupervisoryAuthority = dailySupervisoryAuthority;
    }

    public String getDailySupervisore() {
        return dailySupervisore;
    }

    public void setDailySupervisore(String dailySupervisore) {
        this.dailySupervisore = dailySupervisore;
    }

    public String getClassificationCode() {
        return classificationCode;
    }

    public void setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getReportingTelephone() {
        return reportingTelephone;
    }

    public void setReportingTelephone(String reportingTelephone) {
        this.reportingTelephone = reportingTelephone;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DrugBusinessEnterprise [id=" + id + ", licenseNumber=" + licenseNumber + ", enterpriseName="
                + enterpriseName + ", enterpriseNameEN=" + enterpriseNameEN + ", socialCreditCode=" + socialCreditCode
                + ", legalRepresentative=" + legalRepresentative + ", enterprisePersonCharge=" + enterprisePersonCharge
                + ", qualityPersonCharge=" + qualityPersonCharge + ", registeredAddress=" + registeredAddress
                + ", productionAddress=" + productionAddress + ", productionScope=" + productionScope + ", issuingDate="
                + issuingDate + ", expirationDate=" + expirationDate + ", issuingAuthority=" + issuingAuthority
                + ", issuingPersone=" + issuingPersone + ", dailySupervisoryAuthority=" + dailySupervisoryAuthority
                + ", dailySupervisore=" + dailySupervisore + ", classificationCode=" + classificationCode
                + ", province=" + province + ", status=" + status + ", reportingTelephone=" + reportingTelephone
                + ", memo=" + memo + "]";
    }

}
