# Random String Generator App

## Overview
This Android application demonstrates interaction with a content provider to generate random strings with metadata. The app follows modern Android development practices using Kotlin, Jetpack Compose, and MVVM architecture.

## Features Implemented

✅ **IAV-1**: User can set the length of the string via a text input field  
✅ **IAV-2**: Clicking the "Generate" button queries the content provider  
✅ **IAV-3**: Shows generated string with length and creation date/time  
✅ **IAV-4**: Old strings remain visible in a scrollable list  
✅ **IAV-5**: "Delete All" button removes all generated strings  
✅ **IAV-6**: Each string has a delete button to remove it individually  
✅ **IAV-7**: Comprehensive error handling for:  
  - Invalid input  
  - Content provider errors  
  - Network/timeout issues  
  - Database operations  

![Alt Text](screenshots/filename.png)