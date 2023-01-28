package com.yapr.dataservice.javaflavor.model;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;


public class AbstractDoc {
    @Id
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
            return false;
        }

        AbstractDoc that = (AbstractDoc) obj;

        return this.id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

}

