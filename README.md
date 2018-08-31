Hybrid of Clean and MVVM Architecture using RxJava2, Dagger2, Retrofit, Room, ViewModel, LiveData

1. DATA layer has repository pattern in which the offline data is stored using Room Persistence Library along with RxJava2 for reactive operations and repositry is also using retrofit remote service to do the network operations.

2. DOMAIN layer is having use-cases written in pure Java langauge so that they can be tested thoroughly.

3. PRESENTATION layer contains the UI part along with the ViewModels from Android Architecture. So, basically ViewModel will communicate with the UI and it will use the domain layer use-cases to communicate with the data layer(repository). We are also using Dagger2 for our dependencies in the whole project. If need be, will also use a memory leak detection library for memory leaks(LeakCanary)
