package ge.mgl.entities;

import javax.persistence.*;

@Entity
@Table(name = "t_ccy_exchange")
public class TCCYExchange extends SuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private FUser user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private TCustomer customer;

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

    @Column(name = "is_manual", nullable = false, columnDefinition = " BOOLEAN DEFAULT FALSE ")
    private boolean manual = false;

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

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }
}
