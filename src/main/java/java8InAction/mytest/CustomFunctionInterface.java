package java8InAction.mytest;

@FunctionalInterface
public interface CustomFunctionInterface<T,U,R> {

    R apply(T t, U u);
}
