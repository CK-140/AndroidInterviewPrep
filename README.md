Activity and Fragments-

-Activity Lifecycle:

On Opening app and pressing back button

![image22](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/5a728514-2e8e-46e5-a214-b5e81897609c)

On going back to app:

![image8](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/1d91abf9-1649-47bb-942c-b2f3f30ff390)


On killing the app:

![image6](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/2c05bb1f-5207-4921-a9e3-18a4f29868ff)


Now let’s try launching ActivtyB from ActivityA and then coming back to A with back button:

![image17](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/17f082ba-6ece-40b6-87a4-41c907491aa7)


A goes to pause on button click then onCreate()>onStart()>onResume() of B are called, then after that 
onStop() of A is called, now If we press back, onPause() B is called, onRestart()>onStart()>onResume() 
of A are called, after which onStop()>onDestroy() for B are called.



-Important Note: 

When a user taps or gestures Back from a root launcher activity, the system handles the event differently depending on the version of Android that the device is running.
System behavior on Android 11 and lower
The system finishes the activity.
System behavior on Android 12 and higher
The system moves the activity and its task to the background instead of finishing the activity. This behavior matches the default system behavior when navigating out of an app using the Home button or gesture.
In most cases, this behavior means that users can more quickly resume your app from a warm state, instead of having to completely restart the app from a cold state.
If you need to provide custom back navigation, we recommend using the AndroidX Activity APIs rather than overriding onBackPressed(). The AndroidX Activity APIs automatically defer to the appropriate system behavior if there are no components intercepting the system Back tap.
However, if your app overrides onBackPressed() to handle Back navigation and finish the activity, update your implementation to call through to super.onBackPressed() instead of finishing. Calling super.onBackPressed() moves the activity and its task to the background when appropriate and provides a more consistent navigation experience for users across apps.




Launch Modes and Backstack for activities:

There are four launch modes in android for activities: Standard(default), SingleTop, SingleTask, SingleInstance, let’s take a look at their functioning.

What is a task:
A task is a cohesive unit that can move to the background when a user begins a new task or goes to the Home screen. While in the background, all the activities in the task are stopped, but the back stack for the task remains intact—the task loses focus while another task takes place

How to define launch mode: 1. Manifest 2. Intent flags


-Behaviour of launch modes also depends on the affinity, An affinity indicates which task an activity "prefers" to belong to. By default, all the activities from the same app have an affinity for each other: they "prefer" to be in the same task.

1.Standard:
Official definition-
The system creates a new instance of the activity in the task it was started from and routes the intent to it. The activity can be instantiated multiple times, each instance can belong to different tasks, and one task can have multiple instances.


![image12](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/166120c9-a0ba-4ed7-ac0d-634ccfc5e168)



Activities are stacked on top of each other and are popped when back button is pressed in LIFO order


2.SingleTop

Official definition:If an instance of the activity already exists at the top of the current task, the system routes the intent to that instance through a call to its onNewIntent() method, rather than creating a new instance of the activity. The activity is instantiated multiple times, each instance can belong to different tasks, and one task can have multiple instances (but only if the activity at the top of the back stack is not an existing instance of the activity).


![image21](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/fee35d22-3e57-4448-972f-996da2ce734e)


When an activity is declared SingleTop, If it is present on top of the stack and we try to start it again, the same instance that is on top(ActivityB in fig.) will be launched with a call to it’s onNewIntent() method, new Instance won’t be created.


3-SingleTask(Here is where it gets scary :?)

Here there is a differentiating factor that can change the behavior, it depends on the affinity, this launch mode can be launched either with affinity or without affinity


![image11](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/0f874e72-b894-4751-b9f9-e0c2ca224ab1)



Consider here, Activity B is declared as SingleTask, the stack is A>B>C>D, Now I will launch B from D

After launching B

![image19](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/fc83e217-229a-41a7-826d-ee16ce04379a)

We can see, C and D are popped, B is retained which is on top of the stack, Official definition is confusing, it says that B should launch in new Task (This is where affinity comes in, which basically means what is the preference of the Activity, in which Task it wants to get launched, By default, all the activities from the same app have an affinity for each other: they "prefer" to be in the same task.)

By default the affinity is associated with the current package, to change this affinity we can change it in manifest by specifying a different package using android:TaskAffinity for that Activity.


Here’s what will happen if I have SingleTask with affinity(Not default package)

![image1](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/391be325-e1eb-4d49-9aeb-37d44b692cce)


If now I launch Activity C from B, it will launch in the same task as B’s because Bis root Activity in the new task and by default new activity launched will share affinity with the root activity in that task, show below:

![image18](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/1f5f0e1e-1f9e-4677-b606-2440b59754dc)



Now if we bring task-1 to foreground again from recent apps menu, and launch C and D, they will launch again in Task-1, It will look something like this:

![image2](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/f4d873c4-f184-4bf5-b639-f3b293f13b56)



Now the question is If we try to launch B(which is SingleTask with affinity) from Activity D of task 2, what will happen? The answer is, Task-2 will come to foreground with it’s C and D popped and B on top, it means that in SingleTask if ActivityB is part of some different task and it is launched, activities on top of it will be popped and it will come on top of stack.

![image15](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/9afd2b26-5c56-4468-ad72-49f21baa47ec)



 Important closing Note for SingleTask Launch mode with affinity:

-Single task activity will always remain at the root if the task
-There can only be one instance of SingleTask activity across tasks.

Bonus Question, what happens If I have multiple tasks with my SingleTask activity’s running instance, which one will system choose to bring to foreground:
The task with the highest priority (i.e., the one that has the same affinity as the "singleTask" activity you're trying to launch) will be brought to the foreground. If multiple tasks have instances of the "singleTask" activity with the same affinity, the system will bring the most recent task (the one with the most recently started instance of the activity) to the foreground. This behavior ensures that the user sees the most recently used task associated with that specific activity.(dayumnnn)


-SingleTask without TaskAffinity(Default Behaviour)

-Need not be at the root of task
-Will not be launched in new task
-Only single Instance across tasks

![image5](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/c92b367d-674c-4cfa-a07b-8b20c86a8d2d)





-SingleInstance Launch Mode

Activity declared as SingleInstance  is always the single and only activity in it’s task, any other activity started from it will open in a separate task, the concept of affinity only makes one difference in this case, the new task is shown separately in recent apps, otherwise the new task is not shown differently by default in recent apps

![image9](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/6caa7a13-6441-4ede-9462-2573c85710f3)




Here is the difference shown between SingleTask and SingleInstance(Activity B)
In figure 1 where B is SingleInstance
A>B in new Task> C in task-1>D in task-1



-Intent Flags- Changing Activity launch behaviour during runtime

-Flag ACTIVITY_SINGLE_TOP- works same as SingleTop launchmode

-Flag ACTIVITY_CLEAR_TOP works similarly as SingleTask without affinity, default behaviour: A>B>C>B —> A>B (same task)

-Flag ACTIVITY_NEW_TASK - It only brings up the task the launched activity belongs to with the activity on top but does not clear the Activities on top of task.
Same task affinity: works like standard mode
Different task affinity: works like SingleTask with affinity, launches a new task ,just it don’t clear the activities on top of the task.

-Combining FLAG_ACTIVITY_NEW_TASK and FLAG_ACTIVITY_CLEAR_TOP is fully similar to singleTask launch mode.



-Fragments:

Fragment lifecycle:

ActivityA has a fragmentContainer and FragmentA1 is added inside the container inside onCreate of ActivityA.
Note- If we add fragment statically i.e without a container, onAttach is called directly after onCreate of activity.


![image10](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/18a4fd15-ac38-451a-a0df-4cb31d6e58cc)


Ignore onViewStateRestored() as it is not a lifecycle method


Now If I finish A and navigate to Activity B, this is what the lifecycle calls will look like:

![image14](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/561b9589-c5e3-4282-972c-2581bef99b47)





-Fragment Backstack-

Important Note: Fragment backstack is not a backstack containing fragments, it is a backstack of fragment transactions.


-If I add a fragment in my activity, without addToBackStack(), If I try to add another fragment, it won’t work, I must remove the first fragment and then add another

//Misc

-RecyclerView Working:
-Why RecyclerView over listview : Listview doesn’t provide viewHolder pattern out of the box, we have to separately create a ViewHolder pattern, which is not standard across apps and might lead to performance issues, Recyclerview provides ViewHolder pattern out of the box and has onCreateViewHolder() and onBindHolder() methods to Inflate and bind views, apart from that, animations and decorations are easier to apply in RecyclerView, there is layoutManager in recyclerview which can be used to place our views in a certain way on screen.

-RecyclerView working: We have RecyclerView, LayoutManager, Adapter and data source, LayoutManager doesn’t directly communicate with the adapter, There is Recycler in between which provides with the views

When we scroll, views that are moved out of the viewport are added to the scrap heap, the views which haven’t been visible yet are the waiting views, when we scroll further and we have to bring waiting views to viewport, recyclerview will use the views from scrap heap(dirty views) and then will assign them to the waiting views which are now visible

Improving the performance of recyclerView-

1-recyclerView.setHasFixedSize(true): If the height of items in our recyclerView is same we can specify this and recyclerView will avoid calculating the size of view everytime.

2-Experiment by setting the ItemViewCacheSize

3- sharing viewpool if we have nested recyclerview with same viewtype

4- Using Image loading libraries like Glide and Fresco which use Bitmap pool.


How does these image loading libraries work: 

Bitmap pooling is an implementation that tries to reuse the memory of the bitmaps already available instead of allocating new memory every time. Suppose we need to load a bitmap, we first check our pool to see if there is any bitmap available. If it is available, we take a bitmap from the pool and reuse its memory to load our new bitmap otherwise we create a new bitmap. In case, if we do not need any bitmap, we put that bitmap into that pool instead of recycling it so that we can reuse the memory of that bitmap when needed for a new bitmap.

When these libraries have to load the new bitmap, they just get a bitmap that can be reused to load the new one to reuse the same memory from that bitmap pool. Hence no recycling, and no GC calls.

Caching in these libraries work at both memory and disk level to optimize image loading times



Context in android:       

Current state of app’s environment

![image3](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/e9cdea6b-b3bc-47c6-9727-83f8e46da789)


Activities and Application are subclasses of context


Dependency Injection -

Dagger != Dependency injection, it is an annotation processor and code generator.

Consumer @Inject (Activity/ Fragment asks from Connector to get the object)
Producer  @Module @Provides @Binds
Connector @Component

 Consumer asks for object, connector checks with producer if this object can be provided to the consumer, then component gives that object to consumer




Viewmodel working , what makes it lifecycle aware:
We use ViewModelProvoder to instantiate our viewmodel like this:


val viewModel = ViewModelProvider(aViewModelStoreOwner).get(MyViewModel::class.java)

Constructor takes a viewmodelStoreOwner, In android these can have 3 implementations:
Activities, Fragments and Nav Hosts

The responsibility of a ViewModelStoreOwner is to:
Retain a ViewModelStore during configuration changes.
Call ViewModelStore.clear() when it is going to be destroyed.
It means that a ViewModelStoreOwner has a Lifecycle. When it’s in the Destroyed state, it notifies the ViewModelStore of its destruction, which notifies the ViewModel.
Here is the code extract from ComponentActivity that listen its destruction and call ViewModelStore.clear() :

getLifecycle().addObserver(new LifecycleEventObserver() {

   @Override
   public void onStateChanged(@NonNull LifecycleOwner source,


       @NonNull Lifecycle.Event event) {


       if (event == Lifecycle.Event.ON_DESTROY) {


           ...


           // And clear the ViewModelStore


           if (!isChangingConfigurations()) {


               getViewModelStore().clear();


           }


       }


   }


});






So in actual it is the Activity itself that has lifecycle observer which listens it’s destruction and calls getViewModelStore.clear() (if the destruction is non configurational)

A ViewModelStore keeps a reference on a ViewModel by maintaining a HashMap<String, ViewModel>
When the ViewModelProvider creates a new ViewModel, it adds the ViewModel to the map in ViewModelStore.

How it works in a ComponentActivity: ComponentActivity implements ViewModelStoreOwner, so it overrides getViewModelStore()

@NonNull
@Override
public ViewModelStore getViewModelStore() {
    if (getApplication() == null) {
        throw new IllegalStateException("Your activity is not yet attached to the "
                + "Application instance. You can't request ViewModel before onCreate call.");
    }
    ensureViewModelStore();
    return mViewModelStore;
}

mViewModelStore can’t be null. To avoid that, the method ensureViewModelStore() is called. If there was a previous ViewModelStore, it’s retrieved, otherwise, a new ViewModelStore is instantiated.

![image16](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/7c39379d-ae52-428c-bf40-1e9c916be584)


getLastNonConfigurationInstance():  It’s a method of the Activity class that returns a NonConfigurationInstances


Nonconfiguration instances are instances that are not re-created after a configuration change in activity.




Service vs Intent Service: Intent service runs on worker thread and stops itself when the work is complete.


Memory leak: when object outlives it’s lifecycle and is attached somewhere but not being used

Garbage collector in Android:

![image13](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/0fc46ccb-f65b-4173-b67e-a6d49ae9a162)

Ram has heap(dynamic memory alloc)  and stack(static memory alloc) , Garbage collector frees up memory from the heap.
A memory leak happens when the stack still refers to unused objects in the heap.


Singleton pattern:

![image20](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/3ab21223-79c8-4f73-9778-526cc69215a6)




Best way is to declare a static nested class, it will be thread safe as well.

Parcelable vs Serializable: 

![image4](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/031eee95-9f6d-4069-ac74-fe269529d2f0)

Parcelable is run time
Serializable in compile time + reflection(metaprogrammming i.e private methods can be accessed)

Why use bundle instead of map to pass data in intents:

![image7](https://github.com/CK-140/AndroidInterviewPrep/assets/79191512/6119ef9a-43ff-4f83-9e92-dd212ff3b1f0)


Sealed classes vs enums:

In some sense, sealed classes are similar to enum classes: the set of values for an enum type is also restricted, but each enum constant exists only as a single instance, whereas a subclass of a sealed class can have multiple instances, each with its own state.


