class Box {
private int item;
private boolean hasItem = false;
public synchronized void produce(int value)
throws InterruptedException {
while (hasItem) {
wait();
}
item = value;
hasItem = true;
System.out.println(
Thread.currentThread().getName()
+ " produced: " + item
);
notifyAll();
}
public synchronized int consume()
throws InterruptedException {
while (!hasItem) {
wait();
}
int value = item;
hasItem = false;
System.out.println(
Thread.currentThread().getName()
+ " consumed: " + value
13
Inter thread communication & Locks
Coder Army
);
notifyAll();
return value;
}
}

public class Thread_Communication {
public static void main(String[] args) {
Box box = new Box();
Thread producer = new Thread(() -> {
for (int value = 1; value <= 5; value++) {
try {
box.produce(value);
} catch (InterruptedException e) {
Thread.currentThread().interrupt();
return;
}
}
}, "Producer");
Thread consumer = new Thread(() -> {
for (int count = 1; count <= 5; count++) {
try {
box.consume();
} catch (InterruptedException e) {
Thread.currentThread().interrupt();
return;
}
}
}, "Consumer");
producer.start();
consumer.start();
}
}
