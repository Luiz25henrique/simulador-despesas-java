package br.com.listadegastos.compradeitens;

import java.util.Objects;

public class BoughtItem implements Comparable<BoughtItem> {

    private final String purchaceName;
    private final double purchaseValue;

    public BoughtItem(String purchaceName, double purchaseValue) {
        this.purchaceName = purchaceName;
        this.purchaseValue = purchaseValue;
    }

    //GETS
    public String getPurchaceName() {
        return purchaceName;
    }

    public double getPurchaseValue() {
        return purchaseValue;
    }

    @Override
    public int compareTo(BoughtItem other) {
        return Double.compare(this.purchaseValue, other.purchaseValue);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null) {
            return false;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        BoughtItem anotherPurchase = (BoughtItem) object;

        return Objects.equals(this.purchaceName, anotherPurchase.purchaceName) &&
                Double.compare(this.purchaseValue, anotherPurchase.purchaseValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaceName, purchaseValue);
    }

    public String toString() {
        return "Compra: " + purchaceName + " Valor: R$" + String.format("%.2f", purchaseValue);
    }
}
