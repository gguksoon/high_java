package a_collectionTest;

import java.util.LinkedList;

public class StackQueueTest {

	public static void main(String[] args) {
		/*
		 * Stack ==> LIFO(Last In First Out) 후입선출의 자료구조
		 * Queue ==> FIFO 선입선출의 자료구조
		 * 
		 */
		
		LinkedList<String> stack = new LinkedList<>();
		
		/*
		 * Stack의 명령
		 * 1. 자료 입력: push(입력할 값);
		 * 2. 자료 출력: pop();  ==> 자료를 꺼내온 후 꺼내온 자료를 Stack에서 삭제한다.
		 *			  peek(); ==> 꺼내온 자료를 삭제하지 않는다.
		 */
		
		stack.push("홍길동");
		stack.push("일지매");
		stack.push("변학도");
		stack.push("강감찬");
		System.out.println("stack => " + stack);
		
		System.out.println(stack.pop());
		System.out.println("stack => " + stack);
		String data = stack.pop();
		System.out.println("pop()으로 꺼내온 자료: " + data);
		System.out.println("stack =>" + stack);
		
		stack.push("성춘향");
		System.out.println("stack => " + stack);
		System.out.println("pop()으로 꺼내온 자료: " + stack.pop());
		System.out.println("stack => " + stack);
		
		System.out.println("peek()로 꺼내온 자료: " + stack.peek());
		System.out.println("stack => " + stack);
		
		System.out.println("----------------------------------------");
		
		/*
		 * Queue의 명령
		 * 1. 자료 입력: offer(입력할 값);
		 * 2. 자료 출력: poll(); ==> 자료를 Queue에서 꺼내온 후 꺼내온 자료를 Queue에서 삭제한다.
		 * 			  peek(); ==> 꺼내온 자료를 삭제하지 않는다.
		 */
		
		LinkedList<String> queue = new LinkedList<String>();
		
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("변학도");
		queue.offer("강감찬");
				
		System.out.println("queue => " + queue);
		
		String temp = queue.poll();
		System.out.println("꺼내온 자료: " + temp);
		System.out.println("queue => " + queue);
		System.out.println("꺼내온 자료: " + queue.poll());
		System.out.println("queue => " + queue);
		
		queue.offer("성춘향");
		System.out.println("꺼내온 자료: " + queue.poll());
		System.out.println("queue => " + queue);
		
		System.out.println("꺼내온 자료: " + queue.peek());
		System.out.println("queue => " + queue);
		
	}

}
