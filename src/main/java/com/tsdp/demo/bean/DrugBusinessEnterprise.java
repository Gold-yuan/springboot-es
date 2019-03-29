package com.tsdp.demo.bean;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "drugbusinessenterprise", type = "DrugBusinessEnterprise")
public class DrugBusinessEnterprise {
    private Long id;
    private String licenseNumber;
    private String enterpriseName;
    private String enterpriseNameEN;
    private String socialCreditCode;
    private String legalRepresentative;
    private String enterprisePersonCharge;
    private String qualityPersonCharge;
    private String registeredAddress;
    private String productionAddress;
    private String productionScope;
    private String issuingDate;
    private String expirationDate;
    private String issuingAuthority;
    private String issuingPersone;
    private String dailySupervisoryAuthority;
    private String dailySupervisore;
    private String estatus;
    private String classificationCode;
    private String province;
    private String reportingTelephone;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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

    @Override
    public String toString() {
        return "DrugBusinessEnterprise [id=" + id + ", licenseNumber=" + licenseNumber + ", enterpriseName="
                + enterpriseName + ", enterpriseNameEN=" + enterpriseNameEN + ", socialCreditCode=" + socialCreditCode
                + ", legalRepresentative=" + legalRepresentative + ", enterprisePersonCharge=" + enterprisePersonCharge
                + ", qualityPersonCharge=" + qualityPersonCharge + ", registeredAddress=" + registeredAddress
                + ", productionAddress=" + productionAddress + ", productionScope=" + productionScope + ", issuingDate="
                + issuingDate + ", expirationDate=" + expirationDate + ", issuingAuthority=" + issuingAuthority
                + ", issuingPersone=" + issuingPersone + ", dailySupervisoryAuthority=" + dailySupervisoryAuthority
                + ", dailySupervisore=" + dailySupervisore + ", estatus=" + estatus + ", classificationCode="
                + classificationCode + ", province=" + province + ", reportingTelephone=" + reportingTelephone
                + ", memo=" + memo + "]";
    }

}
