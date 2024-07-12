package TokenBucketLLD;

import java.time.Duration;
import java.time.LocalTime;

public class TokenBucket {
    private int tokensAvailable;
    private LocalTime startTimeWindow;
    private int refillRate;
    private int period;
    private int capacity;

    public TokenBucket(int tokensAvailable, int refillRate, int period, int capacity) {
        this.tokensAvailable = tokensAvailable;
        this.startTimeWindow = LocalTime.now();
        this.refillRate = refillRate;
        this.period = period;
        this.capacity = capacity;
    }

    public TokenBucket (TokenBucketBuilder builder) {
        this.tokensAvailable = builder.tokensAvailable == null ? 0 : builder.tokensAvailable;
        this.capacity = builder.capacity == null ? 1 : builder.capacity;
        this.refillRate = builder.refillRate == null ? 1 : builder.refillRate;
        this.period = builder.period == null ? 1 : builder.period;
        this.startTimeWindow = LocalTime.now();
    }

    public static TokenBucketBuilder builder () {
        return new TokenBucketBuilder();
    }

    public int getTokensAvailable() {
        return tokensAvailable;
    }

    public boolean isAllowed (LocalTime currentTime) {
        if (Duration.between(startTimeWindow, currentTime).toSeconds() > period) {
            refillBucket(currentTime);
        }
        if (tokensAvailable > 0) {
            tokensAvailable --;
            return true;
        }
        return false;
    }

    private void refillBucket(LocalTime currentTime) {
        tokensAvailable = Math.min(tokensAvailable + refillRate, capacity);
        long windowTimePassed = Duration.between(startTimeWindow, currentTime).toSeconds();
        long secondsToBeAdded = (windowTimePassed / period ) * period;
        startTimeWindow = startTimeWindow.plusSeconds(secondsToBeAdded);
    }

    public static class TokenBucketBuilder {
        private Integer tokensAvailable = null;
        private Integer refillRate = null;
        private Integer period = null;
        private Integer capacity = null;

        public TokenBucketBuilder() {}

        public TokenBucketBuilder tokensAvailable (int tokensAvailable) {
            this.tokensAvailable = tokensAvailable;
            return this;
        }

        public TokenBucketBuilder refillRate (int refillRate) {
            this.refillRate = refillRate;
            return this;
        }

        public TokenBucketBuilder period (int period) {
            this.period = period;
            return this;
        }

        public TokenBucketBuilder capacity (int capacity) {
            this.capacity = capacity;
            return this;
        }

        public TokenBucket build () {
            return new TokenBucket(this);
        }

    }
}
