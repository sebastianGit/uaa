package org.cloudfoundry.identity.uaa.authentication.event;

import org.cloudfoundry.identity.uaa.audit.AuditEvent;
import org.cloudfoundry.identity.uaa.audit.AuditEventType;
import org.cloudfoundry.identity.uaa.user.UaaUser;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

public class PasswordVerificationSuccessEvent extends AbstractUaaAuthenticationEvent {
    private final UaaUser user;

    public PasswordVerificationSuccessEvent(UaaUser user, Authentication authentication) {
        super(authentication);
        this.user = user;
    }

    @Override
    public AuditEvent getAuditEvent() {
        Assert.notNull(user, "UaaUser cannot be null");
        return createAuditRecord(user.getId(), AuditEventType.PasswordAuthenticationSuccess,
                getOrigin(getAuthenticationDetails()), user.getUsername());
    }

    public UaaUser getUser() {
        return user;
    }
}
