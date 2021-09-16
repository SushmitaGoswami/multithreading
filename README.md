# What is MultiThreading?
A thread is a light weight process, it is given its own context and stack etc. for preserving the state. **Thread state** enables the CPU to switch back and continue from where it stopped.

## True Parallelism vs Logical Parallelism
True parallelism is achieved by assigning tasks to individual CPUs or Processors. This is possible through multicore processors or executing the tasks using multiple CPUs.

If there is one CPU and we want to perform multiple tasks in parallel, then CPU will beshared across those tasks for some stipulated time interval, which stands for interleaved execution, and this way of executing the tasks is known as logical parallelism or psuedo parallelism.In case of logical parallelism, lets assume there are two tasks T1 and T2, when executed in parallel and using one CPU, CPU is switched between T1 and T2 i.e. CPU executed T1 for some stipulated time interval and then switched to T2. Once T2's time slice is completed then it is switched back to T1 and starts from where it stops.

## Process to create thread in Java

When we launch Java application, JVM internally creates few threads, e.g. Main thread for getting started with main() and GarbageCollector for performing garbage collection and few other threads which are need for execution of a Java application.

we can create a thread and execute the tasks with in the application, this enables us to perform parallel activities with in the application.

There are two approaches

 - **Extending the Thread class** - Override the run method. This is not a preferred approach because we are not extending the Thread functionality, instead we are using the Thread to execute a task, hence we should prefer the other one.

 - **Implementing the Runnable interface** - Implement the run menthod and  submit this task for execution. Similarly there is a Callable interface.
 
## Executor Service
Thread creation is a costly activity as it includes creating a separate execution context, stack etc.. Hence we should refrain from creating too many threads. And also creating a thread for each task is not a good idea, instead we can create a pool of threads and effectively utilise them in executing all the task. This could be achieved using ExecutorService in Java. Use the execute method of the ExecutorService to submit a Runnable task, if a thread is available in the pool then it assigns this task to the thread otherwise the task is added to the blocking queue and is kept till a thread is available. 

 - **Creating a ThreadPool**
   Below statement creates a thread pool of size 5.
   `ExecutorService executor = Executors.newFixedThreadPool(5);`
 - **Submitting a Runnable task**
   We can submit a task for execution using the execute method.
   `executor.execute( runnableTaskInstance );`
   
## How do we stop a thread
**stop()** - This method of the Thread class could be used to stop the thread in the middle. But this is the dangerous thing to do as it leaves the system in inconsistent state, because we are not giving the opportunity to the thread to rollback or reverse the actions that it has taken. And hence the stop method is deprecated and should not be used.

**interrupt()** - This method on the thread could be used to stop the thread in the middle.It is up to the thread to consider whether to stop or not.

A thread can then check if it was interrupted or not using **interrupted()**/**Thread.isInterrupted()** method of the Thread class. We can design the thread in a way that it reverses the actions/operations performed and then stop when interrupted.
Both the methods return true if the thread is interrupted when it is alive.

**sleep()** - This method of the thread class is used to block a thread for the given time interval in milliseconds. This method throws InterruptedException if the thread is interrupted while it is in sleep/blocked.


## Thread State

![Thread State](./thread_state.png?raw=true "Thread State")

A thread can be in one of the following states:

- **NEW**

A thread that is created but not yet started is in this state.

- **RUNNABLE**

A thread executing in the Java virtual machine is in this state, internally we can think of it as a combination of two sub states Ready and Running, i.e. when we start the thread it comes to Ready state and wait for the CPU, and if CPU is allocated then it goes into Running state. Once allocated CPU time is completed, in other words when the Thread schedular suspends the thread then it goes back to the Ready state and waits for its turn again.

The yield() method instructs the thread schedular to pass the CPU to other waiting thread if any.

- **BLOCKED**

A thread is blocked if it is waiting for a monitor lock is in this state. i.e. synchronized methods and blocks.

- **WAITING**

A thread that is waiting indefinitely for another thread to perform a particular action is in this state. i.e. wait(), join()

- **TIMED_WAITING**

A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state. i.e. wait(millis), join(millis)

- **TERMINATED**

A thread that has exited i.e. it has either completed its task or terminated in the middle of the execution.

## Thread Priorities
Thread priorities range between 1 and 10.

- **MIN_PRIORITY** - 1 being the minimum priority

- **NORM_PRIORITY** - 5 is the normally priority, this is the default priority value.

- **MAX_PRIORITY** - 10 being the max priority.

**Java setPriority(int newPriority)**  -
A method in the Thread class, this is used to set the new priority for the thread. If the newPriority value is more than the maximum priority 
allowed for the group then maximum priority is considered, i.e. if we try to set 15 then it takes only 10. And for a given ThreadGroup if the maximum allowed 
priority is 7 then any thread with in that group can have a maximum of 7.

**Example** -
Let's say in software installer app, the thread that copies the files should be given more priority than the thread which display the progress 
etc, that speeds up the installation process.

## Thread Group

 - **Thread.currentThread()** - currentThread() is a static method in the class Thread and all the static method in the Thread class normally operate on the thread in which it is being executed. Here Thread.currentThread() returns a reference to the current thread i.e. the main thread.

- **Thread.getThreadGroup()** - A Thread class method that returns a reference to the ThreadGroup to which the corresponding thread instance belongs.

- **ThreadGroup.getParent()** - A ThreadGroup class method that returns a reference to the parent thread group if any. If there is no parent then this method returns null.

- **ThreadGroup.setMaxPriority(int maxPriority)** - Sets the maximum priority for that group so that no thread can exceed this priority with in the group.

## Daemon Thread
Daemon threads are the ones which does not prevent the JVM from exiting the application once it finishes. Daemon threads are handy for performing background tasks such as garbage collection or collecting application statistics etc. Note that the Java Virtual Machine exits when the only threads running are all daemon threads.


## Callable interface 
Unlike Runnable, Callable interface allows us to create an asynchronous task which is capable of returning an Object.

```
interface Callable<V> {
    V call() throws Exception;
}
```
If we implement Callable interface then you we return the result as well unlike Runnable interface. Just need to override the call() method. 

  
## Thread Synchronization
 Thread synchronization is used to solve concurrency problems that exist in parallel processing. Concurrency problem exist when more than one thread is accessing the same shared resource. 

### Thread Safe Code or Re-entrant code
When a code block is safe from concurrency problems then the code is referred as thread safe or re-entrant.

### Synchronization in Java Threads
 There can be following scenarios when we need different ways of synchronization based on the situation
	a. Let's say, there is a single object shared among multiple thread and each thread calls some method on that object.
	b. Let's say, each thread has its own object, but the object has a static variable being operated on by the thread.
	c. Let's say, multiple thread operates on a single object of a third party class.


 - **Synchronized methods (example)**
```
	class Sample {
		synchronized void f() {...}
	}
	Consider Three threads T1, T2, T3 and two objects for Sample they are A,B.

	T1  ..........A.f();  locks A and proceeds

	T2  ..........B.f();  locks B and proceeds

	T3  ..........A.f();  wait till T1 unlocks A.
	
To run a synchronized method object must be locked.
```

 - **Synchronized block (example)**
	a. When synchronization is not required for the entire method i.e only certain part of the code must be synchronized then we use synchronized block.      
 b. When there is a need to perform some operation on a third party non thread safe class object from a thread.

```
	synchronized( object ) {
	  // operations over the object
	}
    The above code is executed only after obtaining lock over the object.
```

 ### Synchronizing static data operations
 Is the below increment() operation thread-safe ?
 
 ```
	class Sample {
	 
		static int a = 5;
		int b = 10;
	 
		public synchronized void increment( ) {
			a++;
			b++;
		}
	 
		// ....
	}
 ```
Answer is NO. It is not because of the static variable a. Lets assume that there are two objects for Sample, in that case both of them will share the same copy of a because it is a class member, where as they get different copies of b, because b is non static i.e. the instance member and each instance will get a separate copy of b.

Assume Thread 1 invoked the increment method over the first object and Thread 2 invoked the increment method over the second object. Because the increment() method is non-static and it is synchronized, object should be locked before getting into the method.

Here Thread1 locks the first object and gets in and also Thread 2 locks the second object and gets in, because both are different objects and hence both the thread acquire locks and they both proceed with the operation.

We will see that b++ is not having any issues, because both the threads are operating on different copies of b, but what about a++ it is still not thread safe.

- **Solution 1**
```
		class Sample {
		 
			static int a = 5;
			int b = 10;
		 
			public  void increment( ) {
				// lock the Class object before modifying
				// static content.
				synchronized(Sample.class) {
					a++;
				}
				
				// lock the object before modifying 
				// instance members.    
				synchronized(this) {
				b++;
				}
			}
			
			// ....
		}
 ``` 
In this case the increment() operation is thread-safe, because for modifying the static member 'a' we are locking the class object, Sample.class returns a reference to class object and synchronized block will acquire lock over the object and then proceed forward with the operation. And for b++ we are locking the current object using the this reference. And hence both the operations are now thread-safe, as we properly locked the corresponding object before modification.

- **Solution 2** 
 ```
		class Sample {
		 
			static int a = 5;
			int b = 10;
		 
			// This method is static and hence it locks the Class object.
			public static synchronized void incrementA( ) {
			a++;
			}
		 
			// This method is non static and hence it locks the object
			// on which it is invoked.
			public synchronized void increment( ) {
				incrementA();
			b++;
			}
		 
			// ....
		 
		}
  ```
Here, when the thread enters this method it locks the class object. 

### Issue with synchronized methods -
Synchronized methods doesn't always solve concurrency problems. Lets consider a simple List class and assume that the size() and add() operations are synchronized
 ```
	class List {
		...
		public synchronized int size(){
		...
		}
		public synchronized void add(Object value) {
		....
		}
	}
 ```
Now, lets analyze a simple scenario consider that list should not contain more than 5 elements, and assume that list is already having 4 elements and two threads are trying to insert an element into the list.

**Thread 1** -
```
	1) if (list.size() < 5) {
	2) 	list.add(value1);
	   }
 ```   
**Thread 2** -
```
	a) if (list.size() < 5) {
	b) 	list.add(value2);
	   }
```    
Lets assume this execution sequence (1)(a)(2)(b) in this case both threads will see that list size is 4 and is less than 5. Hence both will add an element into the list, which makes the list size as 6 violating the condition. We can see the issue is not resolved even with both size() and add() being synchronized methods.

- **Solution** -
	We should apply thread synchronization at operation level with the help of synchronized block. i.e.

	**Thread 1** -
```
	1) synhronized(list) {
	2)   if (list.size() < 5) {
	3) 	   list.add(value1);
		   }
	 }
 ```
	
 **Thread 2** -
```
	a) synchronized(list) {
	b)   if (list.size() < 5) {
	c) 	   list.add(value2);
		  }
	 }
```
Now consider the execution sequence (1)(a)(2)(3)(b)(c)

You can see that list object is locked by Thread1 and hence even when the control switched to Thread2 it can not proceed as the lock is with Thread1. And Thread1 will add the element where as Thread2 will fail.

So when it comes to synchronizing operations synchronized blocks are always better choice over synchronized methods.

## Deadlock -
Two threads are said to be in a deadlock when both the threads are circularly waiting for a lock over the object and hence they both get into a situation where they can not proceed with the execution.

![image](https://user-images.githubusercontent.com/20486206/122676141-3b309180-d1fa-11eb-88eb-9581cb1b039d.png)

## Reentrant Locks

### Read/Write Lock -
Read/Write Lock gives us more flexibility during locking and unlocking. Based on the type of operation being performed over the object we can segregate the locks into

- **readLock** - readLock allows us to lock the object for read operation, and the interesting point is that the read operation can be shared i.e if two threads are waiting for readLock then both of them can proceed forward with the operation as read operation doesn't change the data.

- **writeLock** - Where as writeLock is mutually exclusive i.e. if a writeLock is accepted then all the other lock requests should wait till the thread that owns the lock releases it.

### Example - 
Let us assume the following chronologically ordered lock requests

```
T1 -> lock.readLock();

T2 -> lock.readLock();

T3 -> lock.readLock();

T4 -> lock.writeLock();

T5 -> lock.readLock();
```

>Here T1, T2, T3 can share the readLock and proceed forward with the operation.  Where T4 should wait till T1, T2 and T3 unlocks.

**Why T5 is waiting ?**

Because writeLock is requested by T4 before its request and hence all subsequent requests to read/write locks should wait.

This is in contrast to synchronized methods/blocks because for synchronized method/block there is no segregation of read and write operations. Object is locked no matter whether it is read or write.

## Blocking lock 
```
1. lock()
2. 	read()
3. 	increment()
4. 	write()
5. unlock()
```
## Non Blocking lock - Using an Atomic operation
```
1. do{
2.    oldValue = read()
3.    newValue = increment()
4.    success = compareAndSetValue(oldValue, newValue)
5. } while(!success);   
```



## Thread signalling using wait() and notify() -
Threads can signal each other using the wait and notify methods. wait(), notify() and notifyAll() are the methods of the class Object and hence they are part of all the objects. Just think about a scenario where one thread waits for a signal from some other thread in order to proceed with the execution.

- **wait()** - This method Releases the lock over the object and takes the thread to WAITING state. And the thread remains in that state until some other thread calls the notify() method over the same object. Once notify() is invoked it ends the wait for one single thread and takes the thread to BLOCKED state where the thread remains in that state till the lock is obtained. wait() only returns after obtaining the lock.

- **wait(long millis)** - This method slightly differs, as it takes thread to TIMED_WAITING and waits only for the specified duration.

- **notify()** - This method notifies one single thread where as notifyAll() notifies all the threads waiting for the signal.

>Note - In order to call the wait and notify methods the corresponding thread should hold the lock on the object using synchronized method or block.

## ThreadLocal 
ThreadLocal allows us to associate an object with the Thread. It is normally used to hold the information which should be accessible anywhere during the thread execution.

**Important Methods** -
- **get()** - Returns the value associated with the thread.

- **set(T value)** - Associates the value with the thread.

- **remove()** - Removes the value associated with the thread.

>Note - ThreadLocal is considered an anti-pattern please refrain from using it directly.

## Java.util.concurrent package Utlity
It several utilities which help developing multithreaded application.

- ExecutorService

- Callable interface

- Future object

- Locks

- BlockingQueue implementations

etc.

### BlockingQueue

BlockingQueue is an interface that extends Queue interface. And the implementations include

- **ArrayBlockingQueue**

It is bounded i.e. you need to specify the size. It operates on FIFO logic i.e. first in first out, which means that the first inserted element will be the first to be removed.

- **LinkedBlockingQueue**

Optionally Bounded, based on linked nodes i.e. nothing but the linked list. It too operates on FIFO logic.

- **PriorityBlockingQueue**

Unbounded. Objects should be Comparable or you should provide a Comparator.

#### BlockingQueue operations

1. Operations that throw Exception if the operation fails.

- **add(o)** - It tries to add an element and if there is no sufficient capacity available this method will throw an exception.

- **remove(o)** - Removes the element that matches with the given object it compares the elements using equals method.

- **element()** - Returns the head element with out removing it. But element() method throws an exception if queue is empty

2. Operations that return a boolean value with out exception

- **offer(o)** - Returns true if the element is added otherwise false.

- **poll()** - Removes the head element of the queue and returns it, if queue is empty it returns null.

- **peek()** - Returns the head element with out removing it, it returns null if queue is empty.

3. Operations that block.

- **put(o)** - It will add the element to the queue, but if the queue is full, then it will block the thread till the space is available.

- **take()** - Returns the head element of the queue, if queue is empty this method will block the thread till an element is available.

4. And the methods with timeout

- **offer(o, timeout, timeunit)**
- **poll(timeout, timeunit)**

#### PriorityBlockingQueue -
PriorityBlockingQueue orders the elements through natural order if they are Comparable or we should use the Comparator.

## Fork Join Framework -
- Available from Java7

- An ExecutorService for ForkJoinTasks

- It differs from ExecutorService by virtue of employing Work-stealing. i.e. if a worker thread has no tasks in the pipeline it will take the task from the task queue of the other busy thread so that the workload is efficiently balanced.

- To access the pool, A static common pool is available for the application and it can be accessed through commonPool() method of the ForkJoinPool class. Using the commonPool is the preferred approach because creating multiple thread pools might have an adverse impact on the performance of the application.
```
ForkJoinPool pool = ForkJoinPool.commonPool();
```
- For applications which need separate thread pools, we can construct the ForkJoinPool using the level of parallelism needed and by default it is equal to the number of processors. 
```
ForkJoinPool pool = new ForkJoinPool();
```

### What is fork, join ?
![image](https://user-images.githubusercontent.com/20486206/122709398-cef67200-d27b-11eb-903e-c526e4bc0946.png)

The primary thought process behind fork is that each task is recursively split into subtasks and executed in parallel where as the join operation will wait for the completion of the task and combines the obtained results. To make it simpler, lets assume that the task is to search 100,000 unordered elements and returns the number of occurrences of an element. Assume that, a task will perform the search operation on its own if the list size is 25000. Here the first tasks splits the total elements into two sets of size 50000  and at second level task will split that into two sets of size 25000 each,  and at this level it will not fork any subtasks because the size of the dataset is 25000 and as per our condition we shouLd not create sub tasks.

This is how recursion works as well, there will be a base condition and upon reaching that condition recursive call would be stopped. Similarly here we should put a threshold condition where we will not further fork.

### Types of ForkJoinTask(s)
![image](https://user-images.githubusercontent.com/20486206/122709454-e7ff2300-d27b-11eb-9898-624301fe9c29.png)

ForkJoinTask implements Future interface so we can use it to extract the results once the task is done. ForkJoinTask is further divided into two subtypes one is RecursiveAction and RecursiveTask. These classes are abstract; and when you extend you need to override the compute method. If it is a RecursiveAction the compute operation doesn't return any value. Where as in case of RecursiveTask we can return a value. You can relate that with Runnable and Callable.

## Semaphore
A semaphore controls access to a shared resource through the use of a counter. If the counter is greater than zero, then access is allowed. If it is zero, then access is denied. What the counter is counting are permits that allow access to the shared resource. Thus, to access the resource, a thread must be granted a permit from the semaphore.

## CountDownLatch 
CountDownLatch is used to make sure that a task waits for other threads before it starts. To understand its application, let us consider a server where the main task can only start when all the required services have started.

## CyclicBarrier
A CyclicBarrier is a synchronizer that allows a set of threads to wait for each other to reach a common execution point, also called a barrier. CyclicBarriers are used in programs in which we have a fixed number of threads that must wait for each other to reach a common point before continuing execution.
