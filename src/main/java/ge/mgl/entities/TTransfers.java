package ge.mgl.entities;

import javax.persistence.*;

@Entity
@Table(name = "t_ccy_transfers")
public class TTransfers extends SuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private FUser user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private TCustomer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private TCompany company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_ccy_id")
    private TCCY ccyFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_ccy_id")
    private TCCY ccyTo;

    @Column(name = "from_rate")
    private Double fromRate;

    @Column(name = "to_rate")
    private Double toRate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "file_url")
    private String fileUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FUser getUser() {
        return user;
    }

    public void setUser(FUser user) {
        this.user = user;
    }

    public TCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(TCustomer customer) {
        this.customer = customer;
    }

    public TCompany getCompany() {
        return company;
    }

    public void setCompany(TCompany company) {
        this.company = company;
    }

    public TCCY getCcyFrom() {
        return ccyFrom;
    }

    public void setCcyFrom(TCCY ccyFrom) {
        this.ccyFrom = ccyFrom;
    }

    public TCCY getCcyTo() {
        return ccyTo;
    }

    public void setCcyTo(TCCY ccyTo) {
        this.ccyTo = ccyTo;
    }

    public Double getFromRate() {
        return fromRate;
    }

    public void setFromRate(Double fromRate) {
        this.fromRate = fromRate;
    }

    public Double getToRate() {
        return toRate;
    }

    public void setToRate(Double toRate) {
        this.toRate = toRate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
