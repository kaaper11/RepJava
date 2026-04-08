package zl10;

import lombok.AllArgsConstructor;

import java.util.Optional;

public class DiscountTask {

    public static void main(String[] args) {
        User user1 = new User(
                new Subscription(true, "  sub15 "),
                null,
                200
        );

        User user2 = new User(
                new Subscription(true, "   "),
                new ReferralProgram(true, " ref20 "),
                300
        );

        User user3 = new User(
                null,
                null,
                1500
        );

        User user4 = new User(
                new Subscription(false, "sub50"),
                new ReferralProgram(true, "   "),
                100
        );

        User user5 = null;

        System.out.println(resolveDiscountCode(user1)); // SUB15
        System.out.println(resolveDiscountCode(user2)); // REF20
        System.out.println(resolveDiscountCode(user3)); // LOYAL20
        System.out.println(resolveDiscountCode(user4)); // DEFAULT10
        System.out.println(resolveDiscountCode(user5)); // DEFAULT10
    }

    // LEGACY CODE DO PRZEPISANIA
    public static String resolveDiscountCode(User user) {
        Optional<User> userOpt = Optional.ofNullable(user);
        return userOpt
                .flatMap(User::getSubscription)
                        .filter(Subscription::isActive)
                        .flatMap(Subscription::getDiscountCode)
                        .flatMap(DiscountTask::normalizeCode)
                .or(() -> userOpt
                        .flatMap(User::getReferralProgram)
                        .filter(ReferralProgram::isEnabled)
                        .flatMap(ReferralProgram::getReferralCode)
                        .flatMap(DiscountTask::normalizeCode))
                .or(() -> userOpt
                        .flatMap(User::getLoyaltyPoints)
                        .filter(user1 -> user1 >= 1000)
                        .map(user1 -> "LOYAL20")
                )
                .orElse("DEFAULT10");
    }

    private static Optional<String> normalizeCode(String code) {
        return Optional.ofNullable(code)
                .map(String::trim)
                .filter(value -> !value.isBlank())
                .map(String::toUpperCase);
    }

    @AllArgsConstructor
    static class User {
        private final Subscription subscription;
        private final ReferralProgram referralProgram;
        private final int loyaltyPoints;

        public Optional<Subscription> getSubscription() {
            return Optional.ofNullable(subscription);
        }

        public Optional<ReferralProgram> getReferralProgram() {
            return Optional.ofNullable(referralProgram);
        }

        public Optional<Integer> getLoyaltyPoints() {
            return Optional.ofNullable(loyaltyPoints);
        }
    }

    @AllArgsConstructor
    static class Subscription {
        private final boolean active;
        private final String discountCode;

        public boolean isActive() {
            return active;
        }

        public Optional<String> getDiscountCode() {
            return Optional.ofNullable(discountCode);
        }
    }

    @AllArgsConstructor
    static class ReferralProgram {
        private final boolean enabled;
        private final String referralCode;

        public boolean isEnabled() {
            return enabled;
        }

        public Optional<String> getReferralCode() {
            return Optional.ofNullable(referralCode);
        }
    }
}