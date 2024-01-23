package pe.edu.utp.features.seqco;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Lang {

    public static List<String> buildList(){
        List<String> seq = new ArrayList<>();
        seq.add("Java");
        seq.add("C++");
        seq.add("Python");
        seq.add("Go");
        return seq;
    }

    public static Deque<String> buildDeque(){
        Deque<String> seq = new ArrayDeque<>();
        seq.offer("Java");
        seq.offer("C++");
        seq.offer("Python");
        seq.offer("Go");
        return seq;
    }

    private static SortedSet<String> buildSortedSet() {
        SortedSet<String> seq = new TreeSet<>();
        seq.add("Java");
        seq.add("C++");
        seq.add("Python");
        seq.add("Go");
        return seq;
    }

    public static String getSequenceIter(SequencedCollection seq){
        return (String) seq.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    public static String getSequenceReverse(SequencedCollection seq){
        return (String) seq.reversed().stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    public static void showElements(List<String> studentList,
                                    Deque<String> studentDeque,
                                    SortedSet<String> studentSortedSet){

              //          1         2         3         4         5         6         7         8
              //012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789
        String out = """
                #########################################################################################
                SEQUENCED COLLECTIONS
                #########################################################################################
                INTERFACE    FIRST          LAST           ITER                  REVERSE
                #########################################################################################
                LIST         %-10.10s     %-10.10s     %-20.20s  %-20.20s
                DEQUE        %-10.10s     %-10.10s     %-20.20s  %-20.20s
                SORTEDSET    %-10.10s     %-10.10s     %-20.20s  %-20.20s
                #########################################################################################""";

        String studentListIter = getSequenceIter(studentList);
        String studentListReverse = getSequenceReverse(studentList);

        String studentDequeIter = getSequenceIter(studentDeque);
        String studentDequeReverse = getSequenceReverse(studentDeque);

        String studentSortedSetIter = getSequenceIter(studentSortedSet);
        String studentSortedSetReverse = getSequenceReverse(studentSortedSet);

        System.out.println(out.formatted(
                studentList.getFirst(),     studentList.getLast(),      studentListIter, studentListReverse,
                studentDeque.getFirst(),    studentDeque.getLast(),     studentDequeIter, studentDequeReverse,
                studentSortedSet.getFirst(), studentSortedSet.getLast(), studentSortedSetIter, studentSortedSetReverse
                )
        );

    }

    public static void main(String[] args) throws IOException {

        List<String> studentList = buildList();
        Deque<String> studentDeque = buildDeque();
        SortedSet<String> studentoSortedSet = buildSortedSet();

        showElements(studentList,studentDeque,studentoSortedSet);

    }



}
