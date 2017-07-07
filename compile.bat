@echo off

cd /d %0\..

SET Lib_Name=jarLoader
SET Lib_Source_Dir="%CD%"
SET B4A_SLC=C:\Apps\B4A\Tools\SimpleLibraryCompiler\B4J_LibraryCompiler.exe

RMDIR %Lib_Source_Dir%\bin /Q /S

echo Library Name = %Lib_Name%
echo Library Source Directory = %Lib_Source_Dir%
echo B4A Library Compiler = %B4A_SLC%
echo.

REM ------------------------------------------
REM Compile library
REM ------------------------------------------
echo.

set errorlevel=
%B4A_SLC% %Lib_Name% %Lib_Source_Dir%

IF "%errorlevel%"=="0" GOTO OK
IF "%errorlevel%"=="1" GOTO ERRORS

:OK
GOTO EXIT

:ERRORS
color FC
echo.
echo ******************************************
echo There were compile errors!
echo ******************************************
timeout /t 60 /nobreak

GOTO EXIT

:EXIT
cls
echo. 
echo Done!
echo.