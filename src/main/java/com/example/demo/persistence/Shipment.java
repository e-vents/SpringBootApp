package com.example.demo.persistence;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Shipment {

    @Id
    @GeneratedValue
    private long shipmentId;
    private String trackingNumber;
    private String provider;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sentOn;

    public long getShipmentId() {
        return shipmentId;
    }
    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }
    public String getTrackingNumber() {
        return trackingNumber;
    }
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }
    public Date getSentOn() {
        return sentOn;
    }
    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }
}
