package Utils;

public class Types {
    public static class Result<K, V> {
        private K first;
        private V second;

        public Result(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public K getFirst() {return first; }
        public V getSecond() { return second; }
    }

    public enum Course {
        OOP,
        DEB,
        SU,
        BEEP,
        BOP
    }

}
