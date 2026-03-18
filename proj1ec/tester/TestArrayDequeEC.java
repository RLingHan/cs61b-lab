package tester;
import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import java.util.Objects;


public class TestArrayDequeEC {

    @Test
    public void TestArray(){
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();
        StringBuilder ops = new StringBuilder();

        for (int i = 0; i < 20; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                sad2.addLast(i);
                ops.append("addLast(" + i + ")\n");
            } else {
                sad1.addFirst(i);
                sad2.addFirst(i);
                ops.append("addFirst(" + i + ")\n");
            }
        }
        for (int i = 0; i < 20; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                Integer a = sad1.removeFirst();
                Integer b = sad2.removeFirst();
                ops.append("removeFirst()\n");
                if (!Objects.equals(a,b));{
                    assertEquals(ops.toString(),a,b);
                }
            } else {
                Integer a = sad1.removeLast();
                Integer b = sad2.removeLast();
                ops.append("removeLast()\n");
                if (!Objects.equals(a,b));{
                    assertEquals(ops.toString(),a,b);
                }
            }
        }

    }
}
