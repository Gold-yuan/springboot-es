package com.tsdp.demo.bean;

/**
 * 2、药品经营企业
 * 
 **/

//indexName索引名称 可以理解为数据库名 必须为小写 不然会报org.elasticsearch.indices.InvalidIndexNameException异常
//type类型 可以理解为表名
public class DrugBusinessEnterprise {

    private Long id;

    private String licenseNumber;

    private String enterpriseName;

    private String enterpriseNameEN;

    private String socialCreditCode;

    private String legalRepresentative;

    private String enterprisePersonCharge;

    private String qualityPersonCharge;

    private String address;

    private String warehouseAddress;

    private String scope;

    private String modeOperation;

    private String issuingDate;

    private String expirationDate;

    private String issuingAuthority;

    private String status;

    private String GSPLicenseNumber;

    private String GSPIssuingDate;

    private String GSPexpirationDate;

    private String province;

    private String memo;

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getModeOperation() {
        return modeOperation;
    }

    public void setModeOperation(String modeOperation) {
        this.modeOperation = modeOperation;
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

    public String getGSPLicenseNumber() {
        return GSPLicenseNumber;
    }

    public void setGSPLicenseNumber(String gSPLicenseNumber) {
        GSPLicenseNumber = gSPLicenseNumber;
    }

    public String getGSPIssuingDate() {
        return GSPIssuingDate;
    }

    public void setGSPIssuingDate(String gSPIssuingDate) {
        GSPIssuingDate = gSPIssuingDate;
    }

    public String getGSPexpirationDate() {
        return GSPexpirationDate;
    }

    public void setGSPexpirationDate(String gSPexpirationDate) {
        GSPexpirationDate = gSPexpirationDate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public String toString() {
        return "DrugBusinessEnterprise [id=" + id + ", licenseNumber=" + licenseNumber + ", enterpriseName="
                + enterpriseName + ", enterpriseNameEN=" + enterpriseNameEN + ", socialCreditCode=" + socialCreditCode
                + ", legalRepresentative=" + legalRepresentative + ", enterprisePersonCharge=" + enterprisePersonCharge
                + ", qualityPersonCharge=" + qualityPersonCharge + ", address=" + address + ", warehouseAddress="
                + warehouseAddress + ", scope=" + scope + ", modeOperation=" + modeOperation + ", issuingDate="
                + issuingDate + ", expirationDate=" + expirationDate + ", issuingAuthority=" + issuingAuthority
                + ", status=" + status + ", GSPLicenseNumber=" + GSPLicenseNumber + ", GSPIssuingDate=" + GSPIssuingDate
                + ", GSPexpirationDate=" + GSPexpirationDate + ", province=" + province + ", memo=" + memo + " ]";
    }

}
