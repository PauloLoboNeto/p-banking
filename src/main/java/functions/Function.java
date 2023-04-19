package functions;

@FunctionalInterface
public interface Function<T,H, R> {
    public R apply(T parameter, H param2);
}

