package com.spot.location.repository;

import com.spot.location.model.Location;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.spot.auction.common.generated.Tables.TB_LOCATION;

@Repository
public class LocationRepository {

    private final DSLContext dslContext;

    public LocationRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Location save(Location location) {
        if (location.getSeq() == null) {
            // Insert
            return dslContext.insertInto(TB_LOCATION)
                    .set(TB_LOCATION.SIDO, location.getSido())
                    .set(TB_LOCATION.SIGUNGU, location.getSigungu())
                    .set(TB_LOCATION.EUPMYEONDONG, location.getEupmyeondong())
                    .set(TB_LOCATION.ZIPCODE, location.getZipcode())
                    .set(TB_LOCATION.LAT, location.getLat())
                    .set(TB_LOCATION.LNG, location.getLng())
                    .returning()
                    .fetchOneInto(Location.class);
        } else {
            // Update
            dslContext.update(TB_LOCATION)
                    .set(TB_LOCATION.SIDO, location.getSido())
                    .set(TB_LOCATION.SIGUNGU, location.getSigungu())
                    .set(TB_LOCATION.EUPMYEONDONG, location.getEupmyeondong())
                    .set(TB_LOCATION.ZIPCODE, location.getZipcode())
                    .set(TB_LOCATION.LAT, location.getLat())
                    .set(TB_LOCATION.LNG, location.getLng())
                    .where(TB_LOCATION.SEQ.eq(location.getSeq()))
                    .execute();
            return location;
        }
    }

    public List<Location> findNearbyLocations(Long userId) {
        // 사용자의 인증된 위치 기반으로 주변 위치 조회
        // 실제 구현에서는 좌표 기반 거리 계산이 필요
        return dslContext.selectFrom(TB_LOCATION)
                .orderBy(TB_LOCATION.CREATED_AT.desc())
                .limit(10)
                .fetchInto(Location.class);
    }
}
