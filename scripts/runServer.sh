#!/bin/bash

# -------------------
# 0. ROOT DIRECTORY CHECK
# -------------------

# Check if the script is being run from the root directory of the repo
if [ ! -f "settings.gradle.kts" ]; then
    echo "ERROR:"
    echo "Please run this script from the root directory of the repo."
    exit 1
fi

# -------------------
# 1. CHECK AND FREE UP PORT
# -------------------

# Define the port number
PORT=8081

# Use lsof to check if the port is in use
PID=$(lsof -ti:$PORT)

# If a PID exists, kill the process
if [ ! -z "$PID" ]; then
    echo "Port $PORT is in use by PID $PID. Attempting to free up..."
    sudo kill -9 $PID
    if [ $? -eq 0 ]; then
        echo "Successfully freed up port $PORT."
    else
        echo "Failed to kill process $PID. Check permissions or process state."
        exit 1
    fi
else
    echo "Port $PORT is free."
fi

# -------------------
# 2. RUN THE SERVER
# -------------------

echo "Starting the server on port $PORT..."
./gradlew :server:run --args='dev'