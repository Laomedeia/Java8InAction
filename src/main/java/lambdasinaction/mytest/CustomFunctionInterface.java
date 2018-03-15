package lambdasinaction.mytest;

@FunctionalInterface
public interface CustomFunctionInterface<T,U,R> {

    R apply(T t, U u);
}
