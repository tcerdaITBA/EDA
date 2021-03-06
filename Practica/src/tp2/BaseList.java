package tp2;

public class BaseList<T> {
	private T value;
	private BaseList<T> next;

	public BaseList() {
		this(null, null);
	}

	public BaseList(T v) {
		this(v, null);
	}

	public BaseList(T v, BaseList<T> n) {
		value = v;
		next = n;
	}

	/**
	 * Revierte la lista. Debe invocarse de la siguiente manera:
	 * 		list = list.reverse();
	 * ya que la referencia a la lista original quedará inutilizable.
	 * @return la cabeza de la lista revertida
	 */
	public BaseList<T> reverse() {
		BaseList<T> prev = null;
		BaseList<T> current = this;
		BaseList<T> next;

		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}

		return prev;
	}

	public BaseList<T> reverseRec() {
		return reverseRec(this);
	}

	private BaseList<T> reverseRec(BaseList<T> n) {
		if (n.next == null)
			return n;
		BaseList<T> reversed = reverseRec(n.next);
		n.next.next = n;
		n.next = null;
		return reversed;
	}

	public void add(T v) {
		if (value != null) {
			BaseList<T> list = new BaseList<T> (value, next);
			next = list;
		}
		value = v;
	}

	public BaseList<T> remove(T v) {
		return remove(v, this);
	}

	private BaseList<T> remove(T v, BaseList<T> n) {
		if (n == null)
			return n;
		if (v.equals(n.value))
			return n.next;
		n.next = remove(v, n.next);
		return n;
	}

	public boolean contains(T v) {
		return contains(v, this);
	}

	private boolean contains(T v, BaseList<T> n) {
		if (n == null)
			return false;
		if (v.equals(n.value))
			return true;
		return contains(v, n.next);
	}

	public int count (Condition<T> condition) {
		return count(condition, this);
	}

	private int count (Condition<T> condition, BaseList<T> n) {
		if (n == null)
			return 0;
		return condition.eval(n.value) ? 1 + count(condition, n.next) : count(condition, n.next);
	}

	public BaseList<T> filter(Condition<T> condition) {
		return filter(condition, this);
	}

	private BaseList<T> filter(Condition<T> condition, BaseList<T> n) {
		if (n == null)
			return new BaseList<T>();
		BaseList<T> list = filter(condition, n.next);
		if (condition.eval(n.value))
			list.add(n.value);
		return list;
	}

	public <S> BaseList<S> map(Function<T,S> f) {
		return map(f, this);
	}

	private <S> BaseList<S> map(Function<T,S> f, BaseList<T> n) {
		if (n == null)
			return new BaseList<S>();
		BaseList<S> list = map(f, n.next);
		list.add(f.eval(n.value));
		return list;
	}

	public <S> S inject(TwoFunction<T,S> f, S initial) {
		return inject(f, initial, this);
	}

	private <S> S inject(TwoFunction<T,S> f, S initial, BaseList<T> n) {
		if (n == null)
			return initial;
		return inject(f, f.eval(initial, n.value), n.next);
	}

	public String toString() {
		return new String(toString(this));
	}

	private StringBuffer toString(BaseList<T> n) {
		if (n == null)
			return new StringBuffer();
		StringBuffer str = toString(n.next);
		str.append(n.value.toString() + " ");
		return str;
	}
}
