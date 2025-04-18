@echo off
REM Get the current directory
setlocal
set "CURRENT_DIR=%~dp0"

javac -cp "%CURRENT_DIR%json-20250107.jar" "%CURRENT_DIR%lyrics_big.java"

if %errorlevel% neq 0 (
    echo Compilation failed.
    pause
    exit /b
)

java -cp "%CURRENT_DIR%json-20250107.jar;%CURRENT_DIR%" lyrics
pause
