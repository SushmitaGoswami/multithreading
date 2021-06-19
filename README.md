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

interface Callable<V> {
    V call() throws Exception;
}
If we implement Callable interface then you we return the result as well unlike Runnable interface. Just need to override the call() method. 
