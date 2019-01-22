package ge.mgl.entities;

import javax.persistence.*;

@Entity
@Table(name = "t_ccy")
public class TCCY extends SuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "buy_rate")
    private Double buyRate;

    @Column(name = "sell_rate")
    private Double sellRate;

    @Column(name = "title")
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(Double buyRate) {
        this.buyRate = buyRate;
    }

    public Double getSellRate() {
        return sellRate;
    }

    public void setSellRate(Double sellRate) {
        this.sellRate = sellRate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
