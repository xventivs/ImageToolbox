@echo off
"C:\\Users\\malik\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\cmake.exe" ^
  "-HD:\\AndroidStudioProjects\\ImageResizer\\jp2-coder" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=19" ^
  "-DANDROID_PLATFORM=android-19" ^
  "-DANDROID_ABI=x86" ^
  "-DCMAKE_ANDROID_ARCH_ABI=x86" ^
  "-DANDROID_NDK=C:\\Users\\malik\\AppData\\Local\\Android\\Sdk\\ndk\\25.1.8937393" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\malik\\AppData\\Local\\Android\\Sdk\\ndk\\25.1.8937393" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\malik\\AppData\\Local\\Android\\Sdk\\ndk\\25.1.8937393\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\malik\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\ninja.exe" ^
  "-DCMAKE_CXX_FLAGS=-Wl,--build-id=none" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=D:\\AndroidStudioProjects\\ImageResizer\\jp2-coder\\build\\intermediates\\cxx\\Debug\\1d1g5o6z\\obj\\x86" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=D:\\AndroidStudioProjects\\ImageResizer\\jp2-coder\\build\\intermediates\\cxx\\Debug\\1d1g5o6z\\obj\\x86" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-BD:\\AndroidStudioProjects\\ImageResizer\\jp2-coder\\.cxx\\Debug\\1d1g5o6z\\x86" ^
  -GNinja