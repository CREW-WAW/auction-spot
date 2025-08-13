package com.spot.user.repository;

import com.spot.user.model.User;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.spot.auction.common.generated.Tables.TB_USER;

@Repository
public class UserRepository {

    private final DSLContext dslContext;

    public UserRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Optional<User> findById(Long id) {
        return dslContext.selectFrom(TB_USER)
                .where(TB_USER.SEQ.eq(id))
                .fetchOptionalInto(User.class);
    }

    public Optional<User> findByEmail(String email) {
        return dslContext.selectFrom(TB_USER)
                .where(TB_USER.EMAIL.eq(email))
                .fetchOptionalInto(User.class);
    }

    public boolean existsByEmail(String email) {
        return dslContext.fetchExists(
                dslContext.selectFrom(TB_USER)
                        .where(TB_USER.EMAIL.eq(email))
        );
    }

    public User save(User user) {
        if (user.getSeq() == null) {
            // Insert
            return dslContext.insertInto(TB_USER)
                    .set(TB_USER.EMAIL, user.getEmail())
                    .set(TB_USER.PASSWORD, user.getPassword())
                    .set(TB_USER.NICKNAME, user.getNickname())
                    .set(TB_USER.ADDRESS, user.getAddress())
                    .set(TB_USER.IS_VERIFIED, user.getIsVerified())
                    .set(TB_USER.AGE, user.getAge())
                    .set(TB_USER.NAME, user.getName())
                    .set(TB_USER.ROLE, user.getRole())
                    .returning()
                    .fetchOneInto(User.class);
        } else {
            // Update
            dslContext.update(TB_USER)
                    .set(TB_USER.NICKNAME, user.getNickname())
                    .set(TB_USER.ADDRESS, user.getAddress())
                    .set(TB_USER.AGE, user.getAge())
                    .set(TB_USER.NAME, user.getName())
                    .where(TB_USER.SEQ.eq(user.getSeq()))
                    .execute();
            return user;
        }
    }

    public void deleteById(Long id) {
        dslContext.deleteFrom(TB_USER)
                .where(TB_USER.SEQ.eq(id))
                .execute();
    }
}
