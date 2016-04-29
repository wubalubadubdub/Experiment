/**
 * Created by bearg on 4/28/2016.
 */
public class Trade {

    private String mStatus;
    private int mQuantity;
    private String mIssuer;

    public Trade(final String mStatus, final int mQuantity, final String mIssuer) { // set these once, in the ctor
        this.mStatus = mStatus;
        this.mQuantity = mQuantity;
        this.mIssuer = mIssuer;
    }

    public String getStatus() {
        return mStatus;
    }


    public int getQuantity() {
        return mQuantity;
    }

    public String getIssuer() {
        return mIssuer;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "Status: " + mStatus +
                ", Quantity: " + mQuantity +
                ", Issuer: "  + mIssuer +
                "}";
    }
}
