@echo off

REM Compile the Java file
javac -cp "json-20250107.jar" lyrics.java

REM Check if the compilation was successful
if %errorlevel% neq 0 (
    echo Compilation failed.
    pause
    exit /b
)

REM Check if the class file exists
if exist "lyrics.class" (
    echo Compiled successfully. Running the program...
    REM Run the Java program
    java -cp "json-20250107.jar;." lyrics
) else (
    echo Compilation succeeded, but the class file was not found.
    pause
)
