package annotation01.test03;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCaseTracker {

    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) {
            UseCase useCase = m.getAnnotation(UseCase.class);
            if (useCase != null) {
                System.out.println("Found Use Case:" + useCase.id());
                useCases.remove(new Integer(useCase.id()));
            }
        }
        for (int i : useCases) {
            System.out.println("warning:Missing use case"+i);
        }
    }

    public static void main(String[] args) {
        List<Integer> usecases = new ArrayList<>();
        Collections.addAll(usecases, 47, 48, 49, 50);
        trackUseCases(usecases, PasswordUtil.class);
    }

}
