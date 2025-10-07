# Build Instructions
1. Clone repository:
   git clone https://github.com/username/Watch-face.git
2. Go into the folder:
   cd Watch-face
3. Compile and run:
 * javac --module-path javafx-sdk-18.0.2/lib --add-modules javafx.controls,javafx.fxml -d out $(find . -name "*.java")
 * java --module-path javafx-sdk-18.0.2/lib --add-modules javafx.controls,javafx.fxml -cp out WatchFace

   Make a runnable JAR (optional)
If you want a .jar file for easier distribution:
1. jar --create --file WatchFace.jar --manifest Manifest.txt -C out .
2. Your Manifest.txt should contain:
   * Main-Class: WatchFace
3. java --module-path javafx-sdk-18.0.2/lib --add-modules javafx.controls,javafx.fxml -jar WatchFace.jar
3. nano ~/.local/share/applications/javafx-widget.desktop
5. Paste this inside nano
   * [Desktop Entry]
Name=WatchFace Widget
Comment=Run my JavaFX widget
Exec=java --module-path /your/path/Watch-face/javafx-sdk-18.0.2/lib --add-modules javafx.controls,javafx.fxml -jar /your/path/Watch-face/WatchFace.jar
Icon=utilities-terminal
Terminal=false
Type=Application
MimeType=application/x-java-archive;
⚡ Important: Replace those paths with your real ones (you can get them by pwd).
6. Make the .desktop file executable
   * chmod +x ~/.local/share/applications/javafx-widget.desktop
