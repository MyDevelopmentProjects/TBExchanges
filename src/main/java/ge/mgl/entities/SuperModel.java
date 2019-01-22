package ge.mgl.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by MJaniko on 10/26/2016.
 */
@MappedSuperclass
public abstract class SuperModel {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreated", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    public Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateUpdated")
    public Date dateUpdated;

    @PrePersist
    void onCreate() {
        this.setDateCreated(new Timestamp((new Date()).getTime()));
    }

    @PreUpdate
    void onPersist() {
        this.setDateUpdated(new Timestamp((new Date()).getTime()));
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

}

