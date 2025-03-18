GrievanceHub – Grievance Redressal App
GrievanceHub is a lightweight Android application designed for efficient grievance management, allowing users to submit, view, edit, and update grievances. It utilizes internal text file storage (grievances.txt) instead of a database, making it simple and efficient.

📌 Features
✔ Submit Grievances – Add title, description, and category.
✔ View Grievances – Display stored grievances using RecyclerView.
✔ Edit & Update Status – Modify grievance details and update status (Pending → In Progress → Resolved).
✔ File-Based Storage – Uses FileStorageManager.kt to read, write, and update grievances.

🛠 Technologies Used
💻 Language: Kotlin
🎨 UI Design: XML
📂 Storage: Internal File System (grievances.txt)
🛠 Components: RecyclerView, Activities

📂 File Structure
app/
├── manifests/
│   ├── AndroidManifest.xml  
├── java/com/example/grievancehub/
│   ├── adapters/GrievanceListAdapter.kt  
│   ├── helpers/FileStorageManager.kt  
│   ├── models/GrievanceModel.kt  
│   ├── ui/
│   │   ├── MainActivity.kt  
│   │   ├── AddGrievanceActivity.kt  
│   │   ├── EditGrievanceActivity.kt  
├── res/layout/
│   ├── activity_main.xml  
│   ├── activity_add_grievance.xml  
│   ├── activity_edit_grievance.xml  
│   ├── item_grievance.xml  

🚀 How to Run the App – Step-by-Step Explanation
To run the GrievanceHub app on your local system, follow these steps:

1️⃣ Clone the Repository
This step downloads the project files from GitHub to your local machine.
🔹 Open Command Prompt (Windows) / Terminal (Mac/Linux)
🔹 Run the following command:
git clone https://github.com/yourusername/GrievanceHub-App.git
📌 What This Does?
✔ Downloads the entire project to your computer.
✔ Creates a local copy of the repository in a folder named GrievanceHub-App.

2️⃣ Open the Project in Android Studio
🔹 Launch Android Studio (make sure it's installed).
🔹 Click "Open an Existing Project" and select the GrievanceHub-App folder.
🔹 Wait for Android Studio to sync the Gradle files (this may take a few minutes).
📌 What This Does?
✔ Loads the project in the development environment.
✔ Ensures all dependencies are properly set up.

3️⃣ Build and Run the App
🔹 Connect an Android Device via USB (Enable Developer Mode & USB Debugging) OR
🔹 Use an Emulator (Create an Android Virtual Device in Android Studio).
🔹 Click "Run" (▶️ button) in Android Studio.
📌 What This Does?
✔ Compiles and builds the app.
✔ Deploys and launches the app on the selected emulator or device.
