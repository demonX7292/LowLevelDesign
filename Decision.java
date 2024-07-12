package TokenBucketLLD;

public enum Decision {
    ALLOWED("Allowed"),
    NOT_ALLOWED("Not Allowed");

    public String value;

    Decision(String str) {
        this.value = str;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}