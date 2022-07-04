package com.xm.models;

public class Recommendation {
    String crypto;
    Double normalizedRange;
    public String getCrypto() {
        return crypto;
    }

    public Recommendation setCrypto(String crypto) {
        this.crypto = crypto;
        return this;
    }

    public Double getNormalizedRange() {
        return normalizedRange;
    }

    public Recommendation setNormalizedRange(Double normalizedRange) {
        this.normalizedRange = normalizedRange;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Recommendation that = (Recommendation) o;

        if (getCrypto() != null ? !getCrypto().equals(that.getCrypto()) : that.getCrypto() != null) {
            return false;
        }
        return getNormalizedRange() != null ? getNormalizedRange().equals(that.getNormalizedRange())
                : that.getNormalizedRange() == null;
    }

    @Override
    public int hashCode() {
        int result = getCrypto() != null ? getCrypto().hashCode() : 0;
        result = 31 * result + (getNormalizedRange() != null ? getNormalizedRange().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recommendation{" + "crypto='" + crypto + '\'' + ", normalizedRange=" + normalizedRange + '}';
    }
}
