# LeetProfile

## Overview
LeetProfile is an app used for viewing the LeetCode profile of any user, allowing them to track their own or someone else's account. It provides a smooth and seamless profile view, displaying the latest 20 solved questions, current and upcoming badges, and the total number of solved questions categorized by difficulty levels: Easy, Medium, and Hard.

## Features
- User Profile Lookup: Enter a LeetCode username to fetch and display user details.
- Detailed Insights: View information such as:

    1. Badges earned by the user
    2. The 20 most recent submitted questions
    3. Number of solved questions categorized by difficulty (Easy, Medium, Hard)

- Optimized Data Handling: Efficient caching and data management in ViewModel to minimize API calls.
- MVVM Architecture: Ensures a clean and maintainable code structure for a better user experience.
- Seamless Navigation: Integrated Bottom Navigation and Drawer Navigation using the Navigation Graph.

### Screenshot
<img src="https://github.com/user-attachments/assets/792d9954-d495-417a-8b4c-ccba7e28e6d8" width="300">
<img src="https://github.com/user-attachments/assets/92a64e49-8d21-4c25-adea-c57eb43690ca" width="300">
<img src="https://github.com/user-attachments/assets/44d4a9d6-e3b7-401d-ac96-039443015e63" width="300">
<img src="https://github.com/user-attachments/assets/87b7142e-464e-477d-b93a-8a8c598159b7" width="300">
<img src="https://github.com/user-attachments/assets/95d58ed6-cb4a-4318-a80d-0ab25aa36248" width="300">
<img src="https://github.com/user-attachments/assets/500e3897-95b8-4f0a-ad83-eed579b10ac5" width="300">

### Videos

[LeetProfile Demo 1](https://github.com/user-attachments/assets/f4799b84-7768-41be-b55e-8107463dd6d4)

[LeetProfile Demo 2](https://github.com/user-attachments/assets/79beff37-4cc0-4df7-822a-a99fef67c820)

### Dependencies
LeetProfile relies on the following key dependencies:
- **Retrofit**: A type-safe HTTP client for Android and Java, Retrofit makes it easy to consume RESTful web services. It handles network requests, parsing responses, and serializing/deserializing data, simplifying API integrations.
- **Coroutines**: A concurrency design pattern in Kotlin, Coroutines simplify asynchronous programming by providing structured concurrency. They manage background tasks efficiently, reducing boilerplate code and ensuring smooth execution of long-running operations like network requests.
- **LiveData**: A lifecycle-aware data holder for Android, LiveData ensures UI components receive updates when data changes, reducing memory leaks and improving efficiency in reactive programming.
- **ViewModel**: A lifecycle-conscious component in Android, ViewModel stores and manages UI-related data, surviving configuration changes and ensuring data persistence across activity and fragment lifecycles.

### Contributing
Contributions to LeetProfile are welcome! To contribute:
1. Fork the repository.
2. Create a new branch for your feature or bug fix: `git checkout -b feature-name`.
3. Make your changes and commit them: `git commit -m 'Add new feature'`.
4. Push to the branch: `git push origin feature-name`.
5. Create a pull request with a detailed description of your changes.
