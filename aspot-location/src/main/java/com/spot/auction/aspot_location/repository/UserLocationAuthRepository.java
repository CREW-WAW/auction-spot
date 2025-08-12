package com.spot.auction.aspot_location.repository;

import com.spot.auction.aspot_location.model.UserLocationAuth;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static com.spot.auction.aspot_location.generated.Tables.TB_USER_LOCATION_AUTH;

@Repository
public class UserLocationAuthRepository {

    private final DSLContext dslContext;

    public UserLocationAuthRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public UserLocationAuth save(UserLocationAuth auth) {
        if (auth.getSeq() == null) {
            // Insert
            return dslContext.insertInto(TB_USER_LOCATION_AUTH)
                    .set(TB_USER_LOCATION_AUTH.USER_SEQ, auth.getUserSeq())
                    .set(TB_USER_LOCATION_AUTH.LOCATION_SEQ, auth.getLocationSeq())
                    .set(TB_USER_LOCATION_AUTH.AUTH_STATUS, auth.getAuthStatus().name())
                    .set(TB_USER_LOCATION_AUTH.REQUESTED_AT, auth.getRequestedAt())
                    .set(TB_USER_LOCATION_AUTH.APPROVED_AT, auth.getApprovedAt())
                    .set(TB_USER_LOCATION_AUTH.EXPIRED_AT, auth.getExpiredAt())
                    .set(TB_USER_LOCATION_AUTH.IS_ACTIVE, auth.getIsActive())
                    .returning()
                    .fetchOneInto(UserLocationAuth.class);
        } else {
            // Update
            dslContext.update(TB_USER_LOCATION_AUTH)
                    .set(TB_USER_LOCATION_AUTH.AUTH_STATUS, auth.getAuthStatus().name())
                    .set(TB_USER_LOCATION_AUTH.APPROVED_AT, auth.getApprovedAt())
                    .set(TB_USER_LOCATION_AUTH.EXPIRED_AT, auth.getExpiredAt())
                    .set(TB_USER_LOCATION_AUTH.IS_ACTIVE, auth.getIsActive())
                    .where(TB_USER_LOCATION_AUTH.SEQ.eq(auth.getSeq()))
                    .execute();
            return auth;
        }
    }
}
