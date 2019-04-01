package com.tsdp.demo.bean;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "foodbusinessenterprise", type = "FoodBusinessEnterprise")
//indexName索引名称 可以理解为数据库名 必须为小写 不然会报org.elasticsearch.indices.InvalidIndexNameException异常
//type类型 可以理解为表名
public class FoodBusinessEnterprise {
    private Long id;
    
    private String licenseNumber;
    private String enterpriseName;
    private String enterpriseNameEN;
    private String socialCreditCode;
    private String legalRepresentative;
    private String enterpriseResidence;
    private String businessAddress;
    private String mainBusinessFormat;
    private String businessScope;
    private String dailySupervisoryAuthority;
    private String dailySuperviso;
    private String issuingPerson;
    private String issuingDate;
    private String expirationDate;
    private String issuingAuthority;
    private String status;
    private String reportingTelephone;
    @Field(type=FieldType.Keyword)
    private String province;
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

    public String getEnterpriseResidence() {
        return enterpriseResidence;
    }

    public void setEnterpriseResidence(String enterpriseResidence) {
        this.enterpriseResidence = enterpriseResidence;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getMainBusinessFormat() {
        return mainBusinessFormat;
    }

    public void setMainBusinessFormat(String mainBusinessFormat) {
        this.mainBusinessFormat = mainBusinessFormat;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getDailySupervisoryAuthority() {
        return dailySupervisoryAuthority;
    }

    public void setDailySupervisoryAuthority(String dailySupervisoryAuthority) {
        this.dailySupervisoryAuthority = dailySupervisoryAuthority;
    }

    public String getDailySuperviso() {
        return dailySuperviso;
    }

    public void setDailySuperviso(String dailySuperviso) {
        this.dailySuperviso = dailySuperviso;
    }

    public String getIssuingPerson() {
        return issuingPerson;
    }

    public void setIssuingPerson(String issuingPerson) {
        this.issuingPerson = issuingPerson;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReportingTelephone() {
        return reportingTelephone;
    }

    public void setReportingTelephone(String reportingTelephone) {
        this.reportingTelephone = reportingTelephone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    @Override
    public String toString() {
        return "FoodBusinessEnterprise [id=" + id + ", licenseNumber=" + licenseNumber + ", enterpriseName="
                + enterpriseName + ", enterpriseNameEN=" + enterpriseNameEN + ", socialCreditCode=" + socialCreditCode
                + ", legalRepresentative=" + legalRepresentative + ", enterpriseResidence=" + enterpriseResidence
                + ", businessAddress=" + businessAddress + ", mainBusinessFormat=" + mainBusinessFormat
                + ", businessScope=" + businessScope + ", dailySupervisoryAuthority=" + dailySupervisoryAuthority
                + ", dailySuperviso=" + dailySuperviso + ", issuingPerson=" + issuingPerson + ", issuingDate="
                + issuingDate + ", expirationDate=" + expirationDate + ", issuingAuthority=" + issuingAuthority
                + ", status=" + status + ", reportingTelephone=" + reportingTelephone + ", province=" + province
                + ", memo=" + memo + "]";
    }

}
