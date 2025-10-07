# Build Instructions
1. Clone repository:
   git clone https://github.com/username/Watch-face.git
2. Go into the folder:
   cd Watch-face
3. Compile and run:
   javac --module-path javafx-sdk-18.0.2/lib --add-modules javafx.controls,javafx.fxml -d out $(find . -name "*.java")
   java --module-path javafx-sdk-18.0.2/lib --add-modules javafx.controls,javafx.fxml -jar WatchFace.jar
