GrievanceHub – Grievance Redressal App
GrievanceHub is a lightweight Android application designed for efficient grievance management, allowing users to submit, view, edit, and update grievances. It utilizes internal text file storage (grievances.txt) instead of a database, making it simple and efficient.

📌 Features
✔ Submit Grievances – Add title, description, and category.
✔ View Grievances – Display stored grievances using RecyclerView.
✔ Edit & Update Status – Modify grievance details and update status (Pending → In Progress → Resolved).
✔ File-Based Storage – Uses FileStorageManager.kt to read, write, and update grievances.

🛠 Technologies Used
Language: Kotlin
UI Design: XML
Storage: Internal File System (grievances.txt)
Components: RecyclerView, Activities

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

🚀 How to Run the App
Clone the repository:
git clone https://github.com/yourusername/GrievanceHub-App.git
Open the project in Android Studio.
Build and run the app on an emulator or physical device.

📌 Future Enhancements
Implement Room Database for better storage.
Add user authentication for secure grievance submissions.
Enable cloud storage integration for data backup.
