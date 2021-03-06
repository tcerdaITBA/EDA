package tp2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import taller.OrderedList;
import taller.Randomizer;

public class MergeTest {

	public static void main(String[] args) {
		Random rand = new Random();
		int maxRandom = 1000000;
		Comparator<Integer> cmp = ((a, b) -> Integer.compare(a, b));
		OrderedList<Integer> list1 = new OrderedList<>(cmp);
		OrderedList<Integer> list2 = new OrderedList<>(cmp);
		OrderedList<Integer> list3 = new OrderedList<>(cmp);

		Randomizer<Integer> r1 = new Randomizer<Integer>() {
			public Integer next() {
				return rand.nextInt(maxRandom);
			}
		};

		Randomizer<Integer> r2 = new Randomizer<Integer>() {
			public Integer next() {
				return rand.nextInt(maxRandom) - maxRandom;
			}
		};
		list1.fillRandom(10000, r1);
		list1.fillRandom(15000, r2);
		list2.fillRandom(10000, r2);
		list2.fillRandom(10000, r1);
		list3.fillRandom(15000, r1);
		list3.fillRandom(20000, r2);

		List<OrderedList<Integer>> array = new ArrayList<>();

		array.add(list1);
		array.add(list2);
		array.add(list3);

		OrderedList<Integer> merged = OrderedList.merge(array);
		System.out.println(merged.isOrdered());

		merged = OrderedList.otherMerge(array);
		System.out.println(merged.isOrdered());
	}

}
