import javax.crypto.spec.PSource;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {


        System.out.println(((IntBinaryOperator) Integer::sum).applyAsInt(5, 10));

        BiFunction<Integer, Integer, Integer> biFunction = Integer::sum;
        Integer result = biFunction.apply(10, 20);
        System.out.println(result);

        BiFunction<Double, Double, String> biFunctionString = (Double a, Double b) -> {
            Double resultString = a + b;
            return String.valueOf(resultString);
        };
        Integer resultString = biFunction.apply(10, 20);
        System.out.println(resultString);

        Long numberLong = calcular(1L, 2L, (Long v1, Long v2) -> {
            return v1 + v2;
        });

        System.out.println(numberLong);

//        Runnable lambda = () -> System.out.println("Executando runnable!");
//        new Thread(lambda).start();

        new Thread(() -> System.out.println("Executando runnable diretamente na thread!")).start();

        MyEventConsumer myEventConsumer = new MyEventConsumer() {
            @Override
            public void consumer(Object value) {
                System.out.println(value);
            }
        };

        myEventConsumer.consumer("Teste consumer!");

        receberInterface(
                (Object value) -> System.out.println(value)
        );

        receberInterface(
                System.out::println
        );

        receberInterfaceB(
                "Value", System.out::println
        );

        receberInterfaceB(
                Boolean.TRUE, System.out::println
        );


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        realizaOperacao(list, (n) -> n * 5);

        avaliarExpressao(list, (n) -> n % 2 == 0);

        avaliarExpressao(list, (n) -> n > 5);

        OwnEventConsumer ownEventConsumer = (Object s) -> {
            System.out.println(s);
        };

        OwnEventConsumer ownEventConsumerB = (s) -> {
            System.out.println(s);
        };

        OwnEventConsumer ownEventConsumerC = (s) -> System.out.println(s);

        OwnEventConsumer ownEventConsumerD = s -> System.out.println(s);

        OwnEventConsumer ownEventConsumerE = System.out::println;

        MyEventConsumerReturn myEventConsumerReturn = s -> {
            System.out.println(s);
            return s;
        };

        MyEventConsumerReturn<String> myEventConsumerReturnString = s -> {
            System.out.println(s);
            return String.valueOf(s);
        };

        MyEventConsumerReturn<Integer> myEventConsumerReturnInteger = s -> {
            System.out.println(s);
            return Integer.valueOf((String) s);
        };

        Factory factory = String::new;


        String resultStr = factory.create("teste");

        System.out.println(resultStr);

    }

    private static Long calcular(Long t, Long u, BiFunction<Long, Long, Long> function) {
        return function.apply(t, u);
    }

    private static void receberInterface(MyEventConsumer myEventConsumer) {
        myEventConsumer.consumer("Hello!!!");
    }

    private static void receberInterfaceB(Object o, MyEventConsumer myEventConsumer) {
        myEventConsumer.consumer("Hello!!!");
    }


    public static void realizaOperacao(List<Integer> list, IntFunction<Integer> function) {
        list.forEach(
                n -> {
                    n = function.apply(n);
                    System.out.println(n + "");
                }
        );
    }

    public static void avaliarExpressao(List<Integer> list, Predicate<Integer> predicate) {
        list.forEach(n -> {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        });
    }


}