package tp3;

public class PriorityList<T> implements PriorityQueue<T> {

	private Node<T> first; // contiene la menor prioridad
	
	public void enqueue(T elem, int priority) {
		first = enqueue(elem, priority, first);
	}

	private Node<T> enqueue(T elem, int priority, Node<T> n) {
		if (n == null || priority < n.priority) {
			Queue<T> q = new Queue<>();
			q.enqueue(elem);
			return new Node<>(q, priority, n);
		}
		else if (priority == n.priority)
			n.queue.enqueue(elem);
		else
			n.next = enqueue(elem, priority, n.next);
		return n;
	}

	public T dequeue() {
		T elem = first.queue.dequeue();
		if (first.queue.isEmpty())
			first = first.next;
		return elem;
	}

	public boolean isEmpty() {
		return first == null;
	}

	private static class Node<T> {
		Queue<T> queue;
		int priority;
		Node<T> next;
		public Node(Queue<T> q, int p, Node<T> n) {
			queue = q;
			priority = p;
			next = n;
		}
	}
}
