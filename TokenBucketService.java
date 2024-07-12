package TokenBucketLLD;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TokenBucketService {
    private int period; // in seconds
    private int refillRate;
    private int capacity;
    private Map <String, TokenBucket> map;

    public TokenBucketService(int period, int refillRate, int capacity) {
        this.period = period;
        this.refillRate = refillRate;
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public String isRequestAllowed (String userId) {
        TokenBucket tokenBucket = getTokenBucket(userId);
        Decision decision = tokenBucket.isAllowed(LocalTime.now()) ? Decision.ALLOWED : Decision.NOT_ALLOWED;
        for (Map.Entry<String, TokenBucket> entry : map.entrySet()) {
            System.out.print("( "  + entry.getKey() + " : " + entry.getValue().getTokensAvailable() + " ) | ");
        }
        System.out.println();
        return decision.toString();
    }

    private TokenBucket getTokenBucket (String userId) {
        if (map.get(userId) == null) {
            map.put(userId, TokenBucket.builder().period(period).tokensAvailable(2).capacity(capacity).refillRate(refillRate).build());
        }
        return map.get(userId);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TokenBucketService service = new TokenBucketService(10, 3, 6);
        int id = 1;
        while (id != -1) {
            System.out.println(LocalTime.now() + "\t" + "Enter userid");
            id = in.nextInt();
            if (id != -1) {
                System.out.println(service.isRequestAllowed(String.valueOf(id)));
            }
        }
    }
}
