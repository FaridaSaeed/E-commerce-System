package policy;

import java.util.Date;

public class Expiration implements ExpirationPolicy {
    private final Date expiryDate;

    public Expiration(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return expiryDate.before(new Date());
    }
}
