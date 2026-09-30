[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-3DDC84?logo=android&logoColor=white)](https://developer.android.com/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Room](https://img.shields.io/badge/Room-4285F4?logo=android&logoColor=white)](https://developer.android.com/training/data-storage/room)
[![Hilt](https://img.shields.io/badge/Hilt-4285F4?logo=android&logoColor=white)](https://developer.android.com/training/dependency-injection/hilt-android)
[![MVVM](https://img.shields.io/badge/MVVM-Architecture-6DB33F)](https://developer.android.com/topic/architecture)

# Task Management App

**Task Management App** is a native Android application for creating, organizing, and tracking tasks and subtasks. It supports priorities, due dates, task links, progress tracking, voice search, and local reminders.

The application is built with Kotlin and Jetpack Compose using the MVVM architecture. Room is used for local data storage, Hilt for dependency injection, and Android's native speech recognition and notification APIs provide voice search and local reminders.

The project focuses on an offline-first approach, with task data stored locally and the UI reacting to database changes through `Flow` and ViewModels.

<img width="680" height="384" alt="Image" src="https://github.com/user-attachments/assets/f4512de4-0f2d-4d12-986f-0eac223f695d" />

## Features

* Create, edit, and delete tasks
* Create and manage subtasks
* Add descriptions, priorities, and due dates
* Attach external links to tasks and subtasks
* Mark tasks as completed
* Track task and subtask progress
* Search tasks using voice commands
* Local notifications for upcoming deadlines
* Offline access to stored tasks

## Architecture

The application follows the **MVVM** architecture with a clear separation between the UI, application logic, and local data layer.

```text
Compose UI -> ViewModel -> Room / Repository -> Local Database
```


## Project Structure

The Android application is organized around:

* **UI** - Jetpack Compose screens and components
* **ViewModel** - UI state and application logic
* **Repository** - data access and application data flow
* **Room** - local database and data persistence
* **Models** - tasks, subtasks, and task links
* **Dependency Injection** - Hilt modules for managing dependencies
